package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.enums.ServiceStatus;
import com.netcracker.miniOPF.template.Template;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ServiceHandler
{
    private final Map<Integer, Service> serviceMap;

    public ServiceHandler(Map<Integer, Service> serviceMap)
    {
        this.serviceMap = serviceMap;
    }

    public List<Service> sortServicesByID()
    {
        return serviceMap.values().stream().sorted(Comparator.comparingInt(Service::getID)).toList();
    }

    public List<Service> sortServicesByIDReversed()
    {
        return serviceMap.values().stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Service> sortServicesByName()
    {
        return serviceMap.values().stream().sorted(Comparator.comparing(Service::getName)).toList();
    }

    public List<Service> sortServicesByNameReversed()
    {
        return serviceMap.values().stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public List<Service> sortServicesByDescription()
    {
        return serviceMap.values().stream().sorted(Comparator.comparing(Service::getDescription)).toList();
    }

    public List<Service> sortServicesByDescriptionReversed()
    {
        return serviceMap.values()
                         .stream()
                         .sorted((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()))
                         .toList();
    }

    public List<Service> sortServicesByPrice()
    {
        return serviceMap.values().stream().sorted((o1, o2) ->
                                                   {
                                                       if (o1.getPrice() - o2.getPrice() > 0)
                                                       {
                                                           return 1;
                                                       }
                                                       else if (o1.getPrice() == o2.getPrice())
                                                       {
                                                           return 0;
                                                       }
                                                       else
                                                       {
                                                           return -1;
                                                       }
                                                   }).toList();
    }

    public List<Service> sortServicesByPriceReversed()
    {
        return serviceMap.values().stream().sorted((o1, o2) ->
                                                   {
                                                       if (o2.getPrice() - o1.getPrice() > 0)
                                                       {
                                                           return 1;
                                                       }
                                                       else if (o1.getPrice() == o2.getPrice())
                                                       {
                                                           return 0;
                                                       }
                                                       else
                                                       {
                                                           return -1;
                                                       }
                                                   }).toList();
    }

    public List<Service> sortServicesByTemplateName()
    {
        return serviceMap.values()
                         .stream()
                         .sorted(Comparator.comparing(o -> o.getTemplate().getName()))
                         .toList();
    }

    public List<Service> sortServicesByTemplateNameReversed()
    {
        return serviceMap.values()
                         .stream()
                         .sorted((o1, o2) -> o2.getTemplate().getName().compareTo(o1.getTemplate().getName()))
                         .toList();
    }

    public List<Service> sortServicesByCustomerLogin()
    {
        return serviceMap.values()
                         .stream()
                         .sorted(Comparator.comparing(o -> o.getCustomer().getLogin()))
                         .toList();
    }

    public List<Service> sortServicesByCustomerLoginReversed()
    {
        return serviceMap.values()
                         .stream()
                         .sorted((o1, o2) -> o2.getCustomer().getLogin().compareTo(o1.getCustomer().getLogin()))
                         .toList();
    }

    public List<Service> sortServicesByStatus()
    {
        return serviceMap.values().stream().sorted(Comparator.comparing(Service::getStatus)).toList();
    }

    public List<Service> sortServicesByStatusReversed()
    {
        return serviceMap.values()
                         .stream()
                         .sorted((o1, o2) -> o2.getStatus().compareTo(o1.getStatus()))
                         .toList();
    }

    public List<Service> searchServiceByID(int id)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : serviceMap.values())
        {
            if (cur.getID() == id)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServiceByName(String name)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : serviceMap.values())
        {
            if (cur.getName().equals(name))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServiceByDescription(String description)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : serviceMap.values())
        {
            if (cur.getDescription().equals(description))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServiceByPrice(double price)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : serviceMap.values())
        {
            if (cur.getPrice() == price)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServiceByTemplate(Template template)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : serviceMap.values())
        {
            if (cur.getTemplate().equals(template))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServiceByCustomer(Customer customer)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : serviceMap.values())
        {
            if (cur.getCustomer().equals(customer))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServiceByStatus(ServiceStatus status)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : serviceMap.values())
        {
            if (cur.getStatus().equals(status))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
