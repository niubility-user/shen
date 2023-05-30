package com.jdcloud.media.common.log;

/* loaded from: classes18.dex */
public enum JDCLogUtil {
    INSTANCE;
    
    private ILogCallback mCallback;

    public ILogCallback getCallback() {
        return this.mCallback;
    }

    public void setLogCallback(ILogCallback iLogCallback) {
        this.mCallback = iLogCallback;
    }
}
