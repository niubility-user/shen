package com.jd.dynamic.lib.utils;

/* loaded from: classes13.dex */
public class h {
    public static void a(Runnable runnable) {
        if (runnable != null) {
            if (com.jd.dynamic.b.a.b.o().r()) {
                q.THREAD_POOL_EXECUTOR.execute(runnable);
            } else {
                runnable.run();
            }
        }
    }
}
