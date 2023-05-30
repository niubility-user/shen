package com.jd.aips.verify.api;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class BaseRequest implements Serializable {
    static final long serialVersionUID = -1165925279521515051L;
    public String appAuthorityKey;
    public String appName;
    public String businessId;

    public BaseRequest() {
    }

    public BaseRequest(String str, String str2, String str3) {
        this.appName = str;
        this.appAuthorityKey = str2;
        this.businessId = str3;
    }
}
