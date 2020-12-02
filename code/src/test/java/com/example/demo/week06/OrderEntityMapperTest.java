package com.example.demo.week06;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@MapperScan("com.example.demo.week06")
public class OrderEntityMapperTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    @Transactional
    public void test() {
        Map<String, Object> condition = new HashMap<>(6);
        OrderEntity orderEntity = new OrderEntity(1, "{}", 1);

        long start = System.currentTimeMillis();
        orderMapper.insertOne(orderEntity);

        condition.put("id", 1);
        List<Map<String, Object>> orderEntities = orderMapper.query(condition);
        for (Map item: orderEntities) {
            System.out.println(item);
        }

        orderEntity.setUser_id(2L);
        orderEntities = orderMapper.query(condition);
        for (Map item: orderEntities) {
            System.out.println(item);
        }

        orderMapper.delete(orderEntity.getId());
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

    /**
     * 测试单词插入并提交：10万条数据
     * 198909ms
     */
    @Test @Transactional
    public void testMillionInsertOne() {
        long start = System.currentTimeMillis();
        for (int i=0; i < 100000; i++) {
            OrderEntity order = new OrderEntity(1, "{}", 1);
            orderMapper.insertOne(order);
        }
        System.out.println("insert one spend time: " + (System.currentTimeMillis() - start));
    }

    /**
     * 测试批量插入并提交：10万条数据
     * 7270ms
     */
    @Transactional @Test
    public void testMillionInsertMany() {
        long start = System.currentTimeMillis();
        int number = 100000;
//        int number = 10;
        List<OrderEntity> list = new ArrayList<>(number);
        for (int i=0; i < number; i++) {
            list.add(new OrderEntity(1, "{}", 1));
        }
        orderMapper.insertMany(list);
        System.out.println("insert one spend time: " + (System.currentTimeMillis() - start));
    }
}
