package com.jingdong.manto.sdk.api;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.manto.sdk.IMantoSdkBase;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public interface IRouter extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface CallBack extends IMantoSdkBase {
        void onFail(int i2);

        void onSuccess(Bundle bundle);
    }

    void jumpTo(Context context, JSONObject jSONObject, CallBack callBack);
}
