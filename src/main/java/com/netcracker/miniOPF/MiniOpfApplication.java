package com.netcracker.miniOPF;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.admin.impl.AdminImpl;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.area.impl.AreaImpl;
import com.netcracker.miniOPF.controller.Controller;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.storage.impl.StorageImpl;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.template.impl.TemplateImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MiniOpfApplication {


	static Controller controller;
	static Storage storage;

//	@Autowired
//	public MiniOpfApplication(Controller controller) {
//		this.controller = controller;
//	}

	public MiniOpfApplication(Storage storage) {
		this.storage = storage;
	}

	public static void main(String[] args) throws JsonProcessingException {

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml");
//
//        context.getBean("controller", Controller.class);
//
//        context.close();



		StorageImpl storage = new StorageImpl();

//		storage.adminSorter;

		Admin admin1 = new AdminImpl();
		admin1.setLogin("admin1");
		admin1.setName("Carlos");
		admin1.setPassword("abcdef");

		Area area = new AreaImpl();
		area.setName("NY");
		area.setDescription("State of the USA");

		Template template = new TemplateImpl();
		template.setName("Test Template");
		template.setArea(area);
		template.setPrice(12500);
		template.setDescription("Test Description");

		Admin admin2 = new AdminImpl();
		admin2.setPassword("aaaaa");
		admin2.setLogin("admin2");
		admin2.setName("James");

//		Controller controller = new Controller(storage);
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//		String json = mapper.writeValueAsString(template);
//		System.out.println(json);
//
//		int a = 2;

		Admin admin3 = new AdminImpl();
		admin3.setName("Michael");
		admin3.setLogin("admin3");
		admin3.setPassword("bbbbbbbbbbbbb");

		storage.createAdmin(admin1);
		storage.createAdmin(admin3);
		storage.createAdmin(admin2);

		List<Admin> list = storage.adminSorter.sortAdminsByNameReversed();
		int a = 2;

//		admin1.setID();



//        service.setStatus(2);

//		SpringApplication.run(MiniOpfApplication.class, args);
	}

}
