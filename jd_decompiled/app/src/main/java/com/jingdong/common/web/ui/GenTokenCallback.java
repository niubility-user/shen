package com.jingdong.common.web.ui;

/* loaded from: classes12.dex */
public interface GenTokenCallback {

    /* loaded from: classes12.dex */
    public enum State {
        GEN_TOKEN_FREE,
        GEN_TOKEN_READY,
        GEN_TOKEN_SUCCESS,
        GEN_TOKEN_ING,
        GEN_TOKEN_FAIL
    }

    void onCallback(State state);
}
