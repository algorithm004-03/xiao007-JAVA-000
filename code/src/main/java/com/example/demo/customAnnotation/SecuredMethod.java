package com.example.demo.customAnnotation;

/**
 * @author lw
 */
public class SecuredMethod {

    @Secured(isLocked = true)
    public void lockedMethod() {}

    @Secured(isLocked = false)
    public void unlockedMethod() {}
}
