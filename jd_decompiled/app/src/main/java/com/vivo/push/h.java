package com.vivo.push;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class h implements Runnable {
    final /* synthetic */ com.vivo.push.b.b a;
    final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ e f18282c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(e eVar, com.vivo.push.b.b bVar, String str) {
        this.f18282c = eVar;
        this.a = bVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f18282c.a(this.a);
        this.f18282c.e(this.b);
    }
}
