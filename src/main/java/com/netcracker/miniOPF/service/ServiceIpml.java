package com.netcracker.miniOPF.service;

import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.template.Template;

public class ServiceIpml implements Service{
    private int id;
    private String name;
    private String description;
    private double price;
    private Template template;
    private Customer customer;
    private ServiceStatus status;

    @Override
    public int getID() {return id;}

    @Override
    public void setID(int id) {this.id = id;}

    @Override
    public String getName() {return name;}

    @Override
    public void setName(String name) {this.name = name;}

    @Override
    public String getDiscription() {return description;}

    @Override
    public void setDiscription(String discription) {this.description = discription;}

    @Override
    public double getPrice() {return price;}

    @Override
    public void setPrice(double price) {this.price = price;}

    @Override
    public Template getTemplate() {return template;}

    @Override
    public void setTemplate(Template template) {this.template = template;}

    @Override
    public Customer getCustomer() {return customer;}

    @Override
    public void setCustomer(Customer customer) {this.customer = customer;}

    @Override
    public ServiceStatus getStatus() {return status;}

    @Override
    public void setStatus(int key) {
        ServiceStatus[] statuses = ServiceStatus.values();
        this.status = statuses[key];}
}
