package com.example.code.class9.javacode;

import org.springframework.stereotype.Component;

/**
 * Java代码方式，Bean装配
 * @author lw
 */
@Component
public class MyJavaCodeExample {

    public MyJavaCodeExample() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
