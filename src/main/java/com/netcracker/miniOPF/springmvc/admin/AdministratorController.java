package com.netcracker.miniOPF.springmvc.admin;

import com.netcracker.miniOPF.springmvc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/customers")
    public String showCustomers(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = AdminService.FormParams.ID, required = false) Integer id, Model model)
    {
        if (Objects.nonNull(id))
        {
            model.addAttribute("userId", id);
        }
        return customerService.showCustomers(type, sort, value, model);
    }

    @GetMapping("/admins")
    public String showAdmins(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                             Model model)
    {
        return adminService.showAdmins(type, sort, value, model);
    }

    @GetMapping("/areas")
    public String showAreas(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                            @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                            @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                            Model model)
    {
        return areaService.showAreas(type, sort, value, model);
    }

    @GetMapping("/services")
    public String showServices(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                               @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                               Model model)
    {
        return serviceService.showServices(type, sort, value, model);
    }

    @GetMapping("/orders")
    public String showOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                             Model model)
    {
        return orderService.showOrders(type, sort, value, model);
    }

    @GetMapping("/templates")
    public String showTemplates(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                Model model)
    {
        return templateService.showTemplates(type, sort, value, model);
    }

    @GetMapping("/myorders")
    String showMyOrders(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                        @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                        @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                        Model model)
    {
        return orderService.showMyOrders(type, sort, value, model);
    }


}
