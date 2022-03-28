package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.model.template.Template;
import com.netcracker.miniOPF.utils.storageUtils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRepo
{
    private final Storage storage;
    private final ServiceUtils serviceUtils;

    @Autowired
    public ServiceRepo(Storage storage,
                       ServiceUtils serviceUtils
                      )
    {
        this.storage = storage;
        this.serviceUtils = serviceUtils;
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
        storage.getService(id).setStatus(ServiceStatus.DISCONNECTED);
        storage.deleteService(id);
    }

    public void connectService(int templateID, int customerID)
    {

        storage.createService(storage.createService(templateID, customerID));
    }

    public List<Service> sortServicesByID()
    {
        return serviceUtils.sortServicesByID(storage.getServiceValues());
    }

    public List<Service> sortServicesByIDReversed()
    {
        return serviceUtils.sortServicesByIDReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByName()
    {
        return serviceUtils.sortServicesByName(storage.getServiceValues());
    }

    public List<Service> sortServicesByNameReversed()
    {
        return serviceUtils.sortServicesByNameReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByDescription()
    {
        return serviceUtils.sortServicesByDescription(storage.getServiceValues());
    }

    public List<Service> sortServicesByDescriptionReversed()
    {
        return serviceUtils.sortServicesByDescriptionReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByPrice()
    {
        return serviceUtils.sortServicesByPrice(storage.getServiceValues());
    }

    public List<Service> sortServicesByPriceReversed()
    {
        return serviceUtils.sortServicesByPriceReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByTemplateName()
    {
        return serviceUtils.sortServicesByTemplateName(storage.getServiceValues());
    }

    public List<Service> sortServicesByTemplateNameReversed()
    {
        return serviceUtils.sortServicesByTemplateNameReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByCustomerLogin()
    {
        return serviceUtils.sortServicesByCustomerLogin(storage.getServiceValues());
    }

    public List<Service> sortServicesByCustomerLoginReversed()
    {
        return serviceUtils.sortServicesByCustomerLoginReversed(storage.getServiceValues());
    }

    public List<Service> sortServicesByStatus()
    {
        return serviceUtils.sortServicesByStatus(storage.getServiceValues());
    }

    public List<Service> sortServicesByStatusReversed()
    {
        return serviceUtils.sortServicesByStatusReversed(storage.getServiceValues());
    }

    public Service searchServiceByID(
            int id)
    {
        return serviceUtils.searchServiceByID(storage.getServiceValues(), id);
    }

    public List<Service> searchServicesByName(
            String name)
    {
        return serviceUtils.searchServicesByName(storage.getServiceValues(), name);
    }

    public List<Service> searchServicesByDescription(
            String description)
    {
        return serviceUtils.searchServicesByDescription(storage.getServiceValues(), description);
    }

    public List<Service> searchServicesByPrice(
            double price)
    {
        return serviceUtils.searchServicesByPrice(storage.getServiceValues(), price);
    }

    public List<Service> searchServiceByTemplate(
            Template template)
    {
        return serviceUtils.searchServicesByTemplate(storage.getServiceValues(), template);
    }

    public List<Service> searchServicesByCustomer(
            Customer customer)
    {
        return serviceUtils.searchServicesByCustomer(storage.getServiceValues(), customer);
    }

    public List<Service> searchServicesByStatus(
            ServiceStatus status)
    {
        return serviceUtils.searchServicesByStatus(storage.getServiceValues(), status);
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
