package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.utils.repos.AdminRepo;
import com.netcracker.miniOPF.utils.repos.OrderRepo;
import com.netcracker.miniOPF.utils.storageUtils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                             Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", orderRepo.getOrder(Integer.parseInt(value)));
                case "admin" -> model.addAttribute("table",
                                                   orderRepo.searchOrdersByAdminID(Integer.parseInt(value)));
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
                               @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                               Model model,
                               int userID)
    {
        OrderUtils orderUtils = new OrderUtils();
        List<Order> myOrders = orderRepo.searchOrdersByAdminID(userID);
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", orderUtils.searchOrderByID(myOrders, Integer.parseInt(value)));
                case "admin" -> model.addAttribute("table",
                                                   orderUtils.searchOrdersByAdminID(myOrders, Integer.parseInt(value)));
                case "service" -> model.addAttribute("table",
                                                     orderUtils.searchOrdersByServiceID(myOrders, Integer.parseInt(value)));
                case "status" -> model.addAttribute("table",
                                                    orderUtils.searchOrdersByStatus(myOrders, value));
                case "action" -> model.addAttribute("table",
                                                    orderUtils.searchOrdersByAction(myOrders, value));
            }
            return "admin/adminorders";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", myOrders);
                return "admin/adminorders";
            }
            case "asc" -> {
                switch (type)
                {

                    case "id" -> model.addAttribute("table",
                                                    orderUtils.sortOrdersByID(myOrders));
                    case "admin" -> model.addAttribute("table",
                                                       orderUtils.sortOrdersByAdminID(myOrders));
                    case "service" -> model.addAttribute("table",
                                                         orderUtils.sortOrdersByServiceID(myOrders));
                    case "status" -> model.addAttribute("table",
                                                        orderUtils.sortOrdersByStatus(myOrders));
                    case "action" -> model.addAttribute("table",
                                                        orderUtils.sortOrdersByAction(myOrders));
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table",
                                                    orderUtils.sortOrdersByIDReversed(myOrders));
                    case "admin" -> model.addAttribute("table",
                                                       orderUtils.sortOrdersByAdminIDReversed(myOrders));
                    case "service" -> model.addAttribute("table",
                                                         orderUtils.sortOrdersByServiceIDReversed(myOrders));
                    case "status" -> model.addAttribute("table",
                                                        orderUtils.sortOrdersByStatusReversed(myOrders));
                    case "action" -> model.addAttribute("table",
                                                        orderUtils.sortOrdersByActionReversed(myOrders));
                }
            }
        }

        return "admin/adminorders";
    }
}
