package com.tencent.map.tools.net.http;

/* loaded from: classes9.dex */
public interface HttpResponseListener<T> {
    void onFailure(int i2, String str, Throwable th);

    void onSuccess(int i2, T t);
}
