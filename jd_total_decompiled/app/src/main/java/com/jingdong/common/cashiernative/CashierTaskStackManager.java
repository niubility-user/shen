package com.jingdong.common.cashiernative;

import android.app.Activity;
import com.jingdong.common.ActivityNumController;
import com.jingdong.corelib.utils.Log;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class CashierTaskStackManager {
    private static final String CASHIER_H5_ACTIVITY = "com.jingdong.app.mall.pay.CashierDeskActivity";
    private static final Map<StackTaskId, WeakReference<Activity>> mStackContainer = new HashMap();

    /* loaded from: classes5.dex */
    public enum StackTaskId {
        CASHIER_PAY,
        CASHIER_FINISH,
        CASHIER_QUICK_PAY,
        CASHIER_FRIEND_PAY,
        CASHIER_CREDIT_PAY,
        CASHIER_WEB_CONTAINER,
        CASHIER_FRIEND_PAY_DIALOG
    }

    public static void addCashierCreditPayTask(Activity activity) {
        addTask(StackTaskId.CASHIER_CREDIT_PAY, activity);
    }

    public static void addCashierFinishTask(Activity activity) {
        addTask(StackTaskId.CASHIER_FINISH, activity);
    }

    public static void addCashierFriendPay(Activity activity) {
        addTask(StackTaskId.CASHIER_FRIEND_PAY, activity);
    }

    public static void addCashierFriendPayDialog(Activity activity) {
        addTask(StackTaskId.CASHIER_FRIEND_PAY_DIALOG, activity);
    }

    public static void addCashierPayTask(Activity activity) {
        addTask(StackTaskId.CASHIER_PAY, activity);
    }

    public static void addCashierQuickPayTask(Activity activity) {
        addTask(StackTaskId.CASHIER_QUICK_PAY, activity);
    }

    public static void addCashierWebContainer(Activity activity) {
        addTask(StackTaskId.CASHIER_WEB_CONTAINER, activity);
    }

    private static void addTask(StackTaskId stackTaskId, Activity activity) {
        mStackContainer.put(stackTaskId, new WeakReference<>(activity));
    }

    public static void removeAllCashierTask() {
        Activity activity;
        try {
            Iterator<Map.Entry<StackTaskId, WeakReference<Activity>>> it = mStackContainer.entrySet().iterator();
            while (it.hasNext()) {
                WeakReference<Activity> value = it.next().getValue();
                if (value != null && (activity = value.get()) != null) {
                    activity.finish();
                }
            }
            mStackContainer.clear();
            removeH5CashierPayTask();
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    public static void removeCashierCreditPayTask() {
        removeTask(StackTaskId.CASHIER_CREDIT_PAY);
    }

    public static void removeCashierFinishTask() {
        removeTask(StackTaskId.CASHIER_FINISH);
    }

    public static void removeCashierFriendPayDialogTask() {
        removeTask(StackTaskId.CASHIER_FRIEND_PAY_DIALOG);
    }

    public static void removeCashierFriendPayTask() {
        removeTask(StackTaskId.CASHIER_FRIEND_PAY);
    }

    public static void removeCashierPayTask() {
        removeTask(StackTaskId.CASHIER_PAY);
    }

    public static void removeCashierQuickPayTask() {
        removeTask(StackTaskId.CASHIER_QUICK_PAY);
    }

    public static void removeCashierWebTask() {
        removeTask(StackTaskId.CASHIER_WEB_CONTAINER);
    }

    public static void removeH5CashierPayTask() {
        try {
            ActivityNumController.removeActivity(CASHIER_H5_ACTIVITY);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    private static void removeTask(StackTaskId stackTaskId) {
        Map<StackTaskId, WeakReference<Activity>> map;
        WeakReference<Activity> weakReference;
        if (stackTaskId == null || (weakReference = (map = mStackContainer).get(stackTaskId)) == null) {
            return;
        }
        Activity activity = weakReference.get();
        if (activity != null) {
            activity.finish();
        }
        map.remove(stackTaskId);
    }
}
