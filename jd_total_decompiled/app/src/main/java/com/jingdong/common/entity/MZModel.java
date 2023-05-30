package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes5.dex */
public class MZModel {
    public static final String JD_PUSH = "JdPush";
    public static final String MZ_MODEL = "mzModel";
    public static final String MZ_PUSH = "MZPush";
    private boolean JdPush;
    private boolean MZPush;

    public MZModel(JSONObjectProxy jSONObjectProxy) {
        this.JdPush = jSONObjectProxy.optBoolean("JdPush");
        this.MZPush = jSONObjectProxy.optBoolean(MZ_PUSH);
    }

    public boolean isJdPush() {
        return this.JdPush;
    }

    public boolean isMZPush() {
        return this.MZPush;
    }
}
