package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.utils.repos.AdminRepo;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class AuthorizationController
{
    CustomerRepo customerRepo;
    AdminRepo adminRepo;

    @Autowired
    public AuthorizationController(CustomerRepo customerRepo,
                                   AdminRepo adminRepo)
    {
        this.customerRepo = customerRepo;
        this.adminRepo = adminRepo;
    }

    @GetMapping("/authorization")
    public String showAuth(Model model)
    {
        model.addAttribute("login", "");
        model.addAttribute("errorMessage", "");
        return "authorization";
    }

    @PostMapping("/authorization")
    public String checkUser(Model model,
                            @RequestParam(name = "login") String login,
                            @RequestParam(name = "password") String password)
    {
        Customer customer = null;
        Admin admin = null;
        try
        {
            customer = customerRepo.searchCustomerByLogin(login);
            admin = adminRepo.searchAdminByLogin(login);
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        // TODO логины должны быть различные у всех пользователей, поменять логику в соответствии с этим
        if (customer == null && admin == null)
        {
            model.addAttribute("errorMessage", "There is no such user!");
            return "/authorization";
        }
        else if (customer != null)
        {
            if (customer.getPassword().equals(password))
            {
                model.addAttribute("name", customer.getName());
                model.addAttribute("customer", customer);

                return "redirect:/customer/customerpage?id=" + customer.getId();
            }
            else
            {
                model.addAttribute("login", login);
                model.addAttribute("errorMessage", "Invalid password!");
                return "/authorization";
            }
        }
        else
        {
            if (admin.getPassword().equals(password))
            {
                model.addAttribute("name", admin.getName());
                model.addAttribute("admin", admin);
                return "redirect:/admin/customers?userId=" + admin.getId() + "&sort=none";
            }
            else
            {
                model.addAttribute("login", login);
                model.addAttribute("errorMessage", "Invalid password!");
                return "/authorization";
            }
        }
    }
}