package com.jingdong.jdpush_new;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jd.lib.push.utils.PushMessageUtils;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.i;
import com.jingdong.jdpush_new.j.m;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes12.dex */
public class a {
    private static final String a = "a";
    public static String b;

    /* renamed from: c */
    private static boolean f12779c;

    public static void a(Context context, int i2, String str, String str2) {
        com.jingdong.jdpush_new.j.c.a(context, i2, str, str2, (short) 2108);
    }

    public static String b() {
        if (TextUtils.isEmpty(b)) {
            String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
            b = readDeviceUUID;
            if (TextUtils.isEmpty(readDeviceUUID)) {
                String b2 = m.b(i.d().a(JdSdk.getInstance().getApplicationContext(), "jsp_pushId", "").toString());
                if (!TextUtils.isEmpty(b2)) {
                    b = b2;
                }
            }
        }
        return b;
    }

    public static void c() {
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        if (com.jingdong.jdpush_new.j.c.h(applicationContext) == 0) {
            g.c("APPID\u9519\u8bef");
        } else if (com.jingdong.jdpush_new.j.c.b(applicationContext) == null) {
            g.c("APPSECRET\u9519\u8bef");
        } else {
            b = StatisticsReportUtil.readDeviceUUID();
            PushMessageUtils.controlPushService(true);
        }
    }

    public static void d() {
        try {
            if (f12779c) {
                return;
            }
            f12779c = true;
            c.b().e();
        } catch (Throwable th) {
            g.g(th);
        }
    }

    public static void e(Context context, int i2, String str, String str2, int i3, String str3, String str4) {
        if (i2 != 0 && TextUtils.isEmpty(str)) {
            str = str4 + CartConstant.KEY_YB_INFO_LINK + str3;
        }
        short s = i3 == 1 ? (short) 2112 : (short) 2122;
        g.d(a, str + ((int) s));
        com.jingdong.jdpush_new.j.c.s(context, i2, str, str2, s);
    }

    public static void f(Context context, int i2, String str, int i3) {
        com.jingdong.jdpush_new.j.c.u(context, i2, str, (short) 2104, i3);
    }

    public static void g(int i2, Class<?> cls) {
        com.jingdong.jdpush_new.j.b.a().c(i2, cls);
    }

    public static void h(Context context, Class<?> cls) {
        if (!i.d().a(context, "jsp_intentService", "").equals(cls.getName())) {
            i.d().f(context, "jsp_intentService", cls.getName());
        }
        c.c(context);
    }

    public static void i(Context context, int i2, String str, String str2) {
        com.jingdong.jdpush_new.j.c.a(context, i2, str, str2, (short) 2110);
    }

    public static void j(Context context) {
        context.stopService(new Intent(context, JDSPushService.class));
    }
}
