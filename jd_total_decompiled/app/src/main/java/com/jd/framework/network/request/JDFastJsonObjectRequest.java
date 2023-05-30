package com.jd.framework.network.request;

import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.JDResponseListener;
import java.util.Map;

/* loaded from: classes13.dex */
public class JDFastJsonObjectRequest extends JDRequest<JDJSONObject> {
    private JDResponseListener<JDJSONObject> mResponseListener;

    public JDFastJsonObjectRequest(String str, JDResponseListener<JDJSONObject> jDResponseListener) {
        this(0, str, null, jDResponseListener);
    }

    @Override // com.jd.framework.network.request.JDRequest
    public JDResponseListener<JDJSONObject> getResponseListener() {
        return this.mResponseListener;
    }

    @Override // com.jd.framework.network.request.JDRequest
    public void setResponseListener(JDResponseListener<JDJSONObject> jDResponseListener) {
        this.mResponseListener = jDResponseListener;
    }

    public JDFastJsonObjectRequest(int i2, String str, JDJSONObject jDJSONObject, JDResponseListener<JDJSONObject> jDResponseListener) {
        super(i2, str);
        this.mResponseListener = jDResponseListener;
        this.mPostBody = jDJSONObject == null ? null : jDJSONObject.toString();
    }

    public JDFastJsonObjectRequest(String str, Map<String, String> map, JDResponseListener<JDJSONObject> jDResponseListener) {
        super(1, str);
        this.mResponseListener = jDResponseListener;
        this.mParams = map;
    }
}
