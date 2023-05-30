package com.jingdong.manto.m.e1;

import android.text.TextUtils;
import com.jingdong.manto.f;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.r.d;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends d0 {
    private void a(e0 e0Var, JSONObject jSONObject, int i2, String str) {
        String str2;
        f h2;
        int i3;
        JSONArray optJSONArray = jSONObject.optJSONArray("dataArray");
        if (optJSONArray == null || e0Var == null) {
            str2 = "fail";
        } else {
            for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i4);
                if (jSONObject != null) {
                    String optString = optJSONObject.optString("key");
                    String optString2 = optJSONObject.optString("value");
                    if (TextUtils.equals("firstRenderTime", optString)) {
                        h2 = e0Var.h();
                        i3 = 31;
                    } else if (TextUtils.equals("reRenderTime", optString)) {
                        h2 = e0Var.h();
                        i3 = 32;
                    }
                    d.a(h2, i3, optString2);
                }
            }
            str2 = IMantoBaseModule.SUCCESS;
        }
        e0Var.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        a(hVar, jSONObject, i2, str);
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        a(nVar, jSONObject, i2, str);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "updatePerfData";
    }
}
