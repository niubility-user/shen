package com.tencent.tmsqmsp.oaid2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.IBinder;
import com.tencent.tmsqmsp.oaid2.q;

/* loaded from: classes9.dex */
public class r {

    /* renamed from: e  reason: collision with root package name */
    public static String f18094e = "com.mdid.msa";
    public s a;
    public ServiceConnection b;

    /* renamed from: c  reason: collision with root package name */
    public Context f18095c;
    public q d;

    /* loaded from: classes9.dex */
    public class a implements ServiceConnection {
        public s a;

        public a(r rVar, s sVar) {
            this.a = sVar;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (this) {
                r.this.d = q.a.a(iBinder);
                new t(r.this.d, this.a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            r.this.d = null;
            r.this.d = null;
        }
    }

    public r(Context context, s sVar) {
        if (context == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.f18095c = context;
        this.a = sVar;
        this.b = new a(this, sVar);
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent();
        intent.setClassName(f18094e, "com.mdid.msa.service.MsaKlService");
        intent.setAction("com.bun.msa.action.start.service");
        intent.putExtra("com.bun.msa.param.pkgname", str);
        try {
            intent.putExtra("com.bun.msa.param.runinset", true);
            if (context.startService(intent) != null) {
            }
        } catch (Exception unused) {
        }
    }

    public static boolean a(Context context) {
        try {
            context.getPackageManager().getPackageInfo(f18094e, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public String a() {
        try {
            q qVar = this.d;
            return qVar == null ? "" : qVar.d();
        } catch (Exception unused) {
            return "";
        }
    }

    public void a(String str) {
        s sVar;
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
        intent.setAction("com.bun.msa.action.bindto.service");
        intent.putExtra("com.bun.msa.param.pkgname", str);
        if (this.f18095c.bindService(intent, this.b, 1) || (sVar = this.a) == null) {
            return;
        }
        sVar.b();
    }

    public String b() {
        try {
            q qVar = this.d;
            return qVar == null ? "" : qVar.a();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public boolean c() {
        try {
            q qVar = this.d;
            if (qVar == null) {
                return false;
            }
            return qVar.g();
        } catch (Exception unused) {
            return false;
        }
    }

    public void d() {
        q qVar = this.d;
        if (qVar != null) {
            try {
                qVar.f();
                ServiceConnection serviceConnection = this.b;
                if (serviceConnection != null) {
                    this.f18095c.unbindService(serviceConnection);
                }
            } catch (Exception unused) {
            }
            this.b = null;
            this.d = null;
        }
    }
}
