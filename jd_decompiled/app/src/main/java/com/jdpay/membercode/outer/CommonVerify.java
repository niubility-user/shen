package com.jdpay.membercode.outer;

import com.jdpay.membercode.MemberCodeInteractor;

/* loaded from: classes18.dex */
public class CommonVerify {
    public static final int FROM_OPEN = 2;
    public static final int FROM_PAY_RESULT = 1;
    private static CommonVerify instance;
    private int from;
    private boolean verifying;

    private CommonVerify() {
    }

    public static CommonVerify getInstance() {
        if (instance == null) {
            instance = new CommonVerify();
        }
        return instance;
    }

    public int getFrom() {
        return this.from;
    }

    public boolean isVerifying() {
        return this.verifying;
    }

    public void setFrom(int i2) {
        this.from = i2;
    }

    public void setVerifying(boolean z) {
        this.verifying = z;
    }

    public void verify(String str, String str2, String str3, String str4, int i2, MemberCodeInteractor memberCodeInteractor) {
        if (memberCodeInteractor != null) {
            memberCodeInteractor.startCommonVerifyModule(str, str2, str3, str4, i2);
        }
    }
}
