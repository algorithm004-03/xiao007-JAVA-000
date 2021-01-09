/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mysql.mq.order.demo.service;

import com.mysql.mq.order.demo.mapper.OrderMapper;
import com.mysql.mq.order.demo.model.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lw1243925457
 */
@Slf4j
public class OrderConsumer implements Runnable {


    private final int interval;
    private long skipAmount = 0;

    private final OrderMapper orderMapper;

    public OrderConsumer(OrderMapper orderMapper, int interval) {
        this.orderMapper = orderMapper;
        this.interval = interval;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("start poll order , interval: " + interval);
        while (interval != 0) {
            List<Order> orders = orderMapper.select(skipAmount);
            for (Order order: orders) {
                order.setStatus(1);
                orderMapper.update(order);
            }

            Thread.sleep(interval);
            if (orders.isEmpty()) {
                continue;
            }

            skipAmount = orders.get(orders.size() - 1).getId();
            System.out.println("process order " + orders.size());
        }
    }
}
