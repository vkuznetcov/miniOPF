package com.netcracker.miniOPF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MiniOpfApplication
{


//	static TemplateController controller;
//	static Storage storage;

//	@Autowired
//	public MiniOpfApplication(TemplateController controller) {
//		this.controller = controller;
//	}

//	public MiniOpfApplication(Storage storage) {
//		this.storage = storage;
//	}

    public static void main(String[] args) throws IOException
    {

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml");
//
//        Admin admin = new AdminImpl();
//        admin.setName("James");
//        admin.setLogin("admin1");
//        admin.setPassword("abcd");
//
//        Admin admin1 = new AdminImpl();
//        admin1.setPassword("password");
//        admin1.setLogin("admin2");
//        admin1.setName("Michael");
//
//        Storage storage = context.getBean(StorageImpl.class);
//
//        storage.createAdmin(admin);
//        storage.createAdmin(admin1);
//
//        var list = storage.getAdminValues();
//
//        list.get(0).setName("biba");
//
//        System.out.println(storage.getAdmin(1).getName());
//
//
//
//        JsonHandler.serializeJson(admin, PathConsts.ADMIN_PATH);

        SpringApplication.run(MiniOpfApplication.class, args);
    }

}
