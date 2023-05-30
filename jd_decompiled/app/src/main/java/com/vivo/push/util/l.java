package com.vivo.push.util;

import android.content.Context;
import com.vivo.push.d.r;
import com.vivo.push.model.InsideNotificationItem;
import java.util.List;

/* loaded from: classes11.dex */
final class l implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ k b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(k kVar, List list) {
        this.b = kVar;
        this.a = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InsideNotificationItem insideNotificationItem;
        long j2;
        Context context;
        InsideNotificationItem insideNotificationItem2;
        long j3;
        int i2;
        r.a aVar;
        insideNotificationItem = this.b.b;
        if (insideNotificationItem != null) {
            w b = w.b();
            j2 = this.b.f18308c;
            b.a("com.vivo.push.notify_key", j2);
            context = this.b.a;
            List list = this.a;
            insideNotificationItem2 = this.b.b;
            j3 = this.b.f18308c;
            i2 = this.b.f18309e;
            aVar = this.b.f18310f;
            NotifyAdapterUtil.pushNotification(context, list, insideNotificationItem2, j3, i2, aVar);
        }
    }
}
