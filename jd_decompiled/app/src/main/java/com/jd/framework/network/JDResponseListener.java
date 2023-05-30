package com.jd.framework.network;

import com.jd.framework.network.error.JDError;

/* loaded from: classes13.dex */
public interface JDResponseListener<T> {
    void onCancel();

    void onEnd(JDResponse<T> jDResponse);

    void onError(JDError jDError);

    void onStart();
}
