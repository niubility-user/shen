package com.jd.manto.jdext.zhaixing;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoJDApiRequest;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c extends MantoJDApiRequest {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f6743c;

    public c(String str, JSONObject jSONObject, String str2) {
        this.a = str;
        this.b = str2;
        this.f6743c = jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "miniAppWareDetail";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        JSONObject jSONObject = this.f6743c;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("host_id", postBody.optString("host_id"));
            jSONObject.put("app_id", this.a);
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject.put(IMantoBaseModule.ACTION_ID, this.b);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
