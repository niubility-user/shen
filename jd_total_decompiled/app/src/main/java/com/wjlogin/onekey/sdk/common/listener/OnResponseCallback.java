package com.wjlogin.onekey.sdk.common.listener;

import org.json.JSONObject;

/* loaded from: classes11.dex */
public abstract class OnResponseCallback {
    public abstract void onFail(JSONObject jSONObject);

    public abstract void onSuccess(JSONObject jSONObject);
}
