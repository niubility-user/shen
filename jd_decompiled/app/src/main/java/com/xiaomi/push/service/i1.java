package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import com.xiaomi.push.i;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class i1 extends i.a {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ int f19115g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f19116h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ Context f19117i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ String f19118j;

    /* renamed from: k  reason: collision with root package name */
    final /* synthetic */ Notification f19119k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i1(int i2, String str, Context context, String str2, Notification notification) {
        this.f19115g = i2;
        this.f19116h = str;
        this.f19117i = context;
        this.f19118j = str2;
        this.f19119k = notification;
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        String i2;
        i2 = h1.i(this.f19115g, this.f19116h);
        return i2;
    }

    @Override // java.lang.Runnable
    @TargetApi(19)
    public void run() {
        h1.k(this.f19117i, this.f19118j, this.f19115g, this.f19116h, this.f19119k);
    }
}
