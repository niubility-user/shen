package com.vivo.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.vivo.push.util.t;
import com.vivo.push.util.z;
import com.vivo.vms.IPCInvoke;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes11.dex */
public final class b implements ServiceConnection {
    private static final Object a = new Object();
    private static Map<String, b> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private boolean f18237c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private Context f18238e;

    /* renamed from: g  reason: collision with root package name */
    private volatile IPCInvoke f18240g;

    /* renamed from: i  reason: collision with root package name */
    private String f18242i;

    /* renamed from: j  reason: collision with root package name */
    private Handler f18243j;

    /* renamed from: h  reason: collision with root package name */
    private Object f18241h = new Object();

    /* renamed from: f  reason: collision with root package name */
    private AtomicInteger f18239f = new AtomicInteger(1);

    private b(Context context, String str) {
        this.d = null;
        this.f18243j = null;
        this.f18238e = context;
        this.f18242i = str;
        this.f18243j = new Handler(Looper.getMainLooper(), new c(this));
        String b2 = t.b(context);
        this.d = b2;
        if (!TextUtils.isEmpty(b2) && !TextUtils.isEmpty(this.f18242i)) {
            this.f18237c = z.a(context, this.d) >= 1260;
            b();
            return;
        }
        com.vivo.push.util.p.c(this.f18238e, "init error : push pkgname is " + this.d + " ; action is " + this.f18242i);
        this.f18237c = false;
    }

    private void d() {
        this.f18243j.removeMessages(1);
        this.f18243j.sendEmptyMessageDelayed(1, 3000L);
    }

    private void e() {
        this.f18243j.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            this.f18238e.unbindService(this);
        } catch (Exception e2) {
            com.vivo.push.util.p.a("AidlManager", "On unBindServiceException:" + e2.getMessage());
        }
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        com.vivo.push.util.p.b("AidlManager", "onBindingDied : ".concat(String.valueOf(componentName)));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        e();
        this.f18240g = IPCInvoke.Stub.asInterface(iBinder);
        if (this.f18240g == null) {
            com.vivo.push.util.p.d("AidlManager", "onServiceConnected error : aidl must not be null.");
            f();
            this.f18239f.set(1);
            return;
        }
        if (this.f18239f.get() == 2) {
            a(4);
        } else if (this.f18239f.get() != 4) {
            f();
        }
        synchronized (this.f18241h) {
            this.f18241h.notifyAll();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f18240g = null;
        a(1);
    }

    public static b a(Context context, String str) {
        b bVar = b.get(str);
        if (bVar == null) {
            synchronized (a) {
                bVar = b.get(str);
                if (bVar == null) {
                    bVar = new b(context, str);
                    b.put(str, bVar);
                }
            }
        }
        return bVar;
    }

    private void b() {
        int i2 = this.f18239f.get();
        com.vivo.push.util.p.d("AidlManager", "Enter connect, Connection Status: ".concat(String.valueOf(i2)));
        if (i2 == 4 || i2 == 2 || i2 == 3 || i2 == 5 || !this.f18237c) {
            return;
        }
        a(2);
        if (!c()) {
            a(1);
            com.vivo.push.util.p.a("AidlManager", "bind core service fail");
            return;
        }
        d();
    }

    private boolean c() {
        Intent intent = new Intent(this.f18242i);
        intent.setPackage(this.d);
        try {
            return this.f18238e.bindService(intent, this, 1);
        } catch (Exception e2) {
            com.vivo.push.util.p.a("AidlManager", "bind core error", e2);
            return false;
        }
    }

    public final boolean a() {
        String b2 = t.b(this.f18238e);
        this.d = b2;
        if (TextUtils.isEmpty(b2)) {
            com.vivo.push.util.p.c(this.f18238e, "push pkgname is null");
            return false;
        }
        boolean z = z.a(this.f18238e, this.d) >= 1260;
        this.f18237c = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        this.f18239f.set(i2);
    }

    public final boolean a(Bundle bundle) {
        b();
        if (this.f18239f.get() == 2) {
            synchronized (this.f18241h) {
                try {
                    this.f18241h.wait(2000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        try {
            int i2 = this.f18239f.get();
            if (i2 == 4) {
                this.f18243j.removeMessages(2);
                this.f18243j.sendEmptyMessageDelayed(2, Final.HALF_MINUTE);
                this.f18240g.asyncCall(bundle, null);
                return true;
            }
            com.vivo.push.util.p.d("AidlManager", "invoke error : connect status = ".concat(String.valueOf(i2)));
            return false;
        } catch (Exception e3) {
            com.vivo.push.util.p.a("AidlManager", "invoke error ", e3);
            int i3 = this.f18239f.get();
            com.vivo.push.util.p.d("AidlManager", "Enter disconnect, Connection Status: ".concat(String.valueOf(i3)));
            if (i3 == 2) {
                e();
                a(1);
                return false;
            } else if (i3 == 3) {
                a(1);
                return false;
            } else if (i3 != 4) {
                return false;
            } else {
                a(1);
                f();
                return false;
            }
        }
    }
}
