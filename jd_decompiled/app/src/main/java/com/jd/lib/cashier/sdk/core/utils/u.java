package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.IWXPay;

/* loaded from: classes14.dex */
public class u {
    public static boolean a(Context context) {
        try {
            IUnionPay unionPay = DependInitializer.getUnionPay();
            if (unionPay != null) {
                return unionPay.isUnionWalletInstalled(context);
            }
            return false;
        } catch (Exception e2) {
            com.jd.lib.cashier.sdk.d.h.a.a("LaunchUnionPaySDKFunction", "LaunchUnionPaySDKException", "CashierPayDecorator.isUnionPayInstalled()", e2.getMessage());
            return false;
        }
    }

    public static boolean b() {
        IWXPay wXPay = DependInitializer.getWXPay();
        return wXPay != null && wXPay.isWXInstalled();
    }

    public static boolean c() {
        IWXPay wXPay = DependInitializer.getWXPay();
        return wXPay != null && wXPay.isWXSupported();
    }
}
