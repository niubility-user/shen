package com.meizu.cloud.pushinternal;

import android.content.Context;
import com.meizu.cloud.pushsdk.d.g;

/* loaded from: classes13.dex */
public class DebugLogger {
    public static boolean debug;

    public static void d(String str, String str2) {
        g.e().b(str, str2);
    }

    public static void e(String str, String str2) {
        g.e().a(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        g.e().a(str, str2, th);
    }

    public static void flush() {
        g.e().b(false);
    }

    public static void i(String str, String str2) {
        g.e().c(str, str2);
    }

    public static void init(Context context) {
        g.e().c(context);
    }

    public static void init(Context context, String str) {
        g.e().d(context, str);
    }

    public static boolean isDebuggable() {
        return g.e().a();
    }

    public static void switchDebug(boolean z) {
        g.e().a(z);
    }

    public static void w(String str, String str2) {
        g.e().d(str, str2);
    }
}
