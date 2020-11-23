package com.example.demo.week06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@MapperScan("com.example.demo.week06")
public class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    @Transactional
    public void test() {
        Map<String, Object> condition = new HashMap<>(6);
        Order order = new Order(1, "{}", 1);

        long start = System.currentTimeMillis();
        orderMapper.insert(order);

        condition.put("id", 1);
        List<Order> orders = orderMapper.query(condition);
        for (Order item: orders) {
            System.out.println(item);
        }

        order.setUser_id(2L);
        orders = orderMapper.query(condition);
        for (Order item: orders) {
            System.out.println(item);
        }

        orderMapper.delete(order.getId());
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }
}
