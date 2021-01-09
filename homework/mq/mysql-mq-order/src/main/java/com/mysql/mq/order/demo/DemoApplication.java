package com.mysql.mq.order.demo;

import com.mysql.mq.order.demo.mapper.OrderMapper;
import com.mysql.mq.order.demo.model.Order;
import com.mysql.mq.order.demo.service.OrderConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lw
 */
@SpringBootApplication
@Slf4j
public class DemoApplication implements ApplicationRunner {

    @Value("${interval}")
    private int interval;

    @Autowired
    private OrderMapper orderMapper;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new OrderConsumer(orderMapper, interval));

        for (int i = 0; i < 3; i++) {
            int amount = new Random().nextInt(10) + 10;
            for (int j = 0; j < amount; j++) {
                orderMapper.insert(new Order());
            }
            log.info("insert order amount: " + amount);
            Thread.sleep(1000);
        }
        log.info("insert data end");
    }
}
