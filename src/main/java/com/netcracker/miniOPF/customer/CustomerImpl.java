package com.netcracker.miniOPF.customer;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.service.Service;

import java.util.ArrayList;
import java.util.List;


@JsonTypeName("customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerImpl implements Customer
{
    private String login;
    private String password;
    private double balance;

    @JsonBackReference(value = "services")
    private List<Service> services;
    private String name;
    private int id;

    public CustomerImpl()
    {
        services = new ArrayList<>();
    }

    public CustomerImpl(List<Service> services)
    {
        this.services = services;
    }

    @Override
    public String getLogin()
    {
        return login;
    }

    @Override
    public void setLogin(String login)
    {
        this.login = login;
    }

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public void setID(int id)
    {
        this.id = id;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public double getBalance()
    {
        return balance;
    }

    @Override
    public void setBalance(double balance)
    {
        this.balance = balance;
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
    public List<Service> getServices()
    {
        return services;
    }

    @Override
    public void setServices(List<Service> services)
    {
        this.services = services;
    }

    @Override
    public void addService(Service service)
    {
        services.add(service);
    }

    @Override
    public void removeService(int id)
    {
        int len = services.size();
        for (int index = 0; index < len; index++)
        {
            if (services.get(index).getID() == id)
            {
                services.remove(index);
            }
        }
    }
}
