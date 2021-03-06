package com.netcracker.miniOPF.model.customer;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.area.AreaImpl;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;

import java.util.ArrayList;
import java.util.List;


@JsonTypeName("customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerImpl implements Customer
{
    private String login;
    private String password;
    private double balance;
    private Area area;

    @JsonBackReference(value = "services")
    private List<Service> services;
    private String name;
    private int id;

    public CustomerImpl()
    {
        services = new ArrayList<>();
        area = new AreaImpl();
        balance = 0;
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

        for(int i = 0;i < services.size(); i++){
            this.services.get(i).setCustomer(this);
        }
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
            if (services.get(index).getId() == id)
            {
                services.remove(index);
            }
        }
    }
    @Override
    public Area getArea()
    {
        return area;
    }

    @Override
    public void setArea(Area area)
    {
        this.area = area;
    }
}
