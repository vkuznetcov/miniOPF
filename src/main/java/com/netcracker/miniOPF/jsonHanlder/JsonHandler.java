package com.netcracker.miniOPF.jsonHanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonHandler
{

    public static <T> void serializeJson(T value, String path)
    {
        try (Writer out = new FileWriter(path, true))
        {
            out.write(new ObjectMapper().writeValueAsString(value) + "\n");
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Admin> deserializeAdmins()
    {
        List<Admin> forReturn = new ArrayList<>();
        try (Reader in = new FileReader(PathConsts.ADMIN_PATH);
             Scanner scanner = new Scanner(in))
        {

            while (scanner.hasNext())
            {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, AdminImpl.class));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return forReturn;
    }

    public static List<Area> deserializeAreas()
    {
        List<Area> forReturn = new ArrayList<>();
        try (Reader in = new FileReader(PathConsts.AREA_PATH);
             Scanner scanner = new Scanner(in))
        {

            while (scanner.hasNext())
            {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, AreaImpl.class));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return forReturn;
    }

    public static List<Customer> deserializeCustomers()
    {
        List<Customer> forReturn = new ArrayList<>();
        try (Reader in = new FileReader(PathConsts.CUSTOMER_PATH);
             Scanner scanner = new Scanner(in))
        {

            while (scanner.hasNext())
            {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, CustomerImpl.class));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return forReturn;
    }

    public static List<Order> deserializeOrders()
    {
        List<Order> forReturn = new ArrayList<>();
        try (Reader in = new FileReader(PathConsts.ORDER_PATH);
             Scanner scanner = new Scanner(in))
        {

            while (scanner.hasNext())
            {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, OrderImpl.class));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return forReturn;
    }

    public static List<Service> deserializeServices()
    {
        List<Service> forReturn = new ArrayList<>();
        try (Reader in = new FileReader(PathConsts.SERVICE_PATH);
             Scanner scanner = new Scanner(in))
        {

            while (scanner.hasNext())
            {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, ServiceImpl.class));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return forReturn;
    }

    public static List<Template> deserializeTemplates()
    {
        List<Template> forReturn = new ArrayList<>();
        try (Reader in = new FileReader(PathConsts.TEMPLATE_PATH);
             Scanner scanner = new Scanner(in))
        {

            while (scanner.hasNext())
            {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, TemplateImpl.class));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return forReturn;
    }
}
