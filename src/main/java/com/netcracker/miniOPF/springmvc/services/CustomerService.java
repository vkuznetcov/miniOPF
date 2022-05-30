package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.SQLException;
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
        try
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
                        case "id" -> model.addAttribute("table", customerRepo.sortCustomersByID(false));
                        case "name" -> model.addAttribute("table", customerRepo.sortCustomersByName(false));
                        case "login" -> model.addAttribute("table", customerRepo.sortCustomersByLogin(false));
                        case "password" -> model.addAttribute("table", customerRepo.sortCustomersByPassword(false));
                        case "balance" -> model.addAttribute("table", customerRepo.sortCustomersByBalance(false));
                        case "area" -> model.addAttribute("table", customerRepo.sortCustomersByArea(false));
                    }
                }
                case "desc" -> {
                    switch (type)
                    {
                        case "id" -> model.addAttribute("table", customerRepo.sortCustomersByID(true));
                        case "name" -> model.addAttribute("table", customerRepo.sortCustomersByName(true));
                        case "login" -> model.addAttribute("table", customerRepo.sortCustomersByLogin(true));
                        case "password" -> model.addAttribute("table",
                                                              customerRepo.sortCustomersByPassword(true));
                        case "balance" -> model.addAttribute("table", customerRepo.sortCustomersByBalance(true));
                        case "area" -> model.addAttribute("table", customerRepo.sortCustomersByArea(true));
                    }
                }
            }
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return "admin/customers";
    }

    private boolean checkParams(String areaId, StringBuilder errorMessage) throws SQLException
    {
        boolean error = false;
        if (areaRepo.getArea(Integer.parseInt(areaId)) == null)
        {
            errorMessage.append("There is no such area! ");
            error = true;
        }
        return error;
    }

    public String updateCustomer(CustomerImpl customer,
                                 String areaId,
                                 Model model)
    {
        try
        {
            String errorMessage = "";
            StringBuilder stringBuilder = new StringBuilder(errorMessage);
            if (checkParams(areaId, stringBuilder))
            {
                stringBuilder.append("Error index: ").append(customer.getId());
                model.addAttribute("errorMessage", stringBuilder.toString());
            }
            else
            {
                customer.setArea(areaRepo.getArea(Integer.parseInt(areaId)));
                customerRepo.updateCustomer(customer.getId(), customer);
            }
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return this.showCustomers(null, "none", null, model);
    }

    public String createCustomer(CustomerImpl customer,
                                 String areaId,
                                 Model model)
    {
        try
        {
            String errorMessage = "";
            StringBuilder stringBuilder = new StringBuilder(errorMessage);
            if (checkParams(areaId, stringBuilder))
            {
                stringBuilder.append("Error index: new object creation");
                model.addAttribute("errorMessage", stringBuilder.toString());
            }
            else
            {
                customer.setArea(areaRepo.getArea(Integer.parseInt(areaId)));
                customerRepo.createCustomer(customer);
            }
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return this.showCustomers(null, "none", null, model);
    }

    public List<Customer> sortCustomersByLogin(boolean reversed) throws SQLException
    {
        return customerRepo.sortCustomersByLogin(reversed);
    }

    public List<Customer> sortCustomersByPassword(boolean reversed) throws SQLException
    {
        return customerRepo.sortCustomersByPassword(reversed);
    }

    public List<Customer> sortCustomersByBalance(boolean reversed) throws SQLException
    {
        return customerRepo.sortCustomersByBalance(reversed);
    }

    public List<Customer> sortCustomersByName(boolean reversed) throws SQLException
    {
        return customerRepo.sortCustomersByName(reversed);
    }

    public List<Customer> sortCustomersByID(boolean reversed) throws SQLException
    {
        return customerRepo.sortCustomersByID(reversed);
    }

    public List<Customer> sortCustomersByArea(boolean reversed) throws SQLException
    {
        return customerRepo.sortCustomersByArea(reversed);
    }

    public Customer searchCustomerByLogin(String login) throws SQLException
    {
        return customerRepo.searchCustomerByLogin(login);
    }

    public List<Customer> searchCustomersByPassword(String password) throws SQLException
    {
        return customerRepo.searchCustomersByPassword(password);
    }

    public List<Customer> searchCustomersByBalance(double balance) throws SQLException
    {
        return customerRepo.searchCustomersByBalance(balance);
    }

    public List<Customer> searchCustomersByName(String name) throws SQLException
    {
        return customerRepo.searchCustomersByName(name);
    }

    public List<Customer> searchCustomersByArea(String areaName) throws SQLException
    {
        return customerRepo.searchCustomersByArea(areaName);
    }

    public Customer getCustomer(int id) throws SQLException
    {
        return customerRepo.getCustomer(id);
    }

    public List<Customer> getCustomerValues() throws SQLException
    {
        return customerRepo.getCustomerValues();
    }

    public void createCustomer(Customer customer) throws SQLException
    {
        customerRepo.createCustomer(customer);
    }

    public void deleteCustomer(int id) throws SQLException
    {
        customerRepo.deleteCustomer(id);
    }

    public void updateCustomer(int id, Customer customer) throws SQLException
    {
        customerRepo.updateCustomer(id, customer);
    }
}
