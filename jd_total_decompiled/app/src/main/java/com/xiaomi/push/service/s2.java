package com.xiaomi.push.service;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.xiaomi.push.a8;
import com.xiaomi.push.r9;
import com.xiaomi.push.s9;
import com.xiaomi.push.service.i0;
import com.xiaomi.push.y4;
import java.util.Locale;

/* loaded from: classes11.dex */
public class s2 {
    public final String a;
    public final String b;

    /* renamed from: c */
    public final String f19187c;
    public final String d;

    /* renamed from: e */
    public final String f19188e;

    /* renamed from: f */
    public final String f19189f;

    /* renamed from: g */
    public final int f19190g;

    public s2(String str, String str2, String str3, String str4, String str5, String str6, int i2) {
        this.a = str;
        this.b = str2;
        this.f19187c = str3;
        this.d = str4;
        this.f19188e = str5;
        this.f19189f = str6;
        this.f19190g = i2;
    }

    private static String c(Context context) {
        if ("com.xiaomi.xmsf".equals(context)) {
            if (TextUtils.isEmpty(null)) {
                String g2 = a8.g("ro.miui.region");
                return TextUtils.isEmpty(g2) ? a8.g("ro.product.locale.region") : g2;
            }
            return null;
        }
        return a8.n();
    }

    public static boolean d() {
        try {
            return r9.c(null, "miui.os.Build").getField("IS_ALPHA_BUILD").getBoolean(null);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean e(Context context) {
        return "com.xiaomi.xmsf".equals(context.getPackageName()) && d();
    }

    private static boolean f(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }

    public i0.b a(XMPushService xMPushService) {
        i0.b bVar = new i0.b(xMPushService);
        b(bVar, xMPushService, xMPushService.m156b(), "c");
        return bVar;
    }

    public i0.b b(i0.b bVar, Context context, h2 h2Var, String str) {
        bVar.a = context.getPackageName();
        bVar.b = this.a;
        bVar.f19101i = this.f19187c;
        bVar.f19096c = this.b;
        bVar.f19100h = "5";
        bVar.d = "XMPUSH-PASS";
        bVar.f19097e = false;
        s9.a aVar = new s9.a();
        aVar.a("sdk_ver", 48);
        aVar.a("cpvn", "5_3_0-C");
        aVar.a("cpvc", 50300);
        aVar.a("country_code", b.a(context).f());
        aVar.a("region", b.a(context).b());
        aVar.a("miui_vn", a8.q());
        aVar.a("miui_vc", Integer.valueOf(a8.b(context)));
        aVar.a("xmsf_vc", Integer.valueOf(y4.b(context, "com.xiaomi.xmsf")));
        aVar.a("android_ver", Integer.valueOf(Build.VERSION.SDK_INT));
        aVar.a("n_belong_to_app", Boolean.valueOf(y.t(context)));
        aVar.a("systemui_vc", Integer.valueOf(y4.a(context)));
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            aVar.a("latest_country_code", c2);
        }
        String s = a8.s();
        if (!TextUtils.isEmpty(s)) {
            aVar.a("device_ch", s);
        }
        String u = a8.u();
        if (!TextUtils.isEmpty(u)) {
            aVar.a("device_mfr", u);
        }
        bVar.f19098f = aVar.toString();
        String str2 = f(context) ? "1000271" : this.d;
        s9.a aVar2 = new s9.a();
        aVar2.a("appid", str2);
        aVar2.a("locale", Locale.getDefault().toString());
        aVar2.a("sync", 1);
        if (e(context)) {
            aVar2.a(CartCleanConstants.CART_CLEAN_DIALOG_AB, str);
        }
        bVar.f19099g = aVar2.toString();
        bVar.f19103k = h2Var;
        return bVar;
    }
}
