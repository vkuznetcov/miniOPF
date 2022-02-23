package com.netcracker.miniOPF.utils.controller;

import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.CustomerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class CustomerController
{
    private final Storage storage;
    private final CustomerUtils customerUtils;

    @Autowired
    public CustomerController(Storage storage,
                              CustomerUtils customerUtils)
    {
        this.storage = storage;
        this.customerUtils = customerUtils;
    }

    public List<Customer> sortCustomersByLogin()
    {
        return customerUtils.sortCustomersByLogin(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByLoginReversed()
    {
        return customerUtils.sortCustomersByLoginReversed(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByPassword()
    {
        return customerUtils.sortCustomersByPassword(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByPasswordReversed()
    {
        return customerUtils.sortCustomersByPasswordReversed(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByBalance()
    {
        return customerUtils.sortCustomersByBalance(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByBalanceReversed()
    {
        return customerUtils.sortCustomersByBalanceReversed(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByName()
    {
        return customerUtils.sortCustomersByName(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByNameReversed()
    {
        return customerUtils.sortCustomersByNameReversed(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByID()
    {
        return customerUtils.sortCustomersByID(storage.getCustomerValues());
    }

    public List<Customer> sortCustomersByIDReversed()
    {
        return customerUtils.sortCustomersByIDReversed(storage.getCustomerValues());
    }

    public Customer searchCustomerByLogin(
            String login)
    {
        return customerUtils.searchCustomerByLogin(storage.getCustomerValues(), login);
    }

    public List<Customer> searchCustomersByPassword(
            String password)
    {
        return customerUtils.searchCustomersByPassword(storage.getCustomerValues(), password);
    }

    public List<Customer> searchCustomersByBalance(
            double balance)
    {
        return customerUtils.searchCustomersByBalance(storage.getCustomerValues(), balance);
    }

    public List<Customer> searchCustomersByName(
            String name)
    {
        return customerUtils.searchCustomersByName(storage.getCustomerValues(), name);
    }

    public Customer searchCustomerByID(
            int id)
    {
        return customerUtils.searchCustomerByID(storage.getCustomerValues(), id);
    }

    public Customer getCustomer(int id)
    {
        return storage.getCustomer(id);
    }

    public List<Customer> getCustomerValues()
    {
        return storage.getCustomerValues();
    }

    public void createCustomer(Customer customer)
    {
        storage.createCustomer(customer);
    }
}
