package com.jd.stat.security.jma.b;

/* loaded from: classes18.dex */
public class j {
    public static String a() {
        return g() + "/getinfo";
    }

    public static String b() {
        return g() + "/bypass";
    }

    public static String c() {
        return g() + "/bypassinfo";
    }

    public static String d() {
        return com.jd.stat.security.c.e() ? "https://beta-rjsb-token-m.jd.com" : "https://rjsb-token-m.jd.com";
    }

    public static String e() {
        return d() + "/gettoken";
    }

    public static String f() {
        return d() + "/gettoken_c";
    }

    private static String g() {
        return com.jd.stat.security.c.e() ? "https://beta-bh.m.jd.com" : "https://blackhole.m.jd.com";
    }
}
