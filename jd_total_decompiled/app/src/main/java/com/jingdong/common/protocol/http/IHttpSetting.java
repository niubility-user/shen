package com.jingdong.common.protocol.http;

import androidx.fragment.app.FragmentActivity;

/* loaded from: classes5.dex */
public interface IHttpSetting extends IHttpConfig, IHttpUiConfig {
    public static final int EFFECT_DEFAULT = 1;
    public static final int EFFECT_NO = 0;

    void addHttpSetting();

    void addHttpSetting(FragmentActivity fragmentActivity);

    void cancelRequest();

    void getData();

    void setCallTimeout(int i2);

    void setEnableGatewayQueue(boolean z);

    void setEnableSensitiveParamEnc(boolean z);

    void setTopPriority(boolean z);
}
