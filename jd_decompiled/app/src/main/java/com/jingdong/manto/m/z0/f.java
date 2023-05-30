package com.jingdong.manto.m.z0;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.widget.input.o;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        Integer num;
        n pageView = c0.getPageView(hVar);
        if (pageView != null) {
            try {
                String string = jSONObject.getString("value");
                try {
                    num = Integer.valueOf(jSONObject.getInt("cursor"));
                } catch (Exception unused) {
                    num = null;
                }
                o.a(pageView, string, num);
                hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
                return;
            } catch (Exception unused2) {
                str2 = "fail:invalid data";
            }
        } else {
            str2 = "fail:pageview not found";
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setKeyboardValue";
    }
}
