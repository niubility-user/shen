package com.jdcn.biz.client;

/* loaded from: classes18.dex */
public interface BankCardScanListener {
    void onFail(int i2, String str);

    void onSuccess(BankCardResult bankCardResult);
}
