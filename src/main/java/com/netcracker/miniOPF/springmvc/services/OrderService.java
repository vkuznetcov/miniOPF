package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.model.order.OrderImpl;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.utils.repos.AdminRepo;
import com.netcracker.miniOPF.utils.repos.OrderRepo;
import com.netcracker.miniOPF.utils.storageUtils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        List<String> actions = new ArrayList<>();
        actions.add("CONNECT");
        actions.add("DISCONNECT");
        actions.add("RESUME");
        actions.add("SUSPEND");
        model.addAttribute("actions", actions);

        List<String> statuses = new ArrayList<>();
        statuses.add("ENTERING");
        statuses.add("IN_PROGRESS");
        statuses.add("COMPLETED");
        model.addAttribute("statuses", statuses);

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

    private boolean checkParams(String adminId,
                                String serviceId,
                                StringBuilder errorMessage)
    {

        boolean error = false;
        if (adminRepo.getAdmin(Integer.parseInt(adminId)) == null)
        {
            errorMessage.append("There is no such admin! ");
            error = true;
        }
        if (serviceService.getService(Integer.parseInt(serviceId)) == null)
        {
            errorMessage.append("There is no such service! ");
            error = true;
        }
        return error;
    }

    public String updateOrders(OrderImpl order,
                               String adminId,
                               String serviceId,
                               String status,
                               String action,
                               Model model)
    {
        String errorMessage = "";
        StringBuilder stringBuilder = new StringBuilder(errorMessage);
        if (checkParams(adminId, serviceId, stringBuilder))
        {
            stringBuilder.append("Error index: ").append(order.getId());
            model.addAttribute("errorMessage", stringBuilder.toString());
        }
        else
        {
            order.setAdmin(adminRepo.getAdmin(Integer.parseInt(adminId)));
            order.setService(serviceService.getService(Integer.parseInt(serviceId)));
            order.setAction(OrderAction.valueOf(action.toUpperCase(Locale.ROOT)));
            order.setStatus(OrderStatus.valueOf(status.toUpperCase(Locale.ROOT)));
            orderRepo.updateOrder(order.getId(), order);
        }
        return this.showOrders(null, "none", null, model);
    }

    public String createOrder(OrderImpl order,
                              String adminId,
                              String serviceId,
                              String status,
                              String action,
                              Model model)
    {
        String errorMessage = "";
        StringBuilder stringBuilder = new StringBuilder(errorMessage);
        if (checkParams(adminId, serviceId, stringBuilder))
        {
            stringBuilder.append("Error index: new object creation");
            model.addAttribute("errorMessage", stringBuilder.toString());
        }
        else
        {
            order.setAdmin(adminRepo.getAdmin(Integer.parseInt(adminId)));
            order.setService(serviceService.getService(Integer.parseInt(serviceId)));
            order.setAction(OrderAction.valueOf(action.toUpperCase(Locale.ROOT)));
            order.setStatus(OrderStatus.valueOf(status.toUpperCase(Locale.ROOT)));
            orderRepo.createOrder(order);
        }
        return this.showOrders(null, "none", null, model);
    }

    private boolean checkStart(Order order, StringBuilder errorMessage, Model model){
        boolean error = false;
        if(order.getAdmin() == null){
            error = true;
            errorMessage.append("Error: order must be assigned before starting\n");
        }
        if (!order.getStatus().equals(OrderStatus.ENTERING))
        {
            error = true;
            errorMessage.append("Error: order status must be \"ENTERING\" to start\n");
        }
        if(error){
            errorMessage.append( "Error index: ").append(order.getId());
        }
        model.addAttribute("errorMessage", errorMessage.toString());

        return error;
    }

    public String startOrder(Order order, Model model)
    {
        Order newOrder = orderRepo.getOrder(order.getId());
        if(!checkStart(newOrder, new StringBuilder(""), model)){
            newOrder.setStatus(OrderStatus.IN_PROGRESS);
            this.updateOrderWithCheck(order.getId(), newOrder);
        }
        return this.showOrders(null, "none", null, model);
    }

    public boolean checkClose(Order order, StringBuilder errorMessage, Model model){
        boolean error = false;
        if(order.getAdmin() == null){
            error = true;
            errorMessage.append("Error: order must be assigned before starting\n");
        }
        if (!order.getStatus().equals(OrderStatus.IN_PROGRESS))
        {
            error = true;
            errorMessage.append("Error: order status must be \"IN_PROGRESS\" to start\n");
        }
        if(error){
            errorMessage.append( "Error index: ").append(order.getId());
        }
        model.addAttribute("errorMessage", errorMessage.toString());

        return error;
    }

    public String closeOrder(Order order, Model model){
        Order newOrder = orderRepo.getOrder(order.getId());
        if(!checkClose(newOrder, new StringBuilder(""), model)) {
            com.netcracker.miniOPF.model.service.Service service = orderRepo.getOrder(order.getId()).getService();
            newOrder.setStatus(OrderStatus.COMPLETED);
            switch (newOrder.getAction()){
                case RESUME -> service.setStatus(ServiceStatus.ACTIVE);
                case CONNECT -> service.setStatus(ServiceStatus.ACTIVE);
                case SUSPEND -> service.setStatus(ServiceStatus.SUSPENDED);
                case DISCONNECT -> service.setStatus(ServiceStatus.DISCONNECTED);
            }
            serviceService.updateService(service.getId(), service);
            this.updateOrderWithCheck(newOrder.getId(), newOrder);
        }
        return this.showOrders(null, "none", null, model);
    }

    public String showMyOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                               @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                               Model model,
                               int userID)
    {
        List<String> actions = new ArrayList<>();
        actions.add("CONNECT");
        actions.add("DISCONNECT");
        actions.add("RESUME");
        actions.add("SUSPEND");
        model.addAttribute("actions", actions);

        List<String> statuses = new ArrayList<>();
        statuses.add("ENTERING");
        statuses.add("IN_PROGRESS");
        statuses.add("COMPLETED");
        model.addAttribute("statuses", statuses);
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
                                                     orderUtils.searchOrdersByServiceID(myOrders,
                                                                                        Integer.parseInt(value)));
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

    public String updateMyOrders(OrderImpl order,
                                 String adminId,
                                 String serviceId,
                                 String status,
                                 String action,
                                 Model model, int userID)
    {
        String errorMessage = "";
        StringBuilder stringBuilder = new StringBuilder(errorMessage);
        if (checkParams(adminId, serviceId, stringBuilder))
        {
            stringBuilder.append("Error index: ").append(order.getId());
            model.addAttribute("errorMessage", stringBuilder.toString());
        }
        else
        {
            order.setAdmin(adminRepo.getAdmin(Integer.parseInt(adminId)));
            order.setService(serviceService.getService(Integer.parseInt(serviceId)));
            order.setAction(OrderAction.valueOf(action.toUpperCase(Locale.ROOT)));
            order.setStatus(OrderStatus.valueOf(status.toUpperCase(Locale.ROOT)));
            orderRepo.updateOrder(order.getId(), order);
        }
        return this.showMyOrders(null, "none", null, model, userID);
    }

    public String startMyOrder(Order order, Model model, int userId)
    {
        Order newOrder = orderRepo.getOrder(order.getId());
        if(!checkStart(newOrder, new StringBuilder(""), model)){
            newOrder.setStatus(OrderStatus.IN_PROGRESS);
            this.updateOrderWithCheck(order.getId(), newOrder);
        }
        return this.showMyOrders(null, "none", null, model, userId);
    }

    public String closeMyOrder(Order order, Model model, int userId){
        Order newOrder = orderRepo.getOrder(order.getId());
        if(!checkClose(newOrder, new StringBuilder(""), model)) {
            com.netcracker.miniOPF.model.service.Service service = orderRepo.getOrder(order.getId()).getService();
            newOrder.setStatus(OrderStatus.COMPLETED);
            switch (newOrder.getAction()){
                case RESUME -> service.setStatus(ServiceStatus.ACTIVE);
                case CONNECT -> service.setStatus(ServiceStatus.ACTIVE);
                case SUSPEND -> service.setStatus(ServiceStatus.SUSPENDED);
                case DISCONNECT -> service.setStatus(ServiceStatus.DISCONNECTED);
            }
            serviceService.updateService(service.getId(), service);
            this.updateOrderWithCheck(newOrder.getId(), newOrder);
        }
        return this.showMyOrders(null, "none", null, model, userId);
    }

    public void suspendOrder(int id)
    {
        orderRepo.suspendOrder(id);
    }

    public void resumeOrder(int id)
    {
        orderRepo.resumeOrder(id);
    }

    public List<Order> sortOrdersByID()
    {
        return orderRepo.sortOrdersByID();
    }

    public List<Order> sortOrdersByIDReversed()
    {
        return orderRepo.sortOrdersByIDReversed();
    }

    public List<Order> sortOrdersByAdminID()
    {
        return orderRepo.sortOrdersByAdminID();
    }

    public List<Order> sortOrdersByAdminIDReversed()
    {
        return orderRepo.sortOrdersByAdminIDReversed();
    }

    public List<Order> sortOrdersByServiceID()
    {
        return orderRepo.sortOrdersByServiceID();
    }

    public List<Order> sortOrdersByServiceIDReversed()
    {
        return orderRepo.sortOrdersByServiceIDReversed();
    }

    public List<Order> sortOrdersByStatus()
    {
        return orderRepo.sortOrdersByStatus();
    }

    public List<Order> sortOrdersByStatusReversed()
    {
        return orderRepo.sortOrdersByStatusReversed();
    }

    public List<Order> sortOrdersByAction()
    {
        return orderRepo.sortOrdersByAction();
    }

    public List<Order> sortOrdersByActionReversed()
    {
        return orderRepo.sortOrdersByActionReversed();
    }

    public List<Order> searchOrdersByAdminID(int id)
    {
        return orderRepo.searchOrdersByAdminID(id);
    }

    public List<Order> searchOrdersByServiceID(int serviceID)
    {
        return orderRepo.searchOrdersByServiceID(serviceID);
    }

    public List<Order> searchOrdersByStatus(String status)
    {
        return orderRepo.searchOrdersByStatus(status);
    }

    public List<Order> searchOrdersByAction(String action)
    {
        return orderRepo.searchOrdersByAction(action);
    }

    public Order getOrder(int id)
    {
        return orderRepo.getOrder(id);
    }

    public List<Order> getOrderValues()
    {
        return orderRepo.getOrderValues();
    }

    public void updateOrderWithCheck(int id, Order order)
    {
        orderRepo.updateOrder(id, order);
    }

    public void deleteOrder(int id)
    {
        orderRepo.deleteOrder(id);
    }
}
