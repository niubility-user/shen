package com.jingdong.manto.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

/* loaded from: classes16.dex */
public final class c0 {
    private Context a;
    private b b = new b();

    /* renamed from: c  reason: collision with root package name */
    private c f14311c;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class b extends BroadcastReceiver {
        private String a;

        private b() {
            this.a = null;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            this.a = action;
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                c0.this.f14311c.b();
            } else if ("android.intent.action.SCREEN_OFF".equals(this.a)) {
                c0.this.f14311c.c();
            } else if ("android.intent.action.USER_PRESENT".equals(this.a)) {
                c0.this.f14311c.a();
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a();

        void b();

        void c();
    }

    public c0(Context context) {
        this.a = context;
    }

    private void a() {
        Context context = this.a;
        if (context == null) {
            return;
        }
        if (((PowerManager) context.getSystemService("power")).isScreenOn()) {
            c cVar = this.f14311c;
            if (cVar != null) {
                cVar.b();
                return;
            }
            return;
        }
        c cVar2 = this.f14311c;
        if (cVar2 != null) {
            cVar2.c();
        }
    }

    private void b() {
        if (this.a != null) {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                this.a.registerReceiver(this.b, intentFilter);
            } catch (Exception e2) {
                MantoLog.e("screen", "" + e2);
            }
        }
    }

    private void d() {
        try {
            Context context = this.a;
            if (context != null) {
                context.unregisterReceiver(this.b);
            }
        } catch (Exception e2) {
            MantoLog.e("screen", "" + e2);
        }
    }

    public void a(c cVar) {
        this.f14311c = cVar;
        b();
        a();
    }

    public void c() {
        d();
    }
}
