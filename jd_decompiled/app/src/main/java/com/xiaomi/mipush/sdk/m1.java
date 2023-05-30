package com.xiaomi.mipush.sdk;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class m1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18398g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m1(Context context) {
        this.f18398g = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        MessageHandleService.c(this.f18398g);
    }
}
