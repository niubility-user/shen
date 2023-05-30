package com.meizu.cloud.pushsdk.d;

import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes14.dex */
public final class j {
    private String a = null;
    private Boolean b = null;

    /* renamed from: c  reason: collision with root package name */
    private Integer f15713c = null;
    private Thread.UncaughtExceptionHandler d = null;

    /* renamed from: e  reason: collision with root package name */
    private ThreadFactory f15714e = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements ThreadFactory {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ThreadFactory f15715g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f15716h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ AtomicLong f15717i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Boolean f15718j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ Integer f15719k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ Thread.UncaughtExceptionHandler f15720l;

        a(ThreadFactory threadFactory, String str, AtomicLong atomicLong, Boolean bool, Integer num, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f15715g = threadFactory;
            this.f15716h = str;
            this.f15717i = atomicLong;
            this.f15718j = bool;
            this.f15719k = num;
            this.f15720l = uncaughtExceptionHandler;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread newThread = this.f15715g.newThread(runnable);
            String str = this.f15716h;
            if (str != null) {
                newThread.setName(String.format(str, Long.valueOf(this.f15717i.getAndIncrement())));
            }
            Boolean bool = this.f15718j;
            if (bool != null) {
                newThread.setDaemon(bool.booleanValue());
            }
            Integer num = this.f15719k;
            if (num != null) {
                newThread.setPriority(num.intValue());
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f15720l;
            if (uncaughtExceptionHandler != null) {
                newThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            }
            return newThread;
        }
    }

    private static ThreadFactory c(j jVar) {
        String str = jVar.a;
        Boolean bool = jVar.b;
        Integer num = jVar.f15713c;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = jVar.d;
        ThreadFactory threadFactory = jVar.f15714e;
        if (threadFactory == null) {
            threadFactory = Executors.defaultThreadFactory();
        }
        return new a(threadFactory, str, str != null ? new AtomicLong(0L) : null, bool, num, uncaughtExceptionHandler);
    }

    public j a(String str) {
        String.format(str, 0);
        this.a = str;
        return this;
    }

    public ThreadFactory b() {
        return c(this);
    }
}
