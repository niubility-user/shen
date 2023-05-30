package com.jingdong.common.lbs.http;

/* loaded from: classes5.dex */
public interface JDLbsHttpListener<T> {
    void onFail(JDLbsHttpError jDLbsHttpError);

    void onSuccess(T t);
}
