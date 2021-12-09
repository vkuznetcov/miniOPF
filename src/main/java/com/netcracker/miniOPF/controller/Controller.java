package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.order.OrderAction;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.ServiceImpl;
import com.netcracker.miniOPF.service.ServiceStatus;
import com.netcracker.miniOPF.storage.Storage;

@org.springframework.stereotype.Service
public class Controller{
    Storage storage;

    public Controller(Storage storage){
        this.storage = storage;
    }

    public void suspendService(int id){
        storage.getService(id).setStatus(ServiceStatus.suspended);
    }

    public void suspendOrder(int id){
        storage.getOrder(id).setAction(OrderAction.suspend);
    }

    public void resumeService(int id){
        storage.getService(id).setStatus(ServiceStatus.active);
    }

    public void resumeOrder(int id){
        storage.getOrder(id).setAction(OrderAction.resume);
    }

    public void disconnectService(int id){
        storage.getService(id).setStatus(ServiceStatus.disconnected);
        storage.deleteService(id);
    }

    public void connectService(int templateID, int customerID){

        storage.addService(storage.createService(templateID, customerID));
    }
}
