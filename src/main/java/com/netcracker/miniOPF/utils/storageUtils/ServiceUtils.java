package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.enums.ServiceStatus;
import com.netcracker.miniOPF.template.Template;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ServiceUtils
{

    public List<Service> sortServicesByID(List<Service> values)
    {
        return values.stream().sorted(Comparator.comparingInt(Service::getID)).toList();
    }

    public List<Service> sortServicesByIDReversed(List<Service> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Service> sortServicesByName(List<Service> values)
    {
        return values.stream().sorted(Comparator.comparing(Service::getName)).toList();
    }

    public List<Service> sortServicesByNameReversed(List<Service> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public List<Service> sortServicesByDescription(List<Service> values)
    {
        return values.stream().sorted(Comparator.comparing(Service::getDescription)).toList();
    }

    public List<Service> sortServicesByDescriptionReversed(List<Service> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()))
                .toList();
    }

    public List<Service> sortServicesByPrice(List<Service> values)
    {
        return values.stream().sorted((o1, o2) ->
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

    public List<Service> sortServicesByPriceReversed(List<Service> values)
    {
        return values.stream().sorted((o1, o2) ->
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

    public List<Service> sortServicesByTemplateName(List<Service> values)
    {
        return values
                .stream()
                .sorted(Comparator.comparing(o -> o.getTemplate().getName()))
                .toList();
    }

    public List<Service> sortServicesByTemplateNameReversed(List<Service> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getTemplate().getName().compareTo(o1.getTemplate().getName()))
                .toList();
    }

    public List<Service> sortServicesByCustomerLogin(List<Service> values)
    {
        return values
                .stream()
                .sorted(Comparator.comparing(o -> o.getCustomer().getLogin()))
                .toList();
    }

    public List<Service> sortServicesByCustomerLoginReversed(List<Service> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getCustomer().getLogin().compareTo(o1.getCustomer().getLogin()))
                .toList();
    }

    public List<Service> sortServicesByStatus(List<Service> values)
    {
        return values.stream().sorted(Comparator.comparing(Service::getStatus)).toList();
    }

    public List<Service> sortServicesByStatusReversed(List<Service> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getStatus().compareTo(o1.getStatus()))
                .toList();
    }

    public Service searchServiceByID(List<Service> values, int id)
    {
//        List<Service> list = new ArrayList<>();
//        for (Service cur : values)
//        {
//            if (cur.getID() == id)
//            {
//                list.add(cur);
//            }
//        }
//        return list;

        for (Service cur : values)
        {
            if (cur.getID() == id)
            {
                return cur;
            }
        }
        return null;
    }

    public List<Service> searchServicesByName(List<Service> values, String name)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : values)
        {
            if (cur.getName().equals(name))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServicesByDescription(List<Service> values, String description)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : values)
        {
            if (cur.getDescription().equals(description))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServicesByPrice(List<Service> values, double price)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : values)
        {
            if (cur.getPrice() == price)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServicesByTemplate(List<Service> values, Template template)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : values)
        {
            if (cur.getTemplate().equals(template))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServicesByCustomer(List<Service> values, Customer customer)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : values)
        {
            if (cur.getCustomer().equals(customer))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Service> searchServicesByStatus(List<Service> values, ServiceStatus status)
    {
        List<Service> list = new ArrayList<>();
        for (Service cur : values)
        {
            if (cur.getStatus().equals(status))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
