package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDNetDiagnoModule implements IJDModule {
    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkCommonHelper.startNetworkDiagnoseActivity(context, null);
        JDRouterUtil.callBackComplete(callBackListener);
    }
}
