package com.jd.libs.hybrid.offlineload.entity;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class TestModule extends Module<TestModule> {
    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public TestModule fromJson(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        return this;
    }

    @Override // com.jd.libs.hybrid.base.entity.IClone
    public TestModule publicClone() throws CloneNotSupportedException {
        return (TestModule) super.clone();
    }
}
