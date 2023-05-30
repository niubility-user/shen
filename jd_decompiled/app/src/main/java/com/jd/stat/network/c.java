package com.jd.stat.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes18.dex */
public class c {
    private final ExecutorService a;

    /* loaded from: classes18.dex */
    private static class a {
        private static final c a = new c();
    }

    public static c a() {
        return a.a;
    }

    private c() {
        this.a = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public Future a(Runnable runnable) {
        return this.a.submit(runnable);
    }
}
