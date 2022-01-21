package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.AdminHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class AdminController
{
    private final Storage storage;
    private final AdminHandler adminHandler;

    @Autowired
    public AdminController(Storage storage,
                           AdminHandler adminHandler
                          )
    {
        this.storage = storage;
        this.adminHandler = adminHandler;
    }

    public List<Admin> sortAdminsByID()
    {
        return adminHandler.sortAdminsByID(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByIDReversed()
    {
        return adminHandler.sortAdminsByIDReversed(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByLogin()
    {
        return adminHandler.sortAdminsByLogin(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByLoginReversed()
    {
        return adminHandler.sortAdminsByLoginReversed(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByName()
    {
        return adminHandler.sortAdminsByName(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByNameReversed()
    {
        return adminHandler.sortAdminsByNameReversed(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByPassword()
    {
        return adminHandler.sortAdminsByPassword(storage.getAdminValues());
    }

    public List<Admin> sortAdminsByPasswordReversed()
    {
        return adminHandler.sortAdminsByPasswordReversed(storage.getAdminValues());
    }

    public List<Admin> searchAdminsByID(int ID)
    {
        return adminHandler.searchAdminsByID(storage.getAdminValues(), ID);
    }

    public List<Admin> searchAdminsByLogin(String login)
    {
        return adminHandler.searchAdminsByLogin(storage.getAdminValues(), login);
    }

    public List<Admin> searchAdminsByName(String name)
    {
        return adminHandler.searchAdminsByName(storage.getAdminValues(), name);
    }

    public List<Admin> searchAdminsByPassword(String password)
    {
        return adminHandler.searchAdminsByPassword(storage.getAdminValues(), password);
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
