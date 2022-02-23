package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.utils.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdministratorController
{

    AdminController     adminController;
    AreaController      areaController;
    CustomerController  customerController;
    OrderController     orderController;
    ServiceController   serviceController;
    TemplateController  templateController;

    private static abstract class FormParams{
        public static final String TYPE         = "type";
        public static final String SORT_ORDER   = "sort";
        public static final String SEARCH_VALUE = "valueToSearch";
        public static final String ID           = "id";
    }

    @Autowired
    public AdministratorController(AdminController adminController,
                                   AreaController areaController,
                                   CustomerController customerController,
                                   OrderController orderController,
                                   ServiceController serviceController,
                                   TemplateController templateController)
    {
        this.adminController    = adminController;
        this.areaController     = areaController;
        this.customerController = customerController;
        this.orderController    = orderController;
        this.serviceController  = serviceController;
        this.templateController = templateController;
    }

    @GetMapping("/customers")
    public String showCustomers(@RequestParam(value = FormParams.TYPE, required = false) String type,
                                @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = FormParams.ID, required = false) Integer id, Model model){
        if(Objects.nonNull(id)){
            model.addAttribute("userId", id);
        }
        if(Objects.nonNull(value)){
            switch(type){
                case "id"       -> model.addAttribute("table", customerController.searchCustomerByID(Integer.parseInt(value)));
                case "name"     -> model.addAttribute("table", customerController.searchCustomersByName(value));
                case "login"    -> model.addAttribute("table", customerController.searchCustomerByLogin(value));
                case "password" -> model.addAttribute("table", customerController.searchCustomersByPassword(value));
                case "balance"  -> model.addAttribute("table", customerController.searchCustomersByBalance(Double.parseDouble(value)));
            }
            return "admin/customers";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", customerController.getCustomerValues());
                model.addAttribute("sort", "none");
                return "admin/customers";
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

    @GetMapping("/admins")
    public String showAdmins(@RequestParam(value = FormParams.TYPE, required = false) String type,
                             @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value, Model model){
        if(Objects.nonNull(value)){
            switch(type){
                case "id"       -> model.addAttribute("table", adminController.searchAdminByID(Integer.parseInt(value)));
                case "name"     -> model.addAttribute("table", adminController.searchAdminsByName(value));
                case "login"    -> model.addAttribute("table", adminController.searchAdminByLogin(value));
                case "password" -> model.addAttribute("table", adminController.searchAdminsByPassword(value));
            }
            return "admin/admins";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", adminController.getAdminValues());
                model.addAttribute("sort", "none");
                return "admin/admins";
            }
            case "asc" -> {
                model.addAttribute("sort", "asc");
                switch (type)
                {
                    case "id"       -> model.addAttribute("table", adminController.sortAdminsByID());
                    case "name"     -> model.addAttribute("table", adminController.sortAdminsByName());
                    case "login"    -> model.addAttribute("table", adminController.sortAdminsByLogin());
                    case "password" -> model.addAttribute("table", adminController.sortAdminsByPassword());
                }
            }
            case "desc" -> {
                model.addAttribute("sort", "desc");
                switch (type)
                {
                    case "id"       -> model.addAttribute("table", adminController.sortAdminsByIDReversed());
                    case "name"     -> model.addAttribute("table", adminController.sortAdminsByNameReversed());
                    case "login"    -> model.addAttribute("table", adminController.sortAdminsByLoginReversed());
                    case "password" -> model.addAttribute("table", adminController.sortAdminsByPasswordReversed());
                }
            }
        }

        return "admin/admins";
    }
}
