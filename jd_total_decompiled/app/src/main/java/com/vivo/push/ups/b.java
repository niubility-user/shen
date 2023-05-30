package com.vivo.push.ups;

import com.vivo.push.IPushActionListener;

/* loaded from: classes11.dex */
final class b implements IPushActionListener {
    final /* synthetic */ UPSRegisterCallback a;
    final /* synthetic */ VUpsManager b;

    public b(VUpsManager vUpsManager, UPSRegisterCallback uPSRegisterCallback) {
        this.b = vUpsManager;
        this.a = uPSRegisterCallback;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i2) {
        this.a.onResult(new TokenResult(i2, ""));
    }
}
