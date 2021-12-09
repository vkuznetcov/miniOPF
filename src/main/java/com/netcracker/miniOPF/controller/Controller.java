package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.ServiceImpl;
import com.netcracker.miniOPF.storage.Storage;

@org.springframework.stereotype.Service
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

        storage.addService(storage.createService(templateID, customerID));
    }
}
