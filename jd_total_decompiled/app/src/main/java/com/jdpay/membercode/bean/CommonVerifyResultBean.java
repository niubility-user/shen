package com.jdpay.membercode.bean;

import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;

/* loaded from: classes18.dex */
public class CommonVerifyResultBean implements Bean {
    public static final String KEY_RESULT = "VERIFY_RESULT";
    public static final String STATUS_FAILURE = "VERIFY_FAIL";
    public static final String STATUS_SUCCESS = "VERIFY_SUCCESS";
    @Name("identifyFailMsg")
    public String msg;
    @Name("identifyStatus")
    public String status;
}
