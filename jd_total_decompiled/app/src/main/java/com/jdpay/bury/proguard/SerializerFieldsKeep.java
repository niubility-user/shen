package com.jdpay.bury.proguard;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@AnnotationKeep
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface SerializerFieldsKeep {
}
