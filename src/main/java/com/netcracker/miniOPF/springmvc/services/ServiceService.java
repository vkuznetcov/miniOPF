package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import com.netcracker.miniOPF.utils.repos.ServiceRepo;
import com.netcracker.miniOPF.utils.storageUtils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class ServiceService
{
    ServiceRepo serviceRepo;
    CustomerRepo customerRepo;

    @Autowired
    public ServiceService(ServiceRepo serviceRepo, CustomerRepo customerRepo)
    {
        this.serviceRepo = serviceRepo;
        this.customerRepo = customerRepo;
    }

    public String showServices(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                               @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                               Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", this.getService(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", this.searchServicesByName(value));
                case "description" -> model.addAttribute("table", this.searchServicesByDescription(value));
                case "price" -> model.addAttribute("table",
                                                   this.searchServicesByPrice(Double.parseDouble(value)));
                case "template" -> model.addAttribute("table",
                                                      this.searchServiceByTemplateID(Integer.parseInt(value)));
                case "customer" -> model.addAttribute("table",
                                                      this.searchServicesByCustomerID(Integer.parseInt(value)));
                case "status" -> model.addAttribute("table",
                                                    this.searchServicesByStatus(value.toUpperCase(Locale.ROOT)));
            }
            return "admin/services";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", this.getServiceValues());
                return "admin/services";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", this.sortServicesByID());
                    case "name" -> model.addAttribute("table", this.sortServicesByName());
                    case "description" -> model.addAttribute("table", this.sortServicesByDescription());
                    case "price" -> model.addAttribute("table", this.sortServicesByPrice());
                    case "template" -> model.addAttribute("table", this.sortServicesByTemplateID());
                    case "customer" -> model.addAttribute("table", this.sortServicesByCustomerID());
                    case "status" -> model.addAttribute("table", this.sortServicesByStatus());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", this.sortServicesByIDReversed());
                    case "name" -> model.addAttribute("table", this.sortServicesByNameReversed());
                    case "description" -> model.addAttribute("table",
                                                             this.sortServicesByDescriptionReversed());
                    case "price" -> model.addAttribute("table", this.sortServicesByPriceReversed());
                    case "template" -> model.addAttribute("table",
                                                          this.sortServicesByTemplateIDReversed());
                    case "customer" -> model.addAttribute("table",
                                                          this.sortServicesByCustomerIDReversed());
                    case "status" -> model.addAttribute("table", this.sortServicesByStatusReversed());
                }
            }
        }

        return "admin/services";
    }

    public void suspendService(int id)
    {
        serviceRepo.suspendService(id);
    }

    public void resumeService(int id)
    {
        serviceRepo.resumeService(id);
    }

    public void disconnectService(int id)
    {
        serviceRepo.disconnectService(id);
    }

    public void connectService(int templateID, int customerID)
    {
        serviceRepo.connectService(templateID, customerID);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByID()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByID();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByIDReversed()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByIDReversed();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByName()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByName();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByNameReversed()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByNameReversed();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByDescription()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByDescription();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByDescriptionReversed()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByDescriptionReversed();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByPrice()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByPrice();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByPriceReversed()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByPriceReversed();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByTemplateID()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByTemplateID();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByTemplateIDReversed()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByTemplateIDReversed();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByCustomerID()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByCustomerID();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByCustomerIDReversed()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByCustomerIDReversed();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByStatus()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByStatus();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByStatusReversed()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByStatusReversed();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByName(String name)
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByName(name);
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByDescription(String description)
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByDescription(
                description);
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByPrice(double price)
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByPrice(
                price);
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServiceByTemplateID(int templateID)
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServiceByTemplateID(
                templateID);
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByCustomerID(int customerID)
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByCustomerID(
                customerID);
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByStatus(String status)
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByStatus(
                status);
        for (int i = 0; i < pairs.size(); i++)
        {
            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public com.netcracker.miniOPF.model.service.Service getService(int id)
    {
        Pair<Integer, com.netcracker.miniOPF.model.service.Service> pair = serviceRepo.getService(id);
        com.netcracker.miniOPF.model.service.Service service = pair.getRightValue();
        service.setCustomer(customerRepo.getCustomer(pair.getLeftValue()));

        return service;
    }

    public List<com.netcracker.miniOPF.model.service.Service> getServiceValues()
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.getServiceValues();
        for (int i = 0; i < pairs.size(); i++)
        {

            com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
            service.setCustomer(customerRepo.getCustomer(pairs.get(i).getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public void updateService(int serviceID, com.netcracker.miniOPF.model.service.Service service)
    {
        serviceRepo.updateService(serviceID, service);
    }

    public void deleteService(int id)
    {
        serviceRepo.deleteService(id);
    }
}