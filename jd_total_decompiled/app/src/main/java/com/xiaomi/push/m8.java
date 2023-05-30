package com.xiaomi.push;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.push.f9;
import com.xiaomi.push.u8;

/* loaded from: classes11.dex */
public class m8 {
    public static int a(Context context, y7 y7Var) {
        p7 m185a = y7Var.m185a();
        return b(context, y7Var.b, (m185a == null || m185a.m121a() == null) ? null : m185a.m121a().get("channel_id"));
    }

    private static int b(Context context, String str, String str2) {
        com.xiaomi.push.service.y e2;
        NotificationChannel b;
        if (Build.VERSION.SDK_INT < 26 || context == null || TextUtils.isEmpty(str) || (e2 = com.xiaomi.push.service.y.e(context, str)) == null || (b = e2.b(e2.i(str2))) == null) {
            return 0;
        }
        int i2 = (b.getImportance() != 0 ? 1 : 2) + 0;
        int b2 = com.xiaomi.push.service.c2.b(str, b.getId(), 8);
        if (b2 == 1) {
            i2 += 4;
        } else if (b2 == 0) {
            i2 += 8;
        }
        int i3 = i2;
        int b3 = com.xiaomi.push.service.c2.b(str, b.getId(), 16);
        return b3 == 1 ? i3 + 16 : b3 == 0 ? i3 + 32 : i3;
    }

    public static short c(Context context, y7 y7Var) {
        p7 m185a = y7Var.m185a();
        return d(context, y7Var.b, (m185a == null || m185a.m121a() == null) ? null : m185a.m121a().get("channel_id"));
    }

    public static short d(Context context, String str, String str2) {
        return (short) (y4.f(context, str, false).a() + 0 + (h.b(context) ? 4 : 0) + (h.a(context) ? 8 : 0) + (com.xiaomi.push.service.y.t(context) ? 16 : 0) + g(context, str, str2));
    }

    public static <T extends n8<T, ?>> void e(T t, byte[] bArr) {
        if (bArr == null) {
            throw new s8("the message byte is empty.");
        }
        new r8(new f9.a(true, true, bArr.length)).a(t, bArr);
    }

    public static <T extends n8<T, ?>> byte[] f(T t) {
        if (t == null) {
            return null;
        }
        try {
            return new t8(new u8.a()).a(t);
        } catch (s8 e2) {
            g.j.a.a.a.c.q("convertThriftObjectToBytes catch TException.", e2);
            return null;
        }
    }

    private static int g(Context context, String str, String str2) {
        com.xiaomi.push.service.y e2;
        NotificationChannel b;
        if (Build.VERSION.SDK_INT < 26 || context == null || TextUtils.isEmpty(str) || (e2 = com.xiaomi.push.service.y.e(context, str)) == null || (b = e2.b(e2.i(str2))) == null) {
            return 0;
        }
        return b.getImportance() != 0 ? 32 : 64;
    }
}
