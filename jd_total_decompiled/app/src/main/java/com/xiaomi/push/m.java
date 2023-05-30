package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.push.l;

/* loaded from: classes11.dex */
public class m extends Handler {
    public m(l lVar, Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        l.b bVar = (l.b) message.obj;
        int i2 = message.what;
        if (i2 == 0) {
            bVar.a();
        } else if (i2 == 1) {
            bVar.c();
        }
        super.handleMessage(message);
    }
}
