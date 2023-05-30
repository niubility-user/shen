package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class k extends MantoJDApiRequest {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int f13884c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private JSONArray f13885e;

    /* renamed from: f  reason: collision with root package name */
    private String f13886f;

    /* renamed from: g  reason: collision with root package name */
    private int f13887g;

    public k(String str, String str2, JSONArray jSONArray, String str3, int i2, int i3, String str4) {
        this.a = str;
        this.d = str2;
        this.f13885e = jSONArray;
        this.f13886f = str3;
        this.f13884c = i2;
        this.f13887g = i3;
        this.b = str4;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaCustomReport";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
            postBody.put("event_id", this.d);
            postBody.put("vapp_type", this.f13884c);
            postBody.put("content", this.f13885e);
            postBody.put("report_time", System.currentTimeMillis());
            postBody.put("request_id", this.f13886f);
            postBody.put("report_type", this.f13887g);
            postBody.put("vapp_sdk", this.b);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
