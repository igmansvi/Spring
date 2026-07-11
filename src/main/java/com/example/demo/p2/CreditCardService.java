package com.example.demo.p2;

import org.springframework.stereotype.Component;

@Component
public class CreditCardService implements Payment{
    @Override
    public void pay() { System.out.println("Payment by Credit Card"); }
}
