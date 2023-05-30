package com.tencent.mapsdk.core.components.protocol.service.net.annotation;

import androidx.annotation.Keep;
import g.i.a.a.a;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Keep
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface NetJceResolver {
    Class<? extends a> inJce();

    Class<? extends a> outJce();

    int[] queryRange();
}
