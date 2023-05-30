package com.jingdong.manto.sdk.api;

import android.content.Context;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IHostMenuInterface extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface RedMsgCallBack extends IMantoSdkBase {
        void onMsgRead(int i2, int i3);
    }

    void getRedMsg(Context context, RedMsgCallBack redMsgCallBack);

    void jumpToHome(Context context);

    void jumpToMsgCenter(Context context);

    void jumpToShop(Context context, String str, String str2);
}
