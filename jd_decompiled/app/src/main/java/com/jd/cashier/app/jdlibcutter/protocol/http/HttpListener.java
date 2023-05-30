package com.jd.cashier.app.jdlibcutter.protocol.http;

/* loaded from: classes13.dex */
public interface HttpListener {
    void onEnd(String str);

    void onError(String str);

    void onReady(IHttpConfig iHttpConfig);
}
