package com.netcracker.miniOPF;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.admin.impl.AdminImpl;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.area.impl.AreaImpl;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.customer.impl.CustomerImpl;
import com.netcracker.miniOPF.jsonHanlder.JsonHandler;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.order.enums.OrderAction;
import com.netcracker.miniOPF.order.enums.OrderStatus;
import com.netcracker.miniOPF.order.impl.OrderImpl;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.enums.ServiceStatus;
import com.netcracker.miniOPF.service.impl.ServiceImpl;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.storage.impl.StorageImpl;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.template.impl.TemplateImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MiniOpfApplication
{


//	static Controller controller;
//	static Storage storage;

//	@Autowired
//	public MiniOpfApplication(Controller controller) {
//		this.controller = controller;
//	}

//	public MiniOpfApplication(Storage storage) {
//		this.storage = storage;
//	}

    public static void main(String[] args) throws IOException
    {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
//
//        context.getBean("controller", Controller.class);
//
//        context.close();


//		Controller controller = context.getBean("controller", Controller.class);
//		Storage storage = context.getBean("storage", Storage.class);
        Storage storage = new StorageImpl();

        Admin admin1 = new AdminImpl();
        admin1.setPassword("abcdef");
        admin1.setLogin("admin1");
        admin1.setName("James");
        admin1.setID(0);

        Admin admin2 = new AdminImpl();
        admin2.setPassword("aaaaaa");
        admin2.setLogin("admin2");
        admin2.setName("Nicholas");
        admin2.setID(0);

        Area area = new AreaImpl();
        area.setDescription("Test Area Description");
        area.setName("TestAreaName");
        area.setID(5);

        Template template = new TemplateImpl();
        template.setDescription("Test template");
        template.setPrice(1500);
        template.setName("TemplateName1");
        template.setArea(area);

        Customer customer1 = new CustomerImpl();
        customer1.setBalance(10000);
        customer1.setLogin("Test");
        customer1.setName("Ivan");
        customer1.setPassword("666");

        Customer customer2 = new CustomerImpl();
        customer2.setBalance(10000);
        customer2.setLogin("Test");
        customer2.setName("Ivan");
        customer2.setPassword("666");

        Service service1 = new ServiceImpl();
        service1.setPrice(900);
        service1.setStatus(ServiceStatus.ACTIVE);
        service1.setName("Test Service1");
        service1.setTemplate(template);
        service1.setCustomer(customer2);

        customer1.addService(service1);
        customer1.addService(service1);

        Order order1 = new OrderImpl();
        order1.setAction(OrderAction.RESUME);
        order1.setService(service1);
        order1.setAdmin(admin1);
        order1.setStatus(OrderStatus.IN_PROGRESS);

        Service service2 = new ServiceImpl();
        service2.setPrice(700);
        service2.setStatus(ServiceStatus.ACTIVE);
        service2.setName("Test Service2");
        service2.setTemplate(template);
        service2.setCustomer(customer1);

        Order order2 = new OrderImpl();
        order2.setAction(OrderAction.RESUME);
        order2.setService(service2);
        order2.setAdmin(admin2);
        order2.setStatus(OrderStatus.IN_PROGRESS);
//		JsonHandler.serializeJson(order1);
//		JsonHandler.serializeJson(order2);
//		JsonHandler.serializeJson(customer1);
        List<Service> list = new ArrayList<>();
        list.add(service1);
        list.add(service2);

        Scanner scanner = new Scanner(new FileReader(
                "src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/OrderDB.txt"));

        String json = scanner.nextLine();

        Order orderTest = new ObjectMapper().readValue(json, OrderImpl.class);

        var area2 = JsonHandler.deserializeJson(JsonHandler.EntityType.AREA);

        int a = 2;
        List<Order> order = (List<Order>) JsonHandler.deserializeJson(JsonHandler.EntityType.ORDER);
        for (Order cur : order)
        {
            System.out.println(
                    cur.getService().getName() + cur.getService().getPrice() + cur.getStatus().toString() + "\n");
        }

//		SpringApplication.run(MiniOpfApplication.class, args);
    }

}
