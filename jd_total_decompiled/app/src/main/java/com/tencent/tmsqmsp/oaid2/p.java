package com.tencent.tmsqmsp.oaid2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.jingdong.common.utils.LangUtils;
import com.tencent.tmsqmsp.oaid2.n;

/* loaded from: classes9.dex */
public class p {

    /* renamed from: e  reason: collision with root package name */
    public static String f18092e = "LXOP";
    public Context a;
    public n b;

    /* renamed from: c  reason: collision with root package name */
    public ServiceConnection f18093c;
    public b d;

    /* loaded from: classes9.dex */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (this) {
                p.this.b = n.a.a(iBinder);
                p pVar = p.this;
                b bVar = pVar.d;
                if (bVar != null) {
                    bVar.a(pVar);
                }
                p.this.b("Service onServiceConnected");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            p pVar = p.this;
            pVar.b = null;
            pVar.b("Service onServiceDisconnected");
        }
    }

    /* loaded from: classes9.dex */
    public interface b {
        void a(p pVar);
    }

    public p(Context context, b bVar) {
        String str;
        this.a = null;
        this.d = null;
        if (context == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.a = context;
        this.d = bVar;
        this.f18093c = new a();
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        if (this.a.bindService(intent, this.f18093c, 1)) {
            str = "bindService Successful!";
        } else {
            b bVar2 = this.d;
            if (bVar2 != null) {
                bVar2.a(this);
            }
            str = "bindService Failed!!!";
        }
        b(str);
    }

    public String a() {
        if (this.a == null) {
            a("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        try {
            n nVar = this.b;
            if (nVar != null) {
                return nVar.b();
            }
            return null;
        } catch (Exception e2) {
            a("getOAID error, RemoteException!");
            e2.printStackTrace();
            return null;
        }
    }

    public final void a(String str) {
        c.b(f18092e + LangUtils.SINGLE_SPACE + str);
    }

    public final void b(String str) {
        c.a(f18092e + LangUtils.SINGLE_SPACE + str);
    }

    public boolean b() {
        try {
            if (this.b == null) {
                return false;
            }
            b("Device support opendeviceid");
            return this.b.c();
        } catch (Exception unused) {
            a("isSupport error, RemoteException!");
            return false;
        }
    }

    public String c() {
        Context context = this.a;
        if (context == null) {
            b("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        String packageName = context.getPackageName();
        b("liufeng, getAAID package\uff1a" + packageName);
        if (packageName == null || packageName.equals("")) {
            b("input package is null!");
            return null;
        }
        try {
            n nVar = this.b;
            if (nVar != null) {
                return nVar.b(packageName);
            }
            return null;
        } catch (Exception unused) {
            a("getAAID error, RemoteException!");
            return null;
        }
    }

    public void d() {
        try {
            this.a.unbindService(this.f18093c);
            b("unBind Service successful");
        } catch (IllegalArgumentException unused) {
            a("unBind Service exception");
        }
        this.b = null;
    }
}
