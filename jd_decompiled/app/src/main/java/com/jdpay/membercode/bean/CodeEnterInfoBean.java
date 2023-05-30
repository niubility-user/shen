package com.jdpay.membercode.bean;

import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;

/* loaded from: classes18.dex */
public class CodeEnterInfoBean implements Bean {
    @Name("needVerifyBusinessToken")
    public String bizToken;
    @Name("customerCodeInfo")
    public CodeInfoBean code;
    @Name("url")
    public H5UrlBean h5;
    @Name("needOpen")
    public boolean isNeedOpen;
    @Name("signFlag")
    public boolean isSigned;
    @Name("nextStep")
    public String nextStep;
    @Name("customerCodeOpenInfo")
    public CodeOpenInfoBean openPageInfo;
    @Name("queryResultType")
    public String queryPayResultWay;
    @Name("customerCodeVerifyInfo")
    public CodeVerifyInfoBean verifyInfo;
}
