package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes5.dex */
public class JDModel {
    public static final String HW_PUSH = "HWPush";
    public static final String JD_MODEL = "jdModel";
    public static final String JD_PUSH = "JdPush";
    public static final String MI_PUSH = "MiPush";
    public static final String VIVO_PUSH = "VIVOPush";
    public static final String XG_PUSH = "XGPush";
    private boolean HWPush;
    private boolean JdPush;
    private boolean MiPush;
    private boolean VIVOPush;
    private boolean XGPush;

    public JDModel(JSONObjectProxy jSONObjectProxy) {
        this.JdPush = jSONObjectProxy.optBoolean("JdPush");
        this.MiPush = jSONObjectProxy.optBoolean("MiPush");
        this.HWPush = jSONObjectProxy.optBoolean("HWPush");
        this.XGPush = jSONObjectProxy.optBoolean(XG_PUSH);
        this.VIVOPush = jSONObjectProxy.optBoolean(VIVO_PUSH);
    }

    public boolean isHWPush() {
        return this.HWPush;
    }

    public boolean isJdPush() {
        return this.JdPush;
    }

    public boolean isMiPush() {
        return this.MiPush;
    }

    public boolean isVIVOPush() {
        return this.VIVOPush;
    }

    public boolean isXGPush() {
        return this.XGPush;
    }
}
