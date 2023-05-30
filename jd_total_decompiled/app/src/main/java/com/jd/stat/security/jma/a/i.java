package com.jd.stat.security.jma.a;

import android.content.Context;
import com.jd.stat.common.o;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class i extends b {
    @Override // com.jd.stat.security.jma.a.g
    public JSONObject a(Context context) {
        JSONObject a;
        JSONObject a2;
        JSONObject jSONObject = new JSONObject();
        com.jd.stat.common.b.b.a("[JMA \u654f\u611f\u6743\u9650] appList \u83b7\u53d6\u4e2d");
        if (!com.jd.stat.security.d.a().u()) {
            com.jd.stat.common.b.b.a("[JMA \u654f\u611f\u6743\u9650] appList \u83b7\u53d6JDGroup");
            a = com.jd.stat.common.b.b(context);
        } else {
            com.jd.stat.common.b.b.a("[JMA \u654f\u611f\u6743\u9650] appList \u83b7\u53d6\u5168\u90e8");
            a = com.jd.stat.common.b.a(context);
        }
        if (!com.jd.stat.security.d.a().n()) {
            a2 = o.b(context);
        } else {
            a2 = o.a(context);
        }
        com.jd.stat.common.b.c.a(jSONObject, a, a2);
        return jSONObject;
    }
}
