package com.jingdong.manto.sdk.api;

import com.jingdong.manto.sdk.IMantoSdkBase;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public interface IMantoServerRequester extends IMantoSdkBase {
    public static final String GET = "get";
    public static final String POST = "post";

    /* loaded from: classes16.dex */
    public interface CallBack {
        void onError(Throwable th);

        void onSuccess(JSONObject jSONObject);
    }

    void request(boolean z, String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, String str4, CallBack callBack);
}
