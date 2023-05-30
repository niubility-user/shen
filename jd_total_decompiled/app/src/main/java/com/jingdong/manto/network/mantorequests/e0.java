package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.jsapi.auth.tools.AuthorizeManager;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class e0 extends MantoJDApiRequest {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int f13881c;

    public e0(String str, String str2, AuthorizeManager.State state) {
        this.a = str;
        this.b = str2;
        this.f13881c = state.value();
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaUpdateSetting";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
            postBody.put(Constants.PARAM_SCOPE, this.b);
            postBody.put("status", this.f13881c);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.POST;
    }
}
