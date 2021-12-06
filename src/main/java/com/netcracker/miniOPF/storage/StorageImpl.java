package com.netcracker.miniOPF.storage;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.service.ServiceInt;
import com.netcracker.miniOPF.template.Template;

import java.util.HashMap;
import java.util.Map;

public class StorageImpl implements Storage{
    private Map<Integer, Customer> customerMap;
    private Map<Integer, Order> orderMap;
    private Map<Integer, ServiceInt> serviceMap;
    private Map<Integer, Admin> adminMap;
    private Map<Integer, Area> areaMap;
    private Map<Integer, Template> templateMap;

    public StorageImpl(){
        customerMap = new HashMap<>();
        orderMap = new HashMap<>();
        serviceMap = new HashMap<>();
        adminMap = new HashMap<>();
        areaMap = new HashMap<>();
        templateMap = new HashMap<>();
    }

    @Override
    public void addCustomer(int id, Customer customer) {
         customerMap.put(id,customer);
    }

    @Override
    public Customer getCustomer(int id) {
        return customerMap.get(id);
    }

    @Override
    public void deleteCustomer(int id) {
        customerMap.remove(id);
    }

    @Override
    public void addOrder(int id, Order order) {
         orderMap.put(id,order);
    }

    @Override
    public Order getOrder(int id) {
        return orderMap.get(id);
    }

    @Override
    public void deleteOrder(int id) {
         orderMap.remove(id);
    }

    @Override
    public void addService(int id, ServiceInt serviceInt) {
        serviceMap.put(id, serviceInt);
    }

    @Override
    public ServiceInt getService(int id) {
        return serviceMap.get(id);
    }

    @Override
    public void deleteService(int id) {
          serviceMap.remove(id);
    }

    @Override
    public void addAdmin(int id, Admin admin) {
            adminMap.put(id,admin);
    }

    @Override
    public Admin getAdmin(int id) {
        return adminMap.get(id);
    }

    @Override
    public void deleteAdmin(int id) {
           adminMap.remove(id);
    }

    @Override
    public void addArea(int id, Area area) {
             areaMap.put(id,area);
    }

    @Override
    public Area getArea(int id) {
        return areaMap.get(id);
    }

    @Override
    public void deleteArea(int id) {
             areaMap.remove(id);
    }

    @Override
    public void addTemplate(int id, Template template) {
             templateMap.put(id,template);
    }

    @Override
    public Template getTemplate(int id) {
        return templateMap.get(id);
    }

    @Override
    public void deleteTemplate(int id) {
        templateMap.remove(id);
    }
}
