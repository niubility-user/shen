package com.jd.libs.hybrid.base.entity;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public interface IJsonfy<T> {
    T fromJson(JSONObject jSONObject) throws JSONException;

    JSONObject toJson() throws JSONException;
}
