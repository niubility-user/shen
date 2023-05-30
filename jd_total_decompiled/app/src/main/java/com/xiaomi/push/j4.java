package com.xiaomi.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.xiaomi.push.i4;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class j4 implements i4.a {
    protected Context b;
    private PendingIntent a = null;

    /* renamed from: c  reason: collision with root package name */
    private volatile long f18774c = 0;

    public j4(Context context) {
        this.b = null;
        this.b = context;
    }

    private void b(AlarmManager alarmManager, long j2, PendingIntent pendingIntent) {
        try {
            AlarmManager.class.getMethod("setExact", Integer.TYPE, Long.TYPE, PendingIntent.class).invoke(alarmManager, 2, Long.valueOf(j2), pendingIntent);
        } catch (Exception e2) {
            g.j.a.a.a.c.D("[Alarm] invoke setExact method meet error. " + e2);
        }
    }

    @Override // com.xiaomi.push.i4.a
    public void a() {
        if (this.a != null) {
            try {
                ((AlarmManager) this.b.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(this.a);
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.a = null;
                g.j.a.a.a.c.B("[Alarm] unregister timer");
                this.f18774c = 0L;
                throw th;
            }
            this.a = null;
            g.j.a.a.a.c.B("[Alarm] unregister timer");
            this.f18774c = 0L;
        }
        this.f18774c = 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
        if (r8.f18774c < r4) goto L20;
     */
    @Override // com.xiaomi.push.i4.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(boolean z) {
        long b = com.xiaomi.push.service.n2.c(this.b).b();
        if (z || this.f18774c != 0) {
            if (z) {
                a();
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (!z && this.f18774c != 0) {
                if (this.f18774c <= elapsedRealtime) {
                    this.f18774c += b;
                }
                Intent intent = new Intent(com.xiaomi.push.service.m0.o);
                intent.setPackage(this.b.getPackageName());
                c(intent, this.f18774c);
            }
            b -= elapsedRealtime % b;
            this.f18774c = elapsedRealtime + b;
            Intent intent2 = new Intent(com.xiaomi.push.service.m0.o);
            intent2.setPackage(this.b.getPackageName());
            c(intent2, this.f18774c);
        }
    }

    @Override // com.xiaomi.push.i4.a
    /* renamed from: a */
    public boolean mo81a() {
        return this.f18774c != 0;
    }

    public void c(Intent intent, long j2) {
        AlarmManager alarmManager = (AlarmManager) this.b.getSystemService(NotificationCompat.CATEGORY_ALARM);
        int i2 = Build.VERSION.SDK_INT;
        Context context = this.b;
        this.a = i2 >= 31 ? PendingIntent.getBroadcast(context, 0, intent, 33554432) : PendingIntent.getBroadcast(context, 0, intent, 0);
        if (i2 >= 31 && !a8.j(this.b)) {
            alarmManager.set(2, j2, this.a);
        } else if (i2 >= 23) {
            k0.e(alarmManager, "setExactAndAllowWhileIdle", 2, Long.valueOf(j2), this.a);
        } else {
            b(alarmManager, j2, this.a);
        }
        g.j.a.a.a.c.B("[Alarm] register timer " + j2);
    }
}
