package com.netcracker.miniOPF.jsonHanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.admin.impl.AdminImpl;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.area.impl.AreaImpl;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.customer.impl.CustomerImpl;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.order.impl.OrderImpl;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.impl.ServiceImpl;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.template.impl.TemplateImpl;
import com.netcracker.miniOPF.utils.PathConsts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonHandler
{

    public static <T> void serializeJson(T value)
    {
        try
        {
            String path = null;
            if (value instanceof Admin)
            {
                path = PathConsts.ADMIN_PATH;
            }
            else if (value instanceof Area)
            {
                path = PathConsts.AREA_PATH;
            }
            else if (value instanceof Customer)
            {
                path = PathConsts.CUSTOMER_PATH;
            }
            else if (value instanceof Order)
            {
                path = PathConsts.ORDER_PATH;
            }
            else if (value instanceof Service)
            {
                path = PathConsts.SERVICE_PATH;
            }
            else if (value instanceof Template)
            {
                path = PathConsts.TEMPLATE_PATH;
            }

            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(value);

            assert path != null;
            Writer out = new FileWriter(path, true);
            out.write(json);
            out.write("\n");
            out.flush();
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<?> deserializeJson(EntityType type)
    {
        return switch (type)
                {
                    case ADMIN -> JsonHandler.deserializeAdmin();
                    case AREA -> JsonHandler.deserializeArea();
                    case CUSTOMER -> JsonHandler.deserializeCustomer();
                    case ORDER -> JsonHandler.deserializeOrder();
                    case SERVICE -> JsonHandler.deserializeService();
                    case TEMPLATE -> JsonHandler.deserializeTemplate();
                };
    }

    private static List<Admin> deserializeAdmin()
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

    private static List<Area> deserializeArea()
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

    private static List<Customer> deserializeCustomer()
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

    private static List<Order> deserializeOrder()
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

    private static List<Service> deserializeService()
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

    private static List<Template> deserializeTemplate()
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

    public enum EntityType
    {
        ADMIN,
        AREA,
        CUSTOMER,
        ORDER,
        SERVICE,
        TEMPLATE
    }
}
