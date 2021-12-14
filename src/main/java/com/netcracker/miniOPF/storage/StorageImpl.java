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
    private int idCustomerMap;
    private int idOrderMap;
    private int idServiceMap;
    private int idAdminMap;
    private int idAreaMap;
    private int idTemplateMap;

    public StorageImpl(){
        customerMap = new HashMap<>();
        orderMap = new HashMap<>();
        serviceMap = new HashMap<>();
        adminMap = new HashMap<>();
        areaMap = new HashMap<>();
        templateMap = new HashMap<>();
        idCustomerMap = 0;
        idOrderMap = 0;
        idServiceMap = 0;
        idAdminMap = 0;
        idAreaMap = 0;
        idTemplateMap = 0;
    }
    private int getNextKey(int id){ return id+1;}
    @Override
    public void createCustomer(Customer customer) {
        idCustomerMap = getNextKey(idCustomerMap);
        customer.setID(idCustomerMap);
        customerMap.put(customer.getID(),customer);
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
    public void createOrder(Order order) {
        idOrderMap = getNextKey(idOrderMap);
        order.setID(idOrderMap);
        orderMap.put(order.getID(),order);
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
    public void createService(Service service) {
        idServiceMap = getNextKey(idServiceMap);
        service.setID(idServiceMap);
        serviceMap.put(service.getID(), service);
    }

    @Override
    public Service getService(int id) {
        return serviceMap.get(id);
    }

    @Override
    public void deleteService(int id) {
        serviceMap.remove(id);
    }

    @Override
    public void createAdmin(Admin admin) {
        idAdminMap = getNextKey(idAdminMap);
        admin.setID(idAdminMap);
        adminMap.put(admin.getID(),admin);
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
    public void createArea(Area area) {
        idAreaMap = getNextKey(idAreaMap);
        area.setID(idAreaMap);
        areaMap.put(area.getID(),area);
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
    public void createTemplate(Template template) {
        idTemplateMap = getNextKey(idTemplateMap);
        template.setID(idTemplateMap);
        templateMap.put(template.getID(),template);
    }

    @Override
    public Template getTemplate(int id) {
        return templateMap.get(id);
    }

    @Override
    public void deleteTemplate(int id) {
        templateMap.remove(id);
    }

    public Service createService(int templateID, int customerID){
        Service newService = new ServiceImpl();
        newService.setCustomer(customerMap.get(customerID));
        newService.setTemplate(templateMap.get(templateID));
        return newService;
    }
}
