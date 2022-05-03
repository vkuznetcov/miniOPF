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
import java.util.ArrayList;
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
        List<Template> list = templateRepo.getTemplateValues();
        List<Template> listbyarea = new ArrayList<>();
        for(Template template: list)
        {
            if(template.getArea().getId() == customerRepo.getCustomer(id).getArea().getId())
            {listbyarea.add(template);}
        }
        int size = listbyarea.size();
        List<Template> firstlist = listbyarea.subList(0,size/2);
        List<Template> secondlist = listbyarea.subList(size/2,size);
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
        List<Pair<Integer,Service>> list = serviceRepo.searchServicesByCustomerID(id);
        List <Service> firstlist = new ArrayList<>();
        List <Service> secondlist = new ArrayList<>();
        for(Pair<Integer,Service> serv: list.subList(0,list.size()/2) ){
            firstlist.add(serv.getRightValue());
        }
        for(Pair<Integer,Service> serv: list.subList(list.size()/2,list.size())){
            secondlist.add(serv.getRightValue());
        }
        model.addAttribute("table1",firstlist);
        model.addAttribute("table2",secondlist);
        model.addAttribute("id", id);
        return "customer/activeservices";
    }
    @PostMapping("/avaibleservices")
    public String addAvaibleServices(@RequestParam(value = FormParams.ID, required = false) Integer id,
            @RequestParam(value = "template", required = false) Integer templateid,Model model) {
        double money = customerRepo.getCustomer(id).getBalance() - templateRepo.getTemplate(templateid).getPrice();
 if(money>=0){
        Customer customer = customerRepo.getCustomer(id);
        customer.setBalance(money);
        customerRepo.updateCustomer(id,customer);
        Service service = new ServiceImpl();
        service.setCustomer(customerRepo.getCustomer(id));
        Template template = templateRepo.getTemplate(templateid);
        service.setTemplate(template);
        service.setDescription(template.getDescription());
        service.setName(template.getName());
        service.setPrice(template.getPrice());
        service.setStatus(ServiceStatus.ENTERING);
        serviceRepo.createService(service);
        List<Pair<Integer,Service>> list = serviceRepo.searchServicesByCustomerID(id);
        for(Pair<Integer,Service> serv: list ){
                     if(serv.getRightValue().getName().equals(service.getName()) && serv.getRightValue().getStatus().equals(service.getStatus()))
                     {service.setId(serv.getRightValue().getId());}
        }
        Order order = new OrderImpl();
        order.setService(service);
        order.setStatus(OrderStatus.ENTERING);
        order.setAction(OrderAction.CONNECT);
        orderRepo.createOrder(order);
 }
 else{model.addAttribute("errorMessage", "not enough money");}
        model.addAttribute("name", customerRepo.getCustomer(id).getName());
        model.addAttribute("balance",customerRepo.getCustomer(id).getBalance());
        model.addAttribute("id", id);
        return "customer/avaibleservices";
    }
    @PostMapping(value = "/activeservices")
    public String addActiveServices(@RequestParam(value = FormParams.ID, required = false) Integer id,
                                     @RequestParam(value = "service", required = false) Integer serviceid,Model model) {
        Order order = new OrderImpl();
        order.setService(serviceRepo.getService(serviceid).getRightValue());
        order.setStatus(OrderStatus.ENTERING);
        order.setAction(OrderAction.DISCONNECT);
        orderRepo.createOrder(order);
        model.addAttribute("name", customerRepo.getCustomer(id).getName());
        model.addAttribute("balance",customerRepo.getCustomer(id).getBalance());
        model.addAttribute("id", id);
        return "customer/activeservices";
    }
}