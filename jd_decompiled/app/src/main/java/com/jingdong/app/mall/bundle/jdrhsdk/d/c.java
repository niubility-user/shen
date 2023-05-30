package com.jingdong.app.mall.bundle.jdrhsdk.d;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* loaded from: classes2.dex */
public final class c {
    static volatile Context a;
    private static Handler b;

    public static Context a() {
        return a;
    }

    public static void b(Context context) {
        if (context == null) {
            d.e("RiskHandleSDK", "Context\u4e3anull!");
            return;
        }
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        a = context;
    }

    public static Handler c() {
        if (b == null) {
            b = new Handler(Looper.getMainLooper());
        }
        return b;
    }
}
