package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.admin.AdminImpl;
import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.utils.repos.AdminRepo;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
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
public class RegistrationController
{

    CustomerRepo customerRepo;
    AdminRepo adminRepo;
    AreaRepo areaRepo;

    @Autowired
    public RegistrationController(CustomerRepo customerRepo,
                                  AreaRepo areaRepo,
                                  AdminRepo adminRepo)
    {
        this.customerRepo = customerRepo;
        this.areaRepo = areaRepo;
        this.adminRepo = adminRepo;
    }

    @GetMapping("/registration")
    public String showRegister(Model model)
    {
        model.addAttribute("username", "");
        model.addAttribute("area", "");
        model.addAttribute("login", "");
        List<String> arealist = new ArrayList<>();
        for(Area area: areaRepo.getAreaValues())
        {
            arealist.add(area.getName());
        }
        model.addAttribute("areas", arealist);
        model.addAttribute("errorMessage", "");

        return "registration";
    }


    @PostMapping(value = "/registration")
    public String addUser(@RequestParam(name = "username") String name,
                          @RequestParam(name = "login") String login,
                          @RequestParam(name = "password") String password,
                          @RequestParam(name = "passwordConfirm") String passConfirm,
                          @RequestParam(name = "area") String area,
                          Model model)
    {


        if (password.equals(passConfirm))
        {
            if (login.contains("admin"))
            {
                Admin admin = new AdminImpl();
                admin.setName(name);
                admin.setLogin(login);
                admin.setPassword(password);
                try
                {
                    adminRepo.createAdmin(admin);
                }
                catch (SQLException e)
                {
                    model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
                    e.printStackTrace();
                }
                model.addAttribute("admin", admin);
                return "redirect:/authorization";
            }
            else
            {
                /* TODO у нас много таких мест, где создаются сущности, они повторяются нужно
                    содать фабрики под создание сущностей. Почитать про паттерн фабрика
                 */
                Customer customer = new CustomerImpl();
                customer.setName(name);
                customer.setLogin(login);
                customer.setPassword(password);
                customer.setArea(areaRepo.searchAreaByName(area));
                customerRepo.createCustomer(customer);
                model.addAttribute("customer", customer);
                return "redirect:/authorization";
            }
        }
        else
        {
            model.addAttribute("errorMessage", "Passwords are not equal");
            model.addAttribute("username", name);
            model.addAttribute("area", area);
            model.addAttribute("login", login);
            return "/registration";
        }
    }
}
