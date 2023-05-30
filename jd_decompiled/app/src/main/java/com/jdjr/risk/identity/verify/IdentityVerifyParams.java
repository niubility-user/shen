package com.jdjr.risk.identity.verify;

import com.jd.aips.verify.BaseVerifyParams;
import java.util.HashMap;

/* loaded from: classes18.dex */
public class IdentityVerifyParams extends BaseVerifyParams<IdentityVerifyConfig> {
    static final long serialVersionUID = -3766577291709630605L;
    public HashMap<String, Object> extension;

    public HashMap<String, Object> getExtension() {
        return this.extension;
    }

    public void setExtension(HashMap<String, Object> hashMap) {
        this.extension = hashMap;
    }
}
