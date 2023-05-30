package com.tencent.tmsqmsp.oaid2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.os.RemoteException;
import com.tencent.tmsqmsp.oaid2.m;

/* loaded from: classes9.dex */
public class j {

    /* loaded from: classes9.dex */
    public static final class a {
        public final String a;
        public final boolean b;

        public a(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public final String a() {
            return this.a;
        }

        public final boolean b() {
            return this.b;
        }
    }

    public static a a(Context context) {
        String str;
        k kVar;
        Intent intent;
        String str2;
        a();
        String str3 = "getAdvertisingIdInfo " + System.currentTimeMillis();
        if (Looper.myLooper() == Looper.getMainLooper()) {
            c.b("Cannot be called from the main thread");
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        try {
            context.getPackageManager().getPackageInfo("com.huawei.hwid", 0);
            kVar = new k();
            intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
            intent.setPackage("com.huawei.hwid");
        } catch (PackageManager.NameNotFoundException unused) {
            str = "HMS not found";
        }
        if (!context.bindService(intent, kVar, 1)) {
            str = "bind failed";
            c.b(str);
            return null;
        }
        a();
        try {
            if (kVar.a) {
                throw new IllegalStateException();
            }
            kVar.a = true;
            m a2 = m.a.a(kVar.b.take());
            return new a(a2.m(), a2.h());
        } catch (RemoteException unused2) {
            str2 = "bind hms service RemoteException";
            try {
                c.b(str2);
                return null;
            } finally {
                context.unbindService(kVar);
            }
        } catch (Throwable unused3) {
            str2 = "bind hms service InterruptedException";
            c.b(str2);
            return null;
        }
    }

    public static String a() {
        return "AdId";
    }
}
