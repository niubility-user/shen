package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
class w {
    private static volatile boolean a;

    private static void a(Class<?> cls, Context context) {
        if (a) {
            return;
        }
        try {
            a = true;
            cls.getDeclaredMethod("InitEntry", Context.class).invoke(cls, context);
        } catch (Throwable th) {
            g.j.a.a.a.c.o("mdid:load lib error " + th);
        }
    }

    public static boolean b(Context context) {
        try {
            Class<?> c2 = r9.c(context, "com.bun.miitmdid.core.JLibrary");
            if (c2 != null) {
                a(c2, context);
                return true;
            }
            return false;
        } catch (Throwable th) {
            g.j.a.a.a.c.o("mdid:check error " + th);
            return false;
        }
    }
}
