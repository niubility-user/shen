package com.huawei.secure.android.common.ssl.g;

import android.content.Context;

/* loaded from: classes12.dex */
public class c {
    private static Context a;

    public static Context a() {
        return a;
    }

    public static void b(Context context) {
        if (context == null || a != null) {
            return;
        }
        a = context.getApplicationContext();
    }
}
