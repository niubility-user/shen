package c.t.m.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import c.t.m.g.m5;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class v5 {
    public Context a;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

    /* renamed from: c */
    public ServiceConnection f722c = new a();

    /* loaded from: classes.dex */
    public class a implements ServiceConnection {
        public a() {
            v5.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                v5.this.b.put(iBinder);
            } catch (Exception unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public v5(Context context) {
        this.a = context;
    }

    public void a(m5.b bVar) {
        try {
            this.a.getPackageManager().getPackageInfo("com.huawei.hwid", 0);
        } catch (Exception unused) {
        }
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        if (this.a.bindService(intent, this.f722c, 1)) {
            try {
                x0 x0Var = new x0(this.b.take(), this.a);
                String j2 = x0Var.j();
                x0Var.e();
                x0Var.k();
                boolean b = b();
                if (bVar != null) {
                    bVar.a(j2, b);
                }
            } catch (Exception unused2) {
            } catch (Throwable th) {
                this.a.unbindService(this.f722c);
                throw th;
            }
            this.a.unbindService(this.f722c);
        }
    }

    public boolean b() {
        try {
            this.a.getPackageManager().getPackageInfo("com.huawei.hwid", 0);
            new Intent("com.uodis.opendevice.OPENIDS_SERVICE").setPackage("com.huawei.hwid");
            return !r2.queryIntentServices(r3, 0).isEmpty();
        } catch (Exception unused) {
            return false;
        }
    }
}
