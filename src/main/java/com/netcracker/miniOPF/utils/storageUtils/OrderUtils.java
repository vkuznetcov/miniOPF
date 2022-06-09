package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.model.order.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class OrderUtils
{

    public List<Order> sortOrdersByID(List<Order> values, boolean reversed)
    {
        ListUtils.checkListIsEmptyOrNull(values);

        if (reversed)
        {
            return values.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList();
        }
        return values.stream().sorted(Comparator.comparingInt(Order::getId)).toList();
    }

    public List<Order> sortOrdersByAdminID(List<Order> values, boolean reversed)
    {
        ListUtils.checkListIsEmptyOrNull(values);

        if (reversed)
        {
            return values.stream().sorted((o1, o2) -> o2.getAdmin().getId() - o1.getAdmin().getId()).toList();
        }
        return values.stream().sorted((o1, o2) -> o1.getAdmin().getId() - o2.getAdmin().getId()).toList();

    }

    public List<Order> sortOrdersByServiceID(List<Order> values, boolean reversed)
    {
        ListUtils.checkListIsEmptyOrNull(values);

        if (reversed)
        {
            return values.stream().sorted((o1, o2) -> o2.getService().getId() - o1.getService().getId()).toList();
        }
        return values.stream().sorted((o1, o2) -> o1.getService().getId() - o2.getService().getId()).toList();
    }

    public List<Order> sortOrdersByStatus(List<Order> values, boolean reversed)
    {
        ListUtils.checkListIsEmptyOrNull(values);

        if (reversed)
        {
            return values.stream()
                         .sorted((o1, o2) -> o2.getStatus().toString().compareTo(o1.getStatus().toString()))
                         .toList();

        }
        return values.stream()
                     .sorted((o1, o2) -> o1.getStatus().toString().compareTo(o2.getStatus().toString()))
                     .toList();
    }

    public List<Order> sortOrdersByAction(List<Order> values, boolean reversed)
    {
        ListUtils.checkListIsEmptyOrNull(values);

        if (reversed)
        {
            return values.stream()
                         .sorted((o1, o2) -> o2.getAction().toString().compareTo(o1.getAction().toString()))
                         .toList();

        }
        return values.stream()
                     .sorted((o1, o2) -> o1.getAction().toString().compareTo(o2.getAction().toString()))
                     .toList();
    }

    public Order searchOrderByID(List<Order> values, int id)
    {
        ListUtils.checkListIsEmptyOrNull(values);
        return values.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public List<Order> searchOrdersByAdminID(List<Order> values, int id)
    {
        ListUtils.checkListIsEmptyOrNull(values);
        return values.stream().filter(i -> i.getAdmin().getId() == id).toList();
    }

    public List<Order> searchOrdersByServiceID(List<Order> values, int id)
    {
        ListUtils.checkListIsEmptyOrNull(values);
        return values.stream().filter(i -> i.getService().getId() == id).toList();
    }

    public List<Order> searchOrdersByStatus(List<Order> values, String status)
    {
        ListUtils.checkListIsEmptyOrNull(values);
        return values.stream().filter(i -> i.getStatus().toString().equals(status)).toList();
    }

    public List<Order> searchOrdersByAction(List<Order> values, String action)
    {
        ListUtils.checkListIsEmptyOrNull(values);
        return values.stream().filter(i -> i.getAction().toString().equals(action)).toList();
    }
}
