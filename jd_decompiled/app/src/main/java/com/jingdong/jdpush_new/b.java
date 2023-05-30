package com.jingdong.jdpush_new;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.jd.lib.push.utils.PushMessageUtils;
import com.jingdong.jdpush_new.j.g;

/* loaded from: classes12.dex */
public class b implements ServiceConnection {

    /* renamed from: i  reason: collision with root package name */
    private static volatile Messenger f12780i;

    /* renamed from: g  reason: collision with root package name */
    private boolean f12781g = true;

    /* renamed from: h  reason: collision with root package name */
    private long f12782h;

    private void b(int i2, long j2) {
        try {
            Messenger messenger = f12780i;
            if (messenger != null) {
                Message obtain = Message.obtain((Handler) null, i2);
                Bundle bundle = new Bundle();
                bundle.putLong("t", j2);
                obtain.setData(bundle);
                messenger.send(obtain);
            }
        } catch (Throwable th) {
            g.g(th);
        }
    }

    private void f(long j2) throws RemoteException {
        Message obtain = Message.obtain((Handler) null, 2);
        Bundle bundle = new Bundle();
        bundle.putLong("t", j2);
        obtain.setData(bundle);
        if (f12780i != null) {
            f12780i.send(obtain);
        }
        g.a("send CMD_MAIN_FRAME_ACTIVITY_FINISHED_LOAD");
    }

    private void g() {
        try {
            Messenger messenger = f12780i;
            if (messenger != null) {
                String b = a.b();
                Message obtain = Message.obtain((Handler) null, 10);
                Bundle bundle = new Bundle();
                bundle.putString("UUID", b);
                obtain.setData(bundle);
                messenger.send(obtain);
                g.a("send uuid : " + b);
            }
        } catch (Throwable th) {
            g.g(th);
        }
    }

    public boolean a() {
        return f12780i != null;
    }

    public void c(long j2) {
        b(0, j2);
    }

    public void d(long j2) {
        b(1, j2);
    }

    public void e() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (f12780i != null) {
                f(currentTimeMillis);
            } else {
                this.f12782h = currentTimeMillis;
            }
            g.a("MAIN_FRAME_ACTIVITY_FINISHED_LOAD");
        } catch (Exception e2) {
            g.g(e2);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        g.a("service connected");
        try {
            f12780i = new Messenger(iBinder);
            long j2 = PushMessageUtils.initTime;
            if (j2 != 0 && this.f12781g) {
                d(j2);
                this.f12781g = false;
            }
            g();
            long j3 = this.f12782h;
            if (j3 != 0) {
                f(j3);
            }
        } catch (Throwable th) {
            g.f("init mRemoteMessenger error", th);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        f12780i = null;
        g.c("onServiceDisconnected");
    }
}
