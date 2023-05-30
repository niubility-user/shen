package com.jingdong.common.protocol.http;

/* loaded from: classes5.dex */
public interface HttpListener {
    void onCancel();

    void onEnd(String str);

    void onError(String str);

    void onReady(IHttpConfig iHttpConfig);
}
