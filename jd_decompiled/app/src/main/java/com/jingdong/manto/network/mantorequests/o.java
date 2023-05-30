package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class o extends MantoJDApiRequest {
    private String a;
    private String b;

    public o(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaUserInfo";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
            JSONObject jSONObject = new JSONObject(this.b);
            postBody.put("with_credentials", jSONObject.optBoolean("withCredentials", false));
            postBody.put("from_component", jSONObject.optString("from_component"));
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
