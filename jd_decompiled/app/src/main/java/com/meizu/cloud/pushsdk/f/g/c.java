package com.meizu.cloud.pushsdk.f.g;

import com.meizu.cloud.pushinternal.DebugLogger;

/* loaded from: classes14.dex */
public class c {
    private static int a;

    private static String a() {
        return Thread.currentThread().getName();
    }

    private static String b(String str) {
        return "PushTracker->" + str;
    }

    private static String c(String str, Object... objArr) {
        return a() + "|" + String.format(str, objArr);
    }

    public static void d(b bVar) {
        a = bVar.a();
    }

    public static void e(String str, String str2, Object... objArr) {
        if (a >= b.DEBUG.a()) {
            DebugLogger.d(b(str), c(str2, objArr));
        }
    }

    public static void f(String str, String str2, Object... objArr) {
        if (a >= b.ERROR.a()) {
            DebugLogger.e(b(str), c(str2, objArr));
        }
    }

    public static void g(String str, String str2, Object... objArr) {
        if (a >= b.VERBOSE.a()) {
            DebugLogger.i(b(str), c(str2, objArr));
        }
    }
}
