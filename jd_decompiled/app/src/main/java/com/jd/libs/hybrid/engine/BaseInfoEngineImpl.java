package com.jd.libs.hybrid.engine;

import android.app.Activity;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.BaseInfoAdapter;
import com.jd.libs.hybrid.base.engine.BaseInfoEngine;

/* loaded from: classes16.dex */
public class BaseInfoEngineImpl implements BaseInfoEngine {
    @Override // com.jd.libs.hybrid.base.engine.BaseInfoEngine
    public String getArea() {
        BaseInfoAdapter baseInfoAdapter = (BaseInfoAdapter) HybridSDK.getAdapter(BaseInfoAdapter.NAME);
        return baseInfoAdapter != null ? baseInfoAdapter.getArea() : "";
    }

    @Override // com.jd.libs.hybrid.base.engine.BaseInfoEngine
    public String getLat() {
        BaseInfoAdapter baseInfoAdapter = (BaseInfoAdapter) HybridSDK.getAdapter(BaseInfoAdapter.NAME);
        return baseInfoAdapter != null ? baseInfoAdapter.getLat() : "0.0";
    }

    @Override // com.jd.libs.hybrid.base.engine.BaseInfoEngine
    public String getLng() {
        BaseInfoAdapter baseInfoAdapter = (BaseInfoAdapter) HybridSDK.getAdapter(BaseInfoAdapter.NAME);
        return baseInfoAdapter != null ? baseInfoAdapter.getLng() : "0.0";
    }

    @Override // com.jd.libs.hybrid.base.engine.BaseInfoEngine
    public String getUrl(Activity activity) {
        BaseInfoAdapter baseInfoAdapter = (BaseInfoAdapter) HybridSDK.getAdapter(BaseInfoAdapter.NAME);
        return baseInfoAdapter != null ? baseInfoAdapter.getUrl(activity) : "";
    }
}
