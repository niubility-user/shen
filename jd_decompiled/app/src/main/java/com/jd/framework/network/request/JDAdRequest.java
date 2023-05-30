package com.jd.framework.network.request;

import com.jd.framework.network.JDResponseListener;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDAdRequest extends JDJsonObjectRequest {
    public JDAdRequest(String str, JDResponseListener<JSONObject> jDResponseListener) {
        super(0, str, null, jDResponseListener);
    }
}
