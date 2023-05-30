package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDElderModule {
    private static final String KEY_SOURCE = "source";

    private String parseSource(JSONObject jSONObject, Bundle bundle) {
        String optString = jSONObject != null ? jSONObject.optString("source") : null;
        return ((optString == null || optString.length() == 0) && bundle != null) ? bundle.getString("source") : optString;
    }

    public void showElderModeDialog(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof Activity) {
            JDElderModeUtils.showElderModeDialog((Activity) context, parseSource(jSONObject, bundle));
        } else {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
    }

    public void showNormalModeDialog(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context instanceof Activity) {
            JDElderModeUtils.showNormalModeDialog((Activity) context, parseSource(jSONObject, bundle));
        } else {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
    }
}
