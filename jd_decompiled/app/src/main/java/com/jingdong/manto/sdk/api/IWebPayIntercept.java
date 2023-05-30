package com.jingdong.manto.sdk.api;

import android.content.Context;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IWebPayIntercept extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface IWebPayCallBack {
        void onPayBack(String str);
    }

    void onNativePay(Context context, String str, IWebPayCallBack iWebPayCallBack);
}
