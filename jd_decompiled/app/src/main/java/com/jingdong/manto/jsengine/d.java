package com.jingdong.manto.jsengine;

import android.content.Context;
import android.os.Build;
import com.jingdong.a;
import com.jingdong.sdk.jweb.JWebFactory;

/* loaded from: classes15.dex */
public class d {
    private static a.c a = a.c.x5;

    public static a.c a() {
        return a;
    }

    public static IMantoWebViewJS a(Context context) {
        try {
            if (a.value == a.c.j2v8.value) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if (com.jingdong.manto.u.b.c().i()) {
                        return new e();
                    }
                    if (com.jingdong.manto.u.b.c().g()) {
                        return new e();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            if (a.value == a.c.x5.value && JWebFactory.isCoreReady(JWebFactory.JSContextType.CT_TYPE_X5)) {
                return new f(context);
            }
        } catch (Exception unused) {
        }
        try {
            return new c(context);
        } catch (Exception unused2) {
            return null;
        }
    }

    public static void a(a.c cVar) {
        a = cVar;
    }
}
