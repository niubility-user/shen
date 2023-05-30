package com.laser.open.nfc.b;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public enum c {
    SUCCESS(0, "SUCCESS"),
    CARD_ALREADY_EXISTS(R2.drawable.mm_checkbox_btn_red, "\u76ee\u6807\u5361\u7247\u5df2\u5b58\u5728");
    
    private String msg;
    private int status;

    c(int i2, String str) {
        this.status = i2;
        this.msg = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }
}
