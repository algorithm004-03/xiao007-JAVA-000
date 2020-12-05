package com.seata.tcc.local.tcc;

import io.seata.server.Server;

import java.io.IOException;

/**
 * Seata TCC 服务
 * 需要提前启动
 * @author lw
 */
public class SeataServer {

    public static void main(String[] args) throws IOException {
        Server.main(args);
    }
}
