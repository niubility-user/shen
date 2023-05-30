package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.push.e8;
import com.xiaomi.push.m8;
import com.xiaomi.push.s8;
import com.xiaomi.push.y5;
import com.xiaomi.push.y7;

/* loaded from: classes11.dex */
public class m2 {
    public static e8 a(y7 y7Var) {
        byte[] m191a = y7Var.m191a();
        e8 e8Var = new e8();
        try {
            m8.e(e8Var, m191a);
            return e8Var;
        } catch (s8 unused) {
            return null;
        }
    }

    public static void b(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("ext_fcm_container_buffer");
        String stringExtra2 = intent.getStringExtra("mipush_app_package");
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
            return;
        }
        try {
            byte[] d = d(Base64.decode(stringExtra, 2), context.getSharedPreferences("mipush_apps_scrt", 0).getString(stringExtra2, null));
            if (d != null) {
                n.r(context, x2.d(d), d);
            } else {
                g.j.a.a.a.c.o("notify fcm notification error \uff1adencrypt failed");
            }
        } catch (Throwable th) {
            g.j.a.a.a.c.q("notify fcm notification error ", th);
        }
    }

    public static void c(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        context.getSharedPreferences("mipush_apps_scrt", 0).edit().putString(str, str2).apply();
    }

    public static byte[] d(byte[] bArr, String str) {
        if (TextUtils.isEmpty(str)) {
            g.j.a.a.a.c.o("secret is empty, return null");
            return null;
        }
        try {
            return y5.b(com.xiaomi.push.m0.b(str), bArr);
        } catch (Exception e2) {
            g.j.a.a.a.c.q("dencryption error. ", e2);
            return null;
        }
    }
}
