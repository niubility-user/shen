package com.jd.security.jdguard.f;

/* loaded from: classes17.dex */
public class d {
    static c a;

    public static void a(String str) {
        if (b()) {
            g.a("JDG", h() + "[*] Debug : " + str);
        }
    }

    private static boolean b() {
        try {
            com.jd.security.jdguard.c e2 = com.jd.security.jdguard.b.e();
            if (e2 == null) {
                return false;
            }
            return e2.b();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void c(String str) {
        g.b("JDG", h() + "[-] Error : " + str);
    }

    public static void d(String str, Object... objArr) {
        c(String.format(str, objArr));
    }

    public static void e(Throwable th) {
        if (th == null) {
            return;
        }
        g.b("JDG", th.getMessage());
        f(th);
        th.printStackTrace();
    }

    private static void f(Throwable th) {
        c cVar = a;
        if (cVar != null) {
            try {
                cVar.a(th);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public static void g(String str) {
        if (b()) {
            g.e("JDG", "[-] Warn : " + str);
        }
    }

    private static String h() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length < 6) {
            return "";
        }
        StackTraceElement stackTraceElement = "JDGLog.java".equals(stackTrace[4].getFileName()) ? stackTrace[5] : stackTrace[4];
        return stackTraceElement == null ? "" : String.format("[%s:%d]", stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()));
    }
}
