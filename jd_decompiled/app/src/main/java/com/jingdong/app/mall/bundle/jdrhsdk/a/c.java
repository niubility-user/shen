package com.jingdong.app.mall.bundle.jdrhsdk.a;

import android.app.Activity;

/* loaded from: classes2.dex */
public class c {
    public static b a(Activity activity, com.jingdong.app.mall.bundle.jdrhsdk.b.a aVar) {
        b aVar2;
        if (activity != null && aVar != null) {
            try {
                int c2 = aVar.c();
                if (c2 == 100) {
                    aVar2 = new a(activity);
                } else if (c2 != 101) {
                    com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle.RiskHandleSelector", "error type:" + aVar.c());
                    aVar2 = new d(activity);
                } else {
                    aVar2 = new d(activity);
                }
                aVar2.b(aVar.c());
                aVar2.e(aVar);
                return aVar2;
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
