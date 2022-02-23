package com.netcracker.miniOPF.model.storage;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.template.Template;

import java.util.List;

public interface Storage
{
    void createCustomer(Customer customer);

    Customer getCustomer(int id);

    //void updateCustomer(int id, Customer customer);
    void deleteCustomer(int id);

    void createOrder(Order order);

    Order getOrder(int id);

    void deleteOrder(int id);

    void createService(Service service);

    Service getService(int id);

    void deleteService(int id);

    void createAdmin(Admin admin);

    Admin getAdmin(int id);

    void deleteAdmin(int id);

    void createArea(Area area);

    Area getArea(int id);

    void deleteArea(int id);

    void createTemplate(Template template);

    Template getTemplate(int id);

    void deleteTemplate(int id);

    Service createService(int templateID, int customerID);

    List<Admin> getAdminValues();

    List<Area> getAreaValues();

    List<Customer> getCustomerValues();

    List<Order> getOrderValues();

    List<Service> getServiceValues();

    List<Template> getTemplateValues();
}
