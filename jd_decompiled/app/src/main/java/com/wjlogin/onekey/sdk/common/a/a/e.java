package com.wjlogin.onekey.sdk.common.a.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ g b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(g gVar, String str) {
        this.b = gVar;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        b bVar;
        this.b.f18335l = true;
        bVar = this.b.f18331h;
        bVar.a(this.a);
    }
}
