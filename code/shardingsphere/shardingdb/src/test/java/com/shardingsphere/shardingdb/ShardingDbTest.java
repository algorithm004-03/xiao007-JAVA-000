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

package com.shardingsphere.shardingdb;

import com.shardingsphere.shardingdb.entity.ShardingTable;
import com.shardingsphere.shardingdb.entity.Table1;
import com.shardingsphere.shardingdb.entity.Table2;
import com.shardingsphere.shardingdb.mapper.ShardingTableMapper;
import com.shardingsphere.shardingdb.mapper.Table1Mapper;
import com.shardingsphere.shardingdb.mapper.Table2Mapper;
import com.shardingsphere.shardingdb.thread.ThreadLocalCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author liuwei
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShardingDbTest {

    @Autowired
    private Table1Mapper table1Mapper;

    @Autowired
    private Table2Mapper table2Mapper;

    @Autowired
    private ShardingTableMapper shardingTableMapper;

    @Test
    public void test() {
        final List<Table1> l1 = table1Mapper.selectList(null);
        l1.forEach(System.out::println);

        final List<Table2> l2 = table2Mapper.selectList(null);
        l2.forEach(System.out::println);

        ThreadLocalCache.threadLocal.set("db1");
        System.out.println(shardingTableMapper.selectById(1L));

        ThreadLocalCache.threadLocal.set("db0");
        System.out.println(shardingTableMapper.selectById(1L));
    }
}
