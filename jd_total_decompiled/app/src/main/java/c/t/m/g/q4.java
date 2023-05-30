package c.t.m.g;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

/* loaded from: classes.dex */
public class q4 extends BroadcastReceiver {
    public final y4 a;
    public boolean b;

    public q4(y4 y4Var) {
        this.a = y4Var;
    }

    public void a() {
        if (this.b) {
            this.b = false;
            try {
                this.a.a.unregisterReceiver(this);
            } catch (Exception unused) {
            }
        }
    }

    public final void b(Handler handler) {
        if (handler != null) {
            try {
                this.a.a.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"), null, handler);
            } catch (Exception unused) {
            }
        }
    }

    public void c(Handler handler) {
        if (this.b) {
            return;
        }
        this.b = true;
        b(handler);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            if (intent.getBooleanExtra("noConnectivity", false)) {
                this.a.f(-1);
            } else if (g.c(context)) {
                this.a.f(1);
            } else {
                this.a.f(0);
            }
        } catch (Exception unused) {
        }
    }
}
