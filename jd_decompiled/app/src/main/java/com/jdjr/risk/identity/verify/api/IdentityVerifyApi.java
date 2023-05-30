package com.jdjr.risk.identity.verify.api;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.jd.aips.verify.VerifyEngine;
import com.jd.aips.verify.api.ApiException;
import com.jd.aips.verify.api.VerifyApi;
import com.jdjr.risk.identity.verify.IdentityVerifyConfig;
import com.jdjr.risk.identity.verify.IdentityVerifyParams;
import com.jdjr.risk.identity.verify.IdentityVerifySession;
import com.jdjr.risk.identity.verify.IdentityVerityEngine;
import java.util.HashMap;

/* loaded from: classes18.dex */
public class IdentityVerifyApi extends VerifyApi {
    private IdentityVerifyApi() {
    }

    public static Bundle a(@NonNull Context context, @NonNull IdentityVerifySession identityVerifySession) {
        IdentityVerifyConfigRequest identityVerifyConfigRequest = new IdentityVerifyConfigRequest(context, identityVerifySession);
        HashMap hashMap = new HashMap(3);
        identityVerifyConfigRequest.extra = hashMap;
        hashMap.put(VerifyEngine.VERIFY_CONFIG_TYPE, IdentityVerityEngine.SDK_NAME);
        identityVerifyConfigRequest.extra.put("verificationSDKVersion", "3.1.00");
        identityVerifyConfigRequest.extra.put("token", ((IdentityVerifyParams) identityVerifySession.verifyParams).getVerifyToken());
        Bundle bundle = new Bundle();
        try {
            IdentityVerifyConfigDataWrapper identityVerifyConfigDataWrapper = (IdentityVerifyConfigDataWrapper) VerifyApi.toRequest(context, "/f/securityQueryConfig", identityVerifyConfigRequest, IdentityVerifyConfigDataWrapper.class);
            if (identityVerifyConfigDataWrapper == null) {
                bundle.putInt("code", 10002);
            } else {
                IdentityVerifyConfig identityVerifyConfig = identityVerifyConfigDataWrapper.data;
                if (identityVerifyConfig != null) {
                    bundle.putInt("code", identityVerifyConfigDataWrapper.code);
                    bundle.putSerializable("data", identityVerifyConfig);
                } else {
                    bundle.putInt("code", 10002);
                }
            }
        } catch (ApiException e2) {
            bundle.putInt("code", e2.code);
            bundle.putString("msg", e2.getMessage());
        } catch (Exception unused) {
            bundle.putInt("code", 10003);
        }
        return bundle;
    }
}
