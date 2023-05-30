package com.meizu.cloud.pushsdk.d.m;

import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes14.dex */
public class a implements Executor {

    /* renamed from: g  reason: collision with root package name */
    private final ThreadPoolExecutor f15729g;

    /* loaded from: classes14.dex */
    private static class b {
        private static a a = new a();
    }

    private a() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        d dVar = new d();
        dVar.b("io-pool-%d");
        this.f15729g = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, timeUnit, synchronousQueue, dVar.c());
    }

    public static a a() {
        return b.a;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f15729g.execute(runnable);
    }
}
