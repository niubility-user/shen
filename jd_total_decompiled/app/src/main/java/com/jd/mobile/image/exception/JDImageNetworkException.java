package com.jd.mobile.image.exception;

/* loaded from: classes17.dex */
public class JDImageNetworkException extends Throwable {
    public String dnsIp;
    public boolean isDomainRequest;
    public String requestUrl;

    public JDImageNetworkException(Throwable th, boolean z, String str, String str2) {
        super(th);
        this.isDomainRequest = z;
        this.requestUrl = str;
        this.dnsIp = str2;
    }
}
