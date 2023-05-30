package com.jingdong.manto.widget.input;

import com.jingdong.manto.q.n;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes16.dex */
public final class n implements n.f0, n.c0 {
    final WeakReference<com.jingdong.manto.q.n> a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(com.jingdong.manto.q.n nVar) {
        this.a = new WeakReference<>(nVar);
    }

    public void a() {
        com.jingdong.manto.q.n nVar = this.a.get();
        if (nVar != null) {
            nVar.a((n.f0) this);
            nVar.a((n.c0) this);
        }
    }

    @Override // com.jingdong.manto.q.n.c0
    public final void onDestroy() {
        com.jingdong.manto.q.n nVar = this.a.get();
        if (nVar != null) {
            com.jingdong.manto.utils.e.a(nVar.l());
            o.e(nVar);
            o.d(nVar);
            nVar.b((n.f0) this);
            nVar.b((n.c0) this);
            if (nVar.s() != null) {
                u.a().b(nVar.s());
            }
        }
    }
}
