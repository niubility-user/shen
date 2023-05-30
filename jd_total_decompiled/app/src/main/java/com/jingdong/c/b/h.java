package com.jingdong.c.b;

import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes10.dex */
public final class h {
    public static boolean a;
    private static final String b = c.class.getSimpleName();

    public static void a(String str) {
        boolean z = a;
    }

    public static void b(Throwable th) {
        if (a) {
            d(th);
        }
    }

    public static void c(boolean z) {
        a = z;
    }

    private static String d(Throwable th) {
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
}
