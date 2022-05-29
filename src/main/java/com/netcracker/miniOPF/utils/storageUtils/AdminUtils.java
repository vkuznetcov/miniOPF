package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.model.admin.Admin;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class AdminUtils
{
    public List<Admin> sortAdminsByLogin(List<Admin> values, boolean reversed) throws NullPointerException
    {
        if (values == null || values.isEmpty())
        {
            throw new NullPointerException("Sorting empty list");
        }

        if (reversed)
        {
            return values.stream().sorted((o1, o2) -> o2.getLogin().compareTo(o1.getLogin())).toList();

        }
        else
        {
            return values.stream().sorted(Comparator.comparing(Admin::getLogin)).toList();
        }
    }

    public List<Admin> sortAdminsByPassword(List<Admin> values, boolean reversed) throws NullPointerException
    {
        if (values == null || values.isEmpty())
        {
            throw new NullPointerException("Sorting empty list");
        }

        if (reversed)
        {
            return values
                    .stream()
                    .sorted((o1, o2) -> o2.getPassword().compareTo(o1.getPassword()))
                    .toList();
        }
        else
        {
            return values.stream().sorted(Comparator.comparing(Admin::getPassword)).toList();
        }
    }

    public List<Admin> sortAdminsByID(List<Admin> values, boolean reversed) throws NullPointerException
    {
        if (values == null || values.isEmpty())
        {
            throw new NullPointerException("Sorting empty list");
        }

        if (reversed)
        {
            return values.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList();

        }
        else
        {
            return values.stream().sorted(Comparator.comparingInt(Admin::getId)).toList();
        }
    }

    public List<Admin> sortAdminsByName(List<Admin> values, boolean reversed) throws NullPointerException
    {
        if (values == null || values.isEmpty())
        {
            throw new NullPointerException("Sorting empty list");
        }

        if (reversed)
        {
            return values.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
        }
        else
        {
            return values.stream().sorted(Comparator.comparing(Admin::getName)).toList();
        }
    }

    public Admin searchAdminByLogin(List<Admin> values, String login) throws NullPointerException
    {
        if (values == null || values.isEmpty())
        {
            throw new NullPointerException("Searching in empty list");
        }
        return values.stream().filter(i -> i.getLogin().equals(login)).findAny().orElse(null);
    }

    public List<Admin> searchAdminsByPassword(List<Admin> values, String password) throws NullPointerException
    {
        if (values == null || values.isEmpty())
        {
            throw new NullPointerException("Searching in empty list");
        }

        return values.stream().filter(i -> i.getPassword().equals(password)).toList();
    }

    public Admin searchAdminByID(List<Admin> values, int id) throws NullPointerException
    {
        if (values == null || values.isEmpty())
        {
            throw new NullPointerException("Searching in empty list");
        }

        return values.stream().filter(i -> i.getId() == id).findAny().orElse(null);
    }

    public List<Admin> searchAdminsByName(List<Admin> values, String name) throws NullPointerException
    {
        if (values == null || values.isEmpty())
        {
            throw new NullPointerException("Searching in empty list");
        }

        return values.stream().filter(i -> i.getName().equals(name)).toList();
    }
}
