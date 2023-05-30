package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.o;
import com.xiaomi.push.g7;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class s implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ g7 f18422g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ o.a.C0822a f18423h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(o.a.C0822a c0822a, g7 g7Var) {
        this.f18423h = c0822a;
        this.f18422g = g7Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f18423h.b.add(this.f18422g);
        this.f18423h.c();
    }
}
