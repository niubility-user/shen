package com.jingdong.sdk.jdhttpdns.listener;

/* loaded from: classes.dex */
public interface IFailureController {
    int getFailureCountLimit();

    void onHttpDnsFailure(Throwable th);

    void reachFailureLimit();
}
