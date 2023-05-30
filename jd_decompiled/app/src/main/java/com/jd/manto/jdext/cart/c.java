package com.jd.manto.jdext.cart;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoJDApiRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c extends MantoJDApiRequest {
    private final String a;
    private final JSONArray b;

    public c(String str, JSONArray jSONArray) {
        this.a = str;
        this.b = jSONArray;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "miniAppCheckCart";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getHost() {
        return com.jingdong.a.a ? "https://beta-api.m.jd.com" : super.getHost();
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
            postBody.put("skuInfos", this.b);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
