package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class c extends MantoJDApiRequest {
    String a;
    String b;

    /* renamed from: c  reason: collision with root package name */
    JSONArray f13879c;
    int d;

    public c(String str, String str2, JSONArray jSONArray, int i2) {
        this.a = str;
        this.b = str2;
        this.f13879c = jSONArray;
        this.d = i2;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        int i2 = this.d;
        return i2 == 0 ? "jdaErrorReport" : i2 == 1 ? "jdaPerformanceReport" : i2 == 2 ? "jdaNetworkRequestReport" : "jdaErrorReport";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
            postBody.put("appType", "android");
            postBody.put("clientEngineVersion", "4.2.0.2");
            postBody.put("appVersion", com.jingdong.manto.b.g().b("versionName"));
            postBody.put("reportTime", System.currentTimeMillis());
            postBody.put("vappVersion", this.b);
            postBody.put("infos", this.f13879c);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.POST;
    }
}
