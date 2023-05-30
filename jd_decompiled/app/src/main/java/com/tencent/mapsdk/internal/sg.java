package com.tencent.mapsdk.internal;

import android.content.Context;

/* loaded from: classes9.dex */
public class sg {
    private static final int a = 0;
    private static int b;

    public static int a() {
        int i2;
        synchronized (sg.class) {
            i2 = b;
        }
        return i2;
    }

    public static void a(Context context) {
        synchronized (sg.class) {
            b = kc.a(context).b(l4.z);
        }
    }

    public static void a(Context context, int i2) {
        synchronized (sg.class) {
            b = i2;
            kc.a(context).b(l4.z, i2);
        }
    }
}
