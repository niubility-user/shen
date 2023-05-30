package com.jingdong.common.jdreactFramework;

import com.facebook.react.bridge.Callback;

/* loaded from: classes5.dex */
public class JDCallBackHelper implements JDCallback {
    private Callback mCallback;

    public JDCallBackHelper(Callback callback) {
        this.mCallback = callback;
    }

    @Override // com.jingdong.common.jdreactFramework.JDCallback
    public void invoke(Object... objArr) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.invoke(objArr);
            this.mCallback = null;
        }
    }
}
