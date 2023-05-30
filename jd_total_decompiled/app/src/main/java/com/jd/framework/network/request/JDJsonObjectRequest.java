package com.jd.framework.network.request;

import com.jd.framework.network.JDResponseListener;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDJsonObjectRequest extends JDRequest<JSONObject> {
    private JDResponseListener<JSONObject> mResponseListener;

    public JDJsonObjectRequest(String str, JDResponseListener<JSONObject> jDResponseListener) {
        this(0, str, null, jDResponseListener);
    }

    @Override // com.jd.framework.network.request.JDRequest
    public JDResponseListener<JSONObject> getResponseListener() {
        return this.mResponseListener;
    }

    @Override // com.jd.framework.network.request.JDRequest
    public void setResponseListener(JDResponseListener<JSONObject> jDResponseListener) {
        this.mResponseListener = jDResponseListener;
    }

    public JDJsonObjectRequest(int i2, String str, JSONObject jSONObject, JDResponseListener<JSONObject> jDResponseListener) {
        super(i2, str);
        this.mResponseListener = jDResponseListener;
        this.mPostBody = jSONObject == null ? null : jSONObject.toString();
    }

    public JDJsonObjectRequest(String str, Map<String, String> map, JDResponseListener<JSONObject> jDResponseListener) {
        super(1, str);
        this.mResponseListener = jDResponseListener;
        this.mParams = map;
    }
}
