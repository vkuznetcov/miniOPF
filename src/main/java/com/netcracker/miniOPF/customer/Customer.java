package com.netcracker.miniOPF.customer;

import com.netcracker.miniOPF.service.ServiceInt;

public interface Customer {
    String getLogin();
    void setLogin(String login);

    int getID();
    void setID(int id);

    String getPassword();
    void setPassword(String password);

    double getBalance();
    void setBalance(double balance);

    String getName();
    void setName(String name);

    void addService(ServiceInt serviceInt);
    void removeService(String name);
}
