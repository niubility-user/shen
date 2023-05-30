package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;

/* loaded from: classes14.dex */
public class k0 {
    private static final String a = "k0";

    public static boolean a(Context context) {
        boolean z = false;
        if (context != null) {
            try {
                z = y.r(context, "cn.gov.pbc.dcep");
            } catch (Exception e2) {
                r.d(a, e2.getMessage());
                com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "TransactionTooLargeException", "CyberMoneyPayUtil.isAppInstalled()", e2.getMessage());
            }
        }
        r.d(a, "the cyber app is installed =  " + z);
        return z;
    }

    public static boolean b(Context context) {
        try {
            return y.q(context);
        } catch (Exception e2) {
            r.d(a, e2.getMessage());
            return false;
        }
    }
}
