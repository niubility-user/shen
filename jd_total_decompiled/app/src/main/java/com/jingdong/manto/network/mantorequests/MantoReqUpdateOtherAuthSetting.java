package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class MantoReqUpdateOtherAuthSetting extends MantoJDApiRequest {
    private String appId;
    private String authCode;
    private int status;

    public MantoReqUpdateOtherAuthSetting(String str, String str2, int i2) {
        this.appId = str;
        this.authCode = str2;
        this.status = i2;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "miniAppUpdateAuthorizationStatus";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.appId);
            postBody.put("authcode", this.authCode);
            postBody.put("status", "" + this.status);
        } catch (Throwable unused) {
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.POST;
    }
}
