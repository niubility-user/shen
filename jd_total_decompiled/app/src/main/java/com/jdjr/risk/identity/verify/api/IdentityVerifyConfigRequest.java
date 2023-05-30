package com.jdjr.risk.identity.verify.api;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jd.aips.verify.VerifyEngine;
import com.jd.aips.verify.api.VerifyRequest;
import com.jdjr.risk.identity.verify.IdentityVerifyParams;
import com.jdjr.risk.identity.verify.IdentityVerifySession;

/* loaded from: classes18.dex */
public class IdentityVerifyConfigRequest extends VerifyRequest {
    static final long serialVersionUID = 3731151712039247659L;
    public final String configType;
    public final String sdkToken;

    public IdentityVerifyConfigRequest(@NonNull Context context, @NonNull IdentityVerifySession identityVerifySession) {
        super(context, identityVerifySession);
        this.configType = VerifyEngine.VERIFY_CONFIG_TYPE;
        this.sdkToken = ((IdentityVerifyParams) identityVerifySession.verifyParams).sdkToken;
    }
}
