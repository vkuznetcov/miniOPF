package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import com.netcracker.miniOPF.utils.storageUtils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SettingsController {
    CustomerRepo customerRepo;
    AreaRepo areaRepo;

    @Autowired
    public SettingsController(CustomerRepo customerRepo,
                              AreaRepo areaRepo)
    {
        this.customerRepo = customerRepo;
        this.areaRepo = areaRepo;
    }

    @GetMapping("/settings")
    public String showSettings(@RequestParam(value = CustomerPageController.FormParams.ID, required = false) Integer id,
                               Model model)
    {
        model.addAttribute("id", id);
        model.addAttribute("username", customerRepo.getCustomer(id).getName());
        model.addAttribute("password", customerRepo.getCustomer(id).getPassword());
        model.addAttribute("area", customerRepo.getCustomer(id).getArea().getName());
        model.addAttribute("errorMessage", "");
        return "settings";
    }
    @PostMapping(value = "/settings")
    public String changeCustomer(@RequestParam(value = CustomerPageController.FormParams.ID, required = false) Integer id,
                                 @RequestParam(name = "username") String username,
                                 @RequestParam(name = "area") String area,
                                 @RequestParam(name = "password") String password,
                                 Model model)
    {
        if (areaRepo.searchAreaByName(area)!= null){
        String errorMessage = null;
        model.addAttribute("id", id);
        Customer customer = customerRepo.getCustomer(id);
        customer.setArea(areaRepo.searchAreaByName(area));
        customer.setPassword(password);
        customer.setName(username);
        customerRepo.updateCustomer(id,customer);
        List<Service> list = customerRepo.getCustomer(id).getServices();
        for(Service service : list){
            if(service.getTemplate().getArea() != customerRepo.getCustomer(id).getArea())
            {
                errorMessage += service.getName();
            }
        }
        if(errorMessage != null)
        {model.addAttribute("errorMessage", "Данные сервисы будут отключены:" + errorMessage);
        return "redirect:/settings?id="+id;}
        else {return "redirect:/customer/customerpage?id="+id;}
        }
        else{model.addAttribute("errorMessage", "Нет такого региона:"+ area);
            return "redirect:/settings?id="+id;}
    }
}
