package com.hihonor.push.sdk;

import android.os.Looper;
import com.hihonor.push.framework.aidl.IPushInvoke;
import com.hihonor.push.sdk.c1;
import com.hihonor.push.sdk.g;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes12.dex */
public class m implements g {
    public final AtomicInteger a = new AtomicInteger(1);
    public volatile IPushInvoke b;

    /* renamed from: c */
    public final g.a f1102c;
    public t d;

    public m(g.a aVar) {
        this.f1102c = aVar;
    }

    public final void a(int i2) {
        String str = "notifyFailed result: " + i2;
        g.a aVar = this.f1102c;
        if (aVar != null) {
            c1.a aVar2 = (c1.a) aVar;
            aVar2.getClass();
            if (Looper.myLooper() == c1.this.f1082g.getLooper()) {
                aVar2.c(com.hihonor.push.sdk.b0.a.fromCode(i2));
            } else {
                c1.this.f1082g.post(new b1(aVar2, i2));
            }
        }
    }

    public boolean b() {
        return this.a.get() == 3 || this.a.get() == 4;
    }
}
