package com.jingdong.sdk.jdhttpdns.listener;

import com.jingdong.sdk.jdhttpdns.pojo.FailEvent;

/* loaded from: classes7.dex */
public interface IResolveListener {
    void onFailure(FailEvent failEvent);

    void onSuccess();
}
