package com.jdcn.fido.utils;

import android.content.Context;

/* loaded from: classes18.dex */
public class PackageUtil {
    public static String getPackageName(Context context) {
        if (context != null) {
            try {
                return context.getPackageName();
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }
}
