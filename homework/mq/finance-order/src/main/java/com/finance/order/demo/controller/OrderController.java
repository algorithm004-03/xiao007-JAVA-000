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

package com.finance.order.demo.controller;

import com.finance.order.demo.kafka.KafkaProducer;
import com.finance.order.demo.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lw1243925457
 */
@RestController("/order")
public class OrderController {

    private final KafkaProducer kafkaProducer;

    public OrderController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Order order) {
        kafkaProducer.send(order.toString());
        Map<String, Object> response = new HashMap<>(1);
        response.put("status", "success");
        return response;
    }

    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> response = new HashMap<>(1);
        // 重放就将数据库中的order读出来，这里就模拟一下
        response.put("orders", new ArrayList<>());
        response.put("status", "success");
        return response;
    }
}
