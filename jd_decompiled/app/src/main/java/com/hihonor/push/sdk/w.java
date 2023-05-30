package com.hihonor.push.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

/* loaded from: classes12.dex */
public class w implements ServiceConnection {

    /* renamed from: g  reason: collision with root package name */
    public Messenger f1131g;

    /* renamed from: h  reason: collision with root package name */
    public Bundle f1132h;

    /* renamed from: i  reason: collision with root package name */
    public Context f1133i;

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f1131g = new Messenger(iBinder);
        Message obtain = Message.obtain();
        obtain.setData(this.f1132h);
        try {
            this.f1131g.send(obtain);
        } catch (Exception e2) {
            String str = "message sending failed. " + e2.getMessage();
        }
        try {
            this.f1133i.unbindService(this);
        } catch (Exception unused) {
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f1131g = null;
        this.f1132h = null;
        this.f1133i = null;
    }
}
