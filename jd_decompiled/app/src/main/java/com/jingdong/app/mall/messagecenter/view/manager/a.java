package com.jingdong.app.mall.messagecenter.view.manager;

import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.depend.DependUtil;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes4.dex */
public class a {
    public static void a(String str, HttpGroup.OnCommonListener onCommonListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("feedbackTabV732");
        httpSetting.setPost(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.NEW_MSG_CENTER_HOST));
        httpSetting.putJsonParam(NotificationMessageSummary.BUSINESS_TYPE, str);
        httpSetting.setEffect(0);
        httpSetting.setListener(onCommonListener);
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void b(String str, String str2, String str3, String str4, HttpGroup.OnCommonListener onCommonListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("feedbackSubmitV732");
        httpSetting.setPost(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.NEW_MSG_CENTER_HOST));
        httpSetting.putJsonParam("msgId", str2);
        httpSetting.putJsonParam(NotificationMessageSummary.LAND_PAGE_URL, str);
        httpSetting.putJsonParam("uuid", DependUtil.getInstance().getDepend().getUUID());
        httpSetting.putJsonParam("feedbackOptionIds", str3);
        httpSetting.putJsonParam("feedbackContent", str4);
        httpSetting.setEffect(0);
        httpSetting.setListener(onCommonListener);
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
