package com.xiaomi.push;

import com.xiaomi.push.l;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class n implements Runnable {

    /* renamed from: g */
    final /* synthetic */ l.b f18872g;

    /* renamed from: h */
    final /* synthetic */ l f18873h;

    public n(l lVar, l.b bVar) {
        this.f18873h = lVar;
        this.f18872g = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f18873h.e(this.f18872g);
    }
}
