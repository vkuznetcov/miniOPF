package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.controller.CustomerController;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.customer.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController
{
    CustomerController customerController;

    @Autowired
    public AuthorizationController(CustomerController customerController)
    {
        this.customerController = customerController;
    }

    @GetMapping("/authorization")
    public String showAuth(Model model)
    {
        model.addAttribute("login", "");
        model.addAttribute("errorMessage", "");
        return "authorization";
    }

    @PostMapping("/authorization")
    public String checkCustomer(Model model,
                                @RequestParam(name = "login") String login,
                                @RequestParam(name = "password") String password)
    {
        Customer customer = customerController.searchCustomerByLogin(login);
        if(customer == null){
            model.addAttribute("errorMessage", "There is no such user!");
            return "/authorization";
        }
        else if(customer.getPassword().equals(password)){
            model.addAttribute("name", customer.getName());
            model.addAttribute("customer", customer);
            return "/greeting";
        }
        else
        {
            model.addAttribute("login", login);
            model.addAttribute("errorMessage", "Invalid password!");
            return "/authorization";
        }
    }
}
