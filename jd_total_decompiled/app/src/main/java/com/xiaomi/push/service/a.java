package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.xiaomi.push.a8;
import com.xiaomi.push.r6;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class a {

    /* renamed from: e  reason: collision with root package name */
    private static a f19055e;
    private Context a;
    private List<Message> b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private boolean f19056c = false;
    private Messenger d;

    static {
        String str = r6.a(5) + "-";
    }

    private a(Context context) {
        this.a = context.getApplicationContext();
        if (f()) {
            g.j.a.a.a.c.B("use miui push service");
        }
    }

    private Message a(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    private synchronized void e(Intent intent) {
        if (this.f19056c) {
            Message a = a(intent);
            if (this.b.size() >= 50) {
                this.b.remove(0);
            }
            this.b.add(a);
            return;
        }
        if (this.d == null) {
            this.a.bindService(intent, new y0(this), 1);
            this.f19056c = true;
            this.b.clear();
            this.b.add(a(intent));
        } else {
            try {
                this.d.send(a(intent));
            } catch (RemoteException unused) {
                this.d = null;
                this.f19056c = false;
            }
        }
    }

    private boolean f() {
        if (com.xiaomi.push.b.b) {
            return false;
        }
        try {
            PackageInfo packageInfo = this.a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode >= 104;
        } catch (Exception unused) {
            return false;
        }
    }

    public static a h(Context context) {
        if (f19055e == null) {
            f19055e = new a(context);
        }
        return f19055e;
    }

    public boolean i(Intent intent) {
        try {
            if (a8.i() || Build.VERSION.SDK_INT < 26) {
                this.a.startService(intent);
                return true;
            }
            e(intent);
            return true;
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return false;
        }
    }
}
