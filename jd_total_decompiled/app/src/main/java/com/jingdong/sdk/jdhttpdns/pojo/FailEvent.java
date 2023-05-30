package com.jingdong.sdk.jdhttpdns.pojo;

/* loaded from: classes7.dex */
public final class FailEvent {
    private final Exception exception;
    private final String url;

    public FailEvent(String str, Exception exc) {
        this.url = str;
        this.exception = exc;
    }

    public Exception getException() {
        return this.exception;
    }

    public String getUrl() {
        return this.url;
    }
}
