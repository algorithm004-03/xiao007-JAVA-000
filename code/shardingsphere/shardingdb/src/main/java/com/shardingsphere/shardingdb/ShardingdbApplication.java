package com.shardingsphere.shardingdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shardingsphere.shardingdb.mapper")
@SpringBootApplication
public class ShardingdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingdbApplication.class, args);
    }

}
