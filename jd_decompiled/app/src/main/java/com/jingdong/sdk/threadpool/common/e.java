package com.jingdong.sdk.threadpool.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes10.dex */
final class e {
    private static e b = new e();
    private ScheduledExecutorService a = Executors.newScheduledThreadPool(1, new d("Task_Dispatcher", 5));

    /* loaded from: classes10.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Disposable f15551g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ ExecutorService f15552h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Runnable f15553i;

        a(e eVar, Disposable disposable, ExecutorService executorService, Runnable runnable) {
            this.f15551g = disposable;
            this.f15552h = executorService;
            this.f15553i = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f15551g.setFuture(this.f15552h.submit(this.f15553i));
        }
    }

    private e() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static e a() {
        return b;
    }

    public Disposable b(long j2, ExecutorService executorService, Runnable runnable) {
        Future<?> schedule;
        Disposable disposable = new Disposable();
        if (j2 <= 0) {
            schedule = executorService.submit(runnable);
        } else {
            schedule = this.a.schedule(new a(this, disposable, executorService, runnable), j2, TimeUnit.MILLISECONDS);
        }
        disposable.setFuture(schedule);
        return disposable;
    }
}
