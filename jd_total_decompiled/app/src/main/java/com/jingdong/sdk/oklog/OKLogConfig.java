package com.jingdong.sdk.oklog;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.oklog.a.b;
import com.jingdong.sdk.oklog.core.MemoryLogPrinter;
import com.jingdong.sdk.oklog.core.c;
import com.jingdong.sdk.oklog.core.d;
import com.jingdong.sdk.oklog.reporter.AbsLogReporter;
import com.jingdong.sdk.talos.a;

/* loaded from: classes.dex */
public class OKLogConfig {
    public static Context CXT;
    private static boolean DEBUG;
    private static boolean INIT;
    private static b LOG_PROCESSOR;
    private static AbsLogReporter LOG_REPORTER;
    private static String[] LOG_WRAPPER_CLASS_NAMES;
    private static MemoryLogPrinter MEM_PRINTER;

    public static void d(String str, String str2) {
        if (isReport()) {
            MEM_PRINTER.d(str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.a(str, str2);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().d(str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.d(th, str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.b(str, str2, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().d(th, str, str2);
    }

    public static void d(String str, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.d(th, str, new Object[0]);
        }
        try {
            if (a.h().h().a()) {
                a.c(str, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().d(th, str, new Object[0]);
    }

    public static void d(String str, Object... objArr) {
        if (isReport()) {
            MEM_PRINTER.d(str, objArr);
        }
        try {
            if (a.h().h().a()) {
                String a = d.a(objArr);
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                a.a(str, a);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().d(str, objArr);
    }

    public static void e(String str, String str2) {
        if (isReport()) {
            MEM_PRINTER.e(str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.d(str, str2);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().e(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.e(th, str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.e(str, str2, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().e(th, str, str2);
    }

    public static void e(String str, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.e(th, str, new Object[0]);
        }
        try {
            if (a.h().h().a()) {
                a.f(str, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().e(th, str, new Object[0]);
    }

    public static void e(String str, Object... objArr) {
        if (isReport()) {
            MEM_PRINTER.e(str, objArr);
        }
        try {
            if (a.h().h().a()) {
                String a = d.a(objArr);
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                a.d(str, a);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().e(str, objArr);
    }

    private static c getPrinter() {
        b bVar = LOG_PROCESSOR;
        if (bVar == null) {
            return null;
        }
        return bVar.a();
    }

    public static void i(String str, String str2) {
        if (isReport()) {
            MEM_PRINTER.i(str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.i(str, str2);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().i(str, str2);
    }

    public static void i(String str, String str2, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.i(th, str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.j(str, str2, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().i(th, str, str2);
    }

    public static void i(String str, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.i(th, str, new Object[0]);
        }
        try {
            if (a.h().h().a()) {
                a.k(str, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().i(th, str, new Object[0]);
    }

    public static void i(String str, Object... objArr) {
        if (isReport()) {
            MEM_PRINTER.i(str, objArr);
        }
        try {
            if (a.h().h().a()) {
                String a = d.a(objArr);
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                a.i(str, a);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().i(str, objArr);
    }

    public static boolean inLogWrapperClasses(String str) {
        String[] strArr = LOG_WRAPPER_CLASS_NAMES;
        if (strArr != null) {
            for (String str2 : strArr) {
                if (str2.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isDebug() {
        return DEBUG;
    }

    private static boolean isReport() {
        return (LOG_REPORTER == null || MEM_PRINTER == null) ? false : true;
    }

    public static void json(String str, String str2) {
        if (isReport()) {
            MEM_PRINTER.json(str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.m(str, str2);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().json(str, str2);
    }

    public static void v(String str, String str2) {
        if (isReport()) {
            MEM_PRINTER.v(str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.o(str, str2);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().v(str, str2);
    }

    public static void v(String str, String str2, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.v(th, str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.p(str, str2, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().v(th, str, str2);
    }

    public static void v(String str, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.v(th, str, new Object[0]);
        }
        try {
            if (a.h().h().a()) {
                a.q(str, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().v(th, str, new Object[0]);
    }

    public static void v(String str, Object... objArr) {
        if (isReport()) {
            MEM_PRINTER.v(str, objArr);
        }
        try {
            if (a.h().h().a()) {
                String a = d.a(objArr);
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                a.o(str, a);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().v(str, objArr);
    }

    public static void w(String str, String str2) {
        if (isReport()) {
            MEM_PRINTER.w(str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.r(str, str2);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().w(str, str2);
    }

    public static void w(String str, String str2, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.w(th, str, str2);
        }
        try {
            if (a.h().h().a()) {
                a.s(str, str2, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().w(th, str, str2);
    }

    public static void w(String str, Throwable th) {
        if (isReport()) {
            MEM_PRINTER.w(th, str, new Object[0]);
        }
        try {
            if (a.h().h().a()) {
                a.t(str, th);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().w(th, str, new Object[0]);
    }

    public static void w(String str, Object... objArr) {
        if (isReport()) {
            MEM_PRINTER.w(str, objArr);
        }
        try {
            if (a.h().h().a()) {
                String a = d.a(objArr);
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                a.r(str, a);
            }
        } catch (Throwable unused) {
        }
        if (!isDebug() || getPrinter() == null) {
            return;
        }
        getPrinter().w(str, objArr);
    }

    @Deprecated
    public OKLogConfig diskRecord(boolean z, Context context) {
        return diskRecord(z, context, 0);
    }

    @Deprecated
    public OKLogConfig diskRecord(boolean z, Context context, int i2) {
        if (context instanceof Application) {
            CXT = context;
        } else {
            CXT = context.getApplicationContext();
        }
        return this;
    }

    public OKLogConfig setDebug(boolean z) {
        DEBUG = z;
        return this;
    }

    public OKLogConfig setLogReporter(AbsLogReporter absLogReporter) {
        LOG_REPORTER = absLogReporter;
        return this;
    }

    public OKLogConfig setLogWrapperClassFullNames(String[] strArr) {
        LOG_WRAPPER_CLASS_NAMES = strArr;
        return this;
    }

    public void start() {
        if (INIT) {
            return;
        }
        INIT = true;
        LOG_PROCESSOR = DEBUG ? new com.jingdong.sdk.oklog.a.a() : new com.jingdong.sdk.oklog.a.c();
        if (LOG_REPORTER != null) {
            MEM_PRINTER = new MemoryLogPrinter(LOG_REPORTER);
        }
        if (DEBUG) {
            OKLog.V = true;
            OKLog.D = true;
            OKLog.I = true;
            OKLog.W = true;
            OKLog.E = true;
            return;
        }
        AbsLogReporter absLogReporter = LOG_REPORTER;
        if (absLogReporter != null) {
            OKLog.V = absLogReporter.isReportable(2);
            OKLog.D = LOG_REPORTER.isReportable(3);
            OKLog.I = LOG_REPORTER.isReportable(4);
            OKLog.W = LOG_REPORTER.isReportable(5);
            OKLog.E = LOG_REPORTER.isReportable(6);
        }
    }
}
