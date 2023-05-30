package com.jd.stat.common;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes18.dex */
public class EncryptUtil {
    private static boolean a;

    static {
        b();
    }

    public static boolean a() {
        b();
        return a;
    }

    public static native boolean androidServerDetected();

    public static synchronized void b() {
        synchronized (EncryptUtil.class) {
            if (!a) {
                com.jd.stat.common.relinker.b.a(com.jd.stat.security.c.a, "jdJmaEncryptUtil");
                a = true;
            }
        }
    }

    @Deprecated
    public static native boolean checkSimulator();

    public static native int checkSu(Object[] objArr);

    public static native String detect();

    public static native HashMap<String, String> encrypt(Context context, String str, String str2, boolean z);

    public static String a(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            double random = Math.random();
            double d = 62;
            Double.isNaN(d);
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int) (random * d)));
        }
        return sb.toString();
    }
}
