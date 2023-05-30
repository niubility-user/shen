package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
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
import com.xiaomi.push.r7;
import com.xiaomi.push.s7;
import com.xiaomi.push.t7;
import com.xiaomi.push.x7;
import com.xiaomi.push.y5;
import com.xiaomi.push.y7;
import java.nio.ByteBuffer;

/* loaded from: classes11.dex */
public class z {
    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends n8<T, ?>> y7 a(Context context, T t, c7 c7Var) {
        return b(context, t, c7Var, !c7Var.equals(c7.Registration), context.getPackageName(), o0.c(context).d());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends n8<T, ?>> y7 b(Context context, T t, c7 c7Var, boolean z, String str, String str2) {
        return c(context, t, c7Var, z, str, str2, true);
    }

    protected static <T extends n8<T, ?>> y7 c(Context context, T t, c7 c7Var, boolean z, String str, String str2, boolean z2) {
        String str3;
        byte[] f2 = m8.f(t);
        if (f2 != null) {
            y7 y7Var = new y7();
            if (z) {
                String t2 = o0.c(context).t();
                if (TextUtils.isEmpty(t2)) {
                    str3 = "regSecret is empty, return null";
                } else {
                    try {
                        f2 = y5.c(com.xiaomi.push.m0.b(t2), f2);
                    } catch (Exception unused) {
                        g.j.a.a.a.c.D("encryption error. ");
                    }
                }
            }
            r7 r7Var = new r7();
            r7Var.a = 5L;
            r7Var.f203a = "fakeid";
            y7Var.a(r7Var);
            y7Var.a(ByteBuffer.wrap(f2));
            y7Var.a(c7Var);
            y7Var.b(z2);
            y7Var.b(str);
            y7Var.a(z);
            y7Var.a(str2);
            return y7Var;
        }
        str3 = "invoke convertThriftObjectToBytes method, return null.";
        g.j.a.a.a.c.o(str3);
        return null;
    }

    public static n8 d(Context context, y7 y7Var) {
        byte[] m191a;
        if (y7Var.m193b()) {
            byte[] j2 = v0.j(context, y7Var, r0.ASSEMBLE_PUSH_FCM);
            if (j2 == null) {
                j2 = com.xiaomi.push.m0.b(o0.c(context).t());
            }
            try {
                m191a = y5.b(j2, y7Var.m191a());
            } catch (Exception e2) {
                throw new h1("the aes decrypt failed.", e2);
            }
        } else {
            m191a = y7Var.m191a();
        }
        n8 e3 = e(y7Var.a(), y7Var.f269b);
        if (e3 != null) {
            m8.e(e3, m191a);
        }
        return e3;
    }

    private static n8 e(c7 c7Var, boolean z) {
        switch (a0.a[c7Var.ordinal()]) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends n8<T, ?>> y7 f(Context context, T t, c7 c7Var, boolean z, String str, String str2) {
        return c(context, t, c7Var, z, str, str2, false);
    }
}
