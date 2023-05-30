package com.jd.jdsec.c.h.c;

import androidx.annotation.NonNull;

/* loaded from: classes13.dex */
public class l {
    private static String a() {
        return com.jd.jdsec.c.g.j() ? "https://anti-sdk-report-pre.m.jd.com" : "https://anti-sdk-report.m.jd.com";
    }

    private static String b() {
        return com.jd.jdsec.c.g.j() ? "https://terminal-compute-pre.m.jd.com" : "https://terminal-compute.m.jd.com";
    }

    @NonNull
    public static String c() {
        return b() + "/tcd/log";
    }

    @NonNull
    public static String d() {
        return a() + "/report_event";
    }

    @NonNull
    public static String e() {
        return a() + "/report_fixinfo";
    }
}
