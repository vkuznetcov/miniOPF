package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.order.enums.OrderAction;
import com.netcracker.miniOPF.order.enums.OrderStatus;
import com.netcracker.miniOPF.service.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OrderHandler
{

    private final Map<Integer, Order> orderMap;

    public OrderHandler(Map<Integer, Order> orderMap)
    {
        this.orderMap = orderMap;
    }

    public List<Order> sortOrdersByID()
    {
        return orderMap.values().stream().sorted(Comparator.comparingInt(Order::getID)).toList();
    }

    public List<Order> sortOrdersByIDReversed()
    {
        return orderMap.values().stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Order> sortOrdersByAdminLogin()
    {
        return orderMap.values().stream().sorted(Comparator.comparing(o -> o.getAdmin().getLogin())).toList();
    }

    public List<Order> sortOrdersByAdminLoginReversed()
    {
        return orderMap.values()
                       .stream()
                       .sorted((o1, o2) -> o2.getAdmin().getLogin().compareTo(o1.getAdmin().getLogin()))
                       .toList();
    }

    public List<Order> sortOrdersByServiceName()
    {
        return orderMap.values().stream().sorted(Comparator.comparing(o -> o.getService().getName())).toList();
    }

    public List<Order> sortOrdersByServiceNameReversed()
    {
        return orderMap.values()
                       .stream()
                       .sorted((o1, o2) -> o2.getService().getName().compareTo(o1.getService().getName()))
                       .toList();
    }

    public List<Order> sortOrdersByStatus()
    {
        return orderMap.values().stream().sorted(Comparator.comparing(Order::getStatus)).toList();
    }

    public List<Order> sortOrdersByStatusReversed()
    {
        return orderMap.values().stream().sorted((o1, o2) -> o2.getStatus().compareTo(o1.getStatus())).toList();
    }

    public List<Order> sortOrdersByAction()
    {
        return orderMap.values().stream().sorted(Comparator.comparing(Order::getAction)).toList();
    }

    public List<Order> sortOrdersByActionReversed()
    {
        return orderMap.values().stream().sorted((o1, o2) -> o2.getAction().compareTo(o1.getAction())).toList();
    }

    public List<Order> searchOrderByID(int id)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : orderMap.values())
        {
            if (cur.getID() == id)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrderByAdmin(Admin admin)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : orderMap.values())
        {
            if (cur.getAdmin().equals(admin))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrderByService(Service service)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : orderMap.values())
        {
            if (cur.getService().equals(service))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrderByStatus(OrderStatus status)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : orderMap.values())
        {
            if (cur.getStatus().equals(status))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrderByAction(OrderAction action)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : orderMap.values())
        {
            if (cur.getAction().equals(action))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
