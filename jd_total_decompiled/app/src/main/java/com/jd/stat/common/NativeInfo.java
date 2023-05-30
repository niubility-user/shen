package com.jd.stat.common;

/* loaded from: classes18.dex */
public class NativeInfo {
    public static String a() {
        try {
            EncryptUtil.b();
            return getEnvVersion();
        } catch (Throwable th) {
            com.jd.stat.common.b.b.a("NativeInfo", th.getMessage());
            return "";
        }
    }

    private static native String getEnvVersion();

    public static native String getProp(String str);
}
