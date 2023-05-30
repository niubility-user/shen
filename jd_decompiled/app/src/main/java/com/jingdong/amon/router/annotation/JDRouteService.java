package com.jingdong.amon.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface JDRouteService {
    String constructor() default "";

    String host() default "";

    Class[] interfaces() default {Object.class};

    String path();

    String scheme() default "";

    boolean singleton() default false;
}
