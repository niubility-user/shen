package com.jdjr.risk.device.b;

import android.content.Context;
import com.jdcn.risk.cpp.LoadDoor;
import com.jdjr.risk.device.c.ad;
import com.jdjr.risk.device.c.ae;
import com.jdjr.risk.device.c.aj;
import com.jdjr.risk.device.c.ak;
import com.jdjr.risk.device.c.t;
import com.jingdong.common.jump.OpenAppConstant;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class l extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public l() {
        this.a = new com.jdjr.risk.device.entity.k();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_safe_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.k kVar = (com.jdjr.risk.device.entity.k) this.a;
            if (jSONObject.optInt("hk") == 1) {
                kVar.b(t.a(context));
            }
            if (jSONObject.optInt("vir") == 1) {
                kVar.c(String.valueOf(new ak().a(context)));
            }
            if (jSONObject.optInt("virA") == 1) {
                kVar.d(String.valueOf(aj.a(context)));
            }
            if (jSONObject.optInt("rt") == 1) {
                kVar.a(String.valueOf(ad.a(context)));
            }
            if (jSONObject.optInt("rt_c") == 1) {
                kVar.a(ae.a());
            }
            if (jSONObject.optInt("hk_c") == 1) {
                kVar.a(new JSONObject(com.jdjr.risk.device.c.i.a()));
            }
            if (jSONObject.optInt("eml") == 1) {
                kVar.b(LoadDoor.e().a());
            }
            if (jSONObject.optInt("dk") == 1) {
                kVar.g("{\"code\":\"0000000\",\"des\":\"\"}");
            }
            if (jSONObject.optInt("rta") == 1) {
                kVar.h("");
            }
            if (jSONObject.optInt("hka") == 1) {
                kVar.i("");
            }
            if (jSONObject.optInt(OpenAppConstant.HOST_1) == 1) {
                kVar.e("");
            }
            if (jSONObject.optInt("virtualApk") == 1) {
                kVar.f("");
            }
        }
    }
}
