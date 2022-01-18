package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class CustomerController
{
    private final Storage storage;
    private final CustomerHandler customerHandler;

    @Autowired
    public CustomerController(Storage storage,
                              CustomerHandler customerHandler)
    {
        this.storage = storage;
        this.customerHandler = customerHandler;
    }

    public List<Customer> sortCustomersByLogin()
    {
        return customerHandler.sortCustomersByLogin(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByLoginReversed()
    {
        return customerHandler.sortCustomersByLoginReversed(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByPassword()
    {
        return customerHandler.sortCustomersByPassword(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByPasswordReversed()
    {
        return customerHandler.sortCustomersByPasswordReversed(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByBalance()
    {
        return customerHandler.sortCustomersByBalance(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByBalanceReversed()
    {
        return customerHandler.sortCustomersByBalanceReversed(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByName()
    {
        return customerHandler.sortCustomersByName(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByNameReversed()
    {
        return customerHandler.sortCustomersByNameReversed(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByID()
    {
        return customerHandler.sortCustomersByID(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByIDReversed()
    {
        return customerHandler.sortCustomersByIDReversed(storage.getCustomerValues());
    }

    public List<Customer> searchCustomerByLogin(
            String login)
    {
        return customerHandler.searchCustomerByLogin(storage.getCustomerValues(), login);
    }

    public List<Customer> searchCustomerByPassword(
            String password)
    {
        return customerHandler.searchCustomerByPassword(storage.getCustomerValues(), password);
    }

    public List<Customer> searchCustomerByBalance(
            double balance)
    {
        return customerHandler.searchCustomerByBalance(storage.getCustomerValues(), balance);
    }

    public List<Customer> searchCustomerByName(
            String name)
    {
        return customerHandler.searchCustomerByName(storage.getCustomerValues(), name);
    }

    public List<Customer> searchCustomerByID(
            int id)
    {
        return customerHandler.searchCustomerByID(storage.getCustomerValues(), id);
    }
}
