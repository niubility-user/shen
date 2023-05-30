package com.jingdong.aura.sdk.update.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* loaded from: classes4.dex */
public final class i {
    private static i a;
    private Handler b;

    private i(Handler handler) {
        this.b = handler;
    }

    public static i a() {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    new HandlerThread("AuraUpdate:MainUIHandler").start();
                    a = new i(new Handler(Looper.getMainLooper()));
                }
            }
        }
        return a;
    }

    public final boolean a(Runnable runnable) {
        return this.b.post(runnable);
    }
}
