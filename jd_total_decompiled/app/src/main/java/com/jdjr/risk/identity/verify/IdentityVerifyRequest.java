package com.jdjr.risk.identity.verify;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class IdentityVerifyRequest implements Serializable {
    static final long serialVersionUID = -2310521721610967342L;
    @SerializedName("IdentityParams")
    public IdentityVerifyParams identityVerifyParams;
    public String type;

    public IdentityVerifyParams getIdentityVerifyParams() {
        return this.identityVerifyParams;
    }

    public String getType() {
        return this.type;
    }

    public void setIdentityVerifyParams(IdentityVerifyParams identityVerifyParams) {
        this.identityVerifyParams = identityVerifyParams;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        return "IdentityVerifyRequest{identityParams=" + this.identityVerifyParams + ", type='" + this.type + "'}";
    }
}
