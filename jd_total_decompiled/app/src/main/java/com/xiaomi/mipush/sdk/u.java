package com.xiaomi.mipush.sdk;

/* loaded from: classes11.dex */
class u implements Runnable {

    /* renamed from: g */
    final /* synthetic */ NotificationClickedActivity f18428g;

    public u(NotificationClickedActivity notificationClickedActivity) {
        this.f18428g = notificationClickedActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        g.j.a.a.a.c.E("clicked activity finish by timeout.");
        this.f18428g.finish();
    }
}
