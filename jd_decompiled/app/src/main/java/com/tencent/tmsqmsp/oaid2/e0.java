package com.tencent.tmsqmsp.oaid2;

import android.content.Context;

/* loaded from: classes9.dex */
public class e0 {
    public static String a(Context context) {
        if (d0.a) {
            return h0.f18080f.a(d0.a(context), "AUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }

    public static boolean a() {
        if (d0.a) {
            return d0.b;
        }
        throw new RuntimeException("SDK Need Init First!");
    }

    public static String b(Context context) {
        if (d0.a) {
            return h0.f18080f.a(d0.a(context), "OUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }

    public static void c(Context context) {
        d0.b = h0.f18080f.a(d0.a(context));
        d0.a = true;
    }
}
