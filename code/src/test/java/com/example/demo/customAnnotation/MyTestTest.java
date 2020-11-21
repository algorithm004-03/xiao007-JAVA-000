package com.example.demo.customAnnotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class MyTestTest {

    @MyTest(description = "description", length = 1)
    private String proprerties;

    @Test
    public void test() {
        Class c = MyTestTest.class;

        for (Field f: c.getDeclaredFields()) {
            if (f.isAnnotationPresent(MyTest.class)) {
                MyTest annotation = f.getAnnotation(MyTest.class);
                System.out.println(f.getName() + "-" + annotation.description() + "-" + annotation.length());
                assert "description".equals(annotation.description());
                assert annotation.length() == 1;
            }
        }
    }
}
