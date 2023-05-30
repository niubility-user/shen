package com.jd.cashier.app.jdlibcutter.protocol.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes13.dex */
public class CashierNavigator {
    public static void jumpBtCombinationPayPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.btcombinationpay.view.BtCombinationPayActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToCashierRiskPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.risk.view.CashierRiskActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToCashierWebPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.webviewcontainer.view.CashierWebViewActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToCreditPayPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToFinishPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.complete.view.CashierCompleteActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToFriendPayDialogPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToFriendPayPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToPayPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.pay.view.CashierPayActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToQuickPayPage(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.jd.lib.cashier.sdk.quickpay.view.CashierQuickPayActivity");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void jumpToCashierRiskPage(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(activity, "com.jd.lib.cashier.sdk.risk.view.CashierRiskActivity");
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i2);
    }

    public static void jumpToCashierWebPage(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(activity, "com.jd.lib.cashier.sdk.webviewcontainer.view.CashierWebViewActivity");
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i2);
    }

    public static void jumpToCreditPayPage(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(activity, "com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity");
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i2);
    }

    public static void jumpToFinishPage(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(activity, "com.jd.lib.cashier.sdk.complete.view.CashierCompleteActivity");
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i2);
    }

    public static void jumpToFriendPayPage(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(activity, "com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity");
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i2);
    }

    public static void jumpToPayPage(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(activity, "com.jd.lib.cashier.sdk.pay.view.CashierPayActivity");
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i2);
    }

    public static void jumpToQuickPayPage(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(activity, "com.jd.lib.cashier.sdk.quickpay.view.CashierQuickPayActivity");
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i2);
    }
}
