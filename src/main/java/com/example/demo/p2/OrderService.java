package com.example.demo.p2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    Payment payment;

    public OrderService(@Qualifier("creditCardService") Payment payment) { this.payment = payment; }

    public void placeOrder() {
        payment.pay();
        System.out.println("Order placed!");
    }
}
