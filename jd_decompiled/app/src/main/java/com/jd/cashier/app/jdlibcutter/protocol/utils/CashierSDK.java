package com.jd.cashier.app.jdlibcutter.protocol.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.navigator.CashierNavigator;

/* loaded from: classes13.dex */
public class CashierSDK {
    public static void doPay(final Activity activity, final Bundle bundle) {
        if (activity == null || bundle == null) {
            return;
        }
        String string = bundle.getString("orderId");
        String string2 = bundle.getString("orderType");
        String string3 = bundle.getString("payablePrice");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            CashierNavigator.jumpToPayPage(activity, bundle);
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.jd.cashier.app.jdlibcutter.protocol.utils.CashierSDK.1
                @Override // java.lang.Runnable
                public void run() {
                    CashierNavigator.jumpToPayPage(activity, bundle);
                }
            });
        }
    }
}
