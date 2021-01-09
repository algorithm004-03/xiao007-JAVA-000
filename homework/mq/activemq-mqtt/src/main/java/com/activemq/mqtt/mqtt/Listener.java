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

package com.activemq.mqtt.mqtt;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

/**
 * @author lw1243925457
 */
public class Listener {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("admin");

        final CallbackConnection connection = mqtt.callbackConnection();
        connection.listener(new org.fusesource.mqtt.client.Listener() {

            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onPublish(UTF8Buffer utf8Buffer, Buffer buffer, Runnable runnable) {
                String message = buffer.utf8().toString();
                System.out.println("Receive message : " + message);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Topic[] topics = {new Topic("mqttTest", QoS.AT_LEAST_ONCE)};
                connection.subscribe(topics, new Callback<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        System.out.println("subscribe success");
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("subscribe failed");
                    }
                });
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        synchronized (Listener.class) {
            while (true) {
                Listener.class.wait();
            }
        }
    }
}
