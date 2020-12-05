package com.seata.tcc.local.tcc.services.impl;

import com.seata.tcc.local.tcc.services.UserService;
import com.seata.tcc.local.tcc.utils.ResultHolder;
import io.seata.rm.tcc.api.BusinessActionContext;

/**
 * @author lw
 */
public class UserServiceImpl implements UserService {

    @Override
    public boolean prepare(BusinessActionContext actionContext, int a) {
        String xid = actionContext.getXid();
        System.out.println("TccActionOne prepare, xid:" + xid);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        System.out.println("TccActionOne commit, xid:" + xid);
        ResultHolder.setActionOneResult(xid, "T");
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        System.out.println("TccActionOne rollback, xid:" + xid);
        ResultHolder.setActionOneResult(xid, "R");
        return true;
    }
}
