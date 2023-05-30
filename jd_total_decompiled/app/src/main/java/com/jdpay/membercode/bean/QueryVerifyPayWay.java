package com.jdpay.membercode.bean;

import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;

/* loaded from: classes18.dex */
public class QueryVerifyPayWay implements Bean {
    @Name("needVerifyBusinessToken")
    public String bizToken;
    @Name("nextStep")
    public String nextStep;
}
