package com.jdcn.risk.cpp;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes18.dex */
public class a {
    private static volatile SharedPreferences a;

    public static SharedPreferences a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = context.getSharedPreferences("BIOMETRIC_VERSION", 0);
                }
            }
        }
        return a;
    }
}
