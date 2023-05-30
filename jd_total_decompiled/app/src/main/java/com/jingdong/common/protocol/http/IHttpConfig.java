package com.jingdong.common.protocol.http;

/* loaded from: classes5.dex */
public interface IHttpConfig {
    void putJsonParam(Object obj);

    void putJsonParam(String str);

    void putJsonParam(String str, Object obj);

    void setAttempts(int i2);

    void setEffect(int i2);

    void setFunctionId(String str);

    void setHost(String str);

    void setListener(HttpListener httpListener);

    void setMd5(String str);

    void setNotifyUser(boolean z);

    void setPost(boolean z);
}
