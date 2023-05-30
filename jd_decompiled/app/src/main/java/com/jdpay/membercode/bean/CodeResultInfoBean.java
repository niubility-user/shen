package com.jdpay.membercode.bean;

import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;

/* loaded from: classes18.dex */
public class CodeResultInfoBean implements Bean {
    public static final String NEXT_STEP_TOKEN = "VERIFY";
    public static final String NEXT_STEP_URL = "URL";
    public static final String QUERY_WAY_PULL = "PULL";
    public static final String QUERY_WAY_PUSH = "PUSH";
    @Name("type")
    public String nextStep;
    @Name("verifyInfo")
    public String token;
    @Name("url")
    public String url;
}
