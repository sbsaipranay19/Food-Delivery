package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.learning.entity.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.service.UserService;

@SpringBootApplication
public class FoodDeliverySpringBootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication
				.run(FoodDeliverySpringBootApplication.class, args);
//		UserService service = applicationContext.getBean(UserService.class);
//		for (int i = 0; i < 3; i++) {
//			System.out.println("Adding User: " + i);
//			Register register  = new Register("sbsai31"+i+"@gmail.com","Sai Pranay","12345678","Hyderbad",0);
//			try {
//				service.addUser(register);
//			} catch (AlreadyExistsException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		applicationContext.close();
	}

}
