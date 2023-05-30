package com.jd.aips.common.utils;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes12.dex */
public class BiometricTokenUtil {
    private static boolean a;

    public static String buildBiometricToken(Context context, String str) {
        return buildBiometricToken(context, str, "JDT-AI-SDKS");
    }

    public static String buildBiometricToken(Context context, String str, String str2) {
        if (a) {
            return "";
        }
        try {
            Class<?> cls = Class.forName("com.jdjr.risk.biometric.core.BiometricManager");
            String str3 = (String) cls.getMethod("getCacheTokenByBizId", Context.class, String.class, String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), context, str2, str);
            if (!TextUtils.isEmpty(str3) && !str3.startsWith("1") && !str3.startsWith("2")) {
                if (!str3.startsWith("3")) {
                    return str3;
                }
            }
            return "BiometricManagerError";
        } catch (Throwable th) {
            if (th instanceof ClassNotFoundException) {
                a = true;
            }
            return "BiometricManagerException";
        }
    }
}
