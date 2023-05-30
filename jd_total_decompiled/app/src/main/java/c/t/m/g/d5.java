package c.t.m.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import c.t.m.g.m5;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class d5 {
    public Context a;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

    /* renamed from: c */
    public ServiceConnection f352c = new a();

    /* loaded from: classes.dex */
    public class a implements ServiceConnection {
        public a() {
            d5.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                d5.this.b.put(iBinder);
            } catch (Exception unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public d5(Context context) {
        this.a = context;
    }

    public void a(m5.b bVar) {
        try {
            this.a.getPackageManager().getPackageInfo("com.asus.msa.SupplementaryDID", 0);
        } catch (Exception unused) {
        }
        Intent intent = new Intent();
        intent.setAction("com.asus.msa.action.ACCESS_DID");
        intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        if (this.a.bindService(intent, this.f352c, 1)) {
            try {
                p0 p0Var = new p0(this.b.take());
                String e2 = p0Var.e();
                boolean j2 = p0Var.j();
                if (bVar != null) {
                    bVar.a(e2, j2);
                }
            } catch (Exception unused2) {
            }
        }
    }
}
