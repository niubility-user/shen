package com.jd.libs.hybrid.engine;

import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.RequestPreloadAdapter;
import com.jd.libs.hybrid.base.engine.RequestPreloadEngine;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class RequestPreloadEngineImpl implements RequestPreloadEngine {
    @Override // com.jd.libs.hybrid.base.engine.RequestPreloadEngine
    public String custom(String str, JSONObject jSONObject) {
        RequestPreloadAdapter requestPreloadAdapter = (RequestPreloadAdapter) HybridSDK.getAdapter(RequestPreloadAdapter.NAME);
        return requestPreloadAdapter != null ? requestPreloadAdapter.custom(str, jSONObject) : "";
    }
}
