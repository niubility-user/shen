package com.jd.cashier.app.jdlibcutter.protocol.http;

/* loaded from: classes13.dex */
public interface IHttpConfig {
    void putJsonParam(Object obj);

    void putJsonParam(String str);

    void putJsonParam(String str, Object obj);

    void putQueryParam(String str, String str2);

    void setAppId(String str);

    void setAttempts(int i2);

    void setCallTimeOut(int i2);

    @Deprecated
    void setConnectTimeOut(int i2);

    void setFunctionId(String str);

    void setHost(String str);

    void setListener(HttpListener httpListener);

    @Deprecated
    void setReadTimeOut(int i2);

    void setSecretKey(String str);
}
