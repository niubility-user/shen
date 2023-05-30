package com.jingdong.sdk.jdcrashreport.b;

import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes7.dex */
public class r {
    private static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            return "[Logger]: " + th2.getMessage();
        }
    }

    public static void b(String str, String str2) {
        if (com.jingdong.sdk.jdcrashreport.d.S()) {
            while (str2.length() > 3000) {
                str2.substring(0, 3000);
                str2 = str2.substring(3000);
            }
        }
    }

    public static void c(String str, String str2, Throwable th) {
        com.jingdong.sdk.jdcrashreport.d.S();
    }

    public static void d(String str, String str2, Object... objArr) {
        if (com.jingdong.sdk.jdcrashreport.d.S()) {
            for (String format = String.format(str2, objArr); format.length() > 3000; format = format.substring(3000)) {
                format.substring(0, 3000);
            }
        }
    }

    public static void e(String str, Throwable th) {
        if (com.jingdong.sdk.jdcrashreport.d.S()) {
            for (String a = a(th); a.length() > 3000; a = a.substring(3000)) {
                a.substring(0, 3000);
            }
        }
    }

    public static void f(String str, String str2) {
        if (com.jingdong.sdk.jdcrashreport.d.S()) {
            while (str2.length() > 3000) {
                str2.substring(0, 3000);
                str2 = str2.substring(3000);
            }
        }
    }

    public static void g(String str, Throwable th) {
        if (com.jingdong.sdk.jdcrashreport.d.S()) {
            for (String a = a(th); a.length() > 3000; a = a.substring(3000)) {
                a.substring(0, 3000);
            }
        }
    }

    public static void h(String str, String str2) {
        if (com.jingdong.sdk.jdcrashreport.d.S()) {
            while (str2.length() > 3000) {
                str2.substring(0, 3000);
                str2 = str2.substring(3000);
            }
        }
    }
}
