package com.vivo.push;

import com.vivo.push.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class g implements IPushActionListener {
    final /* synthetic */ e.a a;
    final /* synthetic */ e b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(e eVar, e.a aVar) {
        this.b = eVar;
        this.a = aVar;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i2) {
        com.vivo.push.util.b bVar;
        if (i2 == 0) {
            Object[] b = this.a.b();
            if (b != null && b.length != 0) {
                this.b.a((String) this.a.b()[0]);
                return;
            } else {
                com.vivo.push.util.p.a("PushClientManager", "bind app result is null");
                return;
            }
        }
        this.b.f18276k = null;
        bVar = this.b.f18275j;
        bVar.b("APP_TOKEN");
    }
}
