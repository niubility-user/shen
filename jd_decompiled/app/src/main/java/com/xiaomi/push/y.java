package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
class y {
    static int a;

    public static t a(Context context) {
        if (a8.i()) {
            a = 1;
            return new x(context);
        } else if (p.g(context)) {
            a = 2;
            return new p(context);
        } else if (a0.j(context)) {
            a = 4;
            return new a0(context);
        } else if (f0.c(context)) {
            a = 5;
            return new f0(context);
        } else if (w.b(context)) {
            a = 3;
            return new u(context);
        } else {
            a = 0;
            return new e0();
        }
    }
}
