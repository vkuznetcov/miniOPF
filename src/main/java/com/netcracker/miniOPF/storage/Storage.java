package com.netcracker.miniOPF.storage;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.template.Template;

public interface Storage {
    void addCustomer(int id, Customer customer);
    void deleteCustomer(int id);

    void addOrder(int id, Order order);
    void deleteOrder(int id);

    void addService(int id, Service service);
    void  deleteService(int id);

    void addAdmin (int id, Admin admin);
    void deleteAdmin(int id);

    void addArea(int id, Area area);
    void deleteArea(int id);

    void addTemplate(int id, Template template);
    void deleteTemplate(int id);
}
