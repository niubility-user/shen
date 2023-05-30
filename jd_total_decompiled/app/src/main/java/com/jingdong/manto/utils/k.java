package com.jingdong.manto.utils;

import android.content.Intent;
import android.content.IntentFilter;

/* loaded from: classes16.dex */
public class k {

    /* renamed from: c  reason: collision with root package name */
    private static k f14313c;
    private int a;
    private boolean b;

    private k() {
    }

    public static synchronized k c() {
        k kVar;
        synchronized (k.class) {
            if (f14313c == null) {
                f14313c = new k();
            }
            kVar = f14313c;
        }
        return kVar;
    }

    public int a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        try {
            Intent registerReceiver = com.jingdong.a.g().registerReceiver(null, intentFilter);
            this.a = (registerReceiver.getIntExtra("level", 0) * 100) / registerReceiver.getIntExtra("scale", 0);
        } catch (Throwable th) {
            MantoLog.e("MantoBatteryUtil", th.getMessage());
        }
        return this.a;
    }

    public boolean b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        try {
            boolean z = true;
            if (com.jingdong.a.g().registerReceiver(null, intentFilter).getIntExtra("status", 1) != 2) {
                z = false;
            }
            this.b = z;
        } catch (Throwable th) {
            MantoLog.e("MantoBatteryUtil", th.getMessage());
        }
        return this.b;
    }
}
