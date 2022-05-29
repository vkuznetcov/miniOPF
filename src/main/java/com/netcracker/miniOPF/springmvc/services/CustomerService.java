package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
import com.netcracker.miniOPF.model.template.TemplateImpl;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService
{
    private final CustomerRepo customerRepo;
    private final AreaRepo areaRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, AreaRepo areaRepo)
    {
        this.customerRepo = customerRepo;
        this.areaRepo = areaRepo;
    }

    public String showCustomers(String type, String sort, String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table",
                                                customerRepo.getCustomer(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", customerRepo.searchCustomersByName(value));
                case "login" -> model.addAttribute("table", customerRepo.searchCustomerByLogin(value));
                case "password" -> model.addAttribute("table", customerRepo.searchCustomersByPassword(value));
                case "balance" -> model.addAttribute("table",
                                                     customerRepo.searchCustomersByBalance(Double.parseDouble(
                                                             value)));
                case "area" -> model.addAttribute("table",
                        customerRepo.searchCustomersByArea(value));
            }
            return "admin/customers";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", customerRepo.getCustomerValues());
                return "admin/customers";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", customerRepo.sortCustomersByID());
                    case "name" -> model.addAttribute("table", customerRepo.sortCustomersByName());
                    case "login" -> model.addAttribute("table", customerRepo.sortCustomersByLogin());
                    case "password" -> model.addAttribute("table", customerRepo.sortCustomersByPassword());
                    case "balance" -> model.addAttribute("table", customerRepo.sortCustomersByBalance());
                    case "area" -> model.addAttribute("table", customerRepo.sortCustomersByArea());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", customerRepo.sortCustomersByIDReversed());
                    case "name" -> model.addAttribute("table", customerRepo.sortCustomersByNameReversed());
                    case "login" -> model.addAttribute("table", customerRepo.sortCustomersByLoginReversed());
                    case "password" -> model.addAttribute("table",
                                                          customerRepo.sortCustomersByPasswordReversed());
                    case "balance" -> model.addAttribute("table", customerRepo.sortCustomersByBalanceReversed());
                    case "area" -> model.addAttribute("table", customerRepo.sortCustomersByAreaReversed());
                }
            }
        }

        return "admin/customers";
    }

    private boolean checkParams(String areaId, StringBuilder errorMessage){
        boolean error = false;
        if(areaRepo.getArea(Integer.parseInt(areaId)) == null){
            errorMessage.append("There is no such area! ");
            error = true;
        }
        return error;
    }

    public String updateCustomer(CustomerImpl customer,
                                 String areaId,
                                 Model model){
        String errorMessage = "";
        StringBuilder stringBuilder = new StringBuilder(errorMessage);
        if(checkParams(areaId, stringBuilder)){
            stringBuilder.append("Error index: ").append(customer.getId());
            model.addAttribute("errorMessage", stringBuilder.toString());
        }
        else{
            customer.setArea(areaRepo.getArea(Integer.parseInt(areaId)));
            customerRepo.updateCustomer(customer.getId(), customer);
        }
        return this.showCustomers(null, "none", null, model);
    }

    public String createCustomer(CustomerImpl customer,
                                 String areaId,
                                 Model model){
        String errorMessage = "";
        StringBuilder stringBuilder = new StringBuilder(errorMessage);
        if(checkParams(areaId, stringBuilder)){
            stringBuilder.append("Error index: new object creation");
            model.addAttribute("errorMessage", stringBuilder.toString());
        }
        else{
            customer.setArea(areaRepo.getArea(Integer.parseInt(areaId)));
            customerRepo.createCustomer(customer);
        }
        return this.showCustomers(null, "none", null, model);
    }

    public List<Customer> sortCustomersByLogin()
    {
        return customerRepo.sortCustomersByLogin();
    }

    public List<Customer> sortCustomersByLoginReversed()
    {
        return customerRepo.sortCustomersByLoginReversed();
    }

    public List<Customer> sortCustomersByPassword()
    {
        return customerRepo.sortCustomersByPassword();
    }

    public List<Customer> sortCustomersByPasswordReversed()
    {
        return customerRepo.sortCustomersByPasswordReversed();
    }

    public List<Customer> sortCustomersByBalance()
    {
        return customerRepo.sortCustomersByBalance();
    }

    public List<Customer> sortCustomersByBalanceReversed()
    {
        return customerRepo.sortCustomersByBalanceReversed();
    }

    public List<Customer> sortCustomersByName()
    {
        return customerRepo.sortCustomersByName();
    }

    public List<Customer> sortCustomersByNameReversed()
    {
        return customerRepo.sortCustomersByNameReversed();
    }

    public List<Customer> sortCustomersByID()
    {
        return customerRepo.sortCustomersByID();
    }

    public List<Customer> sortCustomersByIDReversed()
    {
        return customerRepo.sortCustomersByIDReversed();
    }

    public Customer searchCustomerByLogin(String login)
    {
        return customerRepo.searchCustomerByLogin(login);
    }

    public List<Customer> searchCustomersByPassword(String password)
    {
        return customerRepo.searchCustomersByPassword(password);
    }

    public List<Customer> searchCustomersByBalance(double balance)
    {
        return customerRepo.searchCustomersByBalance(balance);
    }

    public List<Customer> searchCustomersByName(String name)
    {
        return customerRepo.searchCustomersByName(name);
    }

    public Customer getCustomer(int id)
    {
        return customerRepo.getCustomer(id);
    }

    public List<Customer> getCustomerValues()
    {
        return customerRepo.getCustomerValues();
    }

    public void createCustomer(Customer customer)
    {
        customerRepo.createCustomer(customer);
    }

    public void deleteCustomer(int id)
    {
        customerRepo.deleteCustomer(id);
    }

    public void updateCustomer(int id, Customer customer)
    {
        customerRepo.updateCustomer(id, customer);
    }
}
