package com.jdpay.recover;

/* loaded from: classes18.dex */
public interface Recoverable {
    boolean isRecoverable();

    void onRecover();

    void onSave();
}
