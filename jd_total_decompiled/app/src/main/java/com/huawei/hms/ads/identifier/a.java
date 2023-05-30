package com.huawei.hms.ads.identifier;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes12.dex */
public final class a implements ServiceConnection {
    public boolean a = false;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            String str = "onServiceConnected " + System.currentTimeMillis();
            this.b.put(iBinder);
        } catch (InterruptedException unused) {
            String str2 = "onServiceConnected InterruptedException " + System.currentTimeMillis();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        String str = "onServiceDisconnected " + System.currentTimeMillis();
    }
}
