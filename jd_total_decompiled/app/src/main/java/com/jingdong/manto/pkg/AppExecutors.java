package com.jingdong.manto.pkg;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes16.dex */
public class AppExecutors {
    private final Executor a;
    private final Executor b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f13966c;

    /* loaded from: classes16.dex */
    private static class b implements Executor {
        private Handler a;

        private b() {
            this.a = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.a.post(runnable);
        }
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(5), new b());
    }

    private AppExecutors(Executor executor, Executor executor2, Executor executor3) {
        this.a = executor;
        this.b = executor2;
        this.f13966c = executor3;
    }

    public Executor diskIO() {
        return this.a;
    }

    public Executor mainThread() {
        return this.f13966c;
    }

    public Executor networkIO() {
        return this.b;
    }
}
