package com.netcracker.miniOPF.utils.controllers;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class OrderController
{
    private final Storage storage;
    private final OrderUtils orderUtils;

    @Autowired
    public OrderController(Storage storage,
                           OrderUtils orderUtils)
    {
        this.storage = storage;
        this.orderUtils = orderUtils;
    }

    public void suspendOrder(int id)
    {
        storage.getOrder(id).setAction(OrderAction.SUSPEND);
    }

    public void resumeOrder(int id)
    {
        storage.getOrder(id).setAction(OrderAction.RESUME);
    }

    public List<Order> sortOrdersByID()
    {
        return orderUtils.sortOrdersByID(storage.getOrderValues());
    }

    public List<Order> sortOrdersByIDReversed()
    {
        return orderUtils.sortOrdersByIDReversed(storage.getOrderValues());
    }

    public List<Order> sortOrdersByAdminLogin()
    {
        return orderUtils.sortOrdersByAdminLogin(storage.getOrderValues());
    }

    public List<Order> sortOrdersByAdminLoginReversed()
    {
        return orderUtils.sortOrdersByAdminLoginReversed(storage.getOrderValues());
    }

    public List<Order> sortOrdersByServiceName()
    {
        return orderUtils.sortOrdersByServiceName(storage.getOrderValues());
    }

    public List<Order> sortOrdersByServiceNameReversed()
    {
        return orderUtils.sortOrdersByServiceNameReversed(storage.getOrderValues());
    }

    public List<Order> sortOrdersByStatus()
    {
        return orderUtils.sortOrdersByStatus(storage.getOrderValues());
    }

    public List<Order> sortOrdersByStatusReversed()
    {
        return orderUtils.sortOrdersByStatusReversed(storage.getOrderValues());
    }

    public List<Order> sortOrdersByAction()
    {
        return orderUtils.sortOrdersByAction(storage.getOrderValues());
    }

    public List<Order> sortOrdersByActionReversed()
    {
        return orderUtils.sortOrdersByActionReversed(storage.getOrderValues());
    }

    public Order searchOrderByID(
            int id)
    {
        return orderUtils.searchOrderByID(storage.getOrderValues(), id);
    }

    public List<Order> searchOrdersByAdmin(
            Admin admin)
    {
        return orderUtils.searchOrdersByAdmin(storage.getOrderValues(), admin);
    }

    public List<Order> searchOrdersByService(
            Service service)
    {
        return orderUtils.searchOrdersByService(storage.getOrderValues(), service);
    }

    public List<Order> searchOrdersByStatus(
            OrderStatus status)
    {
        return orderUtils.searchOrdersByStatus(storage.getOrderValues(), status);
    }

    public List<Order> searchOrdersByAction(
            OrderAction action)
    {
        return orderUtils.searchOrdersByAction(storage.getOrderValues(), action);
    }

    public Order getOrder(int id)
    {
        return storage.getOrder(id);
    }

    public List<Order> getOrderValues()
    {
        return storage.getOrderValues();
    }
}
