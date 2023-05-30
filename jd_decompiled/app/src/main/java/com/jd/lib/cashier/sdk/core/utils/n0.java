package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import com.jingdong.common.utils.pay.OctopusUtils;

/* loaded from: classes14.dex */
public class n0 {
    private static final String a = "n0";

    public static boolean a(Context context) {
        boolean z = false;
        if (context != null) {
            try {
                z = y.r(context, OctopusUtils.COM_OCTOPUSCARDS_NFC_READER);
            } catch (Exception e2) {
                r.d(a, e2.getMessage());
                com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "TransactionTooLargeException", "OctopusPayUtil.isAppInstalled()", e2.getMessage());
            }
        }
        r.d(a, "the octopus app is installed =  " + z);
        return z;
    }
}
