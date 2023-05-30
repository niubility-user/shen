package com.jingdong.common.eldermode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.eldermode.helper.IElderModeUserInfoConfig;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes5.dex */
public class JDElderModeUserInfoConfigImpl implements IElderModeUserInfoConfig {
    private BroadcastReceiver loginAndExitReceiver;

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeUserInfoConfig
    public boolean hasLogin() {
        return LoginUserBase.hasLogin();
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeUserInfoConfig
    public void registerLoginReceiver(@NotNull Context context) {
        if (this.loginAndExitReceiver == null) {
            this.loginAndExitReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.eldermode.JDElderModeUserInfoConfigImpl.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    String action = intent.getAction();
                    if ("com.jingdong.action.user.login.in".equals(action)) {
                        if (intent.getIntExtra(LoginConstans.REFRESH_MODE, 0) == LoginConstans.REFRESH_MODE_VALUE) {
                            return;
                        }
                        JDElderModeUtils.onLoginIn();
                    } else if ("com.jingdong.action.user.login.out".equals(action)) {
                        JDElderModeUtils.onLoginOut();
                    }
                }
            };
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.in");
        intentFilter.addAction("com.jingdong.action.user.login.out");
        try {
            context.registerReceiver(this.loginAndExitReceiver, intentFilter);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeUserInfoConfig
    public void unregisterLoginReceiver(@NotNull Context context) {
        BroadcastReceiver broadcastReceiver = this.loginAndExitReceiver;
        if (broadcastReceiver != null) {
            try {
                context.unregisterReceiver(broadcastReceiver);
            } catch (Exception unused) {
            }
        }
    }
}
