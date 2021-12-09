package com.netcracker.miniOPF.storage;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.template.Template;

public interface Storage {
    void addCustomer(Customer customer);
    Customer getCustomer(int id);
    //void updateCustomer(int id, Customer customer);
    void deleteCustomer(int id);

    void addOrder(Order order);
    Order getOrder(int id);
    void deleteOrder(int id);

    void addService(Service service);
    Service getService(int id);
    void  deleteService(int id);

    void addAdmin(Admin admin);
    Admin getAdmin(int id);
    void deleteAdmin(int id);

    void addArea(Area area);
    Area getArea(int id);
    void deleteArea(int id);

    void addTemplate(Template template);
    Template getTemplate(int id);
    void deleteTemplate(int id);

    Service createService(int templateID, int customerID);
}
