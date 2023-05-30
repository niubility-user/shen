package com.xiaomi.push.service;

import com.xiaomi.push.i;

/* loaded from: classes11.dex */
class o extends i.a {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f19154g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ y f19155h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ int f19156i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(String str, y yVar, int i2) {
        this.f19154g = str;
        this.f19155h = yVar;
        this.f19156i = i2;
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return this.f19154g;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f19155h.m(this.f19156i);
    }
}
