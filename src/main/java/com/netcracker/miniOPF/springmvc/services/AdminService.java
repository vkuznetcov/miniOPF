package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.utils.repos.*;
import com.netcracker.miniOPF.utils.storageUtils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Objects;

@Service
public class AdminService
{
    private final AdminRepo adminRepo;
    OrderRepo orderRepo;
    ServiceRepo serviceRepo;
    TemplateRepo templateRepo;

    public static class FormParams
    {
        public static final String TYPE = "type";
        public static final String SORT_ORDER = "sort";
        public static final String SEARCH_VALUE = "valueToSearch";
        public static final String ID = "id";
    }


    @Autowired
    public AdminService(AdminRepo adminController,
                        OrderRepo orderController,
                        ServiceRepo serviceController,
                        TemplateRepo templateController)
    {
        this.adminRepo = adminController;
        this.orderRepo = orderController;
        this.serviceRepo = serviceController;
        this.templateRepo = templateController;
    }

    public String showAdmins(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", adminRepo.getAdmin(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", adminRepo.searchAdminsByName(value));
                case "login" -> model.addAttribute("table", adminRepo.searchAdminByLogin(value));
                case "password" -> model.addAttribute("table", adminRepo.searchAdminsByPassword(value));
            }
            return "admin/admins";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", adminRepo.getAdminValues());
                return "admin/admins";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", adminRepo.sortAdminsByID());
                    case "name" -> model.addAttribute("table", adminRepo.sortAdminsByName());
                    case "login" -> model.addAttribute("table", adminRepo.sortAdminsByLogin());
                    case "password" -> model.addAttribute("table", adminRepo.sortAdminsByPassword());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", adminRepo.sortAdminsByIDReversed());
                    case "name" -> model.addAttribute("table", adminRepo.sortAdminsByNameReversed());
                    case "login" -> model.addAttribute("table", adminRepo.sortAdminsByLoginReversed());
                    case "password" -> model.addAttribute("table", adminRepo.sortAdminsByPasswordReversed());
                }
            }
        }

        return "admin/admins";
    }
}
