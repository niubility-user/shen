package com.jingdong.manto.network.mantorequests;

import android.text.TextUtils;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class j extends MantoJDApiRequest {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f13882c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private JSONObject f13883e;

    public j(String str, String str2, String str3, String str4, JSONObject jSONObject) {
        this.a = str;
        this.b = str2;
        this.f13882c = str3;
        this.d = str4;
        this.f13883e = jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return this.f13882c;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject jSONObject = this.f13883e;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("host_id", com.jingdong.manto.b.e());
            jSONObject.put("app_id", this.a);
            jSONObject.put("type", this.b);
            jSONObject.put("useInnerAppId", true);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        MantoBaseRequest.RequestMethod requestMethod = MantoBaseRequest.RequestMethod.POST;
        return TextUtils.equals(IMantoServerRequester.GET, this.d) ? MantoBaseRequest.RequestMethod.GET : TextUtils.equals(IMantoServerRequester.POST, this.d) ? requestMethod : TextUtils.equals("delete", this.d) ? MantoBaseRequest.RequestMethod.DELETE : TextUtils.equals("put", this.d) ? MantoBaseRequest.RequestMethod.PUT : requestMethod;
    }
}
