package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.service.ServiceImpl;
import com.netcracker.miniOPF.service.ServiceInt;
import com.netcracker.miniOPF.storage.Storage;
import org.springframework.stereotype.Service;

@Service
public class Controller{
    Storage storage;

    public Controller(Storage storage){
        this.storage = storage;
    }

    public void suspendService(int id){
        storage.getService(id).setStatus(2);
    }

    public void suspendOrder(int id){
        storage.getOrder(id).setAction(3);
    }

    public void resumeService(int id){
        storage.getService(id).setStatus(1);
    }

    public void resumeOrder(int id){
        storage.getOrder(id).setAction(2);
    }

    public void disconnectService(int id){
        storage.getService(id).setStatus(3);
        storage.deleteService(id);
    }

    public void connectService(int templateID, int customerID){
        ServiceInt service = new ServiceImpl();
        service.setCustomer(storage.getCustomer(customerID));
        service.setTemplate(storage.getTemplate(templateID));
        storage.addService(1, service);
    }
}
