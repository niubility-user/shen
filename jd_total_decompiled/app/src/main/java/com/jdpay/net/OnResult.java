package com.jdpay.net;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface OnResult {
    public static final int THREAD_MAIN = 1;
    public static final int THREAD_WORKER = 0;

    boolean isThrowable() default false;

    int onThread() default 1;
}
