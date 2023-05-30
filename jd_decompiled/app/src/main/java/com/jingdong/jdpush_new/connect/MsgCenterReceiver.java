package com.jingdong.jdpush_new.connect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.jingdong.jdpush_new.connect.MessageIntentService;

/* loaded from: classes12.dex */
public class MsgCenterReceiver extends BroadcastReceiver {
    private static final String TAG = MsgCenterReceiver.class.getSimpleName();
    private static MsgCenterReceiver mReceiver;

    private MsgCenterReceiver() {
    }

    public static MsgCenterReceiver getReceiver() {
        if (mReceiver == null) {
            mReceiver = new MsgCenterReceiver();
        }
        return mReceiver;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        MessageIntentService.addJob(new MessageIntentService.a(intent));
        try {
            context.startService(new Intent(context, MessageIntentService.class));
        } catch (Exception unused) {
        }
    }

    public void registBroadcastReceiver(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("com.jingdong.jdpush.MSG_CENTER");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(getReceiver(), intentFilter);
        } catch (IllegalArgumentException unused) {
        }
    }

    public void unregisterReceiver(Context context) {
        try {
            context.unregisterReceiver(getReceiver());
        } catch (IllegalArgumentException unused) {
        }
    }
}
