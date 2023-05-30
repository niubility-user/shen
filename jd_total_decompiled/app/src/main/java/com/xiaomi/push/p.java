package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class p implements t {

    /* renamed from: m  reason: collision with root package name */
    private static boolean f18921m;

    /* renamed from: g  reason: collision with root package name */
    private Context f18922g;

    /* renamed from: h  reason: collision with root package name */
    private ServiceConnection f18923h;

    /* renamed from: k  reason: collision with root package name */
    private volatile boolean f18926k;

    /* renamed from: i  reason: collision with root package name */
    private volatile int f18924i = 0;

    /* renamed from: j  reason: collision with root package name */
    private volatile String f18925j = null;

    /* renamed from: l  reason: collision with root package name */
    private final Object f18927l = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class a implements ServiceConnection {
        private a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            new Thread(new s(this, iBinder)).start();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class b {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static String a(IBinder iBinder) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                iBinder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static boolean b(IBinder iBinder) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                iBinder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt() != 0;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public p(Context context) {
        this.f18922g = context;
        d();
    }

    private void d() {
        boolean z;
        this.f18923h = new a();
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        try {
            z = this.f18922g.bindService(intent, this.f18923h, 1);
        } catch (Exception unused) {
            z = false;
        }
        this.f18924i = z ? 1 : 2;
    }

    private void f(String str) {
        if (this.f18924i != 1 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f18927l) {
            try {
                g.j.a.a.a.c.o("huawei's " + str + " wait...");
                this.f18927l.wait(3000L);
            } catch (Exception unused) {
            }
        }
    }

    public static boolean g(Context context) {
        boolean z;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.hwid", 128);
            z = (packageInfo.applicationInfo.flags & 1) != 0;
            f18921m = packageInfo.versionCode >= 20602000;
        } catch (Exception unused) {
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ServiceConnection serviceConnection = this.f18923h;
        if (serviceConnection != null) {
            try {
                this.f18922g.unbindService(serviceConnection);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.xiaomi.push.t
    public String a() {
        f("getOAID");
        return this.f18925j;
    }

    @Override // com.xiaomi.push.t
    /* renamed from: a */
    public boolean mo30a() {
        return f18921m;
    }
}
