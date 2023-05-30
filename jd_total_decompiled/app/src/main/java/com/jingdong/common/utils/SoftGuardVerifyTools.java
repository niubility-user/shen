package com.jingdong.common.utils;

import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Map;

@Deprecated
/* loaded from: classes6.dex */
public class SoftGuardVerifyTools extends BaseGuardVerifyTool {
    @Override // com.jingdong.common.utils.BaseGuardVerifyTool
    public boolean checkAndHandleEvent(HttpResponse httpResponse, HttpSetting httpSetting, boolean z) {
        if (z) {
            try {
                JDHttpTookit.getEngine().getGuardVerifyPlugin().onTriggerVerifyCheck(httpSetting.getFunctionId(), httpResponse.getHeader().get("X_Verify_Add"), httpResponse.getHeader().get("X_Verify_Expire"));
                if (httpSetting.getOnEndListener() != null && (httpSetting.getOnEndListener() instanceof com.jingdong.common.network.DefaultEffectHttpListener)) {
                    ((com.jingdong.common.network.DefaultEffectHttpListener) httpSetting.getOnEndListener()).clearMission();
                }
            } catch (Throwable unused) {
            }
            return false;
        }
        return false;
    }

    public boolean isWindowShowing() {
        return JDHttpTookit.getEngine().getGuardVerifyPlugin().isVerifyWindowShowing();
    }

    @Override // com.jingdong.common.utils.BaseGuardVerifyTool
    public boolean shouldInterceptRequest(HttpResponse httpResponse) {
        Map<String, String> header = httpResponse.getHeader();
        return header != null && header.containsKey("X_Verify_Add") && header.containsKey("X_Verify_Expire");
    }
}
