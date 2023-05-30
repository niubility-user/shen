package com.jingdong.common.entity;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class JDQuickPassParam implements Serializable {
    public static final String JDPAY_ENTRANCE_VERIFY = "JDPAY_ENTRANCE_VERIFY";
    public static final String JD_PAY_CODE_SOURCE_JDJR = "jdjr";
    public static final String JD_PAY_CODE_SOURCE_JDMALL = "jdmall";
    public static final String JD_PAY_QUICK_PASS = "JD_PAY_QUICK_PASS";
    public static final String JD_QUICK_PASS_MODE_H5 = "H5";
    public static final String JD_QUICK_PASS_MODE_NATIVE = "NATIVE";
    public static final String JD_QUICK_PASS_PARAM = "JD_QUICK_PASS_PARAM";
    private static final long serialVersionUID = 1;
    public String mode;
    public String pin;
    public String sessionKey;
    public String source;

    public String toString() {
        return "JDQuickPassParam{source='" + this.source + "', mode='" + this.mode + "', sessionKey='" + this.sessionKey + "', pin='" + this.pin + "'}";
    }
}
