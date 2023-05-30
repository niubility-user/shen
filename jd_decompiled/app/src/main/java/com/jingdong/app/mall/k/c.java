package com.jingdong.app.mall.k;

import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class c {
    private static c b;
    private ConcurrentHashMap<String, Boolean> a = new ConcurrentHashMap<>();

    private c() {
    }

    public static c b() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public boolean a(com.jingdong.app.mall.k.j.a... aVarArr) {
        boolean z;
        boolean z2 = true;
        if (aVarArr != null && aVarArr.length > 0) {
            for (com.jingdong.app.mall.k.j.a aVar : aVarArr) {
                if (aVar != null) {
                    try {
                        z = aVar.init();
                    } catch (Throwable th) {
                        if (OKLog.D) {
                            th.printStackTrace();
                        }
                        z = false;
                    }
                    if (!z) {
                        z2 = false;
                    }
                    this.a.put(aVar.getClass().getCanonicalName(), Boolean.valueOf(z));
                }
            }
        }
        return z2;
    }
}
