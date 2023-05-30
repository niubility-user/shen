package com.jd.fireeye.security.fireeye;

import org.json.JSONObject;

/* loaded from: classes13.dex */
public abstract class DeepLinkFireEyeCallback implements FireEyeCallback {
    @Override // com.jd.fireeye.security.fireeye.FireEyeCallback
    @Deprecated
    public void onSuccess() {
    }

    public abstract void onSuccess(JSONObject jSONObject);
}
