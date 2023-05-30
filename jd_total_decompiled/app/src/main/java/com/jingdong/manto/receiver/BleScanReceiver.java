package com.jingdong.manto.receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.jingdong.manto.m.t0.d.e.f;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class BleScanReceiver extends BroadcastReceiver {
    f a;

    public BleScanReceiver(f fVar) {
        this.a = fVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            MantoLog.i(this.a.b, "Receive intent failed");
            return;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            int state = defaultAdapter.getState();
            MantoLog.d(this.a.b, String.format("state:%d", Integer.valueOf(state)));
            if (state == 12 || state == 11) {
                return;
            }
            if (state == 10 || state == 13) {
                MantoLog.i(this.a.b, "bluetooth is disable, stop scan");
                this.a.f13712f.set(false);
                this.a.a();
            }
        }
    }
}
