package com.jd.android.sdk.oaid.a;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;

/* loaded from: classes12.dex */
class j implements ServiceConnection {
    private static final String a = j.class.getSimpleName();
    private final Context b;

    /* renamed from: c  reason: collision with root package name */
    private final OaidInfoRequestListener f1695c;
    private final a d;

    /* loaded from: classes12.dex */
    public interface a {
        String a(IBinder iBinder);
    }

    private j(Context context, OaidInfoRequestListener oaidInfoRequestListener, a aVar) {
        this.b = context instanceof Application ? context : context.getApplicationContext();
        this.f1695c = oaidInfoRequestListener;
        this.d = aVar;
    }

    public static void a(Context context, Intent intent, OaidInfoRequestListener oaidInfoRequestListener, a aVar) {
        j jVar = new j(context, oaidInfoRequestListener, aVar);
        try {
            if (!jVar.b.bindService(intent, jVar, 1)) {
                throw new h("Service binding failed");
            }
            com.jd.android.sdk.oaid.b.a(a, "Service has been bound: ".concat(String.valueOf(intent)));
        } catch (Throwable unused) {
            jVar.f1695c.onResult(new OaidInfo());
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String str = a;
        com.jd.android.sdk.oaid.b.a(str, "Service has been connected: " + componentName.getClassName());
        try {
            try {
                try {
                    OaidInfo oaidInfo = new OaidInfo();
                    String a2 = this.d.a(iBinder);
                    if (!TextUtils.isEmpty(a2)) {
                        oaidInfo.setOAID(a2);
                    }
                    com.jd.android.sdk.oaid.b.a(str, "OAID/AAID acquire success: ".concat(String.valueOf(a2)));
                    this.f1695c.onResult(oaidInfo);
                    this.b.unbindService(this);
                    com.jd.android.sdk.oaid.b.a(str, "Service has been unbound: " + componentName.getClassName());
                } catch (Exception unused) {
                    this.f1695c.onResult(new OaidInfo());
                    this.b.unbindService(this);
                    com.jd.android.sdk.oaid.b.a(a, "Service has been unbound: " + componentName.getClassName());
                }
            } catch (Throwable th) {
                try {
                    this.b.unbindService(this);
                    com.jd.android.sdk.oaid.b.a(a, "Service has been unbound: " + componentName.getClassName());
                } catch (Exception e2) {
                    com.jd.android.sdk.oaid.b.a(a, "Service unbound :", e2);
                }
                throw th;
            }
        } catch (Exception e3) {
            com.jd.android.sdk.oaid.b.a(a, "Service unbound :", e3);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        com.jd.android.sdk.oaid.b.a(a, "Service has been disconnected: " + componentName.getClassName());
    }
}
