package com.netcracker.miniOPF.storage;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.template.Template;

import java.util.Map;

public class StorageIpml implements Storage{
    private Map<Integer, Customer> customerMap;
    private Map<Integer, Order> orderMap;
    private Map<Integer, Service> serviceMap;
    private Map<Integer, Admin> adminMap;
    private Map<Integer, Area> areaMap;
    private Map<Integer, Template> templateMap;

    @Override
    public void addCustomer(int key, Customer customer) {
         customerMap.put(key,customer);
    }

    @Override
    public void deleteCustomer(int key) {
        customerMap.remove(key);
    }

    @Override
    public void addOrder(int key, Order order) {
         orderMap.put(key,order);
    }

    @Override
    public void deleteOrder(int key) {
         orderMap.remove(key);
    }

    @Override
    public void addService(int key, Service service) {
          serviceMap.put(key,service);
    }

    @Override
    public void deleteService(int key) {
          serviceMap.remove(key);
    }

    @Override
    public void addAdmin(int key, Admin admin) {
            adminMap.put(key,admin);
    }

    @Override
    public void deleteAdmin(int key) {
           adminMap.remove(key);
    }

    @Override
    public void addArea(int key, Area area) {
             areaMap.put(key,area);
    }

    @Override
    public void deleteArea(int key) {
             areaMap.remove(key);
    }

    @Override
    public void addTemplate(int key, Template template) {
             templateMap.put(key,template);
    }

    @Override
    public void deleteTemplate(int key) {
              templateMap.remove(key);
    }
}
