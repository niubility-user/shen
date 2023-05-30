package com.vivo.push.ups;

import android.content.Context;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;

/* loaded from: classes11.dex */
final class a implements IPushActionListener {
    final /* synthetic */ UPSRegisterCallback a;
    final /* synthetic */ Context b;

    /* renamed from: c */
    final /* synthetic */ VUpsManager f18295c;

    public a(VUpsManager vUpsManager, UPSRegisterCallback uPSRegisterCallback, Context context) {
        this.f18295c = vUpsManager;
        this.a = uPSRegisterCallback;
        this.b = context;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i2) {
        this.a.onResult(new TokenResult(i2, PushClient.getInstance(this.b).getRegId()));
    }
}
