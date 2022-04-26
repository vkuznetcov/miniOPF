package com.netcracker.miniOPF.springmvc.admin;

import com.netcracker.miniOPF.model.admin.Admin;
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

import java.util.Objects;

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

    int userID;

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
    public String showCustomers(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                Model model)
    {
        if (Objects.nonNull(id))
        {
            userID = id;
        }
        Admin admin = new AdminImpl();
        model.addAttribute("admin", admin);
        return customerService.showCustomers(type, sort, value, model);
    }

    @PostMapping("/customers")
    public String updateCustomers(@ModelAttribute("customer") CustomerImpl customer,
                                  @RequestParam(name = "areaId") String areaId,
                                  Model model)
    {
        customerService.updateCustomer(customer, areaId, model);
        return "redirect:/admin/customers?sort=none";
    }

    @PostMapping("/customers/delete")
    public String deleteCustomer(@ModelAttribute("customer") CustomerImpl customer)
    {
        customerService.deleteCustomer(customer.getId());
        return "redirect:/admin/customers?sort=none";
    }

    @PostMapping("/customers/create")
    public String createCustomer(@ModelAttribute("customer") CustomerImpl customer,
                                 @RequestParam(name = "areaId") String areaId,
                                 Model model)
    {
        customerService.createCustomer(customer, areaId, model);
        return "redirect:/admin/customers?sort=none";
    }


    //ADMIN LOGIC
    @GetMapping("/admins")
    public String showAdmins(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                             Model model)
    {
        return adminService.showAdmins(type, sort, value, model);
    }

    @PostMapping("/admins")
    public String updateAdmins(@ModelAttribute("admin") AdminImpl admin)
    {
        adminService.updateAdmin(admin.getId(), admin);
        return "redirect:/admin/admins?sort=none";
    }

    @PostMapping("/admins/delete")
    public String deleteAdmin(@ModelAttribute("admin") AdminImpl admin)
    {
        adminService.deleteAdmin(admin.getId());
        return "redirect:/admin/admins?sort=none";
    }

    @PostMapping("/admins/create")
    public String createAdmin(@ModelAttribute("admin") AdminImpl admin)
    {
        adminService.createAdmin(admin);
        return "redirect:/admin/admins?sort=none";
    }


    //AREA LOGIC
    @GetMapping("/areas")
    public String showAreas(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                            @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                            @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                            Model model)
    {
        Area area = new AreaImpl();
        model.addAttribute("area", area);
        return areaService.showAreas(type, sort, value, model);
    }

    @PostMapping("/areas")
    public String updateAreas(@ModelAttribute("area") AreaImpl area)
    {
        areaService.updateArea(area.getId(), area);
        return "redirect:/admin/areas?sort=none";
    }

    @PostMapping("/areas/delete")
    public String deleteArea(@ModelAttribute("area") AreaImpl area)
    {
        areaService.deleteArea(area.getId());
        return "redirect:/admin/areas?sort=none";
    }

    @PostMapping("/areas/create")
    public String createArea(@ModelAttribute("area") AreaImpl area)
    {
        areaService.createArea(area);
        return "redirect:/admin/areas?sort=none";
    }


    //SERVICE LOGIC
    @GetMapping("/services")
    public String showServices(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                               @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                               Model model)
    {
        return serviceService.showServices(type, sort, value, model);
    }

    @PostMapping("/services")
    public String updateServices(@ModelAttribute("service") ServiceImpl service,
                                 @RequestParam(name = "customerId") String customerId,
                                 @RequestParam(name = "templateId") String templateId,
                                 @RequestParam(name = "status") String status,
                                 Model model)
    {
        return serviceService.updateServices(service, customerId, templateId, status, model);
    }

    @PostMapping("/services/delete")
    public String deleteService(@ModelAttribute("area") ServiceImpl service)
    {
        serviceService.deleteService(service.getId());
        return "redirect:/admin/services?sort=none";
    }

    @PostMapping("/services/create")
    public String createService(@ModelAttribute("service") ServiceImpl service,
                                @RequestParam(name = "customerId") String customerId,
                                @RequestParam(name = "templateId") String templateId,
                                @RequestParam(name = "status") String status,
                                Model model)
    {
        return serviceService.createService(service, customerId, templateId, status, model);
    }


    //ORDER LOGIC
    @GetMapping("/orders")
    public String showOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                             Model model)
    {
        return orderService.showOrders(type, sort, value, model);
    }

    @PostMapping("/orders")
    public String updateOrders(@ModelAttribute("order") OrderImpl order,
                               @RequestParam(name = "adminId") String adminId,
                               @RequestParam(name = "serviceId") String serviceId,
                               @RequestParam(name = "status") String status,
                               @RequestParam(name = "action") String action,
                               Model model)
    {
        return orderService.updateOrders(order, adminId, serviceId, status, action, model);
    }

    @PostMapping("/orders/delete")
    public String deleteOrder(@ModelAttribute("order") OrderImpl order)
    {
        orderService.deleteOrder(order.getId());
        return "redirect:/admin/orders?sort=none";
    }

    @PostMapping("/orders/create")
    public String createOrder(@ModelAttribute("order") OrderImpl order,
                              @RequestParam(name = "adminId") String adminId,
                              @RequestParam(name = "serviceId") String serviceId,
                              @RequestParam(name = "status") String status,
                              @RequestParam(name = "action") String action,
                              Model model)
    {
        return orderService.createOrder(order, adminId, serviceId, status, action, model);
    }


    //TEMPLATE LOGIC
    @GetMapping("/templates")
    public String showTemplates(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                Model model)
    {
        return templateService.showTemplates(type, sort, value, model);
    }

    @PostMapping("/templates")
    public String updateTemplates(@ModelAttribute("template") TemplateImpl template,
                                  @RequestParam(name = "areaId") String areaId,
                                  Model model)
    {
        return templateService.updateTemplates(template, areaId, model);
    }

    @PostMapping("/templates/delete")
    public String deleteTemplate(@ModelAttribute("template") TemplateImpl template)
    {
        templateService.deleteTemplate(template.getId());
        return "redirect:/admin/templates?sort=none";
    }

    @PostMapping("/templates/create")
    public String createTemplate(@ModelAttribute("template") TemplateImpl template,
                                 @RequestParam(name = "areaId") String areaId,
                                 Model model)
    {
        return templateService.createTemplate(template, areaId, model);
    }


    //MY ORDERS LOGIC
    @GetMapping("/myorders")
    String showMyOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                        @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                        @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                        Model model)
    {
        return orderService.showMyOrders(type, sort, value, model, userID);
    }

    @PostMapping("/myorders")
    public String updateMyOrders(@ModelAttribute("order") OrderImpl order,
                                 @RequestParam(name = "adminId") String adminId,
                                 @RequestParam(name = "serviceId") String serviceId,
                                 @RequestParam(name = "status") String status,
                                 @RequestParam(name = "action") String action,
                                 Model model)
    {
        return orderService.updateMyOrders(order, adminId, serviceId, status, action, model, userID);
    }

    @PostMapping("/myorders/delete")
    public String deleteMyOrder(@ModelAttribute("order") OrderImpl order)
    {
        orderService.deleteOrder(order.getId());
        return "redirect:/admin/myorders?sort=none";
    }


}
