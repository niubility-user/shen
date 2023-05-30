package com.jingdong.manto.m.u0.o.k0.c0;

import com.jingdong.manto.m.u0.n;

/* loaded from: classes15.dex */
public abstract class b {
    private com.jingdong.manto.sdk.c<n> a = new com.jingdong.manto.sdk.c<>(100);

    public abstract n a();

    public void a(n nVar) {
        if (nVar != null) {
            this.a.a(nVar);
        }
    }

    public final n b() {
        n a = this.a.a();
        return a == null ? a() : a;
    }
}
