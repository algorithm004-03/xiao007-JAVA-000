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

package com.shardingsphere.shardingdb.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Configuration
public class ShardingDataSourceMybatisPlusConfig extends MybatisPlusAutoConfiguration {

    private final MultipleDbConfig multipleDbConfig;

    public ShardingDataSourceMybatisPlusConfig(MybatisPlusProperties properties,
                                               ObjectProvider<Interceptor[]> interceptorsProvider,
                                               ObjectProvider<TypeHandler[]> typeHandlersProvider,
                                               ObjectProvider<LanguageDriver[]> languageDriversProvider,
                                               ResourceLoader resourceLoader,
                                               ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                               ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
                                               ObjectProvider<List<MybatisPlusPropertiesCustomizer>> mybatisPlusPropertiesCustomizerProvider,
                                               ApplicationContext applicationContext,
                                               MultipleDbConfig multipleDbConfig) {
        super(properties, interceptorsProvider, typeHandlersProvider, languageDriversProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider, mybatisPlusPropertiesCustomizerProvider, applicationContext);
        this.multipleDbConfig = multipleDbConfig;
    }

    @Primary
    @Bean("dataSource")
    public DataSource getDataSource() throws SQLException {
        // 配置真实数据源
        Map<String, MultipleDbConfig.DbSource> dbs = multipleDbConfig.getDatasources();
        Map<String, DataSource> dataSourceMap = new HashMap<>(dbs.size());
        for (String dbName: dbs.keySet()) {
            MultipleDbConfig.DbSource dbConfig = dbs.get(dbName);
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl(dbConfig.getJdbcUrl());
            dataSource.setUsername(dbConfig.getUsername());
            dataSource.setPassword(dbConfig.getPassword());
            dataSourceMap.put(dbName, dataSource);
        }

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        // 遍历表的固定映射：表table1到数据源db0访问，表table2到数据源db1访问
        Map<String, String> rules = multipleDbConfig.getRules();
        for (final String table: rules.keySet()) {
            // 配置添加 t_order 表规则
            final String actualDataNodes = String.join(".", rules.get(table), table);
            shardingRuleConfig.getTables().add(new ShardingTableRuleConfiguration(table, actualDataNodes));
        }

        // 配置 sharding_table 表的访问，需要自定义实现分库和分表算法
        ShardingTableRuleConfiguration ShardingTableRuleConfiguration = new ShardingTableRuleConfiguration("sharding_table", "db${0..1}.sharding_table");
        shardingRuleConfig.setDefaultDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("id", "customDbSharding"));
        shardingRuleConfig.setDefaultTableShardingStrategy(new StandardShardingStrategyConfiguration("id", "customTableSharding"));
        shardingRuleConfig.getTables().add(ShardingTableRuleConfiguration);

        // 配置分库算法
        Properties dbShardingAlgorithmProps = new Properties();
        dbShardingAlgorithmProps.setProperty("strategy", "standard");
        dbShardingAlgorithmProps.setProperty("algorithmClassName", "com.shardingsphere.shardingdb.config.CustomDbSharding");
        shardingRuleConfig.getShardingAlgorithms().put("customDbSharding", new ShardingSphereAlgorithmConfiguration("CLASS_BASED", dbShardingAlgorithmProps));

        // 配置分表算法
        Properties tableShardingAlgorithmProps = new Properties();
        tableShardingAlgorithmProps.setProperty("strategy", "standard");
        tableShardingAlgorithmProps.setProperty("algorithmClassName", "com.shardingsphere.shardingdb.config.CustomTableSharding");
        shardingRuleConfig.getShardingAlgorithms().put("customTableSharding", new ShardingSphereAlgorithmConfiguration("CLASS_BASED", tableShardingAlgorithmProps));

        // 开启Sql日志
        final Properties properties = new Properties();
        properties.setProperty("sql-show", "true");

        // 创建 ShardingSphereDataSource
        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), properties);
    }

    @Override
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource")DataSource dataSource) throws Exception {
        return super.sqlSessionFactory(getDataSource());
    }

    @Override
    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return super.sqlSessionTemplate(sqlSessionFactory);
    }
}