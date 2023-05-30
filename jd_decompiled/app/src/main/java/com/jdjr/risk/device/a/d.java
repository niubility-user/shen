package com.jdjr.risk.device.a;

import android.content.Context;
import com.jdjr.risk.device.b.o;

/* loaded from: classes18.dex */
public class d {
    public static void a(Context context, String str, String str2, com.jdjr.risk.biometric.a.a aVar) {
        if (context == null || aVar == null) {
            return;
        }
        try {
            if (aVar.b()) {
                b(context, str, str2, aVar);
            }
        } catch (Throwable unused) {
        }
    }

    private static void b(Context context, String str, String str2, com.jdjr.risk.biometric.a.a aVar) {
        try {
            if (o.a.compareAndSet(false, true)) {
                int C = aVar.C();
                new o(context).a(str, str2, aVar.D(), C, com.jdjr.risk.util.httputil.a.g());
            }
        } catch (Exception unused) {
        }
    }
}
