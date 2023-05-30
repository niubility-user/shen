package com.tencent.tmsqmsp.oaid2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.tmsqmsp.oaid2.g0;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes9.dex */
public class h0 {

    /* renamed from: f  reason: collision with root package name */
    public static final h0 f18080f = new h0();
    public g0 a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f18081c;
    public final Object d = new Object();

    /* renamed from: e  reason: collision with root package name */
    public ServiceConnection f18082e = new a();

    /* loaded from: classes9.dex */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            h0.this.a = g0.a.a(iBinder);
            synchronized (h0.this.d) {
                h0.this.d.notify();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            h0.this.a = null;
        }
    }

    public String a(Context context, String str) {
        String b;
        synchronized (this) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new IllegalStateException("Cannot run on MainThread");
            }
            if (this.a == null) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
                intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
                if (context.bindService(intent, this.f18082e, 1)) {
                    synchronized (this.d) {
                        try {
                            this.d.wait(3000L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (this.a != null) {
                    try {
                        b = b(context, str);
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        b = "";
                        return b;
                    }
                }
                b = "";
            } else {
                try {
                    b = b(context, str);
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                    b = "";
                    return b;
                }
            }
        }
        return b;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0027 -> B:15:0x002a). Please submit an issue!!! */
    public boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            if (Build.VERSION.SDK_INT >= 28) {
                if (packageInfo != null && packageInfo.getLongVersionCode() >= 1) {
                    return true;
                }
            } else if (packageInfo != null && packageInfo.versionCode >= 1) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public final String b(Context context, String str) {
        Signature[] signatureArr;
        if (TextUtils.isEmpty(this.b)) {
            this.b = context.getPackageName();
        }
        if (TextUtils.isEmpty(this.f18081c)) {
            String str2 = null;
            try {
                signatureArr = context.getPackageManager().getPackageInfo(this.b, 64).signatures;
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                signatureArr = null;
            }
            if (signatureArr != null && signatureArr.length > 0) {
                byte[] byteArray = signatureArr[0].toByteArray();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                    if (messageDigest != null) {
                        byte[] digest = messageDigest.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b : digest) {
                            sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                        }
                        str2 = sb.toString();
                    }
                } catch (NoSuchAlgorithmException e3) {
                    e3.printStackTrace();
                }
            }
            this.f18081c = str2;
        }
        String a2 = ((g0.a.C0816a) this.a).a(this.b, this.f18081c, str);
        return TextUtils.isEmpty(a2) ? "" : a2;
    }
}
