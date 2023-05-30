package com.jingdong.app.mall.home.floor.common.i;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class g {
    private static volatile JDJSONObject a;
    private static boolean b;

    /* renamed from: c */
    private static boolean f9294c;
    private static boolean d;

    /* renamed from: e */
    private static boolean f9295e;

    /* renamed from: f */
    private static boolean f9296f;

    /* renamed from: g */
    private static boolean f9297g;

    /* renamed from: h */
    private static boolean f9298h;

    /* renamed from: i */
    private static boolean f9299i;

    /* renamed from: j */
    private static boolean f9300j;

    /* renamed from: k */
    private static boolean f9301k;

    /* renamed from: l */
    public static boolean f9302l;

    /* renamed from: m */
    public static boolean f9303m;

    /* renamed from: n */
    public static boolean f9304n;
    public static String o;
    public static String p;
    private static int q;
    private static int r;
    private static int s;
    private static int t;

    public static int a() {
        return q;
    }

    public static boolean b() {
        return f9296f;
    }

    public static boolean c() {
        return f9301k;
    }

    public static int d() {
        int i2 = s;
        if (i2 <= 0) {
            i2 = 12;
        }
        return com.jingdong.app.mall.home.floor.common.d.d(i2);
    }

    public static int e(String str) {
        return f(str, 0);
    }

    public static int f(String str, int i2) {
        return a == null ? i2 : a.optInt(str, i2);
    }

    public static String g(String str) {
        return h(str, "");
    }

    public static String h(String str, String str2) {
        return a == null ? str2 : a.optString(str, str2);
    }

    public static int i() {
        int i2 = r;
        if (i2 > 10) {
            return i2;
        }
        return 22;
    }

    public static int j() {
        int i2 = t;
        return i2 > 10 ? Math.min(com.jingdong.app.mall.home.floor.common.d.d(i2), com.jingdong.app.mall.home.floor.common.d.f9279g) : com.jingdong.app.mall.home.floor.common.d.f9279g;
    }

    public static boolean k() {
        return f9297g;
    }

    public static boolean l() {
        return f9298h;
    }

    public static boolean m() {
        return b;
    }

    public static boolean n() {
        return f9295e;
    }

    public static boolean o() {
        return f9299i;
    }

    public static boolean p() {
        return f9294c;
    }

    public static boolean q() {
        return d;
    }

    public static void r(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        com.jingdong.app.mall.home.floor.common.h.a.e(jDJSONObject);
        a = jDJSONObject;
        boolean equals = TextUtils.equals(h("innerMessageSwitch", "1"), "0");
        b = equals;
        boolean z = false;
        if (equals) {
            com.jingdong.app.mall.home.o.a.f.m(new AtomicBoolean(false));
        }
        q = com.jingdong.app.mall.home.n.h.c.h(h("bOffset", "-1"), -1);
        f9294c = TextUtils.equals(g("openBDark"), "1");
        d = TextUtils.equals(g("popASwitch"), "1");
        f9295e = TextUtils.equals(g("seckillSwitch"), "1");
        f9296f = TextUtils.equals(g("coverRecommend"), "1");
        f9297g = TextUtils.equals(g("seckillCountdown"), "1");
        f9298h = TextUtils.equals(h("areaPopSwitch", "1"), "0");
        f9299i = TextUtils.equals(h("notifyNavi", "1"), "1");
        if (com.jingdong.app.mall.home.o.a.f.f10457c && TextUtils.equals(h("xViewGuideFirst", "1"), "0")) {
            z = true;
        }
        f9300j = z;
        f9301k = TextUtils.equals(g("dialogCheckOpen"), "1");
        f9304n = TextUtils.equals(h("keepDeep", "0"), "0");
        f9303m = TextUtils.equals(h("topXview", "0"), "1");
        o = g("channelId");
        f9302l = TextUtils.equals(g("closeShareCheck"), "1");
        p = h("searchDirect_android", "");
        r = e("coreLabelSize");
        t = e("shaderHeight");
        s = e("cardCorner");
    }

    public static boolean s() {
        return f9300j;
    }
}
