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
    public void addCustomer(int id, Customer customer) {
         customerMap.put(id,customer);
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
    public void deleteOrder(int id) {
         orderMap.remove(id);
    }

    @Override
    public void addService(int id, Service service) {
          serviceMap.put(id,service);
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
    public void deleteAdmin(int id) {
           adminMap.remove(id);
    }

    @Override
    public void addArea(int id, Area area) {
             areaMap.put(id,area);
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
    public void deleteTemplate(int id) {
              templateMap.remove(id);
    }
}
