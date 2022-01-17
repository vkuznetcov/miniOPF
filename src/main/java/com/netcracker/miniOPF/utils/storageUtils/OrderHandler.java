package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.order.enums.OrderAction;
import com.netcracker.miniOPF.order.enums.OrderStatus;
import com.netcracker.miniOPF.service.Service;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class OrderHandler
{

    public List<Order> sortOrdersByID(List<Order> values)
    {
        return values.stream().sorted(Comparator.comparingInt(Order::getID)).toList();
    }

    public List<Order> sortOrdersByIDReversed(List<Order> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Order> sortOrdersByAdminLogin(List<Order> values)
    {
        return values.stream().sorted(Comparator.comparing(o -> o.getAdmin().getLogin())).toList();
    }

    public List<Order> sortOrdersByAdminLoginReversed(List<Order> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getAdmin().getLogin().compareTo(o1.getAdmin().getLogin()))
                .toList();
    }

    public List<Order> sortOrdersByServiceName(List<Order> values)
    {
        return values.stream().sorted(Comparator.comparing(o -> o.getService().getName())).toList();
    }

    public List<Order> sortOrdersByServiceNameReversed(List<Order> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getService().getName().compareTo(o1.getService().getName()))
                .toList();
    }

    public List<Order> sortOrdersByStatus(List<Order> values)
    {
        return values.stream().sorted(Comparator.comparing(Order::getStatus)).toList();
    }

    public List<Order> sortOrdersByStatusReversed(List<Order> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getStatus().compareTo(o1.getStatus())).toList();
    }

    public List<Order> sortOrdersByAction(List<Order> values)
    {
        return values.stream().sorted(Comparator.comparing(Order::getAction)).toList();
    }

    public List<Order> sortOrdersByActionReversed(List<Order> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getAction().compareTo(o1.getAction())).toList();
    }

    public List<Order> searchOrderByID(List<Order> values, int id)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getID() == id)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrderByAdmin(List<Order> values, Admin admin)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getAdmin().equals(admin))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrderByService(List<Order> values, Service service)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getService().equals(service))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrderByStatus(List<Order> values, OrderStatus status)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getStatus().equals(status))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrderByAction(List<Order> values, OrderAction action)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getAction().equals(action))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
