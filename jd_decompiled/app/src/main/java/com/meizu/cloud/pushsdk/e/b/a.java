package com.meizu.cloud.pushsdk.e.b;

import com.meizu.cloud.pushinternal.DebugLogger;

/* loaded from: classes14.dex */
public class a {
    private static boolean a = false;
    private static String b = "AndroidNetworking";

    public static void a() {
        a = true;
    }

    public static void b(String str) {
        if (a) {
            DebugLogger.d(b, str);
        }
    }

    public static void c(String str) {
        if (a) {
            DebugLogger.i(b, str);
        }
    }
}
