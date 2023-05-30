package com.jingdong.sdk.jdcrashreport.b;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes7.dex */
public class x {
    public static long a(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(str).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return System.currentTimeMillis();
        }
    }

    public static String b() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    public static String c(long j2) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j2));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    public static String d(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date);
        } catch (Exception unused) {
            return new Date().toString();
        }
    }
}
