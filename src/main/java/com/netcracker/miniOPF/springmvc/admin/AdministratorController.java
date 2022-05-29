package com.netcracker.miniOPF.springmvc.admin;

import com.netcracker.miniOPF.model.admin.AdminImpl;
import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.area.AreaImpl;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
import com.netcracker.miniOPF.model.order.OrderImpl;
import com.netcracker.miniOPF.model.service.ServiceImpl;
import com.netcracker.miniOPF.model.template.TemplateImpl;
import com.netcracker.miniOPF.springmvc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class AdministratorController
{
    AdminService adminService;
    AreaService areaService;
    CustomerService customerService;
    OrderService orderService;
    ServiceService serviceService;
    TemplateService templateService;

    @Autowired
    public AdministratorController(AdminService adminService,
                                   AreaService areaService,
                                   CustomerService customerService,
                                   OrderService orderService,
                                   ServiceService serviceService,
                                   TemplateService templateService)
    {
        this.adminService = adminService;
        this.areaService = areaService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.serviceService = serviceService;
        this.templateService = templateService;
    }


    //CUSTOMER LOGIC
    @GetMapping("/customers")
    public String showCustomers(@RequestParam(value = FormParams.TYPE, required = false) String type,
                                @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                Model model)
    {
        model.addAttribute("userId", userId);
        return customerService.showCustomers(type, sort, value, model);
    }

    @PostMapping("/customers")
    public String updateCustomers(@ModelAttribute("customer") CustomerImpl customer,
                                  @RequestParam(name = "areaId") String areaId,
                                  @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                  Model model)
    {
        model.addAttribute("userId", userId);
        customerService.updateCustomer(customer, areaId, model);
        return customerService.showCustomers(null, "none", null, model);
    }

    @PostMapping("/customers/delete")
    public String deleteCustomer(@ModelAttribute("customer") CustomerImpl customer,
                                 @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                 Model model)
    {
        model.addAttribute("userId", userId);
        customerService.deleteCustomer(customer.getId());
        return customerService.showCustomers(null, "none", null, model);
    }

    @PostMapping("/customers/create")
    public String createCustomer(@ModelAttribute("customer") CustomerImpl customer,
                                 @RequestParam(name = "areaId") String areaId,
                                 @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                 Model model)
    {
        model.addAttribute("userId", userId);
        customerService.createCustomer(customer, areaId, model);
        return customerService.showCustomers(null, "none", null, model);
    }


    //ADMIN LOGIC
    @GetMapping("/admins")
    public String showAdmins(@RequestParam(value = FormParams.TYPE, required = false) String type,
                             @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                             @RequestParam(value = FormParams.ID, required = false) Integer userId,
                             Model model)
    {
        model.addAttribute("userId", userId);
        return adminService.showAdmins(type, sort, value, model);
    }

    @PostMapping("/admins")
    public String updateAdmins(@ModelAttribute("admin") AdminImpl admin,
                               @RequestParam(value = FormParams.ID, required = false) Integer userId,
                               Model model)
    {
        model.addAttribute("userId", userId);
        try
        {
            adminService.updateAdmin(admin.getId(), admin);
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return adminService.showAdmins(null, "none", null, model);
    }

    @PostMapping("/admins/delete")
    public String deleteAdmin(@ModelAttribute("admin") AdminImpl admin,
                              @RequestParam(value = FormParams.ID, required = false) Integer userId,
                              Model model)
    {
        model.addAttribute("userId", userId);
        try
        {
            adminService.deleteAdmin(admin.getId());
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return adminService.showAdmins(null, "none", null, model);
    }

    @PostMapping("/admins/create")
    public String createAdmin(@ModelAttribute("admin") AdminImpl admin,
                              @RequestParam(value = FormParams.ID, required = false) Integer userId,
                              Model model)
    {
        model.addAttribute("userId", userId);
        try
        {
            adminService.createAdmin(admin);
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return adminService.showAdmins(null, "none", null, model);
    }


    //AREA LOGIC
    @GetMapping("/areas")
    public String showAreas(@RequestParam(value = FormParams.TYPE, required = false) String type,
                            @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                            @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                            @RequestParam(value = FormParams.ID, required = false) Integer userId,
                            Model model)
    {
        Area area = new AreaImpl();
        model.addAttribute("userId", userId);
        model.addAttribute("area", area);
        return areaService.showAreas(type, sort, value, model);
    }

    @PostMapping("/areas")
    public String updateAreas(@ModelAttribute("area") AreaImpl area,
                              @RequestParam(value = FormParams.ID, required = false) Integer userId,
                              Model model)
    {
        model.addAttribute("userId", userId);
        areaService.updateArea(area.getId(), area);
        return areaService.showAreas(null, "none", null, model);
    }

    @PostMapping("/areas/delete")
    public String deleteArea(@ModelAttribute("area") AreaImpl area,
                             @RequestParam(value = FormParams.ID, required = false) Integer userId,
                             Model model)
    {
        model.addAttribute("userId", userId);
        areaService.deleteArea(area.getId());
        return areaService.showAreas(null, "none", null, model);
    }

    @PostMapping("/areas/create")
    public String createArea(@ModelAttribute("area") AreaImpl area,
                             @RequestParam(value = FormParams.ID, required = false) Integer userId,
                             Model model)
    {
        model.addAttribute("userId", userId);
        areaService.createArea(area);
        return areaService.showAreas(null, "none", null, model);    }


    //SERVICE LOGIC
    @GetMapping("/services")
    public String showServices(@RequestParam(value = FormParams.TYPE, required = false) String type,
                               @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                               @RequestParam(value = FormParams.ID, required = false) Integer userId,
                               Model model)
    {
        model.addAttribute("userId", userId);
        return serviceService.showServices(type, sort, value, model);
    }

    @PostMapping("/services")
    public String updateServices(@ModelAttribute("service") ServiceImpl service,
                                 @RequestParam(name = "customerId") String customerId,
                                 @RequestParam(name = "templateId") String templateId,
                                 @RequestParam(name = "status") String status,
                                 @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                 Model model)
    {
        model.addAttribute("userId", userId);
        return serviceService.updateServices(service, customerId, templateId, status, model);
    }

    @PostMapping("/services/delete")
    public String deleteService(@ModelAttribute("area") ServiceImpl service,
                                @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                Model model)
    {
        model.addAttribute("userId", userId);
        serviceService.deleteService(service.getId());
        return "redirect:/admin/services?sort=none";
    }

    @PostMapping("/services/create")
    public String createService(@ModelAttribute("service") ServiceImpl service,
                                @RequestParam(name = "customerId") String customerId,
                                @RequestParam(name = "templateId") String templateId,
                                @RequestParam(name = "status") String status,
                                @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                Model model)
    {
        model.addAttribute("userId", userId);
        return serviceService.createService(service, customerId, templateId, status, model);
    }


    //ORDER LOGIC
    @GetMapping("/orders")
    public String showOrders(@RequestParam(value = FormParams.TYPE, required = false) String type,
                             @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                             @RequestParam(value = FormParams.ID, required = false) Integer userId,
                             Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.showOrders(type, sort, value, model);
    }

    @PostMapping("/orders")
    public String updateOrders(@ModelAttribute("order") OrderImpl order,
                               @RequestParam(name = "adminId") String adminId,
                               @RequestParam(name = "serviceId") String serviceId,
                               @RequestParam(name = "status") String status,
                               @RequestParam(name = "action") String action,
                               @RequestParam(value = FormParams.ID, required = false) Integer userId,
                               Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.updateOrders(order, adminId, serviceId, status, action, model);
    }

    @PostMapping("/orders/delete")
    public String deleteOrder(@ModelAttribute("order") OrderImpl order,
                              @RequestParam(value = FormParams.ID, required = false) Integer userId,
                              Model model)
    {
        model.addAttribute("userId", userId);
        orderService.deleteOrder(order.getId());
        return "redirect:/admin/orders?sort=none";
    }

    @PostMapping("/orders/create")
    public String createOrder(@ModelAttribute("order") OrderImpl order,
                              @RequestParam(name = "adminId") String adminId,
                              @RequestParam(name = "serviceId") String serviceId,
                              @RequestParam(name = "status") String status,
                              @RequestParam(name = "action") String action,
                              @RequestParam(value = FormParams.ID, required = false) Integer userId,
                              Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.createOrder(order, adminId, serviceId, status, action, model);
    }

    @PostMapping("/orders/startorder")
    public String startOrder(@ModelAttribute("order") OrderImpl order,
                             @RequestParam(value = FormParams.ID, required = false) Integer userId,
                             Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.startOrder(order, model);
    }

    @PostMapping("/orders/closeorder")
    public String closeOrder(@ModelAttribute("order") OrderImpl order,
                             @RequestParam(value = FormParams.ID, required = false) Integer userId,
                             Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.closeOrder(order, model);
    }


    //TEMPLATE LOGIC
    @GetMapping("/templates")
    public String showTemplates(@RequestParam(value = FormParams.TYPE, required = false) String type,
                                @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                Model model)
    {
        model.addAttribute("userId", userId);
        return templateService.showTemplates(type, sort, value, model);
    }

    @PostMapping("/templates")
    public String updateTemplates(@ModelAttribute("template") TemplateImpl template,
                                  @RequestParam(name = "areaId") String areaId,
                                  @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                  Model model)
    {
        model.addAttribute("userId", userId);
        return templateService.updateTemplates(template, areaId, model);
    }

    @PostMapping("/templates/delete")
    public String deleteTemplate(@ModelAttribute("template") TemplateImpl template,
                                 @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                 Model model)
    {
        model.addAttribute("userId", userId);
        templateService.deleteTemplate(template.getId());
        return "redirect:/admin/templates?sort=none";
    }

    @PostMapping("/templates/create")
    public String createTemplate(@ModelAttribute("template") TemplateImpl template,
                                 @RequestParam(name = "areaId") String areaId,
                                 @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                 Model model)
    {
        model.addAttribute("userId", userId);
        return templateService.createTemplate(template, areaId, model);
    }


    //MY ORDERS LOGIC
    @GetMapping("/myorders")
    String showMyOrders(@RequestParam(value = FormParams.TYPE, required = false) String type,
                        @RequestParam(value = FormParams.SORT_ORDER, required = false) String sort,
                        @RequestParam(value = FormParams.SEARCH_VALUE, required = false) String value,
                        @RequestParam(value = FormParams.ID, required = false) Integer userId,
                        Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.showMyOrders(type, sort, value, model, userId);
    }

    @PostMapping("/myorders")
    public String updateMyOrders(@ModelAttribute("order") OrderImpl order,
                                 @RequestParam(name = "adminId") String adminId,
                                 @RequestParam(name = "serviceId") String serviceId,
                                 @RequestParam(name = "status") String status,
                                 @RequestParam(name = "action") String action,
                                 @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                 Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.updateMyOrders(order, adminId, serviceId, status, action, model, userId);
    }

    @PostMapping("/myorders/delete")
    public String deleteMyOrder(@ModelAttribute("order") OrderImpl order,
                                @RequestParam(value = FormParams.ID, required = false) Integer userId,
                                Model model)
    {
        model.addAttribute("userId", userId);
        orderService.deleteOrder(order.getId());
        return "redirect:/admin/myorders?sort=none";
    }

    @PostMapping("/orders/startmyorder")
    public String startMyOrder(@ModelAttribute("order") OrderImpl order,
                               @RequestParam(value = FormParams.ID, required = false) Integer userId,
                               Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.startMyOrder(order, model, userId);
    }

    @PostMapping("/orders/closemyorder")
    public String closeMyOrder(@ModelAttribute("order") OrderImpl order,
                               @RequestParam(value = FormParams.ID, required = false) Integer userId,
                               Model model)
    {
        model.addAttribute("userId", userId);
        return orderService.closeMyOrder(order, model, userId);
    }

    //SETTINGS LOGIC
    @GetMapping("/settings")
    public String settings(@RequestParam(value = FormParams.ID, required = false) Integer userId,
                           Model model)
    {
        try
        {
            model.addAttribute("self", adminService.getAdmin(userId));
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        model.addAttribute("userId", userId);
        return "/admin/settings";
    }

    @PostMapping("/settings")
    public String updateUser(@RequestParam(value = FormParams.ID, required = false) Integer userId,
                           @ModelAttribute("admin") AdminImpl admin,
                           @RequestParam(value = FormParams.PASSWORD) String newPassword,
                           @RequestParam(value = FormParams.PASSWORD_CONFIRM) String newPasswordConfirm,
                           Model model)
    {
        model.addAttribute("userId", userId);
        return adminService.updateUser(userId, admin, model, newPassword, newPasswordConfirm);
    }


}
