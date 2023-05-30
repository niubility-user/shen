package com.jdcn.risk.cpp;

import android.content.Context;

/* loaded from: classes18.dex */
public class BiometricHelper {
    static {
        try {
            System.loadLibrary("biometric");
        } catch (Throwable unused) {
        }
    }

    private static native String nativeGetCachedToken(Context context);

    private static native String nativeGetToken(Context context, String str, String str2);

    private static native int[] nativeStartBiometric(Context context, String str, String str2);
}
