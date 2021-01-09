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

package com.mysql.mq.order.demo.mapper;

import com.mysql.mq.order.demo.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lw1243925457
 */
@Mapper
@Repository
public interface OrderMapper {

    @Insert("insert into mq_order(status) values(#{status})")
    void insert(Order order);

    @Update("update mq_order set status = #{status} where id = #{id}")
    void update(Order order);

    @Select("select * from mq_order where status = 0 and id > #{lately_index}")
    List<Order> select(@Param("lately_index") long latelyIndex);
}
