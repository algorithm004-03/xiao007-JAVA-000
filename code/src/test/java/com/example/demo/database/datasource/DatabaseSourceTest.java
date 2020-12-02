package com.example.demo.database.datasource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 测试数据源配置是否成功
 * 查看URL连接是否是配置文件中的三个
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DatabaseSourceTest {

    @Autowired
    @Qualifier("master")
    DataSource masterDataSource;
    @Autowired
    @Qualifier("slave1")
    DataSource slave1DataSource;
    @Autowired
    @Qualifier("slave2")
    DataSource slave2DataSource;

    @Test
    public void testDatabaseSource() throws SQLException {
        System.out.println(masterDataSource.toString());
        System.out.println(slave1DataSource.toString());
        System.out.println(slave2DataSource.toString());

        System.out.println(masterDataSource.getConnection().toString());
        System.out.println(slave1DataSource.getConnection().toString());
        System.out.println(slave2DataSource.getConnection().toString());

        System.out.println(masterDataSource.getConnection().getMetaData().getURL());
        System.out.println(slave1DataSource.getConnection().getMetaData().getURL());
        System.out.println(slave2DataSource.getConnection().getMetaData().getURL());
    }
}
