package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.order.enums.OrderAction;
import com.netcracker.miniOPF.service.enums.ServiceStatus;
import com.netcracker.miniOPF.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@org.springframework.stereotype.Service
@Component("controller")
public class Controller
{
    private final Storage storage;

    @Autowired
    public Controller(Storage storage)
    {
        this.storage = storage;
    }

    public void suspendService(int id)
    {
        storage.getService(id).setStatus(ServiceStatus.SUSPENDED);
    }

    public void suspendOrder(int id)
    {
        storage.getOrder(id).setAction(OrderAction.SUSPEND);
    }

    public void resumeService(int id)
    {
        storage.getService(id).setStatus(ServiceStatus.ACTIVE);
    }

    public void resumeOrder(int id)
    {
        storage.getOrder(id).setAction(OrderAction.RESUME);
    }

    public void disconnectService(int id)
    {
        storage.getService(id).setStatus(ServiceStatus.DISCONNECCTED);
        storage.deleteService(id);
    }

    public void connectService(int templateID, int customerID)
    {

        storage.createService(storage.createService(templateID, customerID));
    }
}
