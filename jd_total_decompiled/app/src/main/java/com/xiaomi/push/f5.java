package com.xiaomi.push;

import android.util.Log;
import com.xiaomi.push.i5;

/* loaded from: classes11.dex */
public class f5 {
    private static final boolean a = Log.isLoggable("BCompressed", 3);

    public static byte[] a(e5 e5Var, byte[] bArr) {
        try {
            byte[] a2 = i5.a.a(bArr);
            if (a) {
                g.j.a.a.a.c.p("BCompressed", "decompress " + bArr.length + " to " + a2.length + " for " + e5Var);
                if (e5Var.f18569e == 1) {
                    g.j.a.a.a.c.p("BCompressed", "decompress not support upStream");
                }
            }
            return a2;
        } catch (Exception e2) {
            g.j.a.a.a.c.p("BCompressed", "decompress error " + e2);
            return bArr;
        }
    }
}
