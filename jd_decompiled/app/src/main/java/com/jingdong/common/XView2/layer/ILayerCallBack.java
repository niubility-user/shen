package com.jingdong.common.XView2.layer;

/* loaded from: classes5.dex */
public interface ILayerCallBack {
    void onCloseButtonClicked();

    void onError(String str);

    void onLayerClosed(String str, int i2);

    void onLayerDisplayed(String str);

    void onLayerLoading(String str);

    void onLayerReady(String str);
}
