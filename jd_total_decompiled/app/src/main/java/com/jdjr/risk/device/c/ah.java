package com.jdjr.risk.device.c;

import android.content.Context;
import android.text.TextUtils;
import java.util.UUID;

/* loaded from: classes18.dex */
public class ah {
    public static String a(Context context) {
        try {
            return com.jd.sec.a.a() != null ? com.jd.sec.a.a() : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String c(Context context) {
        String b;
        String str = "";
        try {
            b = com.jd.sec.a.b() != null ? com.jd.sec.a.b() : "";
            try {
            } catch (Throwable unused) {
                str = b;
            }
        } catch (Throwable unused2) {
        }
        if (TextUtils.isEmpty(b)) {
            if (ai.d() != null) {
                str = ai.d();
            }
            return str;
        }
        return b;
    }

    public static String d(Context context) {
        try {
            return com.jd.sec.a.d().env() != null ? com.jd.sec.a.d().env() : "";
        } catch (Throwable unused) {
            return "";
        }
    }
}
