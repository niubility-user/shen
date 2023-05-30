package com.jingdong.app.mall.g;

import android.os.AsyncTask;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* loaded from: classes19.dex */
public class e implements Executor {

    /* renamed from: g  reason: collision with root package name */
    final ArrayDeque<Runnable> f8431g = new ArrayDeque<>();

    /* renamed from: h  reason: collision with root package name */
    Runnable f8432h;

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Runnable f8433g;

        a(Runnable runnable) {
            this.f8433g = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.e("SafeSerialExecutor", "successfully hook SerialExecutor");
            try {
                try {
                    this.f8433g.run();
                } catch (Exception e2) {
                    if (g.b(e2)) {
                        LogUtil.e("SafeSerialExecutor", "catch Vivo 3Max crash!!!!", LogUtil.extractThrowableInfo(e2));
                        ExceptionReporter.reportExceptionToBugly(new RuntimeException("catch vivo 3max crash", e2));
                    } else {
                        throw e2;
                    }
                }
            } finally {
                e.this.a();
            }
        }
    }

    protected synchronized void a() {
        Runnable poll = this.f8431g.poll();
        this.f8432h = poll;
        if (poll != null) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(poll);
        }
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        this.f8431g.offer(new a(runnable));
        if (this.f8432h == null) {
            a();
        }
    }
}
