package com.unionpay.b;

import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.mi.UPTsmAddon;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class i implements UPTsmAddon.UPTsmConnectionListener {
    final /* synthetic */ g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(g gVar) {
        this.a = gVar;
    }

    @Override // com.unionpay.tsmservice.mi.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmConnected() {
        com.unionpay.utils.j.c("uppay", "mi TsmService connected.");
        this.a.b();
    }

    @Override // com.unionpay.tsmservice.mi.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmDisconnected() {
        String str;
        String str2;
        g gVar = this.a;
        str = gVar.d;
        str2 = this.a.f18155e;
        gVar.a(str, str2, UPSEInfoResp.ERROR_NONE, "Tsm service disconnect");
    }
}
