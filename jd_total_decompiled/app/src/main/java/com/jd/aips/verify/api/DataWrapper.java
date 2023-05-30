package com.jd.aips.verify.api;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class DataWrapper implements Serializable {
    static final long serialVersionUID = -186668709346575338L;
    public int code;
    public String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
