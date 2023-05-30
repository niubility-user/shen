package com.jingdong.jdsdk.d.c.a;

import android.content.Context;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.platform.lib.openapi.app.IApplicationContext;

/* loaded from: classes14.dex */
public class f implements IApplicationContext {
    private static f a = new f();

    private f() {
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f();
            }
            fVar = a;
        }
        return fVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.app.IApplicationContext
    public Context getApplicationContext() {
        return JdSdk.getInstance().getApplicationContext();
    }
}
