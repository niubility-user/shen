package com.jingdong.manto.m.o1;

import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.j;
import com.jingdong.manto.q.q;
import com.jingdong.manto.widget.d;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        try {
            int i3 = jSONObject.getInt("index");
            String optString = jSONObject.optString("type", "none");
            String optString2 = jSONObject.optString("badgeValue", "");
            String optString3 = jSONObject.optString("badgeColor", "");
            String optString4 = jSONObject.optString("badgeTextColor", "");
            j firstPage = hVar.h().f13014f.getFirstPage();
            if (!(firstPage instanceof q)) {
                hVar.a(i2, putErrMsg("fail:page not ready", null, str));
                return;
            }
            com.jingdong.manto.widget.d dVar = ((q) firstPage).f14092i;
            String substring = optString2.substring(0, optString2.length() < 4 ? optString2.length() : 4);
            int a = com.jingdong.manto.ui.d.a(optString3, 0);
            int a2 = com.jingdong.manto.ui.d.a(optString4, -1);
            for (int i4 = 0; i4 < dVar.f14347f.size(); i4++) {
                if (i4 == i3) {
                    d.g gVar = dVar.f14347f.get(i4);
                    gVar.a();
                    if ("redDot".equalsIgnoreCase(optString)) {
                        gVar.d = true;
                    } else if ("text".equalsIgnoreCase(optString)) {
                        gVar.f14355e = substring;
                        gVar.f14356f = a;
                        gVar.f14357g = a2;
                    }
                    dVar.a(i3, gVar);
                }
            }
            hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
        } catch (Exception unused) {
            hVar.a(i2, putErrMsg("fail", null, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setTabBarBadge";
    }
}
