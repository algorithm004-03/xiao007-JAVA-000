package com.example.demo.Aspectj;

import com.example.demo.customAnnotation.Secured;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author lw
 */
@Aspect
public class SecuredMethodAspect {

    @Pointcut("@annotation(secured)")
    public void callAt(Secured secured) { }

    @Around("callAt(secured)")
    public void around(ProceedingJoinPoint point, Secured secured) throws Throwable {
        System.out.println("secure around");
    }
}
