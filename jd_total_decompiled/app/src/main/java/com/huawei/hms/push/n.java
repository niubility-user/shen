package com.huawei.hms.push;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes12.dex */
public class n extends Thread {
    private Context a;
    private m b;

    public n(Context context, m mVar) {
        this.a = context;
        this.b = mVar;
    }

    private static Intent a(Context context, m mVar) {
        if (mVar == null) {
            return null;
        }
        Intent b = d.b(context, mVar.d());
        if (mVar.n() != null) {
            try {
                Intent parseUri = Intent.parseUri(mVar.n(), 0);
                parseUri.setSelector(null);
                HMSLog.d("PushSelfShowLog", "Intent.parseUri(msg.intentUri, 0), action:" + parseUri.getAction());
                return d.a(context, mVar.d(), parseUri).booleanValue() ? parseUri : b;
            } catch (Exception e2) {
                HMSLog.w("PushSelfShowLog", "intentUri error," + e2.toString());
                return b;
            }
        }
        if (mVar.a() != null) {
            Intent intent = new Intent(mVar.a());
            if (d.a(context, mVar.d(), intent).booleanValue()) {
                b = intent;
            }
        }
        b.setPackage(mVar.d());
        return b;
    }

    private boolean b(Context context) {
        if ("cosa".equals(this.b.i())) {
            return a(context);
        }
        return true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        HMSLog.i("PushSelfShowLog", "enter run()");
        try {
            if (!b(this.a) || b(this.a, this.b)) {
                return;
            }
            l.a(this.a, this.b);
        } catch (Exception e2) {
            HMSLog.e("PushSelfShowLog", e2.toString());
        }
    }

    private boolean b(Context context, m mVar) {
        if ("cosa".equals(mVar.i()) && a(context, mVar) == null) {
            HMSLog.d("PushSelfShowLog", "launchCosaApp,intent == null");
            return true;
        }
        return false;
    }

    private boolean a(Context context) {
        return d.c(context, this.b.d());
    }
}
