package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.model.admin.Admin;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class AdminUtils
{
    public List<Admin> sortAdminsByLogin(List<Admin> values)
    {
        return values.stream().sorted(Comparator.comparing(Admin::getLogin)).toList();
    }

    public List<Admin> sortAdminsByLoginReversed(List<Admin> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getLogin().compareTo(o1.getLogin())).toList();
    }

    public List<Admin> sortAdminsByPassword(List<Admin> values)
    {
        return values.stream().sorted(Comparator.comparing(Admin::getPassword)).toList();
    }

    public List<Admin> sortAdminsByPasswordReversed(List<Admin> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getPassword().compareTo(o1.getPassword()))
                .toList();
    }

    public List<Admin> sortAdminsByID(List<Admin> values)
    {
        return values.stream().sorted(Comparator.comparingInt(Admin::getId)).toList();
    }

    public List<Admin> sortAdminsByIDReversed(List<Admin> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList();
    }

    public List<Admin> sortAdminsByName(List<Admin> values)
    {
        return values.stream().sorted(Comparator.comparing(Admin::getName)).toList();
    }

    public List<Admin> sortAdminsByNameReversed(List<Admin> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public Admin searchAdminByLogin(List<Admin> values, String login)
    {
//        List<Admin> list = new ArrayList<>();
//        for (Admin cur : values)
//        {
//            if (cur.getLogin().equals(login))
//            {
//                list.add(cur);
//            }
//        }
//        return list;
        // TODO можно сделать короче values.stream().filter()
        for (Admin cur : values)
        {
            if (cur.getLogin().equals(login))
            {
                return cur;
            }
        }
        return null;
    }

    public List<Admin> searchAdminsByPassword(List<Admin> values, String password)
    {
        List<Admin> list = new ArrayList<>();
        for (Admin cur : values)
        {
            if (cur.getPassword().equals(password))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public Admin searchAdminByID(List<Admin> values, int id)
    {
//        List<Admin> list = new ArrayList<>();
//        for (Admin cur : values)
//        {
//            if (cur.getID() == id)
//            {
//                list.add(cur);
//            }
//        }
//        return list;

        for (Admin cur : values)
        {
            if (cur.getId() == id)
            {
                return cur;
            }
        }
        return null;
    }

    public List<Admin> searchAdminsByName(List<Admin> values, String name)
    {
        List<Admin> list = new ArrayList<>();
        for (Admin cur : values)
        {
            if (cur.getName().equals(name))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
