package com.meizu.cloud.pushsdk.d.k;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.meizu.cloud.pushinternal.DebugLogger;

@SuppressLint({"MissingPermission"})
/* loaded from: classes14.dex */
public class a {
    private AlarmManager a;
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private Runnable f15721c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private int f15722e;

    /* renamed from: f  reason: collision with root package name */
    private b f15723f;

    /* renamed from: g  reason: collision with root package name */
    private PendingIntent f15724g;

    /* renamed from: h  reason: collision with root package name */
    private String f15725h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f15726i;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes14.dex */
    public class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !intent.getAction().equals("alarm.util")) {
                return;
            }
            DebugLogger.i("AlarmUtils", "on receive delayed task, keyword: " + a.this.f15725h);
            a.this.f15726i = true;
            a.this.d();
            a.this.f15721c.run();
        }
    }

    public a(Context context, Runnable runnable, long j2) {
        this(context, runnable, j2, true);
    }

    public a(Context context, Runnable runnable, long j2, boolean z) {
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        this.f15721c = runnable;
        this.d = j2;
        this.f15722e = !z ? 1 : 0;
        this.a = (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.f15726i = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            b bVar = this.f15723f;
            if (bVar != null) {
                this.b.unregisterReceiver(bVar);
                this.f15723f = null;
            }
        } catch (Exception e2) {
            DebugLogger.e("AlarmUtils", "clean error, " + e2.getMessage());
        }
    }

    public void b() {
        if (this.a != null && this.f15724g != null && !this.f15726i) {
            DebugLogger.i("AlarmUtils", "cancel  delayed task, keyword: " + this.f15725h);
            this.a.cancel(this.f15724g);
        }
        d();
    }

    public boolean g() {
        if (!this.f15726i) {
            DebugLogger.e("AlarmUtils", "last task not completed");
            return false;
        }
        this.f15726i = false;
        b bVar = new b();
        this.f15723f = bVar;
        this.b.registerReceiver(bVar, new IntentFilter("alarm.util"));
        this.f15725h = String.valueOf(System.currentTimeMillis());
        this.f15724g = PendingIntent.getBroadcast(this.b, 0, new Intent("alarm.util"), 1140850688);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            this.a.setExactAndAllowWhileIdle(this.f15722e, System.currentTimeMillis() + this.d, this.f15724g);
        } else if (i2 >= 19) {
            this.a.setExact(this.f15722e, System.currentTimeMillis() + this.d, this.f15724g);
        } else {
            this.a.set(this.f15722e, System.currentTimeMillis() + this.d, this.f15724g);
        }
        DebugLogger.i("AlarmUtils", "start delayed task, keyword: " + this.f15725h);
        return true;
    }
}
