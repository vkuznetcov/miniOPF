package com.netcracker.miniOPF;

import com.netcracker.miniOPF.service.ServiceInt;
import com.netcracker.miniOPF.service.ServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniOpfApplication {

	public static void main(String[] args) {

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml");
//
//        context.getBean("controller", Controller.class);
//
//        context.close();

        ServiceInt serviceInt = new ServiceImpl();

        serviceInt.setStatus(2);

		SpringApplication.run(MiniOpfApplication.class, args);
	}

}
