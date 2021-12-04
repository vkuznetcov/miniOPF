package com.netcracker.miniOPF.customer;


import com.netcracker.miniOPF.service.Service;

import java.util.List;

public class CustomerIpml implements Customer{
    private String login;
    private String password;
    private double balance;
    private List<Service> services;
    private String name;
    private int id;

    @Override
    public String getLogin() {return login;}

    @Override
    public void setLogin(String login) {this.login = login;}

    @Override
    public int getID() {return id;}

    @Override
    public void setID(int id) {this.id=id;}

    @Override
    public String getPassword() {return password;}

    @Override
    public void setPassword(String password) {this.password=password;}

    @Override
    public double getBalance() {return balance;}

    @Override
    public void setBalance(double balance) {this.balance=balance;}

    @Override
    public String getName() {return name;}

    @Override
    public void setName(String name) {this.name = name;}

    @Override
    public void addService(Service service) {services.add(service);}

    @Override
    public void removeService(String name) {services.remove(name);}
}
