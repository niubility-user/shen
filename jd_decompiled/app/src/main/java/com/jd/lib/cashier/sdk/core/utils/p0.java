package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;

/* loaded from: classes14.dex */
public class p0 {
    public static boolean a(Context context) {
        try {
            return u.a(context);
        } catch (Throwable th) {
            com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "InItPaySdkNorException", "UnionPayUtil.isWalletInstalled()", th.getMessage());
            return false;
        }
    }
}
