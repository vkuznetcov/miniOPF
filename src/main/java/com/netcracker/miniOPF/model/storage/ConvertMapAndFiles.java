package com.netcracker.miniOPF.model.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.admin.AdminImpl;
import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.area.AreaImpl;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.model.order.OrderImpl;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.service.ServiceImpl;
import com.netcracker.miniOPF.model.storage.StorageImpl;
import com.netcracker.miniOPF.model.template.Template;
import com.netcracker.miniOPF.model.template.TemplateImpl;
import com.netcracker.miniOPF.utils.consts.PathConsts;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConvertMapAndFiles
{
    public static HashMap readFileAdmin()
    {
        try (Reader in = new FileReader(PathConsts.ADMIN_PATH);
             Scanner scanner = new Scanner(in))
        {
            HashMap<Integer, Admin> adminHashMapMap = new HashMap<>();
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                AdminImpl admin = new ObjectMapper().readValue(json, AdminImpl.class);
                adminHashMapMap.put(admin.getID(),admin);
            }
            return adminHashMapMap;
        }
        catch (IOException e)
        {e.printStackTrace(); return null;}
    }
    public static HashMap readFileOrder()
    {
        try (Reader in = new FileReader(PathConsts.ORDER_PATH);
             Scanner scanner = new Scanner(in))
        {
            HashMap<Integer, Order> orderHashMapMap = new HashMap<>();
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                OrderImpl order = new ObjectMapper().readValue(json, OrderImpl.class);
                orderHashMapMap.put(order.getID(),order);
            }
            return orderHashMapMap;
        }
        catch (IOException e)
        {e.printStackTrace(); return null;}
    }
    public static HashMap readFileService()
    {
        try (Reader in = new FileReader(PathConsts.SERVICE_PATH);
             Scanner scanner = new Scanner(in))
        {
            HashMap<Integer, Service> serviceHashMap = new HashMap<>();
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                ServiceImpl service = new ObjectMapper().readValue(json, ServiceImpl.class);
                serviceHashMap.put(service.getID(),service);
            }
            return serviceHashMap;
        }
        catch (IOException e)
        {e.printStackTrace();return null;}
    }
    public static HashMap readFileCustomer()
    {
        try (Reader in = new FileReader(PathConsts.CUSTOMER_PATH);
             Scanner scanner = new Scanner(in))
        {
            HashMap<Integer, Customer> customerHashMap = new HashMap<>();
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                CustomerImpl customer = new ObjectMapper().readValue(json, CustomerImpl.class);
                customerHashMap.put(customer.getID(),customer);
            }
            return  customerHashMap;
        }
        catch (IOException e)
        {e.printStackTrace();return null;}
    }
    public static HashMap readFileArea()
    {
        try (Reader in = new FileReader(PathConsts.AREA_PATH);
             Scanner scanner = new Scanner(in))
        {
            HashMap<Integer, Area> areaHashMap = new HashMap<>();
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                AreaImpl area = new ObjectMapper().readValue(json, AreaImpl.class);
                areaHashMap.put(area.getID(),area);
            }
            return areaHashMap;
        }
        catch (IOException e)
        {e.printStackTrace();return null;}
    }
    public static HashMap readFileTemplate()
    {
        try (Reader in = new FileReader(PathConsts.TEMPLATE_PATH);
             Scanner scanner = new Scanner(in))
        {
            HashMap<Integer, Template> templateHashMap = new HashMap<>();
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                TemplateImpl template = new ObjectMapper().readValue(json, TemplateImpl.class);
                templateHashMap.put(template.getID(),template);
            }
            return templateHashMap;
        }
        catch (IOException e)
        {e.printStackTrace();return null;}
    }
    public static void writeFile(Storage storage)
    {
        try
        {
            new File(PathConsts.ADMIN_PATH).delete();
            Writer out = new FileWriter(PathConsts.ADMIN_PATH, true);
            List<Admin> listAdmin = storage.getAdminValues();
            for(Admin admin : listAdmin){
                out.write(new ObjectMapper().writeValueAsString(admin) + "\n");
            }
           out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            new File(PathConsts.AREA_PATH).delete();
            Writer out = new FileWriter(PathConsts.AREA_PATH, true);
            List<Area> listArea = storage.getAreaValues();
            for(Area area : listArea){
                out.write(new ObjectMapper().writeValueAsString(area) + "\n");
            }
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            new File(PathConsts.CUSTOMER_PATH).delete();
            Writer out = new FileWriter(PathConsts.CUSTOMER_PATH, true);
            List<Customer> listCustomer = storage.getCustomerValues();
            for(Customer customer : listCustomer){
                out.write(new ObjectMapper().writeValueAsString(customer) + "\n");
            }
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            new File(PathConsts.ORDER_PATH).delete();
            Writer out = new FileWriter(PathConsts.ORDER_PATH, true);
            List<Order> listOrder = storage.getOrderValues();
            for(Order order : listOrder){
                out.write(new ObjectMapper().writeValueAsString(order) + "\n");
            }
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            new File(PathConsts.SERVICE_PATH).delete();
            Writer out = new FileWriter(PathConsts.SERVICE_PATH, true);
            List<Service> listService = storage.getServiceValues();
            for(Service service : listService){
                //System.out.println(admin);
                out.write(new ObjectMapper().writeValueAsString(service) + "\n");
            }
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            new File(PathConsts.TEMPLATE_PATH).delete();
            Writer out = new FileWriter(PathConsts.TEMPLATE_PATH, true);
            List<Template> listTemplete = storage.getTemplateValues();
            for(Template templete : listTemplete){
                //System.out.println(admin);
                out.write(new ObjectMapper().writeValueAsString(templete) + "\n");
            }
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
