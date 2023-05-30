package com.jd.jdsec.c;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class d {
    private static boolean a;

    public static synchronized void a(Context context, JSONObject jSONObject, e eVar, boolean z, boolean z2) {
        synchronized (d.class) {
            if (!a) {
                a = true;
                com.jd.jdsec.a.l.b.d(z);
                g.b(context);
                c.c().g(jSONObject);
                com.jd.jdsec.b.c.a(z);
                g.d(z2);
                g.c(eVar);
                com.jd.jdsec.a.l.b.e("JDSec.Security.SecurityInit", String.format("init with enableLog:%s debug:%s", Boolean.valueOf(z), Boolean.valueOf(z2)));
            }
        }
    }
}
