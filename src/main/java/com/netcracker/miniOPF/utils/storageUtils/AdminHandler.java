package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.admin.Admin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AdminHandler
{

    private final Map<Integer, Admin> adminMap;

    public AdminHandler(Map<Integer, Admin> adminMap)
    {
        this.adminMap = adminMap;
    }

    public List<Admin> sortAdminsByLogin()
    {
        return adminMap.values().stream().sorted(Comparator.comparing(Admin::getLogin)).toList();
    }

    public List<Admin> sortAdminsByLoginReversed()
    {
        return adminMap.values().stream().sorted((o1, o2) -> o2.getLogin().compareTo(o1.getLogin())).toList();
    }

    public List<Admin> sortAdminsByPassword()
    {
        return adminMap.values().stream().sorted(Comparator.comparing(Admin::getPassword)).toList();
    }

    public List<Admin> sortAdminsByPasswordReversed()
    {
        return adminMap.values()
                       .stream()
                       .sorted((o1, o2) -> o2.getPassword().compareTo(o1.getPassword()))
                       .toList();
    }

    public List<Admin> sortAdminsByID()
    {
        return adminMap.values().stream().sorted(Comparator.comparingInt(Admin::getID)).toList();
    }

    public List<Admin> sortAdminsByIDReversed()
    {
        return adminMap.values().stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Admin> sortAdminsByName()
    {
        return adminMap.values().stream().sorted(Comparator.comparing(Admin::getName)).toList();
    }

    public List<Admin> sortAdminsByNameReversed()
    {
        return adminMap.values().stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public List<Admin> searchAdminByLogin(String login)
    {
        List<Admin> list = new ArrayList<>();
        for (Admin cur : adminMap.values())
        {
            if (cur.getLogin().equals(login))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Admin> searchAdminByPassword(String password)
    {
        List<Admin> list = new ArrayList<>();
        for (Admin cur : adminMap.values())
        {
            if (cur.getPassword().equals(password))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Admin> searchAdminByID(int id)
    {
        List<Admin> list = new ArrayList<>();
        for (Admin cur : adminMap.values())
        {
            if (cur.getID() == id)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Admin> searchAdminByName(String name)
    {
        List<Admin> list = new ArrayList<>();
        for (Admin cur : adminMap.values())
        {
            if (cur.getName().equals(name))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
