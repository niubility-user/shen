package com.meizu.cloud.pushsdk.d.m;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes14.dex */
public class c implements Executor {

    /* renamed from: g  reason: collision with root package name */
    private final ThreadPoolExecutor f15731g;

    /* loaded from: classes14.dex */
    private static class b {
        private static c a = new c();
    }

    private c() {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        d dVar = new d();
        dVar.b("single-pool-%d");
        this.f15731g = new ThreadPoolExecutor(1, 1, 0L, timeUnit, linkedBlockingQueue, dVar.c());
    }

    public static c a() {
        return b.a;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f15731g.execute(runnable);
    }
}
