package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.model.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class CustomerUtils
{

    public List<Customer> sortCustomersByLogin(List<Customer> values)
    {
        return values.stream().sorted(Comparator.comparing(Customer::getLogin)).toList();
    }

    public List<Customer> sortCustomersByLoginReversed(List<Customer> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getLogin().compareTo(o1.getLogin()))
                .toList();
    }

    public List<Customer> sortCustomersByPassword(List<Customer> values)
    {
        return values.stream().sorted(Comparator.comparing(Customer::getPassword)).toList();
    }

    public List<Customer> sortCustomersByPasswordReversed(List<Customer> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getPassword().compareTo(o1.getPassword()))
                .toList();
    }

    public List<Customer> sortCustomersByBalance(List<Customer> values)
    {
        return values.stream().sorted((o1, o2) ->
                                      {
                                          // TODO можно возвращать сразу o1.getBalance()-o2.getBalance
                                          if (o1.getBalance() - o2.getBalance() > 0)
                                          {
                                              return 1;
                                          }
                                          else if (o1.getBalance() == o2.getBalance())
                                          {
                                              return 0;
                                          }
                                          else
                                          {
                                              return -1;
                                          }
                                      }).toList();
    }

    public List<Customer> sortCustomersByBalanceReversed(List<Customer> values)
    {
        return values.stream().sorted((o1, o2) ->
                                      {
                                          if (o2.getBalance() - o1.getBalance() > 0)
                                          {
                                              return 1;
                                          }
                                          else if (o1.getBalance() == o2.getBalance())
                                          {
                                              return 0;
                                          }
                                          else
                                          {
                                              return -1;
                                          }
                                      }).toList();
    }

    public List<Customer> sortCustomersByName(List<Customer> values)
    {
        return values.stream().sorted(Comparator.comparing(Customer::getName)).toList();
    }

    public List<Customer> sortCustomersByNameReversed(List<Customer> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public List<Customer> sortCustomersByID(List<Customer> values)
    {
        return values.stream().sorted(Comparator.comparingInt(Customer::getId)).toList();
    }

    public List<Customer> sortCustomersByIDReversed(List<Customer> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList();
    }

    public Customer searchCustomerByLogin(List<Customer> values, String login)
    {
//        List<Customer> list = new ArrayList<>();
//        for (Customer cur : values)
//        {
//            if (cur.getLogin().equals(login))
//            {
//                list.add(cur);
//            }
//        }
//        return list;

        for (Customer cur : values)
        {
            if (cur.getLogin().equals(login))
            {
                return cur;
            }
        }
        return null;
    }

    public List<Customer> searchCustomersByPassword(List<Customer> values, String password)
    {
        List<Customer> list = new ArrayList<>();
        for (Customer cur : values)
        {
            if (cur.getPassword().equals(password))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Customer> searchCustomersByBalance(List<Customer> values, double balance)
    {
        List<Customer> list = new ArrayList<>();
        for (Customer cur : values)
        {
            if (cur.getBalance() == balance)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Customer> searchCustomersByName(List<Customer> values, String name)
    {
        List<Customer> list = new ArrayList<>();
        for (Customer cur : values)
        {
            if (cur.getName().equals(name))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public Customer searchCustomerByID(List<Customer> values, int id)
    {
//        List<Customer> list = new ArrayList<>();
//        for (Customer cur : values)
//        {
//            if (cur.getID() == id)
//            {
//                list.add(cur);
//            }
//        }
//        return list;

        for (Customer cur : values)
        {
            if (cur.getId() == id)
            {
                return cur;
            }
        }
        return null;
    }
}
