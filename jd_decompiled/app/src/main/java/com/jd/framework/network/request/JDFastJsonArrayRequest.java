package com.jd.framework.network.request;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.network.JDResponseListener;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDFastJsonArrayRequest extends JDRequest<JDJSONArray> {
    private JDResponseListener<JDJSONArray> mResponseListener;

    public JDFastJsonArrayRequest(int i2, String str, JSONObject jSONObject, JDResponseListener<JDJSONArray> jDResponseListener) {
        super(i2, str);
        this.mResponseListener = jDResponseListener;
        this.mPostBody = jSONObject == null ? null : jSONObject.toString();
    }

    @Override // com.jd.framework.network.request.JDRequest
    public JDResponseListener<JDJSONArray> getResponseListener() {
        return this.mResponseListener;
    }

    @Override // com.jd.framework.network.request.JDRequest
    public void setResponseListener(JDResponseListener<JDJSONArray> jDResponseListener) {
        this.mResponseListener = jDResponseListener;
    }
}
