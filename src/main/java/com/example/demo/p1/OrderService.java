package com.example.demo.p1;

public class OrderService {
    Notification notifications;

    public OrderService(Notification notifications) {
        this.notifications = notifications;
    }

    public void placeOrder() {
        System.out.println("Order placed successfully!");
        notifications.sendNotification();
    }
}
