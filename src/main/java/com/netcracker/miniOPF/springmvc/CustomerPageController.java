package com.netcracker.miniOPF.springmvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.model.order.OrderImpl;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.service.ServiceImpl;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.model.storage.ConvertMapAndFiles;
import com.netcracker.miniOPF.model.storage.StorageImpl;
import com.netcracker.miniOPF.model.template.Template;
import com.netcracker.miniOPF.springmvc.services.*;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import com.netcracker.miniOPF.utils.repos.OrderRepo;
import com.netcracker.miniOPF.utils.repos.ServiceRepo;
import com.netcracker.miniOPF.utils.repos.TemplateRepo;
import com.netcracker.miniOPF.utils.storageUtils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/customer")
public class CustomerPageController {
    CustomerRepo customerRepo;
    ServiceRepo serviceRepo;
    TemplateRepo templateRepo;
    OrderRepo orderRepo;


    public static class FormParams{
        public static final String ID    = "id";
    }
    @Autowired
    public CustomerPageController(
            CustomerRepo customerRepo,
            OrderRepo orderRepo,
            ServiceRepo serviceRepo,
            TemplateRepo templateRepo
    )
    {
        this.customerRepo =  customerRepo;
        this.orderRepo = orderRepo;
        this.serviceRepo = serviceRepo;
        this.templateRepo = templateRepo;
    }
    @GetMapping("/customerpage")
    public String greeting(@RequestParam(value = FormParams.ID, required = false) Integer id, Model model){
        model.addAttribute("name", customerRepo.getCustomer(id).getName());
        model.addAttribute("id", id);
        model.addAttribute("balance", customerRepo.getCustomer(id).getBalance());
        return "customer/customerpage";
    }
    @GetMapping("/avaibleservices")
    public String showAvaibleServices
            (@RequestParam(value = FormParams.ID, required = false) Integer id, Model model){
        model.addAttribute("name", customerRepo.getCustomer(id).getName());
        model.addAttribute("balance",customerRepo.getCustomer(id).getBalance());
        int size = templateRepo.getTemplateValues().size();
        List<Template> firstlist = templateRepo.getTemplateValues().subList(0,size/2);
        List<Template> secondlist = templateRepo.getTemplateValues().subList(size/2,size);
        model.addAttribute("table1",firstlist);
        model.addAttribute("table2",secondlist);
        model.addAttribute("id", id);
        return "customer/avaibleservices";
    }
    @GetMapping(value = "/activeservices")
    public String showActiveServices
            (@RequestParam(value = FormParams.ID, required = false) Integer id, Model model){
        model.addAttribute("name", customerRepo.getCustomer(id).getName());
        model.addAttribute("balance",customerRepo.getCustomer(id).getBalance());
        int size = serviceRepo.searchServicesByCustomerID(id).size();
        List<Pair<Integer,Service>> firstlist = serviceRepo.searchServicesByCustomerID(id).subList(0,size/2);
        List<Pair<Integer,Service>> secondlist = serviceRepo.searchServicesByCustomerID(id).subList(size/2,size);
        model.addAttribute("table1",firstlist);
        model.addAttribute("table2",secondlist);
        model.addAttribute("id", id);
        return "customer/activeservices";
    }
    @PostMapping("/avaibleservices")
    public String addAvaibleServices(@RequestParam(value = FormParams.ID, required = false) Integer id,
            @RequestParam(value = "template", required = false) String templatename,Model model) {
        double money = customerRepo.getCustomer(id).getBalance() - templateRepo.searchTemplateByName(templatename).getPrice();
 if(money>=0){
        Customer customer = customerRepo.getCustomer(id);
        customer.setBalance(money);
        customerRepo.updateCustomer(id,customer);
        Service service = new ServiceImpl();
        service.setCustomer(customerRepo.getCustomer(id));
        Template template = templateRepo.searchTemplateByName(templatename);
        service.setTemplate(template);
        service.setDescription(template.getDescription());
        service.setName(template.getName());
        service.setPrice(template.getPrice());
        service.setStatus(ServiceStatus.ENTERING );
        serviceRepo.createService(service);
        List<Pair<Integer,Service>> list = serviceRepo.searchServicesByCustomerID(id);
        for(Pair<Integer,Service> serv: list ){
                     if(serv.getRightValue().getName() == service.getName() && serv.getRightValue().getStatus() ==  service.getStatus())
                     {service.setID(serv.getRightValue().getID());}
        }
        Order order = new OrderImpl();
        order.setService(service);
        order.setStatus(OrderStatus.ENTERING);
        order.setAction(OrderAction.CONNECT);
        orderRepo.createOrder(order);
 }
 else{model.addAttribute("errorMessage", "not enough money");}
        model.addAttribute("id", id);
        return "customer/avaibleservices";
    }
    @PostMapping(value = "/activeservices")
    public String addActiveServices(@RequestParam(value = FormParams.ID, required = false) Integer id,
                                     @RequestParam(value = "service", required = false) String servicename,Model model) {
        /*if(serviceRepo.searchServicesByCustomerID(id).equals()) {
            Service service = customerRepo.getCustomer(id).s;
            service.setStatus(ServiceStatus.SUSPENDED);
            Order order = new OrderImpl();
            order.setService(service);
            order.setStatus(OrderStatus.ENTERING);
            order.setAction(OrderAction.SUSPEND);
            orderRepo.createOrder(order);
            model.addAttribute("id", id);
        }

         */
        return "customer/activeservices";
    }
}