package com.xiaomi.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r9) {
        /*
            r8 = this;
            android.content.Context r0 = r8.b
            com.xiaomi.push.service.n2 r0 = com.xiaomi.push.service.n2.c(r0)
            long r0 = r0.b()
            r2 = 0
            if (r9 != 0) goto L15
            long r4 = r8.f18774c
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L15
            return
        L15:
            if (r9 == 0) goto L1a
            r8.a()
        L1a:
            long r4 = android.os.SystemClock.elapsedRealtime()
            if (r9 != 0) goto L39
            long r6 = r8.f18774c
            int r9 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r9 != 0) goto L27
            goto L39
        L27:
            long r2 = r8.f18774c
            int r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r9 > 0) goto L3f
            long r2 = r8.f18774c
            long r2 = r2 + r0
            r8.f18774c = r2
            long r2 = r8.f18774c
            int r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r9 >= 0) goto L3f
            goto L3c
        L39:
            long r2 = r4 % r0
            long r0 = r0 - r2
        L3c:
            long r4 = r4 + r0
            r8.f18774c = r4
        L3f:
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r0 = com.xiaomi.push.service.m0.o
            r9.<init>(r0)
            android.content.Context r0 = r8.b
            java.lang.String r0 = r0.getPackageName()
            r9.setPackage(r0)
            long r0 = r8.f18774c
            r8.c(r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.j4.a(boolean):void");
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
