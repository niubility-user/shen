package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class q extends MantoJDApiRequest {
    String a;
    String b;

    public q(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaIdeInfos";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getHost() {
        return com.jingdong.a.a ? getBetaHost() : super.getHost();
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
            postBody.put("type", this.b);
            postBody.put("supportSub", 2);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
