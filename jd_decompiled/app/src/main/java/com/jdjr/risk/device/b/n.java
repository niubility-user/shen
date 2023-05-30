package com.jdjr.risk.device.b;

import android.content.Context;
import com.jdjr.risk.device.c.r;
import com.jdjr.risk.device.c.x;
import com.jingdong.common.web.managers.WebPerfManager;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class n extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public n() {
        this.a = new com.jdjr.risk.device.entity.m();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_system_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.m mVar = (com.jdjr.risk.device.entity.m) this.a;
            if (jSONObject.optInt("pltf") == 1) {
                mVar.a("android");
            }
            if (jSONObject.optInt("lxVer") == 1) {
                mVar.b(x.a());
            }
            if (jSONObject.optInt("ua") == 1) {
                mVar.e(r.d(context));
                mVar.c(r.e(context));
            }
            if (jSONObject.optInt(WebPerfManager.FP) == 1) {
                mVar.d(com.jdjr.risk.device.c.o.a());
            }
            if (jSONObject.optInt("fpC") == 1) {
                mVar.f(com.jdjr.risk.device.c.o.b());
            }
        }
    }
}
