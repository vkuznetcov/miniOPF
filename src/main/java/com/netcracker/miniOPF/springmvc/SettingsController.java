package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import com.netcracker.miniOPF.utils.repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SettingsController
{
    CustomerRepo customerRepo;
    AreaRepo areaRepo;
    ServiceRepo serviceRepo;

    @Autowired
    public SettingsController(CustomerRepo customerRepo,
                              AreaRepo areaRepo,
                              ServiceRepo serviceRepo)
    {
        this.customerRepo = customerRepo;
        this.areaRepo = areaRepo;
        this.serviceRepo = serviceRepo;
    }

    @GetMapping("/settings")
    public String showSettings(@RequestParam(value = CustomerPageController.FormParams.ID, required = false) Integer id,
                               Model model)
    {
        try
        {
            model.addAttribute("id", id);
            model.addAttribute("customer", customerRepo.getCustomer(id));
            model.addAttribute("username", customerRepo.getCustomer(id).getName());
            model.addAttribute("password", customerRepo.getCustomer(id).getPassword());
            List<String> arealist = new ArrayList<>();
            for (Area area : areaRepo.getAreaValues())
            {
                arealist.add(area.getName());
            }
            model.addAttribute("areas", arealist);
            model.addAttribute("area", customerRepo.getCustomer(id).getArea().getName());
            //model.addAttribute("errorMessage", "");
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return "/settings";
    }

    @PostMapping("/settings")
    public String changeCustomer(@RequestParam(value = CustomerPageController.FormParams.ID, required = false) Integer id,
                                 @RequestParam(name = "username") String username,
                                 @RequestParam(name = "area") String userarea,
                                 @RequestParam(name = "password") String password,
                                 Model model)
    {
        try
        {
            String errorMessage = new String();
            model.addAttribute("errorMessage", "");
            Customer customer = customerRepo.getCustomer(id);
            customer.setArea(areaRepo.searchAreaByName(userarea));
            customer.setPassword(password);
            customer.setName(username);
            List<Service> customerlist = customerRepo.getCustomer(id).getServices();
            List<Service> list = new ArrayList<Service>();
            /* TODO ???????????????????? ???????????????? ????????????, ?????????????? ?????????? ?????????????? ?? ?????????????????? ??????????, ?????????? ?? Service ????????????
             *   ?? ???????? ?????????? ???????????????? ?????? ???????? - ???????????????????? ????????????????, ?????????????????? ?????????????? ?????????????? ?? update ?? ????????
             *   ?????????? ?????????????????? ???? ?????????????? ?????? ????????????????
             */
            for (Service service : customerlist)
            {
                if (!service.getTemplate().getArea().getName().equals(userarea) &&
                        !service.getStatus().equals(ServiceStatus.DISCONNECTED))
                {
                    errorMessage += service.getName() + '\n';
                    service.setStatus(ServiceStatus.DISCONNECTED);
                    serviceRepo.updateService(service.getId(), service);
                }
                else
                {
                    list.add(service);
                }
            }
            customer.setServices(list);
            customerRepo.updateCustomer(id, customer);
            if (!errorMessage.isEmpty())
            {
                model.addAttribute("errorMessage", "???????????? ?????????????? ?????????? ??????????????????: " + errorMessage);
            }
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return showSettings(id, model);
    }
}
