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
public class RegistrationController
{

    CustomerController customerController;

    @Autowired
    public RegistrationController(CustomerController customerController)
    {
        this.customerController = customerController;
    }

    @GetMapping("/registration")
    public String showRegister(Model model)
    {
        model.addAttribute("username", "");
        model.addAttribute("login", "");
        model.addAttribute("errorMessage", "");

        return "registration";
    }


    @PostMapping(value = "/registration")
    public String addUser(@RequestParam(name = "username") String name,
                          @RequestParam(name = "login") String login,
                          @RequestParam(name = "password") String password,
                          @RequestParam(name = "passwordConfirm") String passConfirm,
                          Model model)
    {


        if(password.equals(passConfirm))
        {
            Customer customer = new CustomerImpl();
            customer.setName(name);
            customer.setLogin(login);
            customer.setPassword(password);
            customerController.createCustomer(customer);
            model.addAttribute("customer", customer);
            return "redirect:/authorization";
        }
        else {
            model.addAttribute("errorMessage", "Passwords are not equal");
            model.addAttribute("username", name);
            model.addAttribute("login", login);
            return "/registration";
        }
    }
}
