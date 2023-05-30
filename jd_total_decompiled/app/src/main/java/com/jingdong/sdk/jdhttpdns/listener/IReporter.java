package com.jingdong.sdk.jdhttpdns.listener;

import com.jingdong.sdk.jdhttpdns.pojo.FailEvent;

/* loaded from: classes.dex */
public interface IReporter {
    void httpDnsMta(FailEvent failEvent);
}
