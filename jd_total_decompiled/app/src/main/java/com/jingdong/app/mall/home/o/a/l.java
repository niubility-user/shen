package com.jingdong.app.mall.home.o.a;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.tencent.connect.common.Constants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class l {
    private static int a;
    private static int b;

    /* renamed from: c */
    private static int f10500c;
    private static final ConcurrentHashMap<String, Integer> d = new ConcurrentHashMap<>();

    /* renamed from: e */
    private static final AtomicBoolean f10501e = new AtomicBoolean(false);

    static {
        try {
            f10500c = Integer.parseInt("1", 2);
            a = Integer.parseInt("10", 2);
            b = Integer.parseInt("100", 2);
            Integer.parseInt(Constants.DEFAULT_UIN, 2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean a() {
        return d(a);
    }

    public static boolean b() {
        return c("clickNavJump") == 0;
    }

    private static int c(String str) {
        try {
            ConcurrentHashMap<String, Integer> concurrentHashMap = d;
            Integer num = concurrentHashMap.get(str);
            if (num != null) {
                return num.intValue();
            }
            Integer valueOf = Integer.valueOf(com.jingdong.app.mall.home.r.e.b.getJsonInt(s.e(), str, 0));
            concurrentHashMap.put(str, valueOf);
            return valueOf.intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private static boolean d(int i2) {
        return i2 == (SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.METHOD_SWITCH, 0) & i2);
    }

    public static boolean e() {
        return f10501e.get();
    }

    public static int f() {
        return c("launchDelay");
    }

    public static boolean g() {
        return c("unLoadRecommend") <= 0;
    }

    public static void h(JDJSONObject jDJSONObject) {
        f10501e.set(TextUtils.equals("1", jDJSONObject.optString("isXcache")));
        d.clear();
    }

    public static void i(String str) {
        if (k.w() || c("reportCrash") == 1) {
            j(new Throwable(str));
        }
    }

    public static void j(Throwable th) {
        try {
            if (k.w() || c("reportCrash") == 1) {
                JdCrashReport.postCaughtException(th, com.jingdong.app.mall.home.r.c.a.f10642k);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static int k() {
        return c("shakeBase");
    }

    public static int l() {
        return c("shakeBorder");
    }

    public static int m() {
        return c("shakeLev");
    }

    public static int n() {
        return c("newShake");
    }

    public static boolean o() {
        return d(f10500c);
    }

    public static boolean p() {
        return d(b);
    }

    public static boolean q() {
        return c("unUseMeasure") == 1;
    }

    public static boolean r() {
        return c("closeChange") == 0;
    }

    public static boolean s() {
        return c("useNewManager") == 1;
    }
}
