package com.netcracker.miniOPF.model.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.model.template.Template;


@JsonTypeName("service")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceImpl implements Service
{
    private int id;
    private String name;
    private String description;
    private double price;
    private Template template;
    private Customer customer;
    private ServiceStatus status;

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
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public double getPrice()
    {
        return price;
    }

    @Override
    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public Template getTemplate()
    {
        return template;
    }

    @Override
    public void setTemplate(Template template)
    {
        this.template = template;
    }

    @Override
    public Customer getCustomer()
    {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    @Override
    public ServiceStatus getStatus()
    {
        return status;
    }

    @Override
    public void setStatus(ServiceStatus status)
    {
        this.status = status;
    }
}
