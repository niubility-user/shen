package com.jingdong.jdsdk.network.dependency;

/* loaded from: classes.dex */
public interface IHardGuardVerifyPlugin {

    /* loaded from: classes14.dex */
    public interface ICheckListener {
        void onCheckFinished(String str);
    }

    void triggerGuardVerifyCheck(String str, ICheckListener iCheckListener);
}
