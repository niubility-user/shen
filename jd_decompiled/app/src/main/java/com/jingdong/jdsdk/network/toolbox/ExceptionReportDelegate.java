package com.jingdong.jdsdk.network.toolbox;

import android.content.Context;
import com.jingdong.jdsdk.network.dependency.IExceptionReportHandler;

/* loaded from: classes14.dex */
public class ExceptionReportDelegate implements IExceptionReportHandler {
    public IExceptionReportHandler reporter = null;

    @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
    public void reportDownloadDowngradeData(String str, String str2, String str3, boolean z, int i2, String str4) {
        this.reporter.reportDownloadDowngradeData(str, str2, str3, z, i2, str4);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
    public void reportDuplicatePicException(String str) {
        this.reporter.reportDuplicatePicException(str);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
    public void reportHttp2PingTimeoutException(String str, String str2) {
        this.reporter.reportHttp2PingTimeoutException(str, str2);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
    public void reportHttpBusinessException(HttpSetting httpSetting, HttpResponse httpResponse) {
        this.reporter.reportHttpBusinessException(httpSetting, httpResponse);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
    public void reportHttpException(String str, HttpSetting httpSetting, HttpError httpError, String str2) {
        this.reporter.reportHttpException(str, httpSetting, httpError, str2);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
    public void reportHttpsErrorToServer(String str, HttpSetting httpSetting, Throwable th) {
        this.reporter.reportHttpsErrorToServer(str, httpSetting, th);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
    public void sendMtaCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7) {
        this.reporter.sendMtaCommonData(context, str, str2, str3, obj, str4, str5, str6, str7);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
    public void sendPropertyData(Context context, String str, String str2, String str3, String str4) {
        this.reporter.sendPropertyData(context, str, str2, str3, str4);
    }

    public void setReporter(IExceptionReportHandler iExceptionReportHandler) {
        this.reporter = iExceptionReportHandler;
    }
}
