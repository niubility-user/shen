package com.jd.manto.jdext.zhaixing;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoJDApiRequest;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class b extends MantoJDApiRequest {
    private String a;
    private JSONObject b;

    public b(String str, JSONObject jSONObject) {
        this.a = str;
        this.b = jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaProductSearch";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        JSONObject jSONObject = this.b;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("host_id", postBody.optString("host_id"));
            jSONObject.put("app_id", this.a);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
