package com.jingdong.sdk.threadpool.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes10.dex */
public class g extends ThreadPoolExecutor {

    /* renamed from: g  reason: collision with root package name */
    private static c f15570g = new c();

    public g(int i2, int i3, long j2, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i2, i3, j2, TimeUnit.SECONDS, blockingQueue, threadFactory, f15570g);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        super.execute(runnable);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new FutureTask(runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        runnable.getClass();
        if (runnable instanceof f) {
            b bVar = new b((f) runnable);
            execute(bVar);
            return bVar;
        }
        RunnableFuture newTaskFor = newTaskFor(runnable, null);
        execute(newTaskFor);
        return newTaskFor;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void terminated() {
        super.terminated();
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        runnable.getClass();
        if (runnable instanceof f) {
            b bVar = new b((f) runnable);
            execute(bVar);
            return bVar;
        }
        RunnableFuture<T> newTaskFor = newTaskFor(runnable, t);
        execute(newTaskFor);
        return newTaskFor;
    }
}
