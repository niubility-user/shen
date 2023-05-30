package com.jingdong.app.mall.home;

import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class HomeShakeCtrl {
    private static final String SHAKE_SWITCH_KEY = "home_shake_switch";
    private static final AtomicBoolean sShakeState = new AtomicBoolean(SharedPreferencesUtil.getBoolean(SHAKE_SWITCH_KEY, true));

    public static boolean getCurrentShakeSwitch() {
        return sShakeState.get();
    }

    public static void updateShakeSwitch(boolean z) {
        sShakeState.set(z);
        SharedPreferencesUtil.putBoolean(SHAKE_SWITCH_KEY, z);
    }
}
