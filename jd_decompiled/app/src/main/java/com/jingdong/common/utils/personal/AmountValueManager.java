package com.jingdong.common.utils.personal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.PersonalPreferenceUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes6.dex */
public class AmountValueManager {
    private static String TAG = "AmountValueManager";
    private final Map<String, IAmountValueChanged> changedList;
    private boolean isShowAmount;
    private final ReentrantReadWriteLock lock;
    private final BroadcastReceiver loginAndExitReceiver;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class AmountValueManagerHolder {
        private static final AmountValueManager INSTANCE = new AmountValueManager();

        private AmountValueManagerHolder() {
        }
    }

    /* loaded from: classes6.dex */
    public interface IAmountValueChanged {
        void onAmountStateChanged(boolean z);
    }

    public static AmountValueManager getInstance() {
        return AmountValueManagerHolder.INSTANCE;
    }

    private boolean objectEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAmountShowStatus() {
        if (LoginUserBase.hasLogin()) {
            this.isShowAmount = PersonalPreferenceUtil.getAmountValueShowFlag();
        } else {
            this.isShowAmount = true;
        }
    }

    public void changeAmountValueState(boolean z) {
        this.isShowAmount = z;
        PersonalPreferenceUtil.setAmountValueShowFlag(z);
        notifyChangeList(z);
    }

    public void clearListener() {
        clearListener("new_wallet");
    }

    public boolean isShowAmountValue() {
        return this.isShowAmount;
    }

    public void notifyChangeList(boolean z) {
        for (Map.Entry<String, IAmountValueChanged> entry : this.changedList.entrySet()) {
            if (entry != null) {
                entry.getValue().onAmountStateChanged(z);
            }
        }
    }

    public void register(String str, IAmountValueChanged iAmountValueChanged) {
        this.lock.writeLock().lock();
        try {
            Map<String, IAmountValueChanged> map = this.changedList;
            if (map != null && !map.containsValue(iAmountValueChanged)) {
                this.changedList.put(str, iAmountValueChanged);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void registerLoginAndExitReceiver(Context context) {
        if (context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.in");
        intentFilter.addAction("com.jingdong.action.user.login.out");
        try {
            context.registerReceiver(this.loginAndExitReceiver, intentFilter);
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(TAG, "AmountValueManager-registerLoginAndExitReceiver: " + th);
            }
        }
    }

    public void unregister(String str, IAmountValueChanged iAmountValueChanged) {
        Map<String, IAmountValueChanged> map;
        if (TextUtils.isEmpty(str) || (map = this.changedList) == null) {
            return;
        }
        Iterator<Map.Entry<String, IAmountValueChanged>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, IAmountValueChanged> next = it.next();
            if (next.getKey().equals(str) && objectEquals(next.getValue(), iAmountValueChanged)) {
                it.remove();
                return;
            }
        }
    }

    public void unregisterLoginAndExitReceiver(Context context) {
        if (context == null) {
            return;
        }
        try {
            context.unregisterReceiver(this.loginAndExitReceiver);
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(TAG, "AmountValueManager-unregisterLoginAndExitReceiver: " + th);
            }
        }
    }

    private AmountValueManager() {
        this.lock = new ReentrantReadWriteLock();
        this.changedList = new IdentityHashMap();
        this.loginAndExitReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.utils.personal.AmountValueManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("com.jingdong.action.user.login.in".equals(action) || "com.jingdong.action.user.login.out".equals(action)) {
                    AmountValueManager.this.updateAmountShowStatus();
                }
            }
        };
        try {
            this.isShowAmount = PersonalPreferenceUtil.getAmountValueShowFlag();
        } catch (Throwable unused) {
        }
    }

    public void clearListener(String str) {
        this.lock.readLock().lock();
        try {
            Map<String, IAmountValueChanged> map = this.changedList;
            if (map != null) {
                Iterator<Map.Entry<String, IAmountValueChanged>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().getKey().equals(str)) {
                        it.remove();
                    }
                }
            }
        } finally {
            this.lock.readLock().unlock();
        }
    }
}
