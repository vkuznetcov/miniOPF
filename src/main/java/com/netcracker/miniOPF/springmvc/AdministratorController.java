package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdministratorController
{

    AdminController adminController;
    AreaController areaController;
    CustomerController customerController;
    OrderController orderController;
    ServiceController serviceController;
    TemplateController templateController;

    @Autowired
    public AdministratorController(AdminController adminController,
                                   AreaController areaController,
                                   CustomerController customerController,
                                   OrderController orderController,
                                   ServiceController serviceController,
                                   TemplateController templateController)
    {
        this.adminController = adminController;
        this.areaController = areaController;
        this.customerController = customerController;
        this.orderController = orderController;
        this.serviceController = serviceController;
        this.templateController = templateController;
    }

    @GetMapping("/home")
    public String showHome(@RequestParam(value = "id") int id, Model model){
        model.addAttribute("name", adminController.getAdmin(id).getName());
        model.addAttribute("user", adminController.getAdmin(id));
        return "admin/home";
    }
    @GetMapping("/customers")
    public String showCustomers(@RequestParam(value = "type", required = false) String type,
                                @RequestParam(value = "sort") String sort,Model model){
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", customerController.getCustomerValues());
                model.addAttribute("sort", "none");
            }
            case "asc" -> {
                model.addAttribute("sort", "asc");
                switch (type)
                {
                    case "id"       -> model.addAttribute("table", customerController.sortCustomersByID());
                    case "name"     -> model.addAttribute("table", customerController.sortCustomersByName());
                    case "login"    -> model.addAttribute("table", customerController.sortCustomersByLogin());
                    case "password" -> model.addAttribute("table", customerController.sortCustomersByPassword());
                    case "balance"  -> model.addAttribute("table", customerController.sortCustomersByBalance());
                }
            }
            case "desc" -> {
                model.addAttribute("sort", "desc");
                switch (type)
                {
                    case "id"       -> model.addAttribute("table", customerController.sortCustomersByIDReversed());
                    case "name"     -> model.addAttribute("table", customerController.sortCustomersByNameReversed());
                    case "login"    -> model.addAttribute("table", customerController.sortCustomersByLoginReversed());
                    case "password" -> model.addAttribute("table", customerController.sortCustomersByPasswordReversed());
                    case "balance"  -> model.addAttribute("table", customerController.sortCustomersByBalanceReversed());
                }
            }
        }

        return "admin/customers";
    }
}
