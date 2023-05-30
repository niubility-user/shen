package com.jingdong.jdpush_new.e;

import android.content.Context;

/* loaded from: classes12.dex */
public class b {
    private static b a;

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public com.jingdong.jdpush_new.g.b b(Context context, short s, String str) {
        if (com.jingdong.jdpush_new.d.b.b(s)) {
            if (s != 2100) {
                if (s != 2104) {
                    if (s != 2108) {
                        if (s != 2110) {
                            if (s != 2112) {
                                if (s == 2118) {
                                    return com.jingdong.jdpush_new.h.a.b(context, com.jingdong.jdpush_new.g.c.c.k(str));
                                }
                                if (s != 2122) {
                                    if (s == 2124) {
                                        return com.jingdong.jdpush_new.h.a.g(str);
                                    }
                                }
                            }
                            return com.jingdong.jdpush_new.h.a.d(context, s, str);
                        }
                        return com.jingdong.jdpush_new.h.a.f(context, str);
                    }
                    return com.jingdong.jdpush_new.h.a.a(context, str);
                }
                return com.jingdong.jdpush_new.h.a.e(context, str);
            }
            return com.jingdong.jdpush_new.h.a.c(context);
        }
        return null;
    }
}
