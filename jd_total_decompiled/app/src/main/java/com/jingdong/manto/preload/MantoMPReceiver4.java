package com.jingdong.manto.preload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class MantoMPReceiver4 extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        MantoLog.w("MantoMPReceiver4", "onReceive: " + intent.getExtras());
    }
}
