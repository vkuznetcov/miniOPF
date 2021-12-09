package com.netcracker.miniOPF.storage;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.ServiceImpl;
import com.netcracker.miniOPF.template.Template;

import java.util.HashMap;
import java.util.Map;

public class StorageImpl implements Storage{
    private Map<Integer, Customer> customerMap;
    private Map<Integer, Order> orderMap;
    private Map<Integer, Service> serviceMap;
    private Map<Integer, Admin> adminMap;
    private Map<Integer, Area> areaMap;
    private Map<Integer, Template> templateMap;
    private int id;

    public StorageImpl(){
        customerMap = new HashMap<>();
        orderMap = new HashMap<>();
        serviceMap = new HashMap<>();
        adminMap = new HashMap<>();
        areaMap = new HashMap<>();
        templateMap = new HashMap<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        customer.setID(id++);
        customerMap.put(customer.getID(),customer);
    }

    @Override
    public Customer getCustomer(int id) {
        return customerMap.get(id);
    }

    @Override
    public void deleteCustomer(int id) {
        this.id--;
        customerMap.remove(id);
    }

    @Override
    public void addOrder(Order order) {
        order.setID(id++);
         orderMap.put(order.getID(),order);
    }

    @Override
    public Order getOrder(int id) {
        return orderMap.get(id);
    }

    @Override
    public void deleteOrder(int id) {
        this.id--;
         orderMap.remove(id);
    }

    @Override
    public void addService(Service service) {
        service.setID(id++);
        serviceMap.put(service.getID(), service);
    }

    @Override
    public Service getService(int id) {
        return serviceMap.get(id);
    }

    @Override
    public void deleteService(int id) {
        this.id--;
        serviceMap.remove(id);
    }

    @Override
    public void addAdmin(Admin admin) {
        admin.setID(id++);
        adminMap.put(admin.getID(),admin);
    }

    @Override
    public Admin getAdmin(int id) {
        return adminMap.get(id);
    }

    @Override
    public void deleteAdmin(int id) {
        this.id--;
        adminMap.remove(id);
    }

    @Override
    public void addArea(Area area) {
        area.setID(id++);
        areaMap.put(area.getID(),area);
    }

    @Override
    public Area getArea(int id) {
        return areaMap.get(id);
    }

    @Override
    public void deleteArea(int id) {
        this.id--;
        areaMap.remove(id);
    }

    @Override
    public void addTemplate(Template template) {
        template.setID(id++);
        templateMap.put(template.getID(),template);
    }

    @Override
    public Template getTemplate(int id) {
        return templateMap.get(id);
    }

    @Override
    public void deleteTemplate(int id) {
        this.id--;
        templateMap.remove(id);
    }

    public Service createService(int templateID, int customerID){
        Service newService = new ServiceImpl();
        newService.setCustomer(customerMap.get(customerID));
        newService.setTemplate(templateMap.get(templateID));
        return newService;
    }
}