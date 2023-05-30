package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes9.dex */
public class TbsVideoUtils {
    private static q a;

    private static void a(Context context) {
        synchronized (TbsVideoUtils.class) {
            if (a == null) {
                f.a(true).a(context, false, false);
                s a2 = f.a(true).a();
                DexLoader c2 = a2 != null ? a2.c() : null;
                if (c2 != null) {
                    a = new q(c2);
                }
            }
        }
    }

    public static void deleteVideoCache(Context context, String str) {
        a(context);
        q qVar = a;
        if (qVar != null) {
            qVar.a(context, str);
        }
    }

    public static String getCurWDPDecodeType(Context context) {
        a(context);
        q qVar = a;
        return qVar != null ? qVar.a(context) : "";
    }
}
