package com.jingdong.common.lbs.proxy;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public final class a {
    public static volatile Context a;
    private static Handler b;

    public static Handler a() {
        if (b == null) {
            b = new Handler(Looper.getMainLooper());
        }
        return b;
    }
}
