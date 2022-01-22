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

package com.shardingsphere.shardingdb.server;

import com.shardingsphere.shardingdb.entity.ShardingTable;
import com.shardingsphere.shardingdb.entity.Table1;
import com.shardingsphere.shardingdb.entity.Table2;
import com.shardingsphere.shardingdb.mapper.ShardingTableMapper;
import com.shardingsphere.shardingdb.mapper.Table1Mapper;
import com.shardingsphere.shardingdb.mapper.Table2Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuwei
 */
@Service
@AllArgsConstructor
public class TableService {

    private final Table1Mapper table1Mapper;
    private final Table2Mapper table2Mapper;
    private final ShardingTableMapper shardingTableMapper;

    public void table1() {
        final List<Table1> l = table1Mapper.selectList(null);
        l.forEach(System.out::println);
    }

    public void table2() {
        final List<Table2> l = table2Mapper.selectList(null);
        l.forEach(System.out::println);
    }

    public void shardingTable() {
        final List<ShardingTable> l = shardingTableMapper.selectList(null);
        l.forEach(System.out::println);
    }
}
