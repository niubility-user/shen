package com.vivo.push;

import android.os.Handler;
import android.os.Message;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class c implements Handler.Callback {
    final /* synthetic */ b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar) {
        this.a = bVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        AtomicInteger atomicInteger;
        AtomicInteger atomicInteger2;
        if (message == null) {
            com.vivo.push.util.p.a("AidlManager", "handleMessage error : msg is null");
            return false;
        }
        int i2 = message.what;
        if (i2 == 1) {
            com.vivo.push.util.p.a("AidlManager", "In connect, bind core service time out");
            atomicInteger = this.a.f18239f;
            if (atomicInteger.get() == 2) {
                this.a.a(1);
            }
        } else if (i2 == 2) {
            atomicInteger2 = this.a.f18239f;
            if (atomicInteger2.get() == 4) {
                this.a.f();
            }
            this.a.a(1);
        } else {
            com.vivo.push.util.p.b("AidlManager", "unknow msg what [" + message.what + "]");
        }
        return true;
    }
}
