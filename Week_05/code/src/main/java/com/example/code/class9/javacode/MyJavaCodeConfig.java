package com.example.code.class9.javacode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 */
@Configuration
public class MyJavaCodeConfig {

    @Bean
    public MyJavaCodeExample javaCodeExample() {
        return new MyJavaCodeExample();
    }
}
