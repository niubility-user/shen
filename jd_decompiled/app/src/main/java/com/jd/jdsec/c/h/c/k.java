package com.jd.jdsec.c.h.c;

import org.json.JSONObject;

/* loaded from: classes13.dex */
public class k {
    static final g a = new f();
    static final g b = new c();

    public static void a(JSONObject jSONObject) {
        com.jd.jdsec.a.l.b.e("JDSec.Security.SendManager", "reportBusinessTrackEvent invoked");
        a.a(jSONObject);
    }

    public static void b(JSONObject jSONObject) {
        com.jd.jdsec.a.l.b.e("JDSec.Security.SendManager", "reportDeviceFixInfo invoked");
        if (j.b()) {
            com.jd.jdsec.a.l.d.e("fixInfo-old", com.jd.jdsec.a.l.d.c("fixInfo-new", ""));
            b.a(jSONObject);
        }
    }
}
