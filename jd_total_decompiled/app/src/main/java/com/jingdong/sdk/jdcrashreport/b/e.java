package com.jingdong.sdk.jdcrashreport.b;

import java.io.File;
import java.util.concurrent.Callable;

/* loaded from: classes7.dex */
public class e implements Callable<Boolean> {
    private e() {
    }

    public static e a() {
        return new e();
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public Boolean call() {
        File[] h2 = j.h();
        return Boolean.valueOf((h2 == null ? 0 : h2.length) != 0);
    }
}
