package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class y0 implements ServiceConnection {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ a f19205g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public y0(a aVar) {
        this.f19205g = aVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        List<Message> list;
        List list2;
        Messenger messenger;
        synchronized (this.f19205g) {
            this.f19205g.d = new Messenger(iBinder);
            this.f19205g.f19056c = false;
            list = this.f19205g.b;
            for (Message message : list) {
                try {
                    messenger = this.f19205g.d;
                    messenger.send(message);
                } catch (RemoteException e2) {
                    g.j.a.a.a.c.s(e2);
                }
            }
            list2 = this.f19205g.b;
            list2.clear();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f19205g.d = null;
        this.f19205g.f19056c = false;
    }
}
