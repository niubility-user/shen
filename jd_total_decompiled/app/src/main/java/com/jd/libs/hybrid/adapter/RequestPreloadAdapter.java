package com.jd.libs.hybrid.adapter;

import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class RequestPreloadAdapter implements IAdapter {
    public static final String NAME = "request_preload";

    public abstract String custom(String str, JSONObject jSONObject);

    @Override // com.jd.libs.hybrid.adapter.IAdapter
    public String getName() {
        return NAME;
    }
}
