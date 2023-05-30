package com.jingdong.manto.network.mantorequests;

import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class MantoCommonRequest implements MantoBaseRequest {
    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        return new JSONObject();
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public final String getSign(JSONObject jSONObject, String str) {
        return "";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public final boolean useJDNet() {
        return false;
    }
}
