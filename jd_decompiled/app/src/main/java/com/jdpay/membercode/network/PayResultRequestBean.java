package com.jdpay.membercode.network;

import com.jdpay.lib.annotation.BusinessParam;
import com.jdpay.lib.annotation.Name;

/* loaded from: classes18.dex */
public class PayResultRequestBean extends EncryptRequestBean {
    @Name("customerCode")
    @BusinessParam
    public String code;
}
