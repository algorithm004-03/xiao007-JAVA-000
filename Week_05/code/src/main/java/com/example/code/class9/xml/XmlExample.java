package com.example.code.class9.xml;

import org.springframework.stereotype.Component;

/**
 * xml方式，Bean装配
 * @author lw
 */
@Component
public class XmlExample {
    public XmlExample() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
