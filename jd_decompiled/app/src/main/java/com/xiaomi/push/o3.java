package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class o3 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18891g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f18892h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ int f18893i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ String f18894j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o3(Context context, String str, int i2, String str2) {
        this.f18891g = context;
        this.f18892h = str;
        this.f18893i = i2;
        this.f18894j = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        n3.e(this.f18891g, this.f18892h, this.f18893i, this.f18894j);
    }
}
