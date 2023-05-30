package com.jingdong.manto.sdk.api;

import com.jingdong.manto.sdk.IMantoSdkBase;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public interface IMantoReport extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface IMantoReportCallback extends IMantoSdkBase {
        void onError(JSONObject jSONObject, Throwable th);

        void onSuccess(JSONObject jSONObject);
    }

    void reportData(String str, JSONObject jSONObject, IMantoReportCallback iMantoReportCallback);
}
