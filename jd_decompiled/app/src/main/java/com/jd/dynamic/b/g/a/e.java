package com.jd.dynamic.b.g.a;

import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class e implements ThreadFactory {

    /* renamed from: g  reason: collision with root package name */
    private final String f1762g = "DYThread";

    /* renamed from: h  reason: collision with root package name */
    private final AtomicInteger f1763h = new AtomicInteger(0);

    /* renamed from: i  reason: collision with root package name */
    private final ArrayList<d> f1764i = new ArrayList<>();

    @Override // java.util.concurrent.ThreadFactory
    @NotNull
    public Thread newThread(@NotNull Runnable runnable) {
        d dVar = new d(runnable, this.f1762g + "-" + this.f1763h.getAndIncrement());
        this.f1764i.add(dVar);
        return dVar;
    }
}
