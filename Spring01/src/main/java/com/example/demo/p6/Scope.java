package com.example.demo.p6;

import org.springframework.stereotype.Component;

@Component
@org.springframework.context.annotation.Scope("prototype")
//@org.springframework.context.annotation.Scope("singleton")
public class Scope {
    Scope() {
        System.out.println("Scope one");
    }

    public void called() {
        System.out.println("Scope called once");
    }
}
