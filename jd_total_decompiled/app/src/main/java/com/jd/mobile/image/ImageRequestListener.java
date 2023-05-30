package com.jd.mobile.image;

/* loaded from: classes17.dex */
public interface ImageRequestListener<T> {
    void onCancel();

    void onFailure(Throwable th);

    void onSuccess(T t);
}
