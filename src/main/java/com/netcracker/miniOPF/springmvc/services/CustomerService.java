package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Service
public class CustomerService
{
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo)
    {
        this.customerRepo = customerRepo;
    }

    public String showCustomers(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                Model model)
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
                }
            }
        }

        return "admin/customers";
    }
}
