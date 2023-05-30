package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import java.security.MessageDigest;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class a0 implements t {

    /* renamed from: l  reason: collision with root package name */
    private static boolean f18440l;

    /* renamed from: g  reason: collision with root package name */
    private Context f18441g;

    /* renamed from: h  reason: collision with root package name */
    private ServiceConnection f18442h;

    /* renamed from: i  reason: collision with root package name */
    private volatile int f18443i = 0;

    /* renamed from: j  reason: collision with root package name */
    private volatile a f18444j = null;

    /* renamed from: k  reason: collision with root package name */
    private final Object f18445k = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class a {
        String a;

        private a(a0 a0Var) {
            this.a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class b implements ServiceConnection {
        private b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (a0.this.f18444j != null) {
                return;
            }
            new Thread(new d0(this, iBinder)).start();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class c {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static String a(IBinder iBinder, String str, String str2, String str3) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                obtain.writeString(str);
                obtain.writeString(str2);
                obtain.writeString(str3);
                iBinder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public a0(Context context) {
        this.f18441g = context;
        g();
    }

    private void g() {
        boolean z;
        this.f18442h = new b();
        Intent intent = new Intent();
        intent.setClassName("com.heytap.openid", "com.heytap.openid.IdentifyService");
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        try {
            z = this.f18441g.bindService(intent, this.f18442h, 1);
        } catch (Exception unused) {
            z = false;
        }
        this.f18443i = z ? 1 : 2;
    }

    private void i(String str) {
        if (this.f18443i != 1 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f18445k) {
            try {
                g.j.a.a.a.c.o("oppo's " + str + " wait...");
                this.f18445k.wait(3000L);
            } catch (Exception unused) {
            }
        }
    }

    public static boolean j(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 128);
            if (packageInfo != null) {
                long longVersionCode = Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
                boolean z = (packageInfo.applicationInfo.flags & 1) != 0;
                f18440l = longVersionCode >= 1;
                if (z) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String k() {
        try {
            Signature[] signatureArr = this.f18441g.getPackageManager().getPackageInfo(this.f18441g.getPackageName(), 64).signatures;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            StringBuilder sb = new StringBuilder();
            for (byte b2 : messageDigest.digest(signatureArr[0].toByteArray())) {
                sb.append(Integer.toHexString((b2 & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        ServiceConnection serviceConnection = this.f18442h;
        if (serviceConnection != null) {
            try {
                this.f18441g.unbindService(serviceConnection);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.xiaomi.push.t
    public String a() {
        i("getOAID");
        if (this.f18444j == null) {
            return null;
        }
        return this.f18444j.a;
    }

    @Override // com.xiaomi.push.t
    /* renamed from: a  reason: collision with other method in class */
    public boolean mo30a() {
        return f18440l;
    }
}
