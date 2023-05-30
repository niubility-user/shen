package com.jd.lib.cashier.sdk.b.i;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint({"SimpleDateFormat"})
/* loaded from: classes14.dex */
public class b {
    static {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        new SimpleDateFormat("yyyy-MM-dd");
        new SimpleDateFormat("HH:mm:ss");
    }

    public static Date a(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(Calendar calendar, String str) {
        return calendar == null ? "" : c(calendar.getTime(), str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000b, code lost:
        if (r3.length() == 0) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String c(java.util.Date r2, java.lang.String r3) {
        /*
            java.lang.String r0 = ""
            if (r2 != 0) goto L5
            return r0
        L5:
            if (r3 == 0) goto Ld
            int r1 = r3.length()     // Catch: java.lang.Exception -> L19
            if (r1 != 0) goto Lf
        Ld:
            java.lang.String r3 = "yyyy-MM-dd HH:mm:ss"
        Lf:
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat     // Catch: java.lang.Exception -> L19
            r1.<init>(r3)     // Catch: java.lang.Exception -> L19
            java.lang.String r2 = r1.format(r2)     // Catch: java.lang.Exception -> L19
            return r2
        L19:
            r2 = move-exception
            r2.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.b.i.b.c(java.util.Date, java.lang.String):java.lang.String");
    }

    public static String d(String str) {
        try {
            return b(Calendar.getInstance(), str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static long e(Date date, Date date2) {
        if (date == null || date2 == null) {
            return 0L;
        }
        try {
            if (date2.getTime() < date.getTime()) {
                return -1L;
            }
            if (date2.getTime() == date.getTime()) {
                return 0L;
            }
            return (date2.getTime() - date.getTime()) / 86400000;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1L;
        }
    }
}
