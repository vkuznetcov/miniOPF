package com.netcracker.miniOPF;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.admin.impl.AdminImpl;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.area.impl.AreaImpl;
import com.netcracker.miniOPF.controller.Controller;
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
import com.netcracker.miniOPF.utils.consts.PathConsts;
import org.springframework.boot.SpringApplication;
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

        Admin admin = new AdminImpl();
        admin.setID(1);
        admin.setName("James");
        admin.setLogin("admin1");
        admin.setPassword("abcd");

        JsonHandler.serializeJson(admin, PathConsts.ADMIN_PATH);

//		SpringApplication.run(MiniOpfApplication.class, args);
        Storage storage = context.getBean("storage",StorageImpl.class);

    }

}
