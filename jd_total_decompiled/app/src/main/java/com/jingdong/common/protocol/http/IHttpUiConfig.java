package com.jingdong.common.protocol.http;

/* loaded from: classes5.dex */
public interface IHttpUiConfig {
    void putJsonParam(Object obj);

    void putJsonParam(String str);

    void putJsonParam(String str, Object obj);

    void setAttempts(int i2);

    void setBussinessId(int i2);

    void setCacheMode(int i2);

    void setFunctionId(String str);

    void setHost(String str);

    void setListener(HttpListener httpListener);

    void setLocalFileCache(boolean z);

    void setLocalFileCacheTime(long j2);
}
