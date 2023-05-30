package com.jd.dynamic.b.g.a;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class g extends com.jd.dynamic.b.g.f {
    private final int b = 4;

    /* renamed from: c  reason: collision with root package name */
    private final a f1765c;
    private final e d;

    /* renamed from: e  reason: collision with root package name */
    private final ThreadPoolExecutor f1766e;

    public g() {
        a aVar = new a();
        this.f1765c = aVar;
        e eVar = new e();
        this.d = eVar;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, Integer.MAX_VALUE, TimeUnit.SECONDS, aVar, eVar);
        this.f1766e = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    @Override // com.jd.dynamic.b.g.f
    protected void a(@NotNull com.jd.dynamic.b.g.c cVar) {
        this.f1766e.execute(cVar);
    }
}
