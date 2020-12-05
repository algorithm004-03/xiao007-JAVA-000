package com.seata.tcc.local.tcc;

import com.seata.tcc.local.tcc.services.StoreService;
import com.seata.tcc.local.tcc.services.TccTransactionService;
import com.seata.tcc.local.tcc.services.UserService;
import com.seata.tcc.local.tcc.services.impl.StoreServiceImpl;
import com.seata.tcc.local.tcc.services.impl.UserServiceImpl;
import com.seata.tcc.local.tcc.utils.ResultHolder;
import io.seata.common.util.StringUtils;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * TCC 测试启动
 * @author lw
 */
public class TccLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("seata-tcc.xml");

        UserService userService = (UserServiceImpl) context.getBean("UserService");
        StoreService storeService = (StoreServiceImpl) context.getBean("StoreService");

        TccTransactionService tccTransactionService = (TccTransactionService) context.getBean("TccTransactionService");
        tccTransactionService.setUserService(userService);
        tccTransactionService.setStoreService(storeService);

        transactionCommit(tccTransactionService);
        transactionCancel(tccTransactionService);

        new ApplicationKeeper(context).keep();
    }

    /**
     * 演示事务提交成功
     */
    private static void transactionCommit(TccTransactionService tccTransactionService) throws InterruptedException {
        String txId = tccTransactionService.doTransactionCommit();
        System.out.println(txId);
        Assert.isTrue(StringUtils.isNotBlank(txId), "事务开启失败");

        Thread.sleep(1000L);

        Assert.isTrue("T".equals(ResultHolder.getActionOneResult(txId)), "tccActionOne commit failed");
        Assert.isTrue("T".equals(ResultHolder.getActionTwoResult(txId)), "tccActionTwo commit failed");

        System.out.println("transaction commit demo finish.");
    }

    /**
     * 演示事务提交回滚
     */
    private static void transactionCancel(TccTransactionService tccTransactionService) throws InterruptedException {
        Map map = new HashMap(16);
        try{
            tccTransactionService.doTransactionRollback(map);
            Assert.isTrue(false, "分布式事务未回滚");
        }catch (Throwable t) {
            Assert.isTrue(true, "分布式事务异常回滚");
        }
        String txId = (String) map.get("xid");
        Thread.sleep(1000L);

        Assert.isTrue("R".equals(ResultHolder.getActionOneResult(txId)), "tccActionOne commit failed");
        Assert.isTrue("R".equals(ResultHolder.getActionTwoResult(txId)), "tccActionTwo commit failed");

        System.out.println("transaction rollback demo finish.");
    }
}
