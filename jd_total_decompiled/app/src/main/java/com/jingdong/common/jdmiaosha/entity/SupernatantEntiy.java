package com.jingdong.common.jdmiaosha.entity;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class SupernatantEntiy {
    public JDJSONObject bottomFloatViewObj;
    public JDJSONObject supernatantJump;
    public boolean isShowSupernatant = false;
    public boolean hasSupernatantData = false;
    public boolean supernatantReady = false;
    public boolean supernatantImgReady = false;
    public boolean newAccounttReady = false;
    public String supernatantID = "";
    public String supernatantUrl = "";
    public String supernatantimgUrl = "";

    public void clear() {
        this.isShowSupernatant = false;
        this.hasSupernatantData = false;
        this.supernatantReady = false;
        this.supernatantImgReady = false;
        this.newAccounttReady = false;
        this.supernatantJump = null;
        this.supernatantID = "";
        this.bottomFloatViewObj = null;
        this.supernatantUrl = "";
        this.supernatantimgUrl = "";
    }
}
