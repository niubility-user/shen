package com.jd.aips.verify.api;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class VerifyRequestWrapper implements Serializable {
    static final long serialVersionUID = -7978498266381647994L;
    public final String appAuthorityKey;
    public final String appName;
    public String data = "";

    public VerifyRequestWrapper(VerifyRequest verifyRequest) {
        this.appName = verifyRequest.appName;
        this.appAuthorityKey = verifyRequest.appAuthorityKey;
    }
}
