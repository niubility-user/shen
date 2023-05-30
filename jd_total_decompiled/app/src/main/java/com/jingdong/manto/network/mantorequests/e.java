package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class e extends MantoJDApiRequest {
    String a;

    public e(String str) {
        this.a = str;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "miniAppAuthorizationStatusList";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(0);
            jSONArray.put(1);
            jSONArray.put(3);
            postBody.put("authcode_list", jSONArray.toString());
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
