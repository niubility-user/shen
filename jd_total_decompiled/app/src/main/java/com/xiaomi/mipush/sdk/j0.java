package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class j0 implements ServiceConnection {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ f0 f18389g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j0(f0 f0Var) {
        this.f18389g = f0Var;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        List<Message> list;
        List list2;
        Messenger messenger;
        synchronized (this.f18389g) {
            this.f18389g.d = new Messenger(iBinder);
            this.f18389g.f18378g = false;
            list = this.f18389g.f18377f;
            for (Message message : list) {
                try {
                    messenger = this.f18389g.d;
                    messenger.send(message);
                } catch (RemoteException e2) {
                    g.j.a.a.a.c.s(e2);
                }
            }
            list2 = this.f18389g.f18377f;
            list2.clear();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f18389g.d = null;
        this.f18389g.f18378g = false;
    }
}
