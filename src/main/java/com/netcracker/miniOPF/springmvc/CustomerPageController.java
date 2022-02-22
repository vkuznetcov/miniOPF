package com.netcracker.miniOPF.springmvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.controller.CustomerController;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.storage.ConvertMapAndFiles;
import com.netcracker.miniOPF.storage.StorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerPageController {
    CustomerController customerController;
    StorageImpl storage = ConvertMapAndFiles.readFile();

    @Autowired
    public CustomerPageController(CustomerController customerController)
    {
        this.customerController = customerController;
    }
    @GetMapping("/customerpage")
    public String greeting(@RequestParam(name = "name" , defaultValue="Ivan") String name,
                           Model model) throws JsonProcessingException {
        List<Service> Servicelist = storage.getServiceValues();
        String services = "";
        for(Service service : Servicelist){
            services += service.getName() + "\n";
        }
        List<Customer> Customerlist = storage.getCustomerValues();
        Double balance = null;
        for(Customer customer : Customerlist){
            if(name.equals(customer.getName()))
            {
                balance = customer.getBalance();
            }
        }
        model.addAttribute("name", name);
        model.addAttribute("balance", balance.toString());
        model.addAttribute("services", services);
        return "customerpage";
    }
    @PostMapping(value = "/active services")
    public String activeServices(@RequestParam(name = "name" , defaultValue="Ivan") String name,
                           Model model) throws JsonProcessingException {
        List<Customer> Customerlist = storage.getCustomerValues();
        String services = "";
        for(Customer customer : Customerlist){
            if(name.equals(customer.getName()))
            {
                services +=  customer.getServices();
            }
        }

        model.addAttribute("services", services);
        return "customerpage";
    }
}
