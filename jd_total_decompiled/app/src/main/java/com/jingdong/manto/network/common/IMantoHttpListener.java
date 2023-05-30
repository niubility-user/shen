package com.jingdong.manto.network.common;

import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class IMantoHttpListener {
    public void onError(JSONObject jSONObject, Throwable th) {
    }

    public void onStart() {
    }

    public abstract void onSuccess(JSONObject jSONObject);
}
