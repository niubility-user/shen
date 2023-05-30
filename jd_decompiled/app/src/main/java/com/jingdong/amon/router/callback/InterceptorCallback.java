package com.jingdong.amon.router.callback;

import com.jingdong.amon.router.module.Letter;

/* loaded from: classes18.dex */
public interface InterceptorCallback {
    void onContinue(Letter letter);

    void onInterrupt(Throwable th);
}
