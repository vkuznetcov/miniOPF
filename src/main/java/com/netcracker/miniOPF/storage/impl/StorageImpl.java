package com.netcracker.miniOPF.storage.impl;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.impl.ServiceImpl;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.utils.storageUtils.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("storage")
public class StorageImpl implements Storage
{
    private Map<Integer, Customer> customerMap;
    private Map<Integer, Order> orderMap;
    private Map<Integer, Service> serviceMap;
    private Map<Integer, Admin> adminMap;
    private Map<Integer, Area> areaMap;
    private Map<Integer, Template> templateMap;
    public CustomerHandler customerHandler;
    public OrderHandler orderHandler;
    public ServiceHandler serviceHandler;
    public AdminHandler adminHandler;
    public AreaHandler areaHandler;
    public TemplateHandler templateHandler;
    private int id;

    public StorageImpl(HashMap customerMap,HashMap orderMap,HashMap serviceMap,HashMap adminMap,HashMap areaMap,HashMap templateMap)
    {
        this.customerMap = customerMap;
        this.orderMap = orderMap;
        this.serviceMap = serviceMap;
        this.adminMap = adminMap;
        this.areaMap = areaMap;
        this.templateMap = templateMap;

        customerHandler = new CustomerHandler(this.customerMap);

        orderHandler = new OrderHandler(this.orderMap);

        serviceHandler = new ServiceHandler(this.serviceMap);

        adminHandler = new AdminHandler(this.adminMap);

        areaHandler = new AreaHandler(this.areaMap);

        templateHandler = new TemplateHandler(this.templateMap);
    }
    public  void setCustomerMap(HashMap customerMap)
    {
        this.customerMap = customerMap;
    }
    public  void setOrderMap (HashMap orderMap)
    {
        this.orderMap = orderMap;
    }
    public void  setServiceMap (HashMap serviceMap)
    {
        this.serviceMap = serviceMap;
    }
    public void setAdminMap(HashMap adminMap)
    {
        this.adminMap = adminMap;
    }
    public void setAreaMap(HashMap areaMap)
    {
        this.areaMap = areaMap;
    }
    public void setTemplateMap(HashMap templateMap)
    {
        this.templateMap = templateMap;
    }
    private int getNextKey(int id)
    {
        return ++id;
    }

    @Override
    public void createCustomer(Customer customer)
    {
        id = getNextKey(id);
        customer.setID(id);
        customerMap.put(customer.getID(), customer);
    }

    public List<Customer> getCustomerValues(){
        return customerMap.values().stream().toList();
    }

    @Override
    public Customer getCustomer(int id)
    {
        return customerMap.get(id);
    }

    @Override
    public void deleteCustomer(int id)
    {
        customerMap.remove(id);
    }

    @Override
    public void createOrder(Order order)
    {
        id = getNextKey(id);
        order.setID(id);
        orderMap.put(order.getID(), order);
    }

    @Override
    public Order getOrder(int id)
    {
        return orderMap.get(id);
    }

    @Override
    public void deleteOrder(int id)
    {
        orderMap.remove(id);
    }

    @Override
    public void createService(Service service)
    {
        id = getNextKey(id);
        service.setID(id);
        serviceMap.put(service.getID(), service);
    }

    @Override
    public Service getService(int id)
    {
        return serviceMap.get(id);
    }

    @Override
    public void deleteService(int id)
    {
        serviceMap.remove(id);
    }

    @Override
    public void createAdmin(Admin admin)
    {
        id = getNextKey(id);
        admin.setID(id);
        adminMap.put(admin.getID(), admin);
    }

    @Override
    public Admin getAdmin(int id)
    {
        return adminMap.get(id);
    }

    @Override
    public void deleteAdmin(int id)
    {
        adminMap.remove(id);
    }

    @Override
    public void createArea(Area area)
    {
        id = getNextKey(id);
        area.setID(id);
        areaMap.put(area.getID(), area);
    }

    @Override
    public Area getArea(int id)
    {
        return areaMap.get(id);
    }

    @Override
    public void deleteArea(int id)
    {
        areaMap.remove(id);
    }

    @Override
    public void createTemplate(Template template)
    {
        id = getNextKey(id);
        template.setID(id);
        templateMap.put(template.getID(), template);
    }

    @Override
    public Template getTemplate(int id)
    {
        return templateMap.get(id);
    }

    @Override
    public void deleteTemplate(int id)
    {
        templateMap.remove(id);
    }

    public Service createService(int templateID, int customerID)
    {
        Service newService = new ServiceImpl();
        newService.setCustomer(customerMap.get(customerID));
        newService.setTemplate(templateMap.get(templateID));
        return newService;
    }
}
