package com.netcracker.miniOPF.springmvc.admin;

import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.utils.controllers.*;
import com.netcracker.miniOPF.utils.storageUtils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Objects;

@Service
public class AdminDAO
{
    AdminController adminController;
    AreaController areaController;
    CustomerController customerController;
    OrderController orderController;
    ServiceController serviceController;
    TemplateController templateController;

    public static class FormParams
    {
        public static final String TYPE = "type";
        public static final String SORT_ORDER = "sort";
        public static final String SEARCH_VALUE = "valueToSearch";
        public static final String ID = "id";
    }


    @Autowired
    public AdminDAO(AdminController adminController,
                                   AreaController areaController,
                                   CustomerController customerController,
                                   OrderController orderController,
                                   ServiceController serviceController,
                                   TemplateController templateController)
    {
        this.adminController = adminController;
        this.areaController = areaController;
        this.customerController = customerController;
        this.orderController = orderController;
        this.serviceController = serviceController;
        this.templateController = templateController;
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
                                                customerController.searchCustomerByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", customerController.searchCustomersByName(value));
                case "login" -> model.addAttribute("table", customerController.searchCustomerByLogin(value));
                case "password" -> model.addAttribute("table", customerController.searchCustomersByPassword(value));
                case "balance" -> model.addAttribute("table",
                                                     customerController.searchCustomersByBalance(Double.parseDouble(
                                                             value)));
            }
            return "admin/customers";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", customerController.getCustomerValues());
                return "admin/customers";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", customerController.sortCustomersByID());
                    case "name" -> model.addAttribute("table", customerController.sortCustomersByName());
                    case "login" -> model.addAttribute("table", customerController.sortCustomersByLogin());
                    case "password" -> model.addAttribute("table", customerController.sortCustomersByPassword());
                    case "balance" -> model.addAttribute("table", customerController.sortCustomersByBalance());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", customerController.sortCustomersByIDReversed());
                    case "name" -> model.addAttribute("table", customerController.sortCustomersByNameReversed());
                    case "login" -> model.addAttribute("table", customerController.sortCustomersByLoginReversed());
                    case "password" -> model.addAttribute("table",
                                                          customerController.sortCustomersByPasswordReversed());
                    case "balance" -> model.addAttribute("table", customerController.sortCustomersByBalanceReversed());
                }
            }
        }

        return "admin/customers";
    }

    public String showAdmins(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", adminController.searchAdminByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", adminController.searchAdminsByName(value));
                case "login" -> model.addAttribute("table", adminController.searchAdminByLogin(value));
                case "password" -> model.addAttribute("table", adminController.searchAdminsByPassword(value));
            }
            return "admin/admins";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", adminController.getAdminValues());
                return "admin/admins";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", adminController.sortAdminsByID());
                    case "name" -> model.addAttribute("table", adminController.sortAdminsByName());
                    case "login" -> model.addAttribute("table", adminController.sortAdminsByLogin());
                    case "password" -> model.addAttribute("table", adminController.sortAdminsByPassword());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", adminController.sortAdminsByIDReversed());
                    case "name" -> model.addAttribute("table", adminController.sortAdminsByNameReversed());
                    case "login" -> model.addAttribute("table", adminController.sortAdminsByLoginReversed());
                    case "password" -> model.addAttribute("table", adminController.sortAdminsByPasswordReversed());
                }
            }
        }

        return "admin/admins";
    }

    public String showAreas(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                            @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                            @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", areaController.searchAreaByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", areaController.searchAreaByName(value));
                case "description" -> model.addAttribute("table", areaController.searchAreasByDescription(value));
            }
            return "admin/areas";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", areaController.getAreaValues());
                return "admin/areas";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", areaController.sortAreasByID());
                    case "name" -> model.addAttribute("table", areaController.sortAreasByName());
                    case "description" -> model.addAttribute("table", areaController.sortAreasByDescription());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", areaController.sortAreasByIDReversed());
                    case "name" -> model.addAttribute("table", areaController.sortAreasByNameReversed());
                    case "description" -> model.addAttribute("table", areaController.sortAreasByDescriptionReversed());
                }
            }
        }

        return "admin/areas";
    }

    public String showServices(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                               @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                               Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", serviceController.searchServiceByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", serviceController.searchServicesByName(value));
                case "description" -> model.addAttribute("table", serviceController.searchServicesByDescription(value));
                case "price" -> model.addAttribute("table",
                                                   serviceController.searchServicesByPrice(Double.parseDouble(value)));
                case "template" -> model.addAttribute("table",
                                                      serviceController.searchServiceByTemplate(templateController.getTemplate(
                                                              Integer.parseInt(value))));
                case "customer" -> model.addAttribute("table",
                                                      serviceController.searchServicesByCustomer(customerController.getCustomer(
                                                              Integer.parseInt(value))));
                case "status" -> model.addAttribute("table",
                                                    serviceController.searchServicesByStatus(ServiceStatus.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
            }
            return "admin/services";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", serviceController.getServiceValues());
                return "admin/services";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", serviceController.sortServicesByID());
                    case "name" -> model.addAttribute("table", serviceController.sortServicesByName());
                    case "description" -> model.addAttribute("table", serviceController.sortServicesByDescription());
                    case "price" -> model.addAttribute("table", serviceController.sortServicesByPrice());
                    case "template" -> model.addAttribute("table", serviceController.sortServicesByTemplateName());
                    case "customer" -> model.addAttribute("table", serviceController.sortServicesByCustomerLogin());
                    case "status" -> model.addAttribute("table", serviceController.sortServicesByStatus());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", serviceController.sortServicesByIDReversed());
                    case "name" -> model.addAttribute("table", serviceController.sortServicesByNameReversed());
                    case "description" -> model.addAttribute("table",
                                                             serviceController.sortServicesByDescriptionReversed());
                    case "price" -> model.addAttribute("table", serviceController.sortServicesByPriceReversed());
                    case "template" -> model.addAttribute("table",
                                                          serviceController.sortServicesByTemplateNameReversed());
                    case "customer" -> model.addAttribute("table",
                                                          serviceController.sortServicesByCustomerLoginReversed());
                    case "status" -> model.addAttribute("table", serviceController.sortServicesByStatusReversed());
                }
            }
        }

        return "admin/services";
    }

    public String showOrders(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", orderController.searchOrderByID(Integer.parseInt(value)));
                case "admin" -> model.addAttribute("table",
                                                   orderController.searchOrdersByAdmin(adminController.getAdmin(Integer.parseInt(
                                                           value))));
                case "service" -> model.addAttribute("table",
                                                     orderController.searchOrdersByService(serviceController.getService(
                                                             Integer.parseInt(value))));
                case "status" -> model.addAttribute("table",
                                                    orderController.searchOrdersByStatus(OrderStatus.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
                case "action" -> model.addAttribute("table",
                                                    orderController.searchOrdersByAction(OrderAction.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
            }
            return "admin/orders";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", orderController.getOrderValues());
                return "admin/orders";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderController.sortOrdersByID());
                    case "admin" -> model.addAttribute("table",
                                                       orderController.sortOrdersByAdminLogin());
                    case "service" -> model.addAttribute("table",
                                                         orderController.sortOrdersByServiceName());
                    case "status" -> model.addAttribute("table",
                                                        orderController.sortOrdersByStatus());
                    case "action" -> model.addAttribute("table",
                                                        orderController.sortOrdersByAction());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderController.sortOrdersByIDReversed());
                    case "admin" -> model.addAttribute("table",
                                                       orderController.sortOrdersByAdminLoginReversed());
                    case "service" -> model.addAttribute("table",
                                                         orderController.sortOrdersByServiceNameReversed());
                    case "status" -> model.addAttribute("table",
                                                        orderController.sortOrdersByStatusReversed());
                    case "action" -> model.addAttribute("table",
                                                        orderController.sortOrdersByActionReversed());
                }
            }
        }

        return "admin/orders";
    }

    public String showTemplates(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                                Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table",
                                                templateController.searchTemplateByID(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table",
                                                  templateController.searchTemplateByName(value));
                case "description" -> model.addAttribute("table",
                                                         templateController.searchTemplateByDescription(value));
                case "price" -> model.addAttribute("table",
                                                   templateController.searchTemplatesByPrice(Double.parseDouble(value)));
                case "area" -> model.addAttribute("table",
                                                  templateController.searchTemplatesByArea(areaController.getArea(
                                                          Integer.parseInt(value))));
            }
            return "admin/templates";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", orderController.getOrderValues());
                return "admin/templates";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", templateController.sortTemplatesByID());
                    case "name" -> model.addAttribute("table",
                                                      templateController.sortTemplatesByName());
                    case "description" -> model.addAttribute("table",
                                                             templateController.sortTemplatesByDescription());
                    case "price" -> model.addAttribute("table",
                                                       templateController.sortTemplatesByPrice());
                    case "area" -> model.addAttribute("table",
                                                      templateController.sortTemplatesByAreaName());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", templateController.sortTemplatesByIDReversed());
                    case "name" -> model.addAttribute("table",
                                                      templateController.sortTemplatesByNameReversed());
                    case "description" -> model.addAttribute("table",
                                                             templateController.sortTemplatesByDescriptionReversed());
                    case "price" -> model.addAttribute("table",
                                                       templateController.sortTemplatesByPriceReversed());
                    case "area" -> model.addAttribute("table",
                                                      templateController.sortTemplatesByAreaNameReversed());
                }
            }
        }

        return "admin/templates";
    }

    //TODO
    String showMyOrders(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                        @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                        @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        OrderUtils orderUtils = new OrderUtils();
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", orderController.searchOrderByID(Integer.parseInt(value)));
                case "admin" -> model.addAttribute("table",
                                                   orderController.searchOrdersByAdmin(adminController.getAdmin(Integer.parseInt(
                                                           value))));
                case "service" -> model.addAttribute("table",
                                                     orderController.searchOrdersByService(serviceController.getService(
                                                             Integer.parseInt(value))));
                case "status" -> model.addAttribute("table",
                                                    orderController.searchOrdersByStatus(OrderStatus.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
                case "action" -> model.addAttribute("table",
                                                    orderController.searchOrdersByAction(OrderAction.valueOf(value.toUpperCase(
                                                            Locale.ROOT))));
            }
            return "admin/orders";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table",
                                   orderController.searchOrdersByAdmin(adminController.getAdmin(Integer.parseInt(
                                           Objects.requireNonNull(model.getAttribute("userId")).toString()))));
                return "admin/orders";
            }
            case "asc" -> {
                switch (type)
                {

                    case "id" -> model.addAttribute("table", orderUtils.sortOrdersByID(orderController.searchOrdersByAdmin(adminController.getAdmin(Integer.parseInt(
                            Objects.requireNonNull(model.getAttribute("userId")).toString())))));
                    case "admin" -> model.addAttribute("table",
                                                       orderController.sortOrdersByAdminLogin());
                    case "service" -> model.addAttribute("table",
                                                         orderController.sortOrdersByServiceName());
                    case "status" -> model.addAttribute("table",
                                                        orderController.sortOrdersByStatus());
                    case "action" -> model.addAttribute("table",
                                                        orderController.sortOrdersByAction());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", orderController.sortOrdersByIDReversed());
                    case "admin" -> model.addAttribute("table",
                                                       orderController.sortOrdersByAdminLoginReversed());
                    case "service" -> model.addAttribute("table",
                                                         orderController.sortOrdersByServiceNameReversed());
                    case "status" -> model.addAttribute("table",
                                                        orderController.sortOrdersByStatusReversed());
                    case "action" -> model.addAttribute("table",
                                                        orderController.sortOrdersByActionReversed());
                }
            }
        }

        return "admin/orders";
    }
}
