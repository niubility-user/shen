package com.jdcn.biz.client;

import java.io.Serializable;

/* loaded from: classes18.dex */
public class BankCardResult implements Serializable {
    static final long serialVersionUID = 7910645165587291997L;
    public int configType;
    public boolean isManualModified;
    public BankCardInfo mBankCardInfo;
    public String serialNo;

    public BankCardInfo getBankCardInfo() {
        return this.mBankCardInfo;
    }

    public int getConfigType() {
        return this.configType;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public boolean isManualModified() {
        return this.isManualModified;
    }

    public void setBankCardInfo(BankCardInfo bankCardInfo) {
        this.mBankCardInfo = bankCardInfo;
    }

    public void setConfigType(int i2) {
        this.configType = i2;
    }

    public void setManualModified(boolean z) {
        this.isManualModified = z;
    }

    public void setSerialNo(String str) {
        this.serialNo = str;
    }
}
