package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class s extends MantoJDApiRequest {
    private String a;

    public s(String str) {
        this.a = str;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaGenerateLoginCode";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.POST;
    }
}
