package c.t.m.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import c.t.m.g.m5;

/* loaded from: classes.dex */
public class d6 {
    public Context a;
    public m1 b;

    /* renamed from: c */
    public ServiceConnection f354c = new a();

    /* loaded from: classes.dex */
    public class a implements ServiceConnection {
        public a() {
            d6.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            d6.this.b = new f1(iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public d6(Context context) {
        this.a = context;
    }

    public void a(m5.b bVar) {
        m1 m1Var;
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        if (!this.a.bindService(intent, this.f354c, 1) || (m1Var = this.b) == null) {
            return;
        }
        String b = m1Var.b();
        boolean d = this.b.d();
        if (bVar != null) {
            bVar.a(b, d);
        }
    }
}
