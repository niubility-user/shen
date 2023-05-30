package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.UPSEInfoResp;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class h implements Handler.Callback {
    final /* synthetic */ g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g gVar) {
        this.a = gVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Handler handler;
        Handler handler2;
        int i2 = message.what;
        if (i2 == 1) {
            handler = this.a.f18159i;
            handler.removeMessages(4);
            com.unionpay.utils.j.c("uppay", "msg error");
            g.a(this.a, message.arg1, (String) message.obj);
            return false;
        } else if (i2 == 4) {
            com.unionpay.utils.j.c("uppay", "timeout");
            g.a(this.a, message.arg1, UPSEInfoResp.ERROR_TIMEOUT);
            g.b(this.a);
            return false;
        } else if (i2 != 4000) {
            return false;
        } else {
            handler2 = this.a.f18159i;
            handler2.removeMessages(4);
            g.a(this.a, (Bundle) message.obj);
            return false;
        }
    }
}
