package com.jd.framework.network.request;

import com.jd.framework.network.JDResponseListener;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDJsonArrayRequest extends JDRequest<JSONArray> {
    private JDResponseListener<JSONArray> mResponseListener;

    public JDJsonArrayRequest(int i2, String str, JSONObject jSONObject, JDResponseListener<JSONArray> jDResponseListener) {
        super(i2, str);
        this.mResponseListener = jDResponseListener;
        this.mPostBody = jSONObject == null ? null : jSONObject.toString();
    }

    @Override // com.jd.framework.network.request.JDRequest
    public JDResponseListener<JSONArray> getResponseListener() {
        return this.mResponseListener;
    }

    @Override // com.jd.framework.network.request.JDRequest
    public void setResponseListener(JDResponseListener<JSONArray> jDResponseListener) {
        this.mResponseListener = jDResponseListener;
    }
}
