package com.jdjr.risk.identity.verify;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jdcn.service_router.interfaces.JdcnBridgeService;
import com.jdcn.service_router.interfaces.JdcnBridgeServiceCallback;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class IdentityVerifyBridgeService implements JdcnBridgeService {
    private void callbackOnFailed(JdcnBridgeServiceCallback jdcnBridgeServiceCallback, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("msg", "\u6838\u9a8c\u670d\u52a1\u8c03\u7528\u5f02\u5e38 " + str);
            jdcnBridgeServiceCallback.serviceCallback(jSONObject);
        } catch (JSONException unused) {
        }
    }

    @Override // com.jdcn.service_router.interfaces.JdcnBridgeService
    public void serviceCall(Context context, JSONObject jSONObject, final JdcnBridgeServiceCallback jdcnBridgeServiceCallback) {
        try {
            String optString = jSONObject.optString("function");
            if (TextUtils.equals(optString, VerifyTracker.P_CODE_VERIFY)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("sdkParams");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("IdentityParams", optJSONObject);
                IdentityVerityEngine.getInstance().checkIdentityVerity(context, null, jSONObject2.toString(), new IdentityVerityCallback(this) { // from class: com.jdjr.risk.identity.verify.IdentityVerifyBridgeService.1
                    @Override // com.jdjr.risk.identity.verify.IdentityVerityCallback
                    public void onVerifyResult(int i2, String str, String str2, Bundle bundle, String str3) {
                        if (jdcnBridgeServiceCallback != null) {
                            try {
                                jdcnBridgeServiceCallback.serviceCallback(new JSONObject(str3).optJSONObject("IdentityCallBackResult"));
                            } catch (JSONException unused) {
                                jdcnBridgeServiceCallback.serviceCallback(new JSONObject());
                            }
                        }
                    }
                });
            } else if (TextUtils.equals(optString, "release")) {
                IdentityVerityEngine.getInstance().release();
                jdcnBridgeServiceCallback.serviceCallback(new JSONObject());
            } else {
                callbackOnFailed(jdcnBridgeServiceCallback, "\u4e0d\u652f\u6301\u7684Function " + optString);
            }
        } catch (Exception e2) {
            callbackOnFailed(jdcnBridgeServiceCallback, e2.getMessage());
        }
    }
}
