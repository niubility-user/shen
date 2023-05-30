package com.jd.jdsec.b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes13.dex */
public class d {
    private ExecutorService a;

    /* loaded from: classes13.dex */
    private static class b {
        private static d a = new d();
    }

    public static d a() {
        return b.a;
    }

    public Future b(Runnable runnable) {
        return this.a.submit(runnable);
    }

    private d() {
        this.a = Executors.newCachedThreadPool();
    }
}
