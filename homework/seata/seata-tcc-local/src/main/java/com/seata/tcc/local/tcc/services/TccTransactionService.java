package com.seata.tcc.local.tcc.services;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;

import java.util.Map;

/**
 * @author lw
 */
public class TccTransactionService {

    private UserService userService;
    private StoreService storeService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @GlobalTransactional
    public String doTransactionCommit() {
        boolean result = userService.prepare(null, 1);
        if(!result){
            throw new RuntimeException("TccActionOne failed.");
        }
        result = storeService.prepare(null, "two");
        if(!result){
            throw new RuntimeException("TccActionTwo failed.");
        }
        return RootContext.getXID();
    }

    @GlobalTransactional
    public void doTransactionRollback(Map map) {
        boolean result = userService.prepare(null, 1);
        if(!result){
            throw new RuntimeException("TccActionOne failed.");
        }
        result = storeService.prepare(null, "two");
        if(!result){
            throw new RuntimeException("TccActionTwo failed.");
        }
        map.put("xid", RootContext.getXID());
        throw new RuntimeException("transacton rollback");
    }
}
