package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoRequestSearch extends MantoJDApiRequest {
    String a;
    int b;

    public MantoRequestSearch(String str, int i2) {
        this.a = str;
        this.b = i2;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaSearch";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("keyword", this.a);
            postBody.put("page", this.b);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
