package com.jdpay.membercode;

import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public abstract class MemberCode implements MemberCodeInteractor {
    @Override // com.jdpay.membercode.MemberCodeInteractor
    public void onActivated() {
    }

    @Override // com.jdpay.membercode.MemberCodeInteractor
    public void onError(int i2, @Nullable Throwable th) {
    }

    @Override // com.jdpay.membercode.MemberCodeInteractor
    public void onInactivated() {
    }

    @Override // com.jdpay.membercode.MemberCodeInteractor
    public void onInited() {
    }

    @Override // com.jdpay.membercode.MemberCodeInteractor
    public boolean onToast(CharSequence charSequence) {
        return false;
    }
}
