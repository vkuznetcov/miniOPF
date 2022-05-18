package com.netcracker.miniOPF.springmvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
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

        // TODO вынести цикл в отдельный метод
        for(Template template: list)
        {
            if(template.getArea().getId() == customerRepo.getCustomer(id).getArea().getId())
            {listbyarea.add(template);}
        }
        model.addAttribute("table",listbyarea);
        model.addAttribute("id", id);
        return "customer/avaibleservices";
    }
    @GetMapping(value = "/activeservices")
    public String showActiveServices
            (@RequestParam(value = FormParams.ID, required = false) Integer id, Model model){
        model.addAttribute("name", customerRepo.getCustomer(id).getName());
        model.addAttribute("balance",customerRepo.getCustomer(id).getBalance());
        // TODO на Service уже есть кастомер. Pair выглядит как кастыль. Если нужен id кастомера лучше с сервиса брать
        List<Pair<Integer,Service>> list = serviceRepo.searchServicesByCustomerID(id);
        List <Service> servicelist = new ArrayList<>();

        for(Pair<Integer,Service> serv: list ){
            if (serv.getRightValue().getStatus() != ServiceStatus.DISCONNECTED )
                servicelist.add(serv.getRightValue());
        }
        model.addAttribute("table",servicelist);
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
        // TODO вынести создание сервиса в фабрику сервисов, как и других сущностей
        Service service = new ServiceImpl();
        service.setCustomer(customerRepo.getCustomer(id));
        Template template = templateRepo.getTemplate(templateid);
        service.setTemplate(template);
        service.setCustomer(customer);
        service.setDescription(template.getDescription());
        service.setName(template.getName());
        service.setPrice(template.getPrice());
        service.setStatus(ServiceStatus.ENTERING);
        serviceRepo.createService(service);

        // TODO убрать класс Pair возвращать просто список Service
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
        return showAvaibleServices(id,model);
    }
    @PostMapping(value = "/activeservices")
    public String addActiveServices(@RequestParam(value = FormParams.ID, required = false) Integer id,
                                     @RequestParam(value = "service", required = false) Integer serviceid,Model model) {
        Order order = new OrderImpl();
        order.setService(serviceRepo.getService(serviceid).getRightValue());
        order.setStatus(OrderStatus.ENTERING);
        order.setAction(OrderAction.DISCONNECT);
        Service service = serviceRepo.getService(serviceid).getRightValue();
        service.setStatus(ServiceStatus.SUSPENDED);
        service.setCustomer(customerRepo.getCustomer(id));
        serviceRepo.updateService(serviceid,service);
        orderRepo.createOrder(order);
        model.addAttribute("name", customerRepo.getCustomer(id).getName());
        model.addAttribute("balance",customerRepo.getCustomer(id).getBalance());
        model.addAttribute("id", id);
        return showActiveServices(id,model);
    }
    @PostMapping(value = "/okno")
    public String setNewBalance(@RequestParam(value = FormParams.ID, required = false) Integer id,
                                @RequestParam(name = "balance") Double balance, Model model) {
        Customer customer = customerRepo.getCustomer(id);
        customer.setBalance(balance);
        customerRepo.updateCustomer(id,customer);
        model.addAttribute("name", customerRepo.getCustomer(id).getName());
        model.addAttribute("balance",customerRepo.getCustomer(id).getBalance());
        model.addAttribute("id", id);
        return greeting(id,model);
    }
}