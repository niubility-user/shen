package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes5.dex */
public class OPPOModel {
    public static final String JD_PUSH = "JdPush";
    public static final String OPPO_MODEL = "oppoModel";
    public static final String OPPO_PUSH = "OPPOPush";
    private boolean JdPush;
    private boolean OPPOPush;

    public OPPOModel(JSONObjectProxy jSONObjectProxy) {
        this.JdPush = jSONObjectProxy.optBoolean("JdPush");
        this.OPPOPush = jSONObjectProxy.optBoolean(OPPO_PUSH);
    }

    public boolean isJdPush() {
        return this.JdPush;
    }

    public boolean isOPPOPush() {
        return this.OPPOPush;
    }
}
