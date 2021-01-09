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
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author lw1243925457
 */
@Slf4j
class KafkaUtil {

    /**
     * 生成kafka消费者
     * @param servers server address
     * @param topic topic list
     * @return kafka consumer
     */
    static KafkaConsumer<String, String> createConsumer(String servers, List<String> topic) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "false");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(topic);
        return kafkaConsumer;
    }

    static void readMessage(KafkaConsumer<String, String> kafkaConsumer, int timeout) {
        ConsumerRecords<String, String> records = kafkaConsumer.poll(timeout);
        for (ConsumerRecord<String, String> record : records) {
            // 保存数据到相应的数据库，这里就不写存到数据库（重放的实现）
            String value = record.value();
            kafkaConsumer.commitAsync();
            System.out.println(value);
        }
    }

    /**
     * 生成kafka生产者
     * 配置 ack all 确保消息
     * @param servers kafka address
     * @return kafka producer
     */
    static KafkaProducer<String, String> createProducer(String servers) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 1_000);
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1_000);
        return new KafkaProducer<>(properties);
    }

    static Future<RecordMetadata> send(KafkaProducer<String, String> producer, String topic, String message) {
        return producer.send(new ProducerRecord<>(topic, message), (recordMetadata, e) -> {
            if (e == null) {
                log.info("send success");
            }else {
                log.info("send failed");
            }
        });
    }
}
