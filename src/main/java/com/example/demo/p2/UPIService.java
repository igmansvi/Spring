package com.example.demo.p2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class UPIService implements Payment{
    @Override
    public void pay() { System.out.println("Payment by UPI"); }
}
