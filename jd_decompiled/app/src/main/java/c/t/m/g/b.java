package c.t.m.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import c.t.m.g.m5;
import c.t.m.g.u1;
import java.security.MessageDigest;

/* loaded from: classes.dex */
public class b {
    public Context a;
    public String b;

    /* renamed from: c */
    public u1 f313c;
    public ServiceConnection d = new a();

    /* loaded from: classes.dex */
    public class a implements ServiceConnection {
        public a() {
            b.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            b.this.f313c = u1.a.d(iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            b.this.f313c = null;
        }
    }

    public b(Context context) {
        this.a = context;
    }

    public String a(m5.b bVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
            intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
            if (this.a.bindService(intent, this.d, 1)) {
                try {
                    SystemClock.sleep(3000L);
                } catch (Exception unused) {
                }
                if (this.f313c != null) {
                    String b = b("OUID");
                    boolean c2 = c();
                    if (bVar != null) {
                        bVar.a(b, c2);
                        return b;
                    }
                    return b;
                }
            }
            return null;
        }
        throw new IllegalStateException("Cannot run on MainThread");
    }

    public final String b(String str) {
        Signature[] signatureArr;
        String packageName = this.a.getPackageName();
        if (this.b == null) {
            String str2 = null;
            try {
                signatureArr = this.a.getPackageManager().getPackageInfo(packageName, 64).signatures;
            } catch (Exception unused) {
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
                } catch (Exception unused2) {
                }
            }
            this.b = str2;
        }
        return ((u1.a.C0008a) this.f313c).d(packageName, this.b, str);
    }

    public final boolean c() {
        try {
            PackageInfo packageInfo = this.a.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            return packageInfo != null && (Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : (long) packageInfo.versionCode) >= 1;
        } catch (Exception unused) {
            return false;
        }
    }
}
