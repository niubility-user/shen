package com.vivo.push.util;

import android.os.Looper;

/* loaded from: classes11.dex */
public final class g {
    public static void a(String str) {
        if (p.a() && Looper.myLooper() == Looper.getMainLooper()) {
            String str2 = "Operation: " + str + " in main thread!";
            new Throwable();
        }
    }
}
