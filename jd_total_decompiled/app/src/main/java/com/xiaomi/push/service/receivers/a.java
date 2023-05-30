package com.xiaomi.push.service.receivers;

import android.content.Context;

/* loaded from: classes11.dex */
class a implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f19181g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ NetworkStatusReceiver f19182h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(NetworkStatusReceiver networkStatusReceiver, Context context) {
        this.f19182h = networkStatusReceiver;
        this.f19181g = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f19182h.a(this.f19181g);
    }
}
