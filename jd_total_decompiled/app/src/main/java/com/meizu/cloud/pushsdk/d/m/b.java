package com.meizu.cloud.pushsdk.d.m;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes14.dex */
public class b implements Executor {

    /* renamed from: g  reason: collision with root package name */
    private final ThreadPoolExecutor f15730g;

    /* renamed from: com.meizu.cloud.pushsdk.d.m.b$b  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    private static class C0757b {
        private static b a = new b();
    }

    private b() {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        d dVar = new d();
        dVar.a(10);
        dVar.b("message-pool-%d");
        this.f15730g = new ThreadPoolExecutor(1, 1, 0L, timeUnit, linkedBlockingQueue, dVar.c());
    }

    public static b a() {
        return C0757b.a;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f15730g.execute(runnable);
    }
}
