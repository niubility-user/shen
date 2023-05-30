package com.jingdong.manto.m.r0;

import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String optString = jSONObject.optString(ViewProps.BACKGROUND_COLOR);
        n pageView = c0.getPageView(hVar);
        if (pageView != null && !TextUtils.isEmpty(optString)) {
            pageView.c(optString);
        }
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setBackgroundColor";
    }
}
