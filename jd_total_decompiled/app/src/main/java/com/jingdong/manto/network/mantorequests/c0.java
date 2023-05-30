package com.jingdong.manto.network.mantorequests;

import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class c0 extends MantoJDApiRequest {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int f13880c;

    public c0(String str, String str2, int i2) {
        this.a = str;
        this.b = str2;
        this.f13880c = i2;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaActivity";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject postBody = super.getPostBody();
        try {
            postBody.put("app_id", this.a);
            postBody.put("venderId", this.b);
            postBody.put(BaseEvent.SCENE, this.f13880c);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return postBody;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.POST;
    }
}
