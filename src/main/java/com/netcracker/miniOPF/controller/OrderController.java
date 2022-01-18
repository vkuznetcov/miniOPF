package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.order.enums.OrderAction;
import com.netcracker.miniOPF.order.enums.OrderStatus;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.OrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class OrderController
{
    private final Storage storage;
    private final OrderHandler orderHandler;

    @Autowired
    public OrderController(Storage storage,
                           OrderHandler orderHandler)
    {
        this.storage = storage;
        this.orderHandler = orderHandler;
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
        return orderHandler.sortOrdersByID(storage.getOrderValues());
    }

    public List<Order> sortOrdersByIDReversed()
    {
        return orderHandler.sortOrdersByIDReversed(storage.getOrderValues());
    }

    public List<Order> sortOrdersByAdminLogin()
    {
        return orderHandler.sortOrdersByAdminLogin(storage.getOrderValues());
    }

    public List<Order> sortOrdersByAdminLoginReversed()
    {
        return orderHandler.sortOrdersByAdminLoginReversed(storage.getOrderValues());
    }

    public List<Order> sortOrdersByServiceName()
    {
        return orderHandler.sortOrdersByServiceName(storage.getOrderValues());
    }

    public List<Order> sortOrdersByServiceNameReversed()
    {
        return orderHandler.sortOrdersByServiceNameReversed(storage.getOrderValues());
    }

    public List<Order> sortOrdersByStatus()
    {
        return orderHandler.sortOrdersByStatus(storage.getOrderValues());
    }

    public List<Order> sortOrdersByStatusReversed()
    {
        return orderHandler.sortOrdersByStatusReversed(storage.getOrderValues());
    }

    public List<Order> sortOrdersByAction()
    {
        return orderHandler.sortOrdersByAction(storage.getOrderValues());
    }

    public List<Order> sortOrdersByActionReversed()
    {
        return orderHandler.sortOrdersByActionReversed(storage.getOrderValues());
    }

    public List<Order> searchOrderByID(
            int id)
    {
        return orderHandler.searchOrderByID(storage.getOrderValues(), id);
    }

    public List<Order> searchOrderByAdmin(
            Admin admin)
    {
        return orderHandler.searchOrderByAdmin(storage.getOrderValues(), admin);
    }

    public List<Order> searchOrderByService(
            Service service)
    {
        return orderHandler.searchOrderByService(storage.getOrderValues(), service);
    }

    public List<Order> searchOrderByStatus(
            OrderStatus status)
    {
        return orderHandler.searchOrderByStatus(storage.getOrderValues(), status);
    }

    public List<Order> searchOrderByAction(
            OrderAction action)
    {
        return orderHandler.searchOrderByAction(storage.getOrderValues(), action);
    }
}
