package c.t.m.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import c.t.m.g.m5;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class h0 {
    public Context a;
    public String b = "com.mdid.msa";

    /* renamed from: c */
    public final LinkedBlockingQueue<IBinder> f460c = new LinkedBlockingQueue<>(1);
    public ServiceConnection d = new a();

    /* loaded from: classes.dex */
    public class a implements ServiceConnection {
        public a() {
            h0.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                h0.this.f460c.put(iBinder);
            } catch (Exception unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public h0(Context context) {
        this.a = context;
    }

    public final int a() {
        try {
            this.a.getPackageManager().getPackageInfo(this.b, 0);
            return 1;
        } catch (Exception unused) {
            return 0;
        }
    }

    public void b(m5.b bVar) {
        try {
            this.a.getPackageManager().getPackageInfo(this.b, 0);
        } catch (Exception unused) {
        }
        String packageName = this.a.getPackageName();
        c(packageName);
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
        intent.setAction("com.bun.msa.action.bindto.service");
        intent.putExtra("com.bun.msa.param.pkgname", packageName);
        if (this.a.bindService(intent, this.d, 1)) {
            try {
                j2 j2Var = new j2(this.f460c.take());
                String c2 = j2Var.c();
                boolean a2 = j2Var.a();
                if (bVar != null) {
                    bVar.a(c2, a2);
                }
                this.a.unbindService(this.d);
            } catch (Exception unused2) {
            } finally {
                this.a.unbindService(this.d);
            }
        }
    }

    public final void c(String str) {
        a();
        Intent intent = new Intent();
        intent.setClassName(this.b, "com.mdid.msa.service.MsaKlService");
        intent.setAction("com.bun.msa.action.start.service");
        intent.putExtra("com.bun.msa.param.pkgname", str);
        try {
            intent.putExtra("com.bun.msa.param.runinset", true);
            if (this.a.startService(intent) != null) {
            }
        } catch (Exception unused) {
        }
    }
}
