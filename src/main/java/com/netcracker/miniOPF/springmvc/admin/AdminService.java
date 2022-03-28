package com.netcracker.miniOPF.springmvc.admin;

import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.utils.repos.*;
import com.netcracker.miniOPF.utils.storageUtils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Objects;

@Service
public class AdminService
{
    AdminRepo adminRepo;
    AreaRepo areaRepo;
    CustomerRepo customerRepo;
    OrderRepo orderRepo;
    ServiceRepo serviceRepo;
    TemplateRepo templateRepo;

    public static class FormParams
    {
        public static final String TYPE = "type";
        public static final String SORT_ORDER = "sort";
        public static final String SEARCH_VALUE = "valueToSearch";
        public static final String ID = "id";
    }


    @Autowired
    public AdminService(AdminRepo adminController,
                        AreaRepo areaController,
                        CustomerRepo customerController,
                        OrderRepo orderController,
                        ServiceRepo serviceController,
                        TemplateRepo templateController)
    {
        this.adminRepo = adminController;
        this.areaRepo = areaController;
        this.customerRepo = customerController;
        this.orderRepo = orderController;
        this.serviceRepo = serviceController;
        this.templateRepo = templateController;
    }

    public String showCustomers(@RequestParam(value = FormParams.TYPE, required = false) String type,
                                @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = FormParams.ID, required = false) Integer id, Model model)
    {
        if (Objects.nonNull(id))
        {
            model.addAttribute("userId", id);
        }
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table",
                                                customerRepo.searchCustomerByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", customerRepo.searchCustomersByName(value));
                case "login" -> model.addAttribute("table", customerRepo.searchCustomerByLogin(value));
                case "password" -> model.addAttribute("table", customerRepo.searchCustomersByPassword(value));
                case "balance" -> model.addAttribute("table",
                                                     customerRepo.searchCustomersByBalance(Double.parseDouble(
                                                             value)));
            }
            return "admin/customers";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", customerRepo.getCustomerValues());
                return "admin/customers";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", customerRepo.sortCustomersByID());
                    case "name" -> model.addAttribute("table", customerRepo.sortCustomersByName());
                    case "login" -> model.addAttribute("table", customerRepo.sortCustomersByLogin());
                    case "password" -> model.addAttribute("table", customerRepo.sortCustomersByPassword());
                    case "balance" -> model.addAttribute("table", customerRepo.sortCustomersByBalance());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", customerRepo.sortCustomersByIDReversed());
                    case "name" -> model.addAttribute("table", customerRepo.sortCustomersByNameReversed());
                    case "login" -> model.addAttribute("table", customerRepo.sortCustomersByLoginReversed());
                    case "password" -> model.addAttribute("table",
                                                          customerRepo.sortCustomersByPasswordReversed());
                    case "balance" -> model.addAttribute("table", customerRepo.sortCustomersByBalanceReversed());
                }
            }
        }

        return "admin/customers";
    }

    public String showAdmins(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", adminRepo.searchAdminByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", adminRepo.searchAdminsByName(value));
                case "login" -> model.addAttribute("table", adminRepo.searchAdminByLogin(value));
                case "password" -> model.addAttribute("table", adminRepo.searchAdminsByPassword(value));
            }
            return "admin/admins";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", adminRepo.getAdminValues());
                return "admin/admins";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", adminRepo.sortAdminsByID());
                    case "name" -> model.addAttribute("table", adminRepo.sortAdminsByName());
                    case "login" -> model.addAttribute("table", adminRepo.sortAdminsByLogin());
                    case "password" -> model.addAttribute("table", adminRepo.sortAdminsByPassword());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", adminRepo.sortAdminsByIDReversed());
                    case "name" -> model.addAttribute("table", adminRepo.sortAdminsByNameReversed());
                    case "login" -> model.addAttribute("table", adminRepo.sortAdminsByLoginReversed());
                    case "password" -> model.addAttribute("table", adminRepo.sortAdminsByPasswordReversed());
                }
            }
        }

        return "admin/admins";
    }

    public String showAreas(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                            @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                            @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", areaRepo.searchAreaByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", areaRepo.searchAreaByName(value));
                case "description" -> model.addAttribute("table", areaRepo.searchAreasByDescription(value));
            }
            return "admin/areas";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", areaRepo.getAreaValues());
                return "admin/areas";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", areaRepo.sortAreasByID());
                    case "name" -> model.addAttribute("table", areaRepo.sortAreasByName());
                    case "description" -> model.addAttribute("table", areaRepo.sortAreasByDescription());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", areaRepo.sortAreasByIDReversed());
                    case "name" -> model.addAttribute("table", areaRepo.sortAreasByNameReversed());
                    case "description" -> model.addAttribute("table", areaRepo.sortAreasByDescriptionReversed());
                }
            }
        }

        return "admin/areas";
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
                case "id" -> model.addAttribute("table", serviceRepo.searchServiceByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", serviceRepo.searchServicesByName(value));
                case "description" -> model.addAttribute("table", serviceRepo.searchServicesByDescription(value));
                case "price" -> model.addAttribute("table",
                                                   serviceRepo.searchServicesByPrice(Double.parseDouble(value)));
                case "template" -> model.addAttribute("table",
                                                      serviceRepo.searchServiceByTemplate(templateRepo.getTemplate(
                                                              Integer.parseInt(value))));
                case "customer" -> model.addAttribute("table",
                                                      serviceRepo.searchServicesByCustomer(customerRepo.getCustomer(
                                                              Integer.parseInt(value))));
                case "status" -> model.addAttribute("table",
                                                    serviceRepo.searchServicesByStatus(ServiceStatus.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
            }
            return "admin/services";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", serviceRepo.getServiceValues());
                return "admin/services";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", serviceRepo.sortServicesByID());
                    case "name" -> model.addAttribute("table", serviceRepo.sortServicesByName());
                    case "description" -> model.addAttribute("table", serviceRepo.sortServicesByDescription());
                    case "price" -> model.addAttribute("table", serviceRepo.sortServicesByPrice());
                    case "template" -> model.addAttribute("table", serviceRepo.sortServicesByTemplateName());
                    case "customer" -> model.addAttribute("table", serviceRepo.sortServicesByCustomerLogin());
                    case "status" -> model.addAttribute("table", serviceRepo.sortServicesByStatus());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", serviceRepo.sortServicesByIDReversed());
                    case "name" -> model.addAttribute("table", serviceRepo.sortServicesByNameReversed());
                    case "description" -> model.addAttribute("table",
                                                             serviceRepo.sortServicesByDescriptionReversed());
                    case "price" -> model.addAttribute("table", serviceRepo.sortServicesByPriceReversed());
                    case "template" -> model.addAttribute("table",
                                                          serviceRepo.sortServicesByTemplateNameReversed());
                    case "customer" -> model.addAttribute("table",
                                                          serviceRepo.sortServicesByCustomerLoginReversed());
                    case "status" -> model.addAttribute("table", serviceRepo.sortServicesByStatusReversed());
                }
            }
        }

        return "admin/services";
    }

    public String showOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", orderRepo.searchOrderByID(Integer.parseInt(value)));
                case "admin" -> model.addAttribute("table",
                                                   orderRepo.searchOrdersByAdmin(adminRepo.getAdmin(Integer.parseInt(
                                                           value))));
                case "service" -> model.addAttribute("table",
                                                     orderRepo.searchOrdersByService(serviceRepo.getService(
                                                             Integer.parseInt(value))));
                case "status" -> model.addAttribute("table",
                                                    orderRepo.searchOrdersByStatus(OrderStatus.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
                case "action" -> model.addAttribute("table",
                                                    orderRepo.searchOrdersByAction(OrderAction.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
            }
            return "admin/orders";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", orderRepo.getOrderValues());
                return "admin/orders";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderRepo.sortOrdersByID());
                    case "admin" -> model.addAttribute("table",
                                                       orderRepo.sortOrdersByAdminLogin());
                    case "service" -> model.addAttribute("table",
                                                         orderRepo.sortOrdersByServiceName());
                    case "status" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByStatus());
                    case "action" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByAction());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderRepo.sortOrdersByIDReversed());
                    case "admin" -> model.addAttribute("table",
                                                       orderRepo.sortOrdersByAdminLoginReversed());
                    case "service" -> model.addAttribute("table",
                                                         orderRepo.sortOrdersByServiceNameReversed());
                    case "status" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByStatusReversed());
                    case "action" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByActionReversed());
                }
            }
        }

        return "admin/orders";
    }

    public String showTemplates(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table",
                                                templateRepo.searchTemplateByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table",
                                                  templateRepo.searchTemplateByName(value));
                case "description" -> model.addAttribute("table",
                                                         templateRepo.searchTemplateByDescription(value));
                case "price" -> model.addAttribute("table",
                                                   templateRepo.searchTemplatesByPrice(Double.parseDouble(value)));
                case "area" -> model.addAttribute("table",
                                                  templateRepo.searchTemplatesByArea(areaRepo.getArea(
                                                          Integer.parseInt(value))));
            }
            return "admin/templates";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", orderRepo.getOrderValues());
                return "admin/templates";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", templateRepo.sortTemplatesByID());
                    case "name" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByName());
                    case "description" -> model.addAttribute("table",
                                                             templateRepo.sortTemplatesByDescription());
                    case "price" -> model.addAttribute("table",
                                                       templateRepo.sortTemplatesByPrice());
                    case "area" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByAreaName());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", templateRepo.sortTemplatesByIDReversed());
                    case "name" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByNameReversed());
                    case "description" -> model.addAttribute("table",
                                                             templateRepo.sortTemplatesByDescriptionReversed());
                    case "price" -> model.addAttribute("table",
                                                       templateRepo.sortTemplatesByPriceReversed());
                    case "area" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByAreaNameReversed());
                }
            }
        }

        return "admin/templates";
    }

    //TODO
    String showMyOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                        @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                        @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        OrderUtils orderUtils = new OrderUtils();
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", orderRepo.searchOrderByID(Integer.parseInt(value)));
                case "admin" -> model.addAttribute("table",
                                                   orderRepo.searchOrdersByAdmin(adminRepo.getAdmin(Integer.parseInt(
                                                           value))));
                case "service" -> model.addAttribute("table",
                                                     orderRepo.searchOrdersByService(serviceRepo.getService(
                                                             Integer.parseInt(value))));
                case "status" -> model.addAttribute("table",
                                                    orderRepo.searchOrdersByStatus(OrderStatus.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
                case "action" -> model.addAttribute("table",
                                                    orderRepo.searchOrdersByAction(OrderAction.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
            }
            return "admin/orders";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table",
                                   orderRepo.searchOrdersByAdmin(adminRepo.getAdmin(Integer.parseInt(
                                           Objects.requireNonNull(model.getAttribute("userId")).toString()))));
                return "admin/orders";
            }
            case "asc" -> {
                switch (type)
                {

                    case "id" -> model.addAttribute("table", orderUtils.sortOrdersByID(orderRepo.searchOrdersByAdmin(
                            adminRepo.getAdmin(Integer.parseInt(
                            Objects.requireNonNull(model.getAttribute("userId")).toString())))));
                    case "admin" -> model.addAttribute("table",
                                                       orderRepo.sortOrdersByAdminLogin());
                    case "service" -> model.addAttribute("table",
                                                         orderRepo.sortOrdersByServiceName());
                    case "status" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByStatus());
                    case "action" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByAction());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderRepo.sortOrdersByIDReversed());
                    case "admin" -> model.addAttribute("table",
                                                       orderRepo.sortOrdersByAdminLoginReversed());
                    case "service" -> model.addAttribute("table",
                                                         orderRepo.sortOrdersByServiceNameReversed());
                    case "status" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByStatusReversed());
                    case "action" -> model.addAttribute("table",
                                                        orderRepo.sortOrdersByActionReversed());
                }
            }
        }

        return "admin/orders";
    }
}
