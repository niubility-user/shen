package com.jdjr.risk.device.b;

import android.content.Context;
import com.jdjr.risk.assist.info.BuildConfig;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class b extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public b() {
        this.a = new com.jdjr.risk.device.entity.b();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_app_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        com.jdjr.risk.device.entity.b bVar = (com.jdjr.risk.device.entity.b) this.a;
        if (jSONObject.optInt("aNm") == 1) {
            bVar.b(com.jdjr.risk.device.c.c.a());
        }
        if (jSONObject.optInt("aVer") == 1) {
            bVar.c(com.jdjr.risk.device.c.c.b());
        }
        if (jSONObject.optInt("cVer") == 1) {
            bVar.d(com.jdjr.risk.device.c.c.c());
        }
        if (jSONObject.optInt("chnl") == 1) {
            bVar.f(BuildConfig.channel);
        }
        if (jSONObject.optInt("inte") == 1) {
            bVar.g(com.jdjr.risk.device.c.c.a(context));
        }
        if (jSONObject.optInt("pkgNm") == 1) {
            bVar.a(com.jdjr.risk.device.c.c.d());
        }
        if (jSONObject.optInt("sghsh") == 1) {
            bVar.e(com.jdjr.risk.device.c.c.e());
        }
    }
}
