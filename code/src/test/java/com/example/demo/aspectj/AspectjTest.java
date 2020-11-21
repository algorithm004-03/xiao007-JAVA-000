package com.example.demo.aspectj;

import com.example.demo.Aspectj.Account;
import com.example.demo.customAnnotation.SecuredMethod;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AspectjTest {

    private Account account;

    @Before
    public void before() {
        account = new Account();
        System.out.println(account.toString());
    }

    @Test
    public void given20AndMin10_whenWithdraw5_thenSuccess() {
        Account myAccount = new Account();
        assertTrue(myAccount.withdraw(5));
    }

    @Test
    public void given20AndMin10_whenWithdraw100_thenFail() {
        Account myAccount = new Account();
        assertFalse(myAccount.withdraw(100));
    }

    @Test
    public void testMethod() throws Exception {
        SecuredMethod service = new SecuredMethod();
        service.unlockedMethod();
        service.lockedMethod();
    }
}
