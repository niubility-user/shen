package com.vivo.push;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class i implements IPushActionListener {
    final /* synthetic */ e a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(e eVar) {
        this.a = eVar;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i2) {
        com.vivo.push.util.b bVar;
        com.vivo.push.util.b bVar2;
        if (i2 == 0) {
            this.a.f18276k = "";
            bVar2 = this.a.f18275j;
            bVar2.a("APP_TOKEN", "");
            this.a.m();
            this.a.f18275j.b("APP_TAGS");
            return;
        }
        this.a.f18276k = null;
        bVar = this.a.f18275j;
        bVar.b("APP_TOKEN");
    }
}
