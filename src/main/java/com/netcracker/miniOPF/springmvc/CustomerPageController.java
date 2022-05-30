package com.netcracker.miniOPF.springmvc;

import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.model.order.OrderImpl;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.service.ServiceImpl;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.model.template.Template;
import com.netcracker.miniOPF.utils.repos.CustomerRepo;
import com.netcracker.miniOPF.utils.repos.OrderRepo;
import com.netcracker.miniOPF.utils.repos.ServiceRepo;
import com.netcracker.miniOPF.utils.repos.TemplateRepo;
import com.netcracker.miniOPF.utils.storageUtils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerPageController
{
    CustomerRepo customerRepo;
    ServiceRepo serviceRepo;
    TemplateRepo templateRepo;
    OrderRepo orderRepo;


    @Autowired
    public CustomerPageController(
            CustomerRepo customerRepo,
            OrderRepo orderRepo,
            ServiceRepo serviceRepo,
            TemplateRepo templateRepo
                                 )
    {
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
        this.serviceRepo = serviceRepo;
        this.templateRepo = templateRepo;
    }

    @GetMapping("/customerpage")
    public String greeting(@RequestParam(value = FormParams.ID, required = false) Integer id, Model model)
    {
        try
        {
            model.addAttribute("name", customerRepo.getCustomer(id).getName());
            model.addAttribute("id", id);
            model.addAttribute("balance", customerRepo.getCustomer(id).getBalance());
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return "customer/customerpage";
    }

    @GetMapping("/avaibleservices")
    public String showAvaibleServices
            (@RequestParam(value = FormParams.ID, required = false) Integer id, Model model)
    {
        try
        {
            model.addAttribute("name", customerRepo.getCustomer(id).getName());
            model.addAttribute("balance", customerRepo.getCustomer(id).getBalance());
            List<Template> list = templateRepo.getTemplateValues();
            List<Template> listbyarea = new ArrayList<>();

            // TODO вынести цикл в отдельный метод
            for (Template template : list)
            {
                if (template.getArea().getId() == customerRepo.getCustomer(id).getArea().getId())
                {
                    listbyarea.add(template);
                }
            }
            model.addAttribute("table", listbyarea);
            model.addAttribute("id", id);
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return "customer/avaibleservices";
    }

    @GetMapping(value = "/activeservices")
    public String showActiveServices
            (@RequestParam(value = FormParams.ID, required = false) Integer id, Model model)
    {
        try
        {
            model.addAttribute("name", customerRepo.getCustomer(id).getName());
            model.addAttribute("balance", customerRepo.getCustomer(id).getBalance());
            // TODO на Service уже есть кастомер. Pair выглядит как кастыль. Если нужен id кастомера лучше с сервиса брать
            List<Pair<Integer, Service>> list = serviceRepo.searchServicesByCustomerID(id);
            List<Service> servicelist = new ArrayList<>();

            for (Pair<Integer, Service> serv : list)
            {
                if (serv.getRightValue().getStatus() != ServiceStatus.DISCONNECTED)
                {
                    servicelist.add(serv.getRightValue());
                }
            }
            model.addAttribute("table", servicelist);
            model.addAttribute("id", id);
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return "customer/activeservices";
    }

    @PostMapping("/avaibleservices")
    public String addAvaibleServices(@RequestParam(value = FormParams.ID, required = false) Integer id,
                                     @RequestParam(value = "template", required = false) Integer templateid,
                                     Model model)
    {
        try
        {
            double money = customerRepo.getCustomer(id).getBalance() - templateRepo.getTemplate(templateid).getPrice();
            if (money >= 0)
            {
                Customer customer = customerRepo.getCustomer(id);
                customer.setBalance(money);
                customerRepo.updateCustomer(id, customer);
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
                List<Pair<Integer, Service>> list = serviceRepo.searchServicesByCustomerID(id);
                for (Pair<Integer, Service> serv : list)
                {
                    if (serv.getRightValue().getName().equals(service.getName()) &&
                            serv.getRightValue().getStatus().equals(service.getStatus()))
                    {
                        service.setId(serv.getRightValue().getId());
                    }
                }
                Order order = new OrderImpl();
                order.setService(service);
                order.setStatus(OrderStatus.ENTERING);
                order.setAction(OrderAction.CONNECT);
                try
                {
                    orderRepo.createOrder(order);
                }
                catch (SQLException e)
                {
                    model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            else
            {
                model.addAttribute("errorMessage", "not enough money");
            }
            model.addAttribute("name", customerRepo.getCustomer(id).getName());
            model.addAttribute("balance", customerRepo.getCustomer(id).getBalance());
            model.addAttribute("id", id);
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return showAvaibleServices(id, model);
    }

    @PostMapping(value = "/activeservices")
    public String addActiveServices(@RequestParam(value = FormParams.ID, required = false) Integer id,
                                    @RequestParam(value = "service", required = false) Integer serviceid, Model model)
    {
        try
        {
            Order order = new OrderImpl();
            order.setService(serviceRepo.getService(serviceid).getRightValue());
            order.setStatus(OrderStatus.ENTERING);
            order.setAction(OrderAction.DISCONNECT);
            Service service = serviceRepo.getService(serviceid).getRightValue();
            service.setStatus(ServiceStatus.SUSPENDED);
            service.setCustomer(customerRepo.getCustomer(id));
            serviceRepo.updateService(serviceid, service);
            orderRepo.createOrder(order);
            model.addAttribute("name", customerRepo.getCustomer(id).getName());
            model.addAttribute("balance", customerRepo.getCustomer(id).getBalance());
            model.addAttribute("id", id);
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return showActiveServices(id, model);
    }

    @PostMapping(value = "/okno")
    public String setNewBalance(@RequestParam(value = FormParams.ID, required = false) Integer id,
                                @RequestParam(name = "balance") Double balance, Model model)
    {
        try
        {
            Customer customer = customerRepo.getCustomer(id);
            customer.setBalance(balance);
            customerRepo.updateCustomer(id, customer);
            model.addAttribute("name", customerRepo.getCustomer(id).getName());
            model.addAttribute("balance", customerRepo.getCustomer(id).getBalance());
            model.addAttribute("id", id);
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return greeting(id, model);
    }

    public static class FormParams
    {
        public static final String ID = "id";
    }
}