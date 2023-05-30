package com.jd.cashier.app.jdlibcutter.protocol.http;

import android.app.Activity;

/* loaded from: classes13.dex */
public interface IHttpSetting extends IHttpConfig, IHttpUiConfig {
    void cancelRequest();

    void doRequest();

    void doRequest(Activity activity);

    void setEnableSensitiveParamEnc(boolean z);
}
