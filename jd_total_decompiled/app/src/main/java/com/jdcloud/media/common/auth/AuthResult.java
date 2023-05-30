package com.jdcloud.media.common.auth;

/* loaded from: classes18.dex */
public enum AuthResult {
    SUCC(0),
    UNINIT(-1),
    INVILID_PARAM(-2),
    BLACK(-3),
    PARSE_FAILED(-4),
    LIMITED_ACCESS(-5);
    
    private int ErrorCode;

    AuthResult(int i2) {
        this.ErrorCode = i2;
    }

    public int getErrorCode() {
        return this.ErrorCode;
    }
}
