package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.controller.AdminController;
import com.netcracker.miniOPF.controller.CustomerController;
import com.netcracker.miniOPF.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController
{
    CustomerController customerController;
    AdminController adminController;

    @Autowired
    public AuthorizationController(CustomerController customerController,
                                   AdminController adminController)
    {
        this.customerController = customerController;
        this.adminController = adminController;
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
        Customer customer = customerController.searchCustomerByLogin(login);
        Admin admin = adminController.searchAdminByLogin(login);
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
                //TODO customer redirect
                return "redirect:/greeting";
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
                return "redirect:/admin/customers?id=" + admin.getID() + "&sort=none";
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
