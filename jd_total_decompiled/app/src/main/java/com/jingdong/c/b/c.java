package com.jingdong.c.b;

import android.content.Context;
import com.jingdong.c.b.b;
import com.jingdong.c.b.g;
import java.util.concurrent.TimeUnit;

/* loaded from: classes10.dex */
public class c {
    public static String a(Context context) {
        if (context != null) {
            b.C0403b c0403b = new b.C0403b();
            c0403b.b(context);
            j b = b(c0403b.a(), 2);
            if (b != null) {
                return b.b;
            }
            return null;
        }
        return "";
    }

    private static j b(b bVar, int i2) {
        long nanoTime = System.nanoTime();
        try {
            j b = (i2 != 2 ? i2 != 3 ? g.a.a(bVar) : g.a.c(bVar) : g.a.b(bVar)).b();
            h.a(bVar.toString());
            h.a(b.toString());
            return b;
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                h.a("lengthMillis : ".concat(String.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime))));
                return null;
            } finally {
                h.a("lengthMillis : ".concat(String.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime))));
            }
        }
    }
}
