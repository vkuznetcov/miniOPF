package com.netcracker.miniOPF.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.miniOPF.MiniOpfApplication;
import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.admin.AdminImpl;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.area.AreaImpl;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.customer.CustomerImpl;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.order.OrderImpl;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.ServiceImpl;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.template.TemplateImpl;
import com.netcracker.miniOPF.utils.consts.PathConsts;
import org.springframework.boot.SpringApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConvertMapAndFiles
{
    public static StorageImpl readFile()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ObjectMapper objectMapper = new ObjectMapper();
        StorageImpl storage = context.getBean("storage", StorageImpl.class);
        try (Reader in = new FileReader(PathConsts.ADMIN_PATH);
             Scanner scanner = new Scanner(in))
        {
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                storage.createAdmin(new ObjectMapper().readValue(json, AdminImpl.class));
            }
        }
        catch (IOException e)
        {e.printStackTrace();}
        try (Reader in = new FileReader(PathConsts.AREA_PATH);
             Scanner scanner = new Scanner(in))
             {
             while (scanner.hasNext())
             {
                String json = scanner.nextLine();
                storage.createArea(new ObjectMapper().readValue(json, AreaImpl.class));
             }
             }
        catch (IOException e)
        {e.printStackTrace();}
        try (Reader in = new FileReader(PathConsts.CUSTOMER_PATH);
             Scanner scanner = new Scanner(in))
        {
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                storage.createCustomer(new ObjectMapper().readValue(json, CustomerImpl.class));
            }
        }
        catch (IOException e)
        {e.printStackTrace();}
        try (Reader in = new FileReader(PathConsts.ORDER_PATH);
             Scanner scanner = new Scanner(in))
        {
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                storage.createOrder(new ObjectMapper().readValue(json, OrderImpl.class));
            }
        }
        catch (IOException e)
        {e.printStackTrace();}
        try (Reader in = new FileReader(PathConsts.SERVICE_PATH);
             Scanner scanner = new Scanner(in))
        {
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                storage.createService(new ObjectMapper().readValue(json, ServiceImpl.class));
            }
        }
        catch (IOException e)
        {e.printStackTrace();}
        try (Reader in = new FileReader(PathConsts.TEMPLATE_PATH);
             Scanner scanner = new Scanner(in))
        {
            while (scanner.hasNext())
            {
                String json = scanner.nextLine();
                storage.createTemplate(new ObjectMapper().readValue(json, TemplateImpl.class));
            }
        }
        catch (IOException e)
        {e.printStackTrace();}
        return storage;
    }

    public static void writeFile(StorageImpl storage)
    {
        try(Writer out = new FileWriter(PathConsts.ADMIN_PATH, true))
        {
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
        try(Writer out = new FileWriter(PathConsts.AREA_PATH, true))
        {
            List<Area> listArea = storage.getAreaValues();
            for(Area area : listArea){
                //System.out.println(admin);
                out.write(new ObjectMapper().writeValueAsString(area) + "\n");
            }
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try(Writer out = new FileWriter(PathConsts.CUSTOMER_PATH, true))
        {
            List<Customer> listCustomer = storage.getCustomerValues();
            for(Customer customer : listCustomer){
                //System.out.println(admin);
                out.write(new ObjectMapper().writeValueAsString(customer) + "\n");
            }
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try(Writer out = new FileWriter(PathConsts.ORDER_PATH, true))
        {
            List<Order> listOrder = storage.getOrderValues();
            for(Order order : listOrder){
                //System.out.println(admin);
                out.write(new ObjectMapper().writeValueAsString(order) + "\n");
            }
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try(Writer out = new FileWriter(PathConsts.SERVICE_PATH, true))
        {
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
        try(Writer out = new FileWriter(PathConsts.TEMPLATE_PATH, true))
        {
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
