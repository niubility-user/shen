package com.jingdong.common.widget.custom.liveplayer.callback;

/* loaded from: classes12.dex */
public interface SmallWindowUICallback extends ErrorCallback {
    void onClose(boolean z);

    @Override // com.jingdong.common.widget.custom.liveplayer.callback.ErrorCallback
    /* synthetic */ void onError(int i2, String str);

    void onShowSmallWindow();
}
