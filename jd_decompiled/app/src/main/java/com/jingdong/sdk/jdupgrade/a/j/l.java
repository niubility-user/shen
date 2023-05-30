package com.jingdong.sdk.jdupgrade.a.j;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes7.dex */
public class l {
    private static l b;
    private final Handler a = new Handler(Looper.getMainLooper());

    private l() {
    }

    public static l a() {
        if (b == null) {
            b = new l();
        }
        return b;
    }

    public void a(Runnable runnable) {
        if (Thread.currentThread() == this.a.getLooper().getThread()) {
            runnable.run();
        } else {
            this.a.post(runnable);
        }
    }
}
