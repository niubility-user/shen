package com.jdpay.membercode.bean;

import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;

/* loaded from: classes18.dex */
public class CodeVerifyInfoBean implements Bean {
    @Name("payWayType")
    public String payWayType;
    @Name("phoneDesc")
    public String phoneDescription;
    @Name("signResult")
    public String signResult;
    @Name("commonTips")
    public String tip;
    @Name("titleDesc")
    public String titleDescription;
}
