package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.mipush.sdk.m;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class h implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f18384g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f18385h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ String f18386i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ m.a f18387j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(String str, String str2, String str3, m.a aVar) {
        this.f18384g = str;
        this.f18385h = str2;
        this.f18386i = str3;
        this.f18387j = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        context = m.a;
        m.E(context, this.f18384g, this.f18385h, null, this.f18386i, this.f18387j);
    }
}
