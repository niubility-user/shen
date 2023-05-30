package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.e8;
import com.xiaomi.push.f8;
import com.xiaomi.push.g8;
import com.xiaomi.push.i8;
import com.xiaomi.push.k8;
import com.xiaomi.push.l8;
import com.xiaomi.push.m8;
import com.xiaomi.push.n8;
import com.xiaomi.push.s7;
import com.xiaomi.push.t7;
import com.xiaomi.push.x7;
import com.xiaomi.push.y7;

/* loaded from: classes11.dex */
public class j1 {
    public static n8 a(Context context, y7 y7Var) {
        if (y7Var.m193b()) {
            return null;
        }
        byte[] m191a = y7Var.m191a();
        n8 b = b(y7Var.a(), y7Var.f269b);
        if (b != null) {
            m8.e(b, m191a);
        }
        return b;
    }

    private static n8 b(c7 c7Var, boolean z) {
        switch (k1.a[c7Var.ordinal()]) {
            case 1:
                return new e8();
            case 2:
                return new k8();
            case 3:
                return new i8();
            case 4:
                return new l8();
            case 5:
                return new g8();
            case 6:
                return new s7();
            case 7:
                return new x7();
            case 8:
                return new f8();
            case 9:
                if (z) {
                    return new c8();
                }
                t7 t7Var = new t7();
                t7Var.a(true);
                return t7Var;
            case 10:
                return new x7();
            default:
                return null;
        }
    }
}
