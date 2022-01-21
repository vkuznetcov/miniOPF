package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.enums.ServiceStatus;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.utils.storageUtils.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class ServiceController
{
    private final Storage storage;
    private final ServiceHandler serviceHandler;

    @Autowired
    public ServiceController(Storage storage,
                             ServiceHandler serviceHandler
                            )
    {
        this.storage = storage;
        this.serviceHandler = serviceHandler;
    }

    public void suspendService(int id)
    {
        storage.getService(id).setStatus(ServiceStatus.SUSPENDED);
    }

    public void resumeService(int id)
    {
        storage.getService(id).setStatus(ServiceStatus.ACTIVE);
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

    public List<Service> sortServicesByID()
    {
        return serviceHandler.sortServicesByID(storage.getServiceValues());
    }

    public List<Service> sortServicesByIDReversed()
    {
        return serviceHandler.sortServicesByIDReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByName()
    {
        return serviceHandler.sortServicesByName(storage.getServiceValues());
    }

    public List<Service> sortServicesByNameReversed()
    {
        return serviceHandler.sortServicesByNameReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByDescription()
    {
        return serviceHandler.sortServicesByDescription(storage.getServiceValues());
    }

    public List<Service> sortServicesByDescriptionReversed()
    {
        return serviceHandler.sortServicesByDescriptionReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByPrice()
    {
        return serviceHandler.sortServicesByPrice(storage.getServiceValues());
    }

    public List<Service> sortServicesByPriceReversed()
    {
        return serviceHandler.sortServicesByPriceReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByTemplateName()
    {
        return serviceHandler.sortServicesByTemplateName(storage.getServiceValues());
    }

    public List<Service> sortServicesByTemplateNameReversed()
    {
        return serviceHandler.sortServicesByTemplateNameReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByCustomerLogin()
    {
        return serviceHandler.sortServicesByCustomerLogin(storage.getServiceValues());
    }

    public List<Service> sortServicesByCustomerLoginReversed()
    {
        return serviceHandler.sortServicesByCustomerLoginReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByStatus()
    {
        return serviceHandler.sortServicesByStatus(storage.getServiceValues());
    }

    public List<Service> sortServicesByStatusReversed()
    {
        return serviceHandler.sortServicesByStatusReversed(storage.getServiceValues());
    }

    public List<Service> searchServiceByID(
            int id)
    {
        return serviceHandler.searchServiceByID(storage.getServiceValues(), id);
    }

    public List<Service> searchServiceByName(
            String name)
    {
        return serviceHandler.searchServiceByName(storage.getServiceValues(), name);
    }

    public List<Service> searchServiceByDescription(
            String description)
    {
        return serviceHandler.searchServiceByDescription(storage.getServiceValues(), description);
    }

    public List<Service> searchServiceByPrice(
            double price)
    {
        return serviceHandler.searchServiceByPrice(storage.getServiceValues(), price);
    }

    public List<Service> searchServiceByTemplate(
            Template template)
    {
        return serviceHandler.searchServiceByTemplate(storage.getServiceValues(), template);
    }

    public List<Service> searchServiceByCustomer(
            Customer customer)
    {
        return serviceHandler.searchServiceByCustomer(storage.getServiceValues(), customer);
    }

    public List<Service> searchServiceByStatus(
            ServiceStatus status)
    {
        return serviceHandler.searchServiceByStatus(storage.getServiceValues(), status);
    }

    public Service getService(int id)
    {
        return storage.getService(id);
    }

    public List<Service> getServiceValues()
    {
        return storage.getServiceValues();
    }
}
