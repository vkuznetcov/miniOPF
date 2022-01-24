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
    @Autowired
    CustomerController customerController;

    @GetMapping("/authorization")
    public String showAuth()
    {
        return "authorization";
    }

    @PostMapping("/authorization")
    public String checkCustomer(Model model,
                                @RequestParam(name = "login") String login,
                                @RequestParam(name = "password") String password)
    {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml");
//
//        CustomerController customerController = (CustomerController) context.getBean("customerController");
        if(customerController.searchCustomerByLogin(login)!=null){
            Customer customer = customerController.searchCustomerByLogin(login);
            model.addAttribute("name", customer.getName());
            model.addAttribute("customer", customer);
            return "redirect:/greeting";
        }
        return "/authorization";
    }
}
