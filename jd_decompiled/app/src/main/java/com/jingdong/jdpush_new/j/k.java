package com.jingdong.jdpush_new.j;

import android.content.Context;

/* loaded from: classes12.dex */
public class k {
    public static void a(Context context, short s, String str, int i2) {
        com.jingdong.jdpush_new.g.b c2;
        if (com.jingdong.jdpush_new.d.b.b(s)) {
            if (s == 2104) {
                c2 = com.jingdong.jdpush_new.h.b.c(context, s, com.jingdong.jdpush_new.g.c.b.k(str));
            } else if (s == 2108) {
                c2 = com.jingdong.jdpush_new.h.b.a(context, s, com.jingdong.jdpush_new.g.c.b.k(str));
            } else if (s != 2110) {
                c2 = (s == 2112 || s == 2122) ? com.jingdong.jdpush_new.h.b.b(context, s, com.jingdong.jdpush_new.g.c.d.g(str)) : null;
            } else {
                c2 = com.jingdong.jdpush_new.h.b.d(context, s, com.jingdong.jdpush_new.g.c.b.k(str));
            }
            com.jingdong.jdpush_new.connect.g.c().b(context, c2, i2, c.d(context));
        }
    }
}
