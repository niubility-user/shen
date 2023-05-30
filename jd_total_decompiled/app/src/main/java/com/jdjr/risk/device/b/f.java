package com.jdjr.risk.device.b;

import android.content.Context;
import com.jdjr.risk.biometric.core.JdcnOaidManager;
import com.jdjr.risk.device.c.ag;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class f extends a {

    /* renamed from: c  reason: collision with root package name */
    public static String f7325c = "device_identity_info";

    /* JADX INFO: Access modifiers changed from: package-private */
    public f() {
        this.a = new com.jdjr.risk.device.entity.f();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return f7325c;
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.f fVar = (com.jdjr.risk.device.entity.f) this.a;
            if (jSONObject.optInt("imei") == 1) {
                fVar.a(ag.c(context));
            }
            if (jSONObject.optInt("imei2") == 1) {
                fVar.b("");
            }
            if (jSONObject.optInt("serlNo") == 1) {
                fVar.c(com.jdjr.risk.device.c.g.a(context));
            }
            if (jSONObject.optInt("andId") == 1) {
                fVar.d(com.jdjr.risk.device.c.b.a(context));
            }
            if (jSONObject.optInt("oaId") == 1) {
                fVar.e(JdcnOaidManager.getInstance().getOaid(context));
            }
            if (jSONObject.optInt("imsi") == 1) {
                fVar.f(ag.b(context));
            }
            if (jSONObject.optInt("imsi2") == 1) {
                fVar.g("");
            }
            if (jSONObject.optInt("advId") == 1) {
                fVar.h(com.jdjr.risk.device.c.a.a(context));
            }
            if (jSONObject.optInt("cid") == 1) {
                fVar.j(com.jdjr.risk.device.c.k.a().a(context));
            }
            if (jSONObject.optInt("simSerlNo") == 1) {
                fVar.i(ag.b());
            }
        }
    }
}
