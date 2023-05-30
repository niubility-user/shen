package com.jdpay.membercode.network;

import com.jdpay.lib.annotation.Name;
import com.jdpay.membercode.bean.a;

/* loaded from: classes18.dex */
public class EncryptRequestBean extends BaseRequestBean implements a {
    @Name("bizData")
    public String bizData;
    @Name("data")
    public String clientKey;

    public String getClientKey() {
        return this.clientKey;
    }

    @Override // com.jdpay.membercode.bean.a
    public void setBusinessData(String str) {
        this.bizData = str;
    }

    @Override // com.jdpay.membercode.bean.a
    public void setClientKey(String str) {
        this.clientKey = str;
    }
}
