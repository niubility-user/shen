package com.jdpay.membercode.bean;

import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;

/* loaded from: classes18.dex */
public class CodeInfoBean implements Bean {
    @Name("customerCode")
    public String code;
    @Name("withHoldOpenInfo")
    public FastPayPageInfoBean fastPayPageInfo;
    @Name("withHoldOpenState")
    public boolean isFastPayAvailable;
    @Name("withHoldNoneOpenInfo")
    public OpenFastPayPageInfoBean openFastPayPageInfo;
    @Name("instructionText")
    public String tip;
}
