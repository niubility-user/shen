package com.meizu.cloud.pushsdk.e.f;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes14.dex */
public class d extends Handler {
    private final WeakReference<com.meizu.cloud.pushsdk.e.e.a> a;

    public d(com.meizu.cloud.pushsdk.e.e.a aVar) {
        super(Looper.getMainLooper());
        this.a = new WeakReference<>(aVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        com.meizu.cloud.pushsdk.e.e.a aVar = this.a.get();
        if (message.what != 1) {
            super.handleMessage(message);
        } else if (aVar != null) {
            com.meizu.cloud.pushsdk.e.g.a aVar2 = (com.meizu.cloud.pushsdk.e.g.a) message.obj;
            aVar.a(aVar2.a, aVar2.b);
        }
    }
}
