package com.jingdong.common.messagepop;

import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes5.dex */
class MessageHttpUtils {
    MessageHttpUtils() {
    }

    public static HttpSetting getSetting() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setPost(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.COMMON_NEW_HOST));
        httpSetting.setEffect(1);
        httpSetting.setNotifyUser(false);
        httpSetting.setCallTimeout(3000);
        httpSetting.setAppId("MessageCenter");
        httpSetting.setSecretKey("ddcccc63f0b2426fb61acb24c9439b3f");
        return httpSetting;
    }
}
