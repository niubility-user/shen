package com.jingdong.jdpush_new.j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public class o {
    private static o b;
    private ScheduledExecutorService a = Executors.newSingleThreadScheduledExecutor();

    /* loaded from: classes12.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Runnable f12854g;

        a(o oVar, Runnable runnable) {
            this.f12854g = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f12854g.run();
            } catch (Throwable th) {
                g.f("\u7ebf\u7a0b\u6c60\u4efb\u52a1\u8fd0\u884c\u51fa\u9519", th);
            }
        }
    }

    /* loaded from: classes12.dex */
    class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Runnable f12855g;

        b(o oVar, Runnable runnable) {
            this.f12855g = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f12855g.run();
            } catch (Throwable th) {
                g.f("\u7ebf\u7a0b\u6c60\u4efb\u52a1\u8fd0\u884c\u51fa\u9519", th);
            }
        }
    }

    private o() {
    }

    public static o b() {
        if (b == null) {
            synchronized (o.class) {
                if (b == null) {
                    b = new o();
                }
            }
        }
        return b;
    }

    public void a(Runnable runnable) {
        this.a.execute(new a(this, runnable));
    }

    public ScheduledFuture<?> c(Runnable runnable, long j2, TimeUnit timeUnit) {
        return this.a.schedule(new b(this, runnable), j2, timeUnit);
    }
}
