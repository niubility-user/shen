package com.tencent.tmsqmsp.oaid2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.tencent.tmsqmsp.oaid2.i0;

/* loaded from: classes9.dex */
public class j0 {

    /* renamed from: e  reason: collision with root package name */
    public static String f18085e = "SDI";

    /* renamed from: f  reason: collision with root package name */
    public static String f18086f = "SI";
    public b a;
    public ServiceConnection b;

    /* renamed from: c  reason: collision with root package name */
    public Context f18087c;
    public i0 d;

    /* loaded from: classes9.dex */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (this) {
                j0.this.d = i0.a.a(iBinder);
                j0 j0Var = j0.this;
                b bVar = j0Var.a;
                if (bVar != null) {
                    bVar.a(j0Var);
                }
                c.c(j0.f18086f + " Service onServiceConnected");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            j0.this.d = null;
            c.c(j0.f18086f + " Service onServiceDisconnected");
        }
    }

    /* loaded from: classes9.dex */
    public interface b {
        void a(j0 j0Var);
    }

    public j0(Context context, b bVar) {
        this.a = null;
        this.f18087c = null;
        if (context == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.f18087c = context;
        this.a = bVar;
        this.b = new a();
    }

    public String a() {
        StringBuilder sb;
        String str;
        Context context = this.f18087c;
        if (context == null) {
            c.c(f18086f + " Context is null.");
            throw new IllegalArgumentException("Context is null, must be new SxCore first");
        }
        String packageName = context.getPackageName();
        c.a(f18086f + "apackage\uff1a" + packageName);
        if (packageName == null || packageName.equals("")) {
            sb = new StringBuilder();
            sb.append(f18086f);
            str = " input package is null!";
        } else {
            try {
                i0 i0Var = this.d;
                if (i0Var != null) {
                    String a2 = i0Var.a(packageName);
                    c.a(f18086f + " getAAID Package: " + packageName);
                    return a2;
                }
                return null;
            } catch (Exception unused) {
                sb = new StringBuilder();
                sb.append(f18086f);
                str = " geta error, RemoteException!";
            }
        }
        sb.append(str);
        c.c(sb.toString());
        return null;
    }

    public String b() {
        if (this.f18087c == null) {
            c.c(f18086f + " Context is null.");
            throw new IllegalArgumentException("Context is null, must be new SxCore first");
        }
        try {
            i0 i0Var = this.d;
            if (i0Var != null) {
                String a2 = i0Var.a();
                c.c(f18085e + " geto call");
                return a2;
            }
            return null;
        } catch (Exception e2) {
            c.c(f18086f + " geto error, RemoteException!");
            e2.printStackTrace();
            return null;
        }
    }

    public void c() {
        StringBuilder sb;
        String str;
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        if (this.f18087c.bindService(intent, this.b, 1)) {
            sb = new StringBuilder();
            sb.append(f18086f);
            str = " bindService Successful!";
        } else {
            this.a.a(this);
            sb = new StringBuilder();
            sb.append(f18086f);
            str = " bindService Failed!";
        }
        sb.append(str);
        c.c(sb.toString());
    }

    public boolean d() {
        try {
            if (this.d == null) {
                c.c(f18086f + " Device not support opendeviceid");
                return false;
            }
            c.c(f18086f + " Device support opendeviceid");
            return true;
        } catch (Exception unused) {
            c.c(f18086f + " isSupport error, RemoteException!");
            return false;
        }
    }

    public void e() {
        try {
            this.f18087c.unbindService(this.b);
            c.c(f18086f + " unBind Service successful");
        } catch (IllegalArgumentException unused) {
            c.c(f18086f + " unBind Service exception");
        }
        this.d = null;
    }
}
