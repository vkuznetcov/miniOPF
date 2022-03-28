package com.netcracker.miniOPF.springmvc.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdministratorController
{
    AdminDAO adminDAO;

    @Autowired
    public AdministratorController(AdminDAO adminDAO)
    {
        this.adminDAO = adminDAO;
    }

    @GetMapping("/customers")
    public String showCustomers(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                                @RequestParam(value = AdminDAO.FormParams.ID, required = false) Integer id, Model model)
    {
        return adminDAO.showCustomers(type, sort, value, id, model);
    }

    @GetMapping("/admins")
    public String showAdmins(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                             Model model)
    {
        return adminDAO.showAdmins(type, sort, value, model);
    }

    @GetMapping("/areas")
    public String showAreas(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                            @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                            @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                            Model model)
    {
        return adminDAO.showAreas(type, sort, value, model);
    }

    @GetMapping("/services")
    public String showServices(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                               @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                               @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                               Model model)
    {
        return adminDAO.showServices(type, sort, value, model);
    }

    @GetMapping("/orders")
    public String showOrders(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                             Model model)
    {
        return adminDAO.showOrders(type, sort, value, model);
    }

    @GetMapping("/templates")
    public String showTemplates(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                                Model model)
    {
        return adminDAO.showTemplates(type, sort, value, model);
    }

    @GetMapping("/myorders")
    String showMyOrders(@RequestParam(value = AdminDAO.FormParams.TYPE, required = false) String type,
                        @RequestParam(value = AdminDAO.FormParams.SORT_ORDER, required = false) String sort,
                        @RequestParam(value = AdminDAO.FormParams.SEARCH_VALUE, required = false) String value,
                        Model model)
    {
        return adminDAO.showMyOrders(type, sort, value, model);
    }


}
