package com.tencent.tauth;

/* loaded from: classes9.dex */
public interface IUiListener {
    void onCancel();

    void onComplete(Object obj);

    void onError(UiError uiError);

    void onWarning(int i2);
}
