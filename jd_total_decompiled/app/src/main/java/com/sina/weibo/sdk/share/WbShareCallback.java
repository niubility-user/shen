package com.sina.weibo.sdk.share;

import com.sina.weibo.sdk.common.UiError;

/* loaded from: classes9.dex */
public interface WbShareCallback {
    void onCancel();

    void onComplete();

    void onError(UiError uiError);
}
