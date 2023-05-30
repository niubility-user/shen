package com.tencent.smtt.sdk;

import android.os.Bundle;

/* loaded from: classes9.dex */
public class MultiProcessHelper {
    private static Object a(String str, Bundle bundle) {
        u a = u.a();
        if (a != null && a.b()) {
            try {
                return a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "invokeStaticMiscMethod", new Class[]{String.class, Bundle.class}, str, bundle);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static boolean getMultiProcessAutoRecoveryEnabled() {
        Object a = a("getMultiProcessAutoRecoveryEnabled", null);
        if (a instanceof Boolean) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    public static String getMultiProcessChoseTypeReason() {
        Object a = a("getMultiProcessChoseTypeReason", null);
        return a instanceof String ? (String) a : "";
    }

    public static boolean getMultiProcessNoSandBoxEnabled() {
        Object a = a("getMultiProcessNoSandBoxIfNotIsolatedEnabled", null);
        if (a instanceof Boolean) {
            return ((Boolean) a).booleanValue();
        }
        return true;
    }

    public static int getMultiProcessType() {
        Object a = a("getMultiProcessType", null);
        if (a instanceof Integer) {
            return ((Integer) a).intValue();
        }
        return -1;
    }
}
