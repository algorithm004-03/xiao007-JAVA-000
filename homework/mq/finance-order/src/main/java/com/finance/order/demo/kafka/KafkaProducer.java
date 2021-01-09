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

package com.finance.order.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author lw1243925457
 */
@Service
@Slf4j
public class KafkaProducer {

    private final KafkaConfig config;

    private final org.apache.kafka.clients.producer.KafkaProducer<String, String> producer;

    public KafkaProducer(KafkaConfig config) {
        log.info("kafka address: " + config.getAddress());
        log.info("kafka topic: " + config.getTopic());
        producer = KafkaUtil.createProducer(config.getAddress());
        this.config = config;
    }

    public synchronized void send(String message) {
        log.info("send message to " + config.getTopic() + " :: " + message);
        try {
            // 进行阻塞一下，确保当前顺序写入
            KafkaUtil.send(producer, config.getTopic(), message).get();
        } catch (Exception e) {
            log.info("Error occur, please check");
        }
    }
}
