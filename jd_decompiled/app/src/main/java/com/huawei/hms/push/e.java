package com.huawei.hms.push;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class e extends Handler {
    private WeakReference<a> a;

    /* loaded from: classes12.dex */
    public interface a {
        void a(Message message);
    }

    public e(a aVar) {
        this.a = new WeakReference<>(aVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        a aVar = this.a.get();
        if (aVar != null) {
            aVar.a(message);
        }
    }
}
