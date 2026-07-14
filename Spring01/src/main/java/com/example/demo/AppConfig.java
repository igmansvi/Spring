package com.example.demo;

import com.example.demo.p3.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo")
public class AppConfig {
//    @Bean
//    public Student student() { return new Student(101, "abc"); }
}
