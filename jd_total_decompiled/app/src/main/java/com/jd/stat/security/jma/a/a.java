package com.jd.stat.security.jma.a;

import android.content.Context;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.stat.common.t;
import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a extends b {
    @Override // com.jd.stat.security.jma.a.g
    public JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", t.a(context));
            jSONObject.put("androidId", BaseInfo.getAndroidId());
            jSONObject.put("client", "android");
            jSONObject.put("clientversion", com.jd.stat.common.c.c(context));
            jSONObject.put(HybridSDK.APP_VERSION_CODE, com.jd.stat.common.c.b(context) + "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
