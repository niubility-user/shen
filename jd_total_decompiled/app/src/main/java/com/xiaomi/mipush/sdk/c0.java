package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c0 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18366g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Intent f18367h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0(Context context, Intent intent) {
        this.f18366g = context;
        this.f18367h = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        PushMessageHandler.b(this.f18366g, this.f18367h);
    }
}
