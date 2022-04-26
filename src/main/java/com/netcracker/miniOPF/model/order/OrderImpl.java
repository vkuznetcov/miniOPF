package com.netcracker.miniOPF.model.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.Service;


@JsonTypeName("order")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderImpl implements Order
{
    private int id;
    private Admin admin;
    private Service service;
    private OrderStatus status;
    private OrderAction action;


    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public Admin getAdmin()
    {
        return admin;
    }

    @Override
    public void setAdmin(Admin admin)
    {
        this.admin = admin;
    }

    @Override
    public Service getService()
    {
        return service;
    }

    @Override
    public void setService(Service service)
    {
        this.service = service;
    }

    @Override
    public OrderAction getAction()
    {
        return action;
    }

    @Override
    public void setAction(OrderAction action)
    {
        this.action = action;
    }

    @Override
    public OrderStatus getStatus()
    {
        return status;
    }

    @Override
    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }
}
