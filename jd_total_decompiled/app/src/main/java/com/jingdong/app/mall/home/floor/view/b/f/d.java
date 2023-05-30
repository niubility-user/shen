package com.jingdong.app.mall.home.floor.view.b.f;

import android.text.TextUtils;
import com.jingdong.app.mall.home.o.a.k;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes4.dex */
public class d {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    private static com.jingdong.app.mall.home.r.c.b b = new com.jingdong.app.mall.home.r.c.b();

    /* renamed from: c */
    private static String f9756c;

    static {
        h();
    }

    public static void a(String str, int i2) {
        b(str, i2, false);
    }

    public static void b(String str, int i2, boolean z) {
        if (TextUtils.isEmpty(f9756c)) {
            h();
        }
        int optInt = b.optInt(str);
        if (optInt >= i2) {
            return;
        }
        b.put(str, optInt + 1);
        if (z) {
            i();
        }
    }

    public static int c(String str, int i2) {
        int M = com.jingdong.app.mall.home.o.a.f.M(str, 0) + 1;
        if (M <= i2) {
            com.jingdong.app.mall.home.o.a.f.y0(str, M);
            com.jingdong.app.mall.home.o.a.f.r0("DayTimesUtil", str + " : " + M);
        }
        return M;
    }

    public static void d(String str, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(f9756c);
        boolean z = com.jingdong.app.mall.home.o.a.f.M(sb.toString(), 0) > 0;
        int M = com.jingdong.app.mall.home.o.a.f.M(str, 0) + 1;
        if (z || M > i2) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.y0(str + f9756c, 1);
        com.jingdong.app.mall.home.o.a.f.y0(str, M);
        com.jingdong.app.mall.home.o.a.f.r0("DayTimesUtil", str + " : " + M);
    }

    private static int e(String str) {
        if (TextUtils.isEmpty(f9756c)) {
            h();
        }
        if (!TextUtils.equals(f9756c, a.format(new Date(System.currentTimeMillis())))) {
            b = new com.jingdong.app.mall.home.r.c.b();
            i();
            return 0;
        }
        return b.optInt(str, 0);
    }

    public static boolean f(String str, int i2) {
        return e(str) < i2;
    }

    public static boolean g(String str, int i2) {
        return com.jingdong.app.mall.home.o.a.f.M(str, 0) < i2 || i2 < 0;
    }

    private static void h() {
        b = com.jingdong.app.mall.home.r.c.b.c(com.jingdong.app.mall.home.o.a.f.O("HOME_DAY_TIMES_KEY", ""));
        f9756c = a.format(new Date(System.currentTimeMillis()));
        if (!TextUtils.equals(f9756c, b.optString("dayTime"))) {
            com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
            b = bVar;
            bVar.put("dayTime", f9756c);
            i();
        }
        String jSONObject = b.toString();
        k.e(jSONObject);
        com.jingdong.app.mall.home.o.a.f.r0(d.class, jSONObject);
    }

    public static void i() {
        if (b == null || TextUtils.isEmpty(f9756c)) {
            return;
        }
        String format = a.format(new Date(System.currentTimeMillis()));
        if (!TextUtils.equals(f9756c, format)) {
            f9756c = format;
            h();
        }
        com.jingdong.app.mall.home.o.a.f.A0("HOME_DAY_TIMES_KEY", b.toString());
    }
}
