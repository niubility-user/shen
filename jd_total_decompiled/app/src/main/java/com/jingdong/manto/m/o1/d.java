package com.jingdong.manto.m.o1;

import com.facebook.react.uimanager.ViewProps;
import com.jingdong.manto.h;
import com.jingdong.manto.i.a;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.j;
import com.jingdong.manto.q.q;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        try {
            a.h hVar2 = hVar.h().t.b;
            String optString = jSONObject.optString("color", hVar2.f13058c);
            String optString2 = jSONObject.optString("selectedColor", hVar2.d);
            String optString3 = jSONObject.optString(ViewProps.BACKGROUND_COLOR, hVar2.f13059e);
            String optString4 = jSONObject.optString("borderStyle", hVar2.f13060f);
            j firstPage = hVar.h().f13014f.getFirstPage();
            if (!(firstPage instanceof q)) {
                hVar.a(i2, putErrMsg("fail:page not ready", null, str));
                return;
            }
            ((q) firstPage).f14092i.a(optString, optString2, optString3, optString4);
            hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
        } catch (Throwable th) {
            hVar.a(i2, putErrMsg("fail:" + th.getMessage(), null, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setTabBarStyle";
    }
}
