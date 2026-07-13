package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.p1.EmailService;
import com.example.demo.p1.Notification;
import com.example.demo.p2.Payment;
import com.example.demo.p2.UPIService;
import com.example.demo.p2.OrderService;
import com.example.demo.p3.StudentService;
import com.example.demo.p4.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//		Notification notifications = new EmailService();
//		OrderService orderService = new OrderService(notifications);
//		orderService.placeOrder();

//		OrderService orderService = new OrderService(new UPIService());
//		orderService.placeOrder();

//		OrderService orderService = context.getBean(OrderService.class);
//		orderService.placeOrder();

//		StudentService studentService = (StudentService) context.getBean("studentService");
//		studentService.display();

		Student student = context.getBean(Student.class);
		student.setName("abc");
		student.setId(101);
		System.out.println(student.getId() + ", " + student.getName());
	}

}
