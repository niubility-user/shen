package com.jd.fireeye.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes13.dex */
public class c {
    private ExecutorService a;

    /* loaded from: classes13.dex */
    private static class b {
        private static c a = new c();
    }

    public static c a() {
        return b.a;
    }

    private c() {
        this.a = Executors.newCachedThreadPool();
    }

    public Future a(Runnable runnable) {
        return this.a.submit(runnable);
    }
}
