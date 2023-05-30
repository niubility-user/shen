package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.cashiernative.CashierTaskStackManager;
import com.jingdong.common.login.ILogin;

/* loaded from: classes5.dex */
public class DeepLinkCashierHelper {
    private static final String HOST_CASHIER_COMPLETE = "cashiercomplete";
    private static final String HOST_CASHIER_CREDIT_PAY = "cashiercreditpay";
    private static final String HOST_CASHIER_FRIEND_PAY = "friendpay";
    private static final String HOST_CASHIER_FRIEND_PAY_DIALOG = "friendpaydialog";
    private static final String HOST_CASHIER_PAY = "cashierpay";
    private static final String HOST_CASHIER_QUICK_PAY = "cashierquickpay";
    private static final String HOST_CASHIER_WEB_VIEW = "cashierweb";

    public static void startCashierComplete(Context context, Bundle bundle) {
        CashierTaskStackManager.removeCashierFinishTask();
        startJump(context, "cashiercomplete", bundle);
    }

    public static void startCashierCreditPay(Context context, Bundle bundle) {
        CashierTaskStackManager.removeAllCashierTask();
        startJump(context, HOST_CASHIER_CREDIT_PAY, bundle);
    }

    public static void startCashierPay(Context context, Bundle bundle) {
        CashierTaskStackManager.removeAllCashierTask();
        startJump(context, "cashierpay", bundle);
    }

    public static void startCashierQuickPay(Context context, Bundle bundle) {
        CashierTaskStackManager.removeCashierQuickPayTask();
        startJump(context, HOST_CASHIER_QUICK_PAY, bundle);
    }

    public static void startCashierWeb(Context context, Bundle bundle) {
        CashierTaskStackManager.removeCashierWebTask();
        startJump(context, HOST_CASHIER_WEB_VIEW, bundle);
    }

    public static void startFriendPay(Context context, Bundle bundle) {
        CashierTaskStackManager.removeCashierFriendPayTask();
        startJump(context, "friendpay", bundle);
    }

    public static void startFriendPayDialog(Context context, Bundle bundle) {
        CashierTaskStackManager.removeCashierFriendPayDialogTask();
        startJump(context, HOST_CASHIER_FRIEND_PAY_DIALOG, bundle);
    }

    private static void startJump(final Context context, final String str, final Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkCashierHelper.1
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str2) {
                if (str.equals(str2)) {
                    DeepLinkCommonHelper.startActivityDirect(context, str, bundle);
                }
            }
        }, str);
    }

    private static void startJumpForResult(final Activity activity, final String str, final Bundle bundle, final int i2) {
        DeepLinkLoginHelper.startLoginActivity(activity, bundle, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkCashierHelper.2
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str2) {
                if (str.equals(str2)) {
                    DeepLinkCommonHelper.startActivityForResult(activity, str, bundle, i2);
                }
            }
        }, str);
    }

    public static void startCashierComplete(Activity activity, Bundle bundle, int i2) {
        CashierTaskStackManager.removeCashierFinishTask();
        startJumpForResult(activity, "cashiercomplete", bundle, i2);
    }

    public static void startCashierCreditPay(Activity activity, Bundle bundle, int i2) {
        CashierTaskStackManager.removeAllCashierTask();
        startJumpForResult(activity, HOST_CASHIER_CREDIT_PAY, bundle, i2);
    }

    public static void startCashierPay(Activity activity, Bundle bundle, int i2) {
        CashierTaskStackManager.removeAllCashierTask();
        startJumpForResult(activity, "cashierpay", bundle, i2);
    }

    public static void startCashierQuickPay(Activity activity, Bundle bundle, int i2) {
        CashierTaskStackManager.removeCashierQuickPayTask();
        startJumpForResult(activity, HOST_CASHIER_QUICK_PAY, bundle, i2);
    }

    public static void startCashierWeb(Activity activity, Bundle bundle, int i2) {
        CashierTaskStackManager.removeCashierWebTask();
        startJumpForResult(activity, HOST_CASHIER_WEB_VIEW, bundle, i2);
    }

    public static void startFriendPay(Activity activity, Bundle bundle, int i2) {
        CashierTaskStackManager.removeCashierFriendPayTask();
        startJumpForResult(activity, "friendpay", bundle, i2);
    }

    public static void startFriendPayDialog(Activity activity, Bundle bundle, int i2) {
        CashierTaskStackManager.removeCashierFriendPayDialogTask();
        startJumpForResult(activity, HOST_CASHIER_FRIEND_PAY_DIALOG, bundle, i2);
    }
}
