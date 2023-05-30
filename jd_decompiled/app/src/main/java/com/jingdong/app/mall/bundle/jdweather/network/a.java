package com.jingdong.app.mall.bundle.jdweather.network;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class a {
    private static volatile a b;
    private volatile Executor a;

    private a() {
        if (this.a == null) {
            this.a = Executors.newSingleThreadExecutor();
        }
    }

    public static synchronized a b() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                synchronized (a.class) {
                    if (b == null) {
                        b = new a();
                    }
                }
            }
            aVar = b;
        }
        return aVar;
    }

    public synchronized void a(d dVar) {
        if (this.a != null && dVar != null) {
            this.a.execute(new c(dVar));
        }
    }
}
