package com.jd.aips.verify.api;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes12.dex */
public class ResultData implements Serializable {
    public static final String EXTRA_KEY_USER_ID = "userId";
    static final long serialVersionUID = 1482909688209157531L;
    private String childToken;
    public Map<String, String> extra;
    public String promptMsg;
    public String verifyId;

    public String getChildToken() {
        return this.childToken;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public String getPromptMsg() {
        return this.promptMsg;
    }

    public String getVerifyId() {
        return this.verifyId;
    }

    public void setChildToken(String str) {
        this.childToken = str;
    }

    public void setExtra(Map<String, String> map) {
        this.extra = map;
    }

    public void setPromptMsg(String str) {
        this.promptMsg = str;
    }

    public void setVerifyId(String str) {
        this.verifyId = str;
    }
}
