package com.jingdong.manto.sdk.api;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.HashMap;

/* loaded from: classes16.dex */
public interface IShareManager extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface ShareCallback {
        void onShareCancel();

        void onShareClickChannel(Bundle bundle);

        void onShareFailed(Bundle bundle);

        void onShareSuccess(Bundle bundle);
    }

    void shareMantoApp(Activity activity, HashMap<String, Object> hashMap, ShareCallback shareCallback);
}
