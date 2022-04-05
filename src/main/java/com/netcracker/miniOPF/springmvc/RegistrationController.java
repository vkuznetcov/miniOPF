package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.admin.AdminImpl;
import com.netcracker.miniOPF.utils.repos.AdminRepo;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController
{

    CustomerRepo customerRepo;
    AdminRepo adminRepo;

    @Autowired
    public RegistrationController(CustomerRepo customerController,
                                  AdminRepo adminController)
    {
        this.customerRepo = customerController;
        this.adminRepo = adminController;
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


        if (password.equals(passConfirm))
        {
            if (login.contains("admin"))
            {
                Admin admin = new AdminImpl();
                admin.setName(name);
                admin.setLogin(login);
                admin.setPassword(password);
                adminRepo.createAdmin(admin);
                model.addAttribute("admin", admin);
                return "redirect:/authorization";
            }
            else
            {
                Customer customer = new CustomerImpl();
                customer.setName(name);
                customer.setLogin(login);
                customer.setPassword(password);
                customerRepo.createCustomer(customer);
                model.addAttribute("customer", customer);
                return "redirect:/authorization";
            }
        }
        else
        {
            model.addAttribute("errorMessage", "Passwords are not equal");
            model.addAttribute("username", name);
            model.addAttribute("login", login);
            return "/registration";
        }
    }
}
