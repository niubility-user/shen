package com.jingdong.common.XView;

/* loaded from: classes5.dex */
public interface XViewCallBack {
    public static final int ERROR_CODE_GENTOKEN = -100;

    void onCloseButtonClicked();

    void onError(int i2);

    void onStart();

    void onXViewDisplayed();

    void onXViewLoadingUrl(String str);

    void onXViewReady();

    void onXViewRequest(XViewRequest xViewRequest);

    void onXViewVisibleChanged(boolean z);

    void onXVivewClosed();
}
