package com.jdjr.risk.device.b;

import android.content.Context;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jdjr.risk.device.c.ai;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public c() {
        this.a = new com.jdjr.risk.device.entity.c();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_basic_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.c cVar = (com.jdjr.risk.device.entity.c) this.a;
            if (jSONObject.optInt("brd") == 1) {
                cVar.d(com.jdjr.risk.device.c.g.h());
            }
            if (jSONObject.optInt("bright") == 1 && context.getContentResolver() != null) {
                int e2 = ai.e(context);
                cVar.b(e2);
                cVar.l(String.valueOf(e2));
            }
            if (jSONObject.optInt("crcy") == 1) {
                cVar.m("");
            }
            if (jSONObject.optInt("ctr") == 1) {
                cVar.h(com.jdjr.risk.device.c.g.i());
            }
            if (jSONObject.optInt("dTime") == 1) {
                cVar.j(com.jdjr.risk.device.c.g.j());
            }
            if (jSONObject.optInt(NoStockRecommendHead.DEVICE) == 1) {
                cVar.e(com.jdjr.risk.device.c.g.k());
            }
            if (jSONObject.optInt("lang") == 1) {
                cVar.i(com.jdjr.risk.device.c.g.l());
            }
            if (jSONObject.optInt("manuf") == 1) {
                cVar.f(com.jdjr.risk.device.c.g.m());
            }
            if (jSONObject.optInt(CustomThemeConstance.NAVI_MODEL) == 1) {
                cVar.c(com.jdjr.risk.device.c.g.b());
            }
            if (jSONObject.optInt("name") == 1) {
                cVar.k(com.jdjr.risk.device.c.g.c());
            }
            if (jSONObject.optInt("os") == 1) {
                cVar.a("android");
            }
            if (jSONObject.optInt("osVr") == 1) {
                cVar.b(com.jdjr.risk.device.c.g.a());
            }
            if (jSONObject.optInt("tz") == 1) {
                cVar.g(com.jdjr.risk.device.c.g.d());
            }
            if (jSONObject.optInt("name_c") == 1) {
                cVar.n(com.jdjr.risk.device.c.g.g());
            }
            if (jSONObject.optInt("sdkVr") == 1) {
                cVar.o(com.jdjr.risk.device.c.g.e());
            }
            if (jSONObject.optInt("lang_c") == 1) {
                cVar.p(ai.a(context));
            }
            if (jSONObject.optInt("fontC") == 1) {
                cVar.q(String.valueOf(ai.c(context)));
            }
            if (jSONObject.optInt("fontD5") == 1) {
                cVar.r(ai.a());
            }
            if (jSONObject.optInt("wallpr") == 1) {
                cVar.s(ai.b(context));
            }
            if (jSONObject.optInt("btl") == 1) {
                cVar.t(com.jdjr.risk.device.c.g.f());
            }
            if (jSONObject.optInt("bright_c") == 1) {
                cVar.a(ai.d(context));
            }
        }
    }
}
