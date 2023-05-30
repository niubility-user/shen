package com.wjlogin.onekey.sdk.common.a.a;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes11.dex */
public class h {
    private static h a;
    private static Executor b;

    public static h a() {
        if (a == null) {
            synchronized (h.class) {
                if (a == null) {
                    a = new h();
                    b = Executors.newCachedThreadPool();
                }
            }
        }
        return a;
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            try {
                b.execute(runnable);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
