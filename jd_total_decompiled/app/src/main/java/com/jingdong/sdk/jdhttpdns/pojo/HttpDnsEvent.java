package com.jingdong.sdk.jdhttpdns.pojo;

/* loaded from: classes7.dex */
public final class HttpDnsEvent {
    private final Exception exception;
    private final boolean status;
    private final String url;

    public HttpDnsEvent(boolean z) {
        this(null, null, z);
    }

    public Exception getException() {
        return this.exception;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isSucceed() {
        return this.status;
    }

    public HttpDnsEvent(String str, Exception exc, boolean z) {
        this.url = str;
        this.exception = exc;
        this.status = z;
    }
}
