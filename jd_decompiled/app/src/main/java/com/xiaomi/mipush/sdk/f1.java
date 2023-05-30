package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.a8;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m7;
import com.xiaomi.push.y7;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class f1 {
    private static volatile f1 b;
    private final Context a;

    private f1(Context context) {
        this.a = context.getApplicationContext();
    }

    private static f1 a(Context context) {
        if (b == null) {
            synchronized (f1.class) {
                if (b == null) {
                    b = new f1(context);
                }
            }
        }
        return b;
    }

    public static void b(Context context, y7 y7Var) {
        a(context).d(y7Var, 0, true);
    }

    public static void c(Context context, y7 y7Var, boolean z) {
        a(context).d(y7Var, 1, z);
    }

    private void d(y7 y7Var, int i2, boolean z) {
        if (a8.j(this.a) || !a8.i() || y7Var == null || y7Var.a != c7.SendMessage || y7Var.m185a() == null || !z) {
            return;
        }
        g.j.a.a.a.c.o("click to start activity result:" + String.valueOf(i2));
        c8 c8Var = new c8(y7Var.m185a().m120a(), false);
        c8Var.c(m7.SDK_START_ACTIVITY.f179a);
        c8Var.b(y7Var.m186a());
        c8Var.d(y7Var.b);
        HashMap hashMap = new HashMap();
        c8Var.f113a = hashMap;
        hashMap.put("result", String.valueOf(i2));
        f0.h(this.a).D(c8Var, c7.Notification, false, false, null, true, y7Var.b, y7Var.f265a, true, false);
    }

    public static void e(Context context, y7 y7Var, boolean z) {
        a(context).d(y7Var, 2, z);
    }

    public static void f(Context context, y7 y7Var, boolean z) {
        a(context).d(y7Var, 3, z);
    }

    public static void g(Context context, y7 y7Var, boolean z) {
        a(context).d(y7Var, 4, z);
    }

    public static void h(Context context, y7 y7Var, boolean z) {
        f1 a;
        int i2;
        o0 c2 = o0.c(context);
        if (TextUtils.isEmpty(c2.q()) || TextUtils.isEmpty(c2.t())) {
            a = a(context);
            i2 = 6;
        } else {
            boolean x = c2.x();
            a = a(context);
            i2 = x ? 7 : 5;
        }
        a.d(y7Var, i2, z);
    }
}
