package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.service.ServiceImpl;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import com.netcracker.miniOPF.utils.repos.ServiceRepo;
import com.netcracker.miniOPF.utils.repos.TemplateRepo;
import com.netcracker.miniOPF.utils.storageUtils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class ServiceService
{
    ServiceRepo serviceRepo;
    CustomerRepo customerRepo;
    TemplateRepo templateRepo;

    @Autowired
    public ServiceService(ServiceRepo serviceRepo, CustomerRepo customerRepo, TemplateRepo templateRepo)
    {
        this.serviceRepo = serviceRepo;
        this.customerRepo = customerRepo;
        this.templateRepo = templateRepo;
    }

    public String showServices(String type, String sort, String value, Model model)
    {

        List<String> statuses = new ArrayList<>();
        statuses.add("ENTERING");
        statuses.add("ACTIVE");
        statuses.add("SUSPENDED");
        statuses.add("DISCONNECTED");
        model.addAttribute("statuses", statuses);
        try
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
        }
        catch (SQLException e){
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return "admin/services";
    }

    private boolean checkService(String customerId, String templateId, StringBuilder errorMessage) throws SQLException
    {
        boolean error = false;
        if (customerRepo.getCustomer(Integer.parseInt(customerId)) == null)
        {
            errorMessage.append("There is no such customer! ");
            error = true;
        }
        if (templateRepo.getTemplate(Integer.parseInt(templateId)) == null)
        {
            errorMessage.append("There is no such template! ");
            error = true;
        }
        return error;
    }

    public String updateServices(ServiceImpl service, String customerId, String templateId, String status, Model model)
    {
        try
        {
            String errorMessage = "";
            StringBuilder stringBuilder = new StringBuilder(errorMessage);
            if (checkService(customerId, templateId, stringBuilder))
            {
                stringBuilder.append("Error index: ").append(service.getId());
                model.addAttribute("errorMessage", stringBuilder.toString());
            }
            else
            {
                service.setCustomer(customerRepo.getCustomer(Integer.parseInt(customerId)));
                service.setTemplate(templateRepo.getTemplate(Integer.parseInt(templateId)));
                service.setStatus(ServiceStatus.valueOf(status));
                serviceRepo.updateService(service.getId(), service);
            }
        }
        catch (SQLException e){
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return this.showServices(null, "none", null, model);
    }

    public String createService(ServiceImpl service, String customerId, String templateId, String status, Model model)
    {
        try
        {
            String errorMessage = "";
            StringBuilder stringBuilder = new StringBuilder(errorMessage);
            if (checkService(customerId, templateId, stringBuilder))
            {
                stringBuilder.append("Error index: new object creation");
                model.addAttribute("errorMessage", stringBuilder.toString());
            }
            else
            {
                service.setCustomer(customerRepo.getCustomer(Integer.parseInt(customerId)));
                service.setTemplate(templateRepo.getTemplate(Integer.parseInt(templateId)));
                service.setStatus(ServiceStatus.valueOf(status));
                serviceRepo.createService(service);
            }
        }
        catch (SQLException e){
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return this.showServices(null, "none", null, model);
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

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByID() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByID();
        return fillServices(services, pairs);
    }

    private List<com.netcracker.miniOPF.model.service.Service> fillServices(List<com.netcracker.miniOPF.model.service.Service> services,
                                                                            List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs)
            throws SQLException
    {
        for (Pair<Integer, com.netcracker.miniOPF.model.service.Service> pair : pairs)
        {
            com.netcracker.miniOPF.model.service.Service service = pair.getRightValue();
            service.setCustomer(customerRepo.getCustomer(pair.getLeftValue()));
            services.add(service);
        }
        return services;
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByIDReversed() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByIDReversed();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByName() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByName();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByNameReversed() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByNameReversed();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByDescription() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByDescription();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByDescriptionReversed() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByDescriptionReversed();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByPrice() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByPrice();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByPriceReversed() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByPriceReversed();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByTemplateID() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByTemplateID();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByTemplateIDReversed() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByTemplateIDReversed();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByCustomerID() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByCustomerID();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByCustomerIDReversed() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByCustomerIDReversed();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByStatus() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByStatus();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> sortServicesByStatusReversed() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.sortServicesByStatusReversed();
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByName(String name) throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByName(name);
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByDescription(String description)
            throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByDescription(
                description);
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByPrice(double price) throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByPrice(
                price);
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServiceByTemplateID(int templateID)
            throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServiceByTemplateID(
                templateID);
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByCustomerID(int customerID)
            throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByCustomerID(
                customerID);
        return fillServices(services, pairs);
    }

    public List<com.netcracker.miniOPF.model.service.Service> searchServicesByStatus(String status) throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.searchServicesByStatus(
                status);
        return fillServices(services, pairs);
    }

    public com.netcracker.miniOPF.model.service.Service getService(int id) throws SQLException
    {
        Pair<Integer, com.netcracker.miniOPF.model.service.Service> pair = serviceRepo.getService(id);
        if (pair == null)
        {
            return null;
        }
        com.netcracker.miniOPF.model.service.Service service = pair.getRightValue();
        service.setCustomer(customerRepo.getCustomer(pair.getLeftValue()));

        return service;
    }

    public List<com.netcracker.miniOPF.model.service.Service> getServiceValues() throws SQLException
    {
        List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
        List<Pair<Integer, com.netcracker.miniOPF.model.service.Service>> pairs = serviceRepo.getServiceValues();
        return fillServices(services, pairs);
    }

    public void updateService(int serviceID, com.netcracker.miniOPF.model.service.Service service)
    {
        serviceRepo.updateService(serviceID, service);
    }

    public void deleteService(int id)
    {
        serviceRepo.deleteService(id);
    }

    public void createService(com.netcracker.miniOPF.model.service.Service service)
    {
        serviceRepo.createService(service);
    }
}
