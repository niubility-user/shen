package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class d0 extends MantoJDApiRequest {
    private String a;

    public d0(String str) {
        this.a = str;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "miniAppInfoBySmartCard";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.POST;
    }
}
