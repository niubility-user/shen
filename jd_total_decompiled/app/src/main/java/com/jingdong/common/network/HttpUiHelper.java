package com.jingdong.common.network;

import android.app.Activity;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.network.utils.HttpUiBaseHelper;

/* loaded from: classes5.dex */
public class HttpUiHelper implements HttpUiBaseHelper {
    @Override // com.jingdong.jdsdk.network.utils.HttpUiBaseHelper
    public void tryEffect(HttpGroupSetting httpGroupSetting, HttpSetting httpSetting) {
        if (httpGroupSetting != null) {
            Activity myActivity = httpGroupSetting.getMyActivity();
            if (1 == httpSetting.getEffect() && httpSetting.getEffectState() == 0 && myActivity != null) {
                DefaultEffectHttpListener defaultEffectHttpListener = new DefaultEffectHttpListener(httpSetting, myActivity);
                if (defaultEffectHttpListener.getProgressBarRootLayout() == null) {
                    defaultEffectHttpListener.setProgressBarRootLayout(httpGroupSetting.getProgressBarRootLayout());
                }
                httpSetting.setListener(defaultEffectHttpListener);
            }
        }
    }
}
