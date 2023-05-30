package com.jdjr.risk.device.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* loaded from: classes18.dex */
public class e {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f7349c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f7350e;

    /* renamed from: f  reason: collision with root package name */
    private BroadcastReceiver f7351f;

    /* renamed from: g  reason: collision with root package name */
    private volatile boolean f7352g;

    /* loaded from: classes18.dex */
    private static class a {
        static final e a = new e();
    }

    private e() {
        this.f7352g = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static e e() {
        return a.a;
    }

    public int a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context) {
        if (this.f7351f != null) {
            return;
        }
        this.f7351f = new BroadcastReceiver() { // from class: com.jdjr.risk.device.c.e.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                int intExtra = intent.getIntExtra("level", -1);
                e.this.b = intent.getIntExtra("scale", -1);
                e.this.f7349c = intent.getIntExtra("status", -1);
                e.this.d = intent.getIntExtra("health", -1);
                e.this.f7350e = intent.getIntExtra("voltage", -1);
                e.this.a = -1;
                if (intExtra >= 0 && e.this.b > 0) {
                    e eVar = e.this;
                    eVar.a = (intExtra * 100) / eVar.b;
                }
                e.this.f7352g = true;
            }
        };
        context.registerReceiver(this.f7351f, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        long currentTimeMillis = System.currentTimeMillis();
        while (!this.f7352g) {
            if (System.currentTimeMillis() - currentTimeMillis > 1000) {
                this.f7352g = true;
            }
        }
        context.unregisterReceiver(this.f7351f);
        this.f7351f = null;
    }

    public int b() {
        return this.f7349c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.f7350e;
    }
}
