package com.jd.viewkit.tool;

/* loaded from: classes18.dex */
public class DateTool {
    public static Long getTime() {
        return Long.valueOf(System.currentTimeMillis() / 1000);
    }

    public static Long getTrueTime(long j2, long j3) {
        return Long.valueOf(j3 - (getTime().longValue() - j2));
    }
}
