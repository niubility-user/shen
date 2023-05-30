package com.jd.libs.hybrid.adapter;

import com.jd.libs.hybrid.base.engine.ConfigEngine;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class ColorHttpAdapter implements IAdapter {
    public static final String NAME = "color_http";

    /* loaded from: classes16.dex */
    public static abstract class Callback implements ConfigEngine.Callback<String> {
    }

    @Override // com.jd.libs.hybrid.adapter.IAdapter
    public String getName() {
        return NAME;
    }

    public abstract void request(String str, String str2, JSONObject jSONObject, Callback callback);
}
