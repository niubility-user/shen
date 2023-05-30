package com.jd.manto.jdext.plus;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoJDApiRequest;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public final class a extends MantoJDApiRequest {
    private final String a;
    private final String b;

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "miniAppAuthorizationStatus";
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
            postBody.put("authcode", this.b);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
