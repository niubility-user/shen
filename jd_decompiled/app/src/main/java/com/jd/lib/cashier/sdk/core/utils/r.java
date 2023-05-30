package com.jd.lib.cashier.sdk.core.utils;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.log.ILog;

/* loaded from: classes14.dex */
public class r {
    public static void a(String str, Object obj) {
        ILog log = DependInitializer.getLog();
        if (log != null) {
            log.d(str, o.b(obj));
        }
    }

    public static void b(String str, String str2) {
        ILog log = DependInitializer.getLog();
        if (log != null) {
            log.d(str, str2);
        }
    }

    public static void c(String str, Object obj) {
        ILog log = DependInitializer.getLog();
        if (log != null) {
            log.e(str, o.b(obj));
        }
    }

    public static void d(String str, String str2) {
        ILog log = DependInitializer.getLog();
        if (log != null) {
            log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        ILog log = DependInitializer.getLog();
        if (log != null) {
            log.e(str, str2, th);
        }
    }

    public static void f(String str, String str2) {
        ILog log = DependInitializer.getLog();
        if (log != null) {
            log.i(str, str2);
        }
    }
}
