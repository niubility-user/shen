package com.xiaomi.mipush.sdk;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.push.a8;
import com.xiaomi.push.c8;
import com.xiaomi.push.h7;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.n8;
import com.xiaomi.push.r3;
import com.xiaomi.push.t3;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class b1 {
    public static void a(Context context, Intent intent, Uri uri) {
        r3 b;
        t3 t3Var;
        if (context == null) {
            return;
        }
        f0.h(context).m();
        if (r3.b(context.getApplicationContext()).c() == null) {
            r3.b(context.getApplicationContext()).l(o0.c(context.getApplicationContext()).d(), context.getPackageName(), com.xiaomi.push.service.b0.d(context.getApplicationContext()).a(h7.AwakeInfoUploadWaySwitch.a(), 0), new p0());
            com.xiaomi.push.service.b0.d(context).j(new d1(102, "awake online config", context));
        }
        if ((context instanceof Activity) && intent != null) {
            b = r3.b(context.getApplicationContext());
            t3Var = t3.ACTIVITY;
        } else if (!(context instanceof Service) || intent == null) {
            if (uri == null || TextUtils.isEmpty(uri.toString())) {
                return;
            }
            r3.b(context.getApplicationContext()).h(t3.PROVIDER, context, null, uri.toString());
            return;
        } else if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
            b = r3.b(context.getApplicationContext());
            t3Var = t3.SERVICE_COMPONENT;
        } else {
            b = r3.b(context.getApplicationContext());
            t3Var = t3.SERVICE_ACTION;
        }
        b.h(t3Var, context, intent, null);
    }

    private static void b(Context context, c8 c8Var) {
        boolean m2 = com.xiaomi.push.service.b0.d(context).m(h7.AwakeAppPingSwitch.a(), false);
        int a = com.xiaomi.push.service.b0.d(context).a(h7.AwakeAppPingFrequency.a(), 0);
        if (a >= 0 && a < 30) {
            g.j.a.a.a.c.B("aw_ping: frquency need > 30s.");
            a = 30;
        }
        boolean z = a >= 0 ? m2 : false;
        if (!a8.i()) {
            c(context, c8Var, z, a);
        } else if (z) {
            com.xiaomi.push.i.b(context.getApplicationContext()).j(new c1(c8Var, context), a);
        }
    }

    public static final <T extends n8<T, ?>> void c(Context context, T t, boolean z, int i2) {
        byte[] f2 = m8.f(t);
        if (f2 == null) {
            g.j.a.a.a.c.o("send message fail, because msgBytes is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_help_ping");
        intent.putExtra("extra_help_ping_switch", z);
        intent.putExtra("extra_help_ping_frequency", i2);
        intent.putExtra("mipush_payload", f2);
        intent.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
        f0.h(context).r(intent);
    }

    public static void d(Context context, String str) {
        g.j.a.a.a.c.o("aw_ping : send aw_ping cmd and content to push service from 3rd app");
        HashMap hashMap = new HashMap();
        hashMap.put("awake_info", str);
        hashMap.put("event_type", String.valueOf(9999));
        hashMap.put("description", "ping message");
        c8 c8Var = new c8();
        c8Var.b(o0.c(context).d());
        c8Var.d(context.getPackageName());
        c8Var.c(m7.AwakeAppResponse.f179a);
        c8Var.a(com.xiaomi.push.service.f0.a());
        c8Var.f113a = hashMap;
        b(context, c8Var);
    }

    public static void e(Context context, String str, int i2, String str2) {
        c8 c8Var = new c8();
        c8Var.b(str);
        c8Var.a(new HashMap());
        c8Var.m35a().put("extra_aw_app_online_cmd", String.valueOf(i2));
        c8Var.m35a().put("extra_help_aw_info", str2);
        c8Var.a(com.xiaomi.push.service.f0.a());
        byte[] f2 = m8.f(c8Var);
        if (f2 == null) {
            g.j.a.a.a.c.o("send message fail, because msgBytes is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_aw_app_logic");
        intent.putExtra("mipush_payload", f2);
        f0.h(context).r(intent);
    }
}
