package com.jd.stat.security;

import android.content.Context;
import com.jd.stat.common.w;

/* loaded from: classes18.dex */
public class a {
    public static void a(Context context, com.jd.stat.common.callback.a<Integer, Integer> aVar) {
        boolean z = false;
        try {
            w.a();
            Class.forName("com.jd.stat.security.jma.JMA");
            z = true;
            e.a(context).a(aVar);
        } catch (Throwable th) {
            if (!z && aVar != null) {
                aVar.b(-1);
            }
            th.printStackTrace();
        }
    }
}
