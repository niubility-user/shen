package com.hihonor.push.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.hihonor.push.framework.aidl.IPushInvoke;
import com.hihonor.push.sdk.c1;
import com.hihonor.push.sdk.g;

/* loaded from: classes12.dex */
public class t implements ServiceConnection {

    /* renamed from: k  reason: collision with root package name */
    public static final Object f1117k = new Object();

    /* renamed from: g  reason: collision with root package name */
    public final com.hihonor.push.sdk.j.a f1118g;

    /* renamed from: h  reason: collision with root package name */
    public a f1119h;

    /* renamed from: i  reason: collision with root package name */
    public Handler f1120i = null;

    /* renamed from: j  reason: collision with root package name */
    public boolean f1121j = false;

    /* loaded from: classes12.dex */
    public interface a {
    }

    public t(com.hihonor.push.sdk.j.a aVar) {
        this.f1118g = aVar;
    }

    public final void a() {
        synchronized (f1117k) {
            Handler handler = this.f1120i;
            if (handler != null) {
                handler.removeMessages(1001);
                this.f1120i = null;
            }
        }
    }

    public final void b(int i2) {
        a aVar = this.f1119h;
        if (aVar != null) {
            k kVar = (k) aVar;
            kVar.a.a.set(i2 == com.hihonor.push.sdk.b0.a.ERROR_SERVICE_TIME_OUT.statusCode ? 2 : 1);
            kVar.a.a(i2);
            kVar.a.b = null;
        }
    }

    public void c() {
        try {
            String str = "trying to unbind service from " + this;
            f0.f1091e.a().unbindService(this);
        } catch (Exception e2) {
            String str2 = "on unBind service exception:" + e2.getMessage();
        }
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        if (this.f1121j) {
            this.f1121j = false;
            return;
        }
        c();
        a();
        a aVar = this.f1119h;
        if (aVar != null) {
            k kVar = (k) aVar;
            kVar.a.a.set(1);
            kVar.a.a(8002005);
            kVar.a.b = null;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        a();
        a aVar = this.f1119h;
        if (aVar != null) {
            k kVar = (k) aVar;
            kVar.a.b = IPushInvoke.Stub.asInterface(iBinder);
            if (kVar.a.b == null) {
                kVar.a.d.c();
                kVar.a.a.set(1);
                kVar.a.a(8002001);
                return;
            }
            kVar.a.a.set(3);
            g.a aVar2 = kVar.a.f1102c;
            if (aVar2 != null) {
                c1.a aVar3 = (c1.a) aVar2;
                if (Looper.myLooper() == c1.this.f1082g.getLooper()) {
                    aVar3.d();
                } else {
                    c1.this.f1082g.post(new z0(aVar3));
                }
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        a aVar = this.f1119h;
        if (aVar != null) {
            k kVar = (k) aVar;
            kVar.a.a.set(1);
            kVar.a.a(8002002);
            kVar.a.b = null;
        }
    }
}
