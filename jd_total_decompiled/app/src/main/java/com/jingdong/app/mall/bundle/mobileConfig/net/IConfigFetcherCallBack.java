package com.jingdong.app.mall.bundle.mobileConfig.net;

import org.json.JSONObject;

/* loaded from: classes12.dex */
public interface IConfigFetcherCallBack {
    void onError(Exception exc);

    void onSuccess(JSONObject jSONObject);
}
