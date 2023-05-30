package com.xiaomi.push.service;

import android.text.TextUtils;

/* loaded from: classes11.dex */
public class f0 {
    private static long a = 0;
    private static String b = "";

    public static String a() {
        if (TextUtils.isEmpty(b)) {
            b = com.xiaomi.push.p0.a(4);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(b);
        long j2 = a;
        a = 1 + j2;
        sb.append(j2);
        return sb.toString();
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 32) {
            return str;
        }
        try {
            return "BlockId_" + str.substring(8);
        } catch (Exception e2) {
            g.j.a.a.a.c.D("Exception occurred when filtering registration packet id for log. " + e2);
            return "UnexpectedId";
        }
    }

    public static String c() {
        return com.xiaomi.push.p0.a(32);
    }
}
