package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.utils.repos.AdminRepo;
import com.netcracker.miniOPF.utils.repos.OrderRepo;
import com.netcracker.miniOPF.utils.storageUtils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Objects;

@Service
public class OrderService
{
    OrderRepo orderRepo;
    AdminRepo adminRepo;
    ServiceService serviceService;

    @Autowired
    public OrderService(OrderRepo orderRepo,
                        AdminRepo adminRepo,
                        ServiceService serviceService)
    {
        this.orderRepo = orderRepo;
        this.adminRepo = adminRepo;
        this.serviceService = serviceService;
    }


    public String showOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", orderRepo.getOrder(Integer.parseInt(value)));
                case "admin" -> model.addAttribute("table",
                                                   orderRepo.searchOrdersByAdminID(adminRepo.getAdmin(Integer.parseInt(
                                                           value))));
                case "service" -> model.addAttribute("table",
                                                     orderRepo.searchOrdersByServiceID(Integer.parseInt(value)));
                case "status" -> model.addAttribute("table",
                                                    orderRepo.searchOrdersByStatus(value.toUpperCase(Locale.ROOT)));
                case "action" -> model.addAttribute("table",
                                                    orderRepo.searchOrdersByAction(value.toUpperCase(Locale.ROOT)));
            }
            return "admin/orders";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", orderRepo.getOrderValues());
                return "admin/orders";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderRepo.sortOrdersByID());
                    case "admin" -> model.addAttribute("table",
                                                       orderRepo.sortOrdersByAdminID());
                    case "service" -> model.addAttribute("table",
                                                         orderRepo.sortOrdersByServiceID());
                    case "status" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByStatus());
                    case "action" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByAction());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderRepo.sortOrdersByIDReversed());
                    case "admin" -> model.addAttribute("table",
                                                       orderRepo.sortOrdersByAdminIDReversed());
                    case "service" -> model.addAttribute("table",
                                                         orderRepo.sortOrdersByServiceIDReversed());
                    case "status" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByStatusReversed());
                    case "action" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByActionReversed());
                }
            }
        }

        return "admin/orders";
    }

    //TODO
    public String showMyOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                               @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        OrderUtils orderUtils = new OrderUtils();
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", orderRepo.getOrder(Integer.parseInt(value)));
                case "admin" -> model.addAttribute("table",
                                                   orderRepo.searchOrdersByAdminID(adminRepo.getAdmin(Integer.parseInt(
                                                           value))));
                case "service" -> model.addAttribute("table",
                                                     orderRepo.searchOrdersByServiceID(Integer.parseInt(value)));
                case "status" -> model.addAttribute("table",
                                                    orderRepo.searchOrdersByStatus(value.toUpperCase(Locale.ROOT)));
                case "action" -> model.addAttribute("table",
                                                    orderRepo.searchOrdersByAction(value.toUpperCase(Locale.ROOT)));
            }
            return "admin/orders";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table",
                                   orderRepo.searchOrdersByAdminID(adminRepo.getAdmin(Integer.parseInt(
                                           Objects.requireNonNull(model.getAttribute("userId")).toString()))));
                return "admin/orders";
            }
            case "asc" -> {
                switch (type)
                {

                    case "id" -> model.addAttribute("table", orderUtils.sortOrdersByID(orderRepo.searchOrdersByAdminID(
                            adminRepo.getAdmin(Integer.parseInt(
                                    Objects.requireNonNull(model.getAttribute("userId")).toString())))));
                    case "admin" -> model.addAttribute("table",
                                                       orderRepo.sortOrdersByAdminID());
                    case "service" -> model.addAttribute("table",
                                                         orderRepo.sortOrdersByServiceID());
                    case "status" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByStatus());
                    case "action" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByAction());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderRepo.sortOrdersByIDReversed());
                    case "admin" -> model.addAttribute("table",
                                                       orderRepo.sortOrdersByAdminIDReversed());
                    case "service" -> model.addAttribute("table",
                                                         orderRepo.sortOrdersByServiceIDReversed());
                    case "status" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByStatusReversed());
                    case "action" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByActionReversed());
                }
            }
        }

        return "admin/adminorders";
    }
}
