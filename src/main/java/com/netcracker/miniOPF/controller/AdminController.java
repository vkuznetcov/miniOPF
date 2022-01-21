package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class AdminController
{
    private final Storage storage;
    private final AdminUtils adminUtils;

    @Autowired
    public AdminController(Storage storage,
                           AdminUtils adminUtils
                          )
    {
        this.storage = storage;
        this.adminUtils = adminUtils;
    }

    public List<Admin> sortAdminsByID()
    {
        return adminUtils.sortAdminsByID(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByIDReversed()
    {
        return adminUtils.sortAdminsByIDReversed(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByLogin()
    {
        return adminUtils.sortAdminsByLogin(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByLoginReversed()
    {
        return adminUtils.sortAdminsByLoginReversed(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByName()
    {
        return adminUtils.sortAdminsByName(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByNameReversed()
    {
        return adminUtils.sortAdminsByNameReversed(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByPassword()
    {
        return adminUtils.sortAdminsByPassword(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByPasswordReversed()
    {
        return adminUtils.sortAdminsByPasswordReversed(storage.getAdminValues());
    }

    public Admin searchAdminByID(int ID)
    {
        return adminUtils.searchAdminByID(storage.getAdminValues(), ID);
    }

    public Admin searchAdminByLogin(String login)
    {
        return adminUtils.searchAdminByLogin(storage.getAdminValues(), login);
    }

    public List<Admin> searchAdminsByName(String name)
    {
        return adminUtils.searchAdminsByName(storage.getAdminValues(), name);
    }

    public List<Admin> searchAdminsByPassword(String password)
    {
        return adminUtils.searchAdminsByPassword(storage.getAdminValues(), password);
    }

    public Admin getAdmin(int id)
    {
        return storage.getAdmin(id);
    }

    public List<Admin> getAdminValues()
    {
        return storage.getAdminValues();
    }
}
