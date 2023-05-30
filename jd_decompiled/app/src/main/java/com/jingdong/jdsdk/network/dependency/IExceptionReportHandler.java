package com.jingdong.jdsdk.network.dependency;

import android.content.Context;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes.dex */
public interface IExceptionReportHandler {
    void reportDownloadDowngradeData(String str, String str2, String str3, boolean z, int i2, String str4);

    void reportDuplicatePicException(String str);

    void reportHttp2PingTimeoutException(String str, String str2);

    void reportHttpBusinessException(HttpSetting httpSetting, HttpResponse httpResponse);

    void reportHttpException(String str, HttpSetting httpSetting, HttpError httpError, String str2);

    void reportHttpsErrorToServer(String str, HttpSetting httpSetting, Throwable th);

    void sendMtaCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7);

    void sendPropertyData(Context context, String str, String str2, String str3, String str4);
}
