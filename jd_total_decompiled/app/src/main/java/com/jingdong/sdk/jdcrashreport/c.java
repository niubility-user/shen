package com.jingdong.sdk.jdcrashreport;

import com.jingdong.sdk.jdcrashreport.b.k;
import java.util.Map;

/* loaded from: classes7.dex */
public class c {
    private static JDCrashReportConfig a = null;
    private static boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    private static long f14899c = 121105110103L;
    private static long d = 45121097110L;

    public static String a() {
        return "yingyan";
    }

    public static String b(String str) {
        return a.d() ? "https://beta-api.m.jd.com" : "https://api.m.jd.com";
    }

    public static Map<String, String> c(Map<String, String> map, String str) {
        map.put("functionId", str);
        map.put("appid", a());
        map.put("t", String.valueOf(System.currentTimeMillis()));
        return map;
    }

    public static synchronized void d(JDCrashReportConfig jDCrashReportConfig) {
        synchronized (c.class) {
            if (!b) {
                a = jDCrashReportConfig;
                b = true;
            }
        }
    }

    public static void e(Map<String, String> map) {
    }

    public static String f() {
        return k.b(f14899c, d);
    }
}
