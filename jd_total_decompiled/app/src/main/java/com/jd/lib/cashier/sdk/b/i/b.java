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
    */
    public static String c(Date date, String str) {
        if (date == null) {
            return "";
        }
        if (str != null) {
            try {
            } catch (Exception e2) {
                e2.printStackTrace();
                return "";
            }
        }
        str = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(str).format(date);
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
