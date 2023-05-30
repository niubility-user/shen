package com.meizu.cloud.pushsdk.d.m;

import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes14.dex */
public final class d {
    private String a = null;
    private Boolean b = null;

    /* renamed from: c  reason: collision with root package name */
    private Integer f15732c = null;
    private Thread.UncaughtExceptionHandler d = null;

    /* renamed from: e  reason: collision with root package name */
    private ThreadFactory f15733e = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements ThreadFactory {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ThreadFactory f15734g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f15735h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ AtomicLong f15736i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Boolean f15737j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ Integer f15738k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ Thread.UncaughtExceptionHandler f15739l;

        a(ThreadFactory threadFactory, String str, AtomicLong atomicLong, Boolean bool, Integer num, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f15734g = threadFactory;
            this.f15735h = str;
            this.f15736i = atomicLong;
            this.f15737j = bool;
            this.f15738k = num;
            this.f15739l = uncaughtExceptionHandler;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread newThread = this.f15734g.newThread(runnable);
            String str = this.f15735h;
            if (str != null) {
                newThread.setName(String.format(str, Long.valueOf(this.f15736i.getAndIncrement())));
            }
            Boolean bool = this.f15737j;
            if (bool != null) {
                newThread.setDaemon(bool.booleanValue());
            }
            Integer num = this.f15738k;
            if (num != null) {
                newThread.setPriority(num.intValue());
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f15739l;
            if (uncaughtExceptionHandler != null) {
                newThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            }
            return newThread;
        }
    }

    private static ThreadFactory d(d dVar) {
        String str = dVar.a;
        Boolean bool = dVar.b;
        Integer num = dVar.f15732c;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = dVar.d;
        ThreadFactory threadFactory = dVar.f15733e;
        if (threadFactory == null) {
            threadFactory = Executors.defaultThreadFactory();
        }
        return new a(threadFactory, str, str != null ? new AtomicLong(0L) : null, bool, num, uncaughtExceptionHandler);
    }

    public d a(Integer num) {
        this.f15732c = num;
        return this;
    }

    public d b(String str) {
        String.format(str, 0);
        this.a = str;
        return this;
    }

    public ThreadFactory c() {
        return d(this);
    }
}
