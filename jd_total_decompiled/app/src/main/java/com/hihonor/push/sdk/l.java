package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class l {
    public static void a(String str) {
        String str2;
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i2 = 2;
        while (true) {
            if (i2 >= stackTrace.length) {
                str2 = "";
                break;
            } else if (!stackTrace[i2].getClass().equals(l.class)) {
                String className = stackTrace[i2].getClassName();
                str2 = className.substring(className.lastIndexOf(46) + 1);
                break;
            } else {
                i2++;
            }
        }
        b("HonorPush_" + str2, str, null);
    }

    public static void b(String str, String str2, Throwable th) {
        if (str2.length() > 4000) {
            int i2 = 0;
            while (i2 < str2.length()) {
                int i3 = i2 + 4000;
                if (str2.length() > i3) {
                    str2.substring(i2, i3);
                } else {
                    str2.substring(i2);
                }
                i2 = i3;
            }
        }
    }
}
