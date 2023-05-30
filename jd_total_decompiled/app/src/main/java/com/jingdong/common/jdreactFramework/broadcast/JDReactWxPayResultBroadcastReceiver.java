package com.jingdong.common.jdreactFramework.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity;

/* loaded from: classes5.dex */
public class JDReactWxPayResultBroadcastReceiver extends BroadcastReceiver {
    private boolean called = false;
    private Activity mActivity;

    public JDReactWxPayResultBroadcastReceiver(Activity activity) {
        this.mActivity = activity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || this.called) {
            return;
        }
        ((JDReactNativeBaseActivity) this.mActivity).unRegistWXPayBroadcast(intent);
        this.called = true;
    }
}
