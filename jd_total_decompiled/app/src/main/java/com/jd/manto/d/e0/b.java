package com.jd.manto.d.e0;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoJDApiRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class b extends MantoJDApiRequest {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private JSONArray f6651c;

    public b(String str, String str2, JSONArray jSONArray) {
        this.a = str;
        this.b = str2;
        this.f6651c = jSONArray;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaZeroOrder";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getHost() {
        return com.jingdong.a.a ? "https://beta-api.m.jd.com" : super.getHost();
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("hostId", com.jingdong.a.b);
            jSONObject.put("appId", this.a);
            jSONObject.put("trackerId", this.b);
            jSONObject.put("skus", this.f6651c);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
