package com.jingdong.app.mall.bundle.familyhelper.util;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes18.dex */
public class FamilyHelperNetUtil {
    public static final String ERROR = "\u7cfb\u7edf\u7e41\u5fd9\uff0c\u522b\u7740\u6025\uff0c\u7a0d\u540e\u518d\u8bd5\u8bd5";
    public static final String ERRORR = "\u7cfb\u7edf\u6570\u636e\u9519\u8bef";
    public static final String NET_ERROR = "\u7f51\u7edc\u5728\u5f00\u5c0f\u5dee\uff0c\u68c0\u67e5\u540e\u518d\u8bd5\u5427";
    private static final String TAG = "FamilyHelperNetUtil";

    public static void sendHttpRequest(IMyActivity iMyActivity, String str, JDJSONObject jDJSONObject, boolean z, HttpGroup.OnCommonListener onCommonListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getVirtualHost());
        httpSetting.setFunctionId(str);
        httpSetting.putJsonParam(jDJSONObject);
        if (LoginUserBase.hasLogin()) {
            httpSetting.setUseCookies(true);
        } else {
            httpSetting.setUseCookies(false);
        }
        httpSetting.setNotifyUser(false);
        httpSetting.setCallTimeout(1000);
        httpSetting.setAttempts(3);
        httpSetting.setEffect(z ? 1 : 0);
        httpSetting.setUseCookies(true);
        httpSetting.setListener(onCommonListener);
        String str2 = "--> " + str + " , Start";
        if (iMyActivity != null) {
            iMyActivity.getHttpGroupaAsynPool().add(httpSetting);
        } else {
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }
}
