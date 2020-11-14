package com.example.code.class9;

import com.example.code.class9.javacode.MyJavaCodeConfig;
import com.example.code.class9.javacode.MyJavaCodeExample;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyJavaCodeExampleTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(MyJavaCodeConfig.class);
        MyJavaCodeExample example = (MyJavaCodeExample) configApplicationContext.getBean("javaCodeExample");
        example.info();
    }
}
