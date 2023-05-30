package com.jd.cashier.app.jdlibcutter.protocol.stackmanager;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes13.dex */
public class PayTaskStackManager {
    private static final Map<StackTaskId, WeakReference<Activity>> mStackContainer = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public enum StackTaskId {
        CASHIER_PAY,
        CASHIER_FINISH,
        CASHIER_QUICK_PAY,
        CASHIER_FRIEND_PAY,
        CASHIER_FRIEND_PAY_DIALOG,
        CASHIER_WEB_CONTAINER,
        CASHIER_BT_COMBINATION
    }

    public static void addCashierBtCombination(Activity activity) {
        addTask(StackTaskId.CASHIER_BT_COMBINATION, activity);
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

    public static void addCashierQuickPay(Activity activity) {
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
        Iterator<Map.Entry<StackTaskId, WeakReference<Activity>>> it = mStackContainer.entrySet().iterator();
        while (it.hasNext()) {
            WeakReference<Activity> value = it.next().getValue();
            if (value != null && (activity = value.get()) != null) {
                activity.finish();
            }
        }
        mStackContainer.clear();
    }

    public static void removeCashierBtCombination() {
        removeTask(StackTaskId.CASHIER_BT_COMBINATION);
    }

    public static void removeCashierFinishPageTask() {
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

    public static void removeCashierWebContainerTask() {
        removeTask(StackTaskId.CASHIER_WEB_CONTAINER);
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
