package com.jd.manto.jdext.zhaixing;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoJDApiRequest;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class d extends MantoJDApiRequest {
    private String a;
    private JSONObject b;

    /* renamed from: c  reason: collision with root package name */
    private String f6744c;

    public d(String str, JSONObject jSONObject, String str2) {
        this.a = str;
        this.f6744c = str2;
        this.b = jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "miniAppWareSizeAndColor";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        JSONObject jSONObject = this.b;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("host_id", postBody.optString("host_id"));
            jSONObject.put("app_id", this.a);
            if (!TextUtils.isEmpty(this.f6744c)) {
                jSONObject.put(IMantoBaseModule.ACTION_ID, this.f6744c);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
