package com.jd.aips.tracker.util;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes12.dex */
public class UemsClock {

    /* renamed from: c  reason: collision with root package name */
    private static final UemsClock f1644c = new UemsClock(1);
    private final long a;
    private final AtomicLong b = new AtomicLong(System.currentTimeMillis());

    private UemsClock(long j2) {
        this.a = j2;
        c();
    }

    private void c() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() { // from class: com.jd.aips.tracker.util.UemsClock.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "uems_clock");
                thread.setDaemon(true);
                return thread;
            }
        });
        Runnable runnable = new Runnable() { // from class: com.jd.aips.tracker.util.UemsClock.2
            @Override // java.lang.Runnable
            public void run() {
                UemsClock.this.b.set(System.currentTimeMillis());
            }
        };
        long j2 = this.a;
        scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, j2, j2, TimeUnit.MILLISECONDS);
    }

    public long b() {
        return this.b.get();
    }

    public static UemsClock a() {
        return f1644c;
    }
}
