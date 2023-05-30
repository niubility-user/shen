package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.o;
import java.util.concurrent.ScheduledFuture;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class t implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ o.a.C0822a f18426g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(o.a.C0822a c0822a) {
        this.f18426g = c0822a;
    }

    @Override // java.lang.Runnable
    public void run() {
        ScheduledFuture scheduledFuture;
        ScheduledFuture scheduledFuture2;
        if (this.f18426g.b.size() != 0) {
            this.f18426g.f();
            return;
        }
        scheduledFuture = this.f18426g.f18404c;
        if (scheduledFuture != null) {
            scheduledFuture2 = this.f18426g.f18404c;
            scheduledFuture2.cancel(false);
            this.f18426g.f18404c = null;
        }
    }
}
