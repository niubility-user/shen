package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes5.dex */
public class HWModel {
    public static final String HW_MODEL = "hwModel";
    public static final String HW_PUSH = "HWPush";
    public static final String JD_PUSH = "JdPush";
    public static final String MI_PUSH = "MiPush";
    private boolean HWPush;
    private boolean JdPush;
    private boolean MiPush;

    public HWModel(JSONObjectProxy jSONObjectProxy) {
        this.JdPush = jSONObjectProxy.optBoolean("JdPush");
        this.MiPush = jSONObjectProxy.optBoolean("MiPush");
        this.HWPush = jSONObjectProxy.optBoolean("HWPush");
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
}
