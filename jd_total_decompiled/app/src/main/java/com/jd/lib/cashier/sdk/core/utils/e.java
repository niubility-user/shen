package com.jd.lib.cashier.sdk.core.utils;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.IStackManager;
import com.jd.lib.cashier.sdk.core.ui.entity.CashierConfig;

/* loaded from: classes14.dex */
public class e {
    public static void a(CashierConfig cashierConfig) {
        try {
            IStackManager stackManager = DependInitializer.getStackManager();
            if (stackManager != null && "cart".equals(cashierConfig.source)) {
                stackManager.removeActivity(0, 2);
            } else if (stackManager != null && "banlance".equals(cashierConfig.source)) {
                stackManager.removeActivity(0, 1);
            } else if (stackManager != null && "productdetail".equals(cashierConfig.source)) {
                stackManager.removeActivity(0, 1);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void b() {
        try {
            IStackManager stackManager = DependInitializer.getStackManager();
            if (stackManager != null) {
                stackManager.removeMiniOrderDetail();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void c() {
        try {
            IStackManager stackManager = DependInitializer.getStackManager();
            if (stackManager != null) {
                stackManager.removeActivity(0, 1);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
