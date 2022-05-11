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
    public String showCustomers(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                Model model)
    {
        model.addAttribute("id", id);
        return customerService.showCustomers(type, sort, value, model);
    }

    @PostMapping("/customers")
    public String updateCustomers(@ModelAttribute("customer") CustomerImpl customer,
                                  @RequestParam(name = "areaId") String areaId,
                                  @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                  Model model)
    {
        model.addAttribute("id", id);
        customerService.updateCustomer(customer, areaId, model);
        return "redirect:/admin/customers?sort=none";
    }

    @PostMapping("/customers/delete")
    public String deleteCustomer(@ModelAttribute("customer") CustomerImpl customer,
                                 @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                 Model model)
    {
        model.addAttribute("id", id);
        customerService.deleteCustomer(customer.getId());
        return "redirect:/admin/customers?sort=none";
    }

    @PostMapping("/customers/create")
    public String createCustomer(@ModelAttribute("customer") CustomerImpl customer,
                                 @RequestParam(name = "areaId") String areaId,
                                 @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                 Model model)
    {
        model.addAttribute("id", id);
        customerService.createCustomer(customer, areaId, model);
        return "redirect:/admin/customers?sort=none";
    }


    //ADMIN LOGIC
    @GetMapping("/admins")
    public String showAdmins(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                             @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                             Model model)
    {
        model.addAttribute("id", id);
        return adminService.showAdmins(type, sort, value, model);
    }

    @PostMapping("/admins")
    public String updateAdmins(@ModelAttribute("admin") AdminImpl admin,
                               @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                               Model model)
    {
        model.addAttribute("id", id);
        adminService.updateAdmin(admin.getId(), admin);
        return "redirect:/admin/admins?sort=none";
    }

    @PostMapping("/admins/delete")
    public String deleteAdmin(@ModelAttribute("admin") AdminImpl admin,
                              @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                              Model model)
    {
        model.addAttribute("id", id);
        adminService.deleteAdmin(admin.getId());
        return "redirect:/admin/admins?sort=none";
    }

    @PostMapping("/admins/create")
    public String createAdmin(@ModelAttribute("admin") AdminImpl admin,
                              @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                              Model model)
    {
        model.addAttribute("id", id);
        adminService.createAdmin(admin);
        return "redirect:/admin/admins?sort=none";
    }


    //AREA LOGIC
    @GetMapping("/areas")
    public String showAreas(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                            @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                            @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                            @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                            Model model)
    {
        Area area = new AreaImpl();
        model.addAttribute("id", id);
        model.addAttribute("area", area);
        return areaService.showAreas(type, sort, value, model);
    }

    @PostMapping("/areas")
    public String updateAreas(@ModelAttribute("area") AreaImpl area,
                              @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                              Model model)
    {
        model.addAttribute("id", id);
        areaService.updateArea(area.getId(), area);
        return "redirect:/admin/areas?sort=none";
    }

    @PostMapping("/areas/delete")
    public String deleteArea(@ModelAttribute("area") AreaImpl area,
                             @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                             Model model)
    {
        model.addAttribute("id", id);
        areaService.deleteArea(area.getId());
        return "redirect:/admin/areas?sort=none";
    }

    @PostMapping("/areas/create")
    public String createArea(@ModelAttribute("area") AreaImpl area,
                             @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                             Model model)
    {
        model.addAttribute("id", id);
        areaService.createArea(area);
        return "redirect:/admin/areas?sort=none";
    }


    //SERVICE LOGIC
    @GetMapping("/services")
    public String showServices(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                               @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                               @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                               Model model)
    {
        model.addAttribute("id", id);
        return serviceService.showServices(type, sort, value, model);
    }

    @PostMapping("/services")
    public String updateServices(@ModelAttribute("service") ServiceImpl service,
                                 @RequestParam(name = "customerId") String customerId,
                                 @RequestParam(name = "templateId") String templateId,
                                 @RequestParam(name = "status") String status,
                                 @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                 Model model)
    {
        model.addAttribute("id", id);
        return serviceService.updateServices(service, customerId, templateId, status, model);
    }

    @PostMapping("/services/delete")
    public String deleteService(@ModelAttribute("area") ServiceImpl service,
                                @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                Model model)
    {
        model.addAttribute("id", id);
        serviceService.deleteService(service.getId());
        return "redirect:/admin/services?sort=none";
    }

    @PostMapping("/services/create")
    public String createService(@ModelAttribute("service") ServiceImpl service,
                                @RequestParam(name = "customerId") String customerId,
                                @RequestParam(name = "templateId") String templateId,
                                @RequestParam(name = "status") String status,
                                @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                Model model)
    {
        model.addAttribute("id", id);
        return serviceService.createService(service, customerId, templateId, status, model);
    }


    //ORDER LOGIC
    @GetMapping("/orders")
    public String showOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                             @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                             Model model)
    {
        model.addAttribute("id", id);
        return orderService.showOrders(type, sort, value, model);
    }

    @PostMapping("/orders")
    public String updateOrders(@ModelAttribute("order") OrderImpl order,
                               @RequestParam(name = "adminId") String adminId,
                               @RequestParam(name = "serviceId") String serviceId,
                               @RequestParam(name = "status") String status,
                               @RequestParam(name = "action") String action,
                               @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                               Model model)
    {
        model.addAttribute("id", id);
        return orderService.updateOrders(order, adminId, serviceId, status, action, model);
    }

    @PostMapping("/orders/delete")
    public String deleteOrder(@ModelAttribute("order") OrderImpl order,
                              @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                              Model model)
    {
        model.addAttribute("id", id);
        orderService.deleteOrder(order.getId());
        return "redirect:/admin/orders?sort=none";
    }

    @PostMapping("/orders/create")
    public String createOrder(@ModelAttribute("order") OrderImpl order,
                              @RequestParam(name = "adminId") String adminId,
                              @RequestParam(name = "serviceId") String serviceId,
                              @RequestParam(name = "status") String status,
                              @RequestParam(name = "action") String action,
                              @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                              Model model)
    {
        model.addAttribute("id", id);
        return orderService.createOrder(order, adminId, serviceId, status, action, model);
    }

    @PostMapping("/orders/startorder")
    public String startOrder(@ModelAttribute("order") OrderImpl order,
                             @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                             Model model)
    {
        model.addAttribute("id", id);
        return orderService.startOrder(order, model);
    }

    @PostMapping("/orders/closeorder")
    public String closeOrder(@ModelAttribute("order") OrderImpl order,
                             @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                             Model model)
    {
        model.addAttribute("id", id);
        return orderService.closeOrder(order, model);
    }


    //TEMPLATE LOGIC
    @GetMapping("/templates")
    public String showTemplates(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                Model model)
    {
        model.addAttribute("id", id);
        return templateService.showTemplates(type, sort, value, model);
    }

    @PostMapping("/templates")
    public String updateTemplates(@ModelAttribute("template") TemplateImpl template,
                                  @RequestParam(name = "areaId") String areaId,
                                  @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                  Model model)
    {
        model.addAttribute("id", id);
        return templateService.updateTemplates(template, areaId, model);
    }

    @PostMapping("/templates/delete")
    public String deleteTemplate(@ModelAttribute("template") TemplateImpl template,
                                 @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                 Model model)
    {
        model.addAttribute("id", id);
        templateService.deleteTemplate(template.getId());
        return "redirect:/admin/templates?sort=none";
    }

    @PostMapping("/templates/create")
    public String createTemplate(@ModelAttribute("template") TemplateImpl template,
                                 @RequestParam(name = "areaId") String areaId,
                                 @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                 Model model)
    {
        model.addAttribute("id", id);
        return templateService.createTemplate(template, areaId, model);
    }


    //MY ORDERS LOGIC
    @GetMapping("/myorders")
    String showMyOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                        @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                        @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                        @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                        Model model)
    {
        model.addAttribute("id", id);
        return orderService.showMyOrders(type, sort, value, model, id);
    }

    @PostMapping("/myorders")
    public String updateMyOrders(@ModelAttribute("order") OrderImpl order,
                                 @RequestParam(name = "adminId") String adminId,
                                 @RequestParam(name = "serviceId") String serviceId,
                                 @RequestParam(name = "status") String status,
                                 @RequestParam(name = "action") String action,
                                 @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                 Model model)
    {
        model.addAttribute("id", id);
        return orderService.updateMyOrders(order, adminId, serviceId, status, action, model, id);
    }

    @PostMapping("/myorders/delete")
    public String deleteMyOrder(@ModelAttribute("order") OrderImpl order,
                                @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                                Model model)
    {
        model.addAttribute("id", id);
        orderService.deleteOrder(order.getId());
        return "redirect:/admin/myorders?sort=none";
    }

    @PostMapping("/orders/startmyorder")
    public String startMyOrder(@ModelAttribute("order") OrderImpl order,
                               @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                               Model model)
    {
        model.addAttribute("id", id);
        return orderService.startMyOrder(order, model, id);
    }

    @PostMapping("/orders/closemyorder")
    public String closeMyOrder(@ModelAttribute("order") OrderImpl order,
                               @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                               Model model)
    {
        model.addAttribute("id", id);
        return orderService.closeMyOrder(order, model, id);
    }

    //SETTINGS LOGIC
    @GetMapping("/settings")
    public String settings(@RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                           Model model)
    {
        model.addAttribute("id", id);
        return null;
    }

    @PostMapping("/settings")
    public String updateUser(@RequestParam(value = AdminService.FormParams.ID, required = false) Integer id,
                           @ModelAttribute("admin") AdminImpl admin,
                           Model model)
    {
        model.addAttribute("id", id);
        adminService.updateAdmin(id, admin);
        return null;
    }


}
