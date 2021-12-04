package com.netcracker.miniOPF.storage;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.template.Template;

public interface Storage {
    void addCustomer(int key, Customer customer);
    void deleteCustomer(int key);

    void addOrder(int key, Order order);
    void deleteOrder(int key);

    void addService(int key, Service service);
    void  deleteService(int key);

    void addAdmin (int key, Admin admin);
    void deleteAdmin(int key);

    void addArea(int key, Area area);
    void deleteArea(int key);

    void addTemplate(int key, Template template);
    void deleteTemplate(int key);
}
