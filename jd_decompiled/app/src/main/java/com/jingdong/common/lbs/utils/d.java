package com.jingdong.common.lbs.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class d {
    private static d a;
    private static Executor b;

    public static d a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                    b = Executors.newCachedThreadPool();
                }
            }
        }
        return a;
    }

    public static void a(Runnable runnable) {
        try {
            b.execute(runnable);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
