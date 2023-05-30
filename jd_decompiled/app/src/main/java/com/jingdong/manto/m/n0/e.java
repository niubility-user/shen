package com.jingdong.manto.m.n0;

import com.facebook.react.uimanager.ViewProps;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        n pageView = c0.getPageView(hVar);
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray != null) {
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                int optInt = optJSONObject.optInt("index", -1);
                String optString = optJSONObject.optString("shareData");
                boolean optBoolean = optJSONObject.optBoolean(ViewProps.VISIBLE, false);
                a aVar = new a();
                aVar.b = optInt;
                aVar.a = optString;
                aVar.f13454c = optBoolean;
                arrayList.add(aVar);
            }
        }
        if (pageView != null) {
            pageView.a(arrayList);
        }
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setCustomMenuData";
    }
}
