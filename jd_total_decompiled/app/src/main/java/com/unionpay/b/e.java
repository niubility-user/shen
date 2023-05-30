package com.unionpay.b;

import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.UPTsmAddon;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class e implements UPTsmAddon.UPTsmConnectionListener {
    final /* synthetic */ b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(b bVar) {
        this.a = bVar;
    }

    @Override // com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmConnected() {
        com.unionpay.utils.j.c("uppay", "TsmService connected.");
        this.a.b();
    }

    @Override // com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmDisconnected() {
        String str;
        String str2;
        b bVar = this.a;
        str = bVar.d;
        str2 = this.a.f18147e;
        bVar.a(str, str2, UPSEInfoResp.ERROR_NONE, "Tsm service disconnect");
    }
}
