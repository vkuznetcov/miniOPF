package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.model.order.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
public class OrderUtils
{

    public List<Order> sortOrdersByID(List<Order> values)
    {
        return values.stream().sorted(Comparator.comparingInt(Order::getId)).toList();
    }

    public List<Order> sortOrdersByIDReversed(List<Order> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList();
    }

    public List<Order> sortOrdersByAdminID(List<Order> values)
    {
//        return values.stream().sorted(Comparator.comparing(o -> o.getAdmin().getLogin())).toList();
        return values.stream().sorted(new Comparator<Order>()
        {
            @Override
            public int compare(Order o1, Order o2)
            {
                return o1.getAdmin().getId() - o2.getAdmin().getId();
            }
        }).toList();

    }

    public List<Order> sortOrdersByAdminIDReversed(List<Order> values)
    {
        return values.stream().sorted(new Comparator<Order>()
        {
            @Override
            public int compare(Order o1, Order o2)
            {
                return o2.getAdmin().getId() - o1.getAdmin().getId();
            }
        }).toList();
    }

    public List<Order> sortOrdersByServiceID(List<Order> values)
    {
        return values.stream().sorted(new Comparator<Order>()
        {
            @Override
            public int compare(Order o1, Order o2)
            {
                return o1.getService().getId() - o2.getService().getId();
            }
        }).toList();
    }

    public List<Order> sortOrdersByServiceIDReversed(List<Order> values)
    {
        return values.stream().sorted(new Comparator<Order>()
        {
            @Override
            public int compare(Order o1, Order o2)
            {
                return o2.getService().getId() - o1.getService().getId();
            }
        }).toList();
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

    public Order searchOrderByID(List<Order> values, int id)
    {
//        List<Order> list = new ArrayList<>();
//        for (Order cur : values)
//        {
//            if (cur.getID() == id)
//            {
//                list.add(cur);
//            }
//        }
//        return list;

        for (Order cur : values)
        {
            if (cur.getId() == id)
            {
                return cur;
            }
        }
        return null;
    }

    public List<Order> searchOrdersByAdminID(List<Order> values, int id)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getAdmin().getId() == id)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrdersByServiceID(List<Order> values, int id)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getService().getId() == id)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrdersByStatus(List<Order> values, String status)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getStatus().toString().equals(status.toUpperCase(Locale.ROOT)))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Order> searchOrdersByAction(List<Order> values, String action)
    {
        List<Order> list = new ArrayList<>();
        for (Order cur : values)
        {
            if (cur.getAction().toString().equals(action.toUpperCase(Locale.ROOT)))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
