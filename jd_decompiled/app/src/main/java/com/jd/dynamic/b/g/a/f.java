package com.jd.dynamic.b.g.a;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class f extends ThreadLocal<com.jd.dynamic.b.g.d> {
    @Override // java.lang.ThreadLocal
    @NotNull
    /* renamed from: a */
    public com.jd.dynamic.b.g.d initialValue() {
        com.jd.dynamic.b.g.d caches = com.jd.dynamic.b.g.e.a().b();
        caches.a();
        Intrinsics.checkExpressionValueIsNotNull(caches, "caches");
        return caches;
    }
}
