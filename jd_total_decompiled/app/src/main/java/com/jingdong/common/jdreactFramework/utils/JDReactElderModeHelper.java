package com.jingdong.common.jdreactFramework.utils;

import com.jingdong.common.jdreactFramework.helper.ElderModeHelper;
import com.jingdong.sdk.eldermode.util.JDElderModeManager;
import com.jingdong.sdk.eldermode.util.JDElderModeUiHelper;
import com.jingdong.sdk.eldermode.util.OnElderModeChangeListener;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDReactElderModeHelper implements ElderModeHelper {
    @Override // com.jingdong.common.jdreactFramework.helper.ElderModeHelper
    public String getColors() {
        JSONObject jSONObject = new JSONObject();
        try {
            JDElderModeUiHelper jDElderModeUiHelper = new JDElderModeUiHelper(true, true, true);
            jSONObject.put("colorBr", "#" + Integer.toHexString(jDElderModeUiHelper.getColorBr()));
            jSONObject.put("colorC1", "#" + Integer.toHexString(jDElderModeUiHelper.getColorC1()));
            jSONObject.put("colorC2", "#" + Integer.toHexString(jDElderModeUiHelper.getColorC2()));
            jSONObject.put("colorBg1", "#" + Integer.toHexString(jDElderModeUiHelper.getColorBg1()));
            jSONObject.put("colorBg2", "#" + Integer.toHexString(jDElderModeUiHelper.getColorBg2()));
            jSONObject.put("colorBg3", "#" + Integer.toHexString(jDElderModeUiHelper.getColorBg3()));
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    @Override // com.jingdong.common.jdreactFramework.helper.ElderModeHelper
    public int getCurrentElderMode() {
        return JDElderModeManager.INSTANCE.getElderMode();
    }

    @Override // com.jingdong.common.jdreactFramework.helper.ElderModeHelper
    public float getTextSize(float f2) {
        return new JDElderModeUiHelper(true, true, true).getTextSize(f2);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.ElderModeHelper
    public ElderModeHelper.Unregister onRegisterElderModeChangeListener(final ElderModeHelper.ElderModeChangeListener elderModeChangeListener) {
        final OnElderModeChangeListener onElderModeChangeListener = new OnElderModeChangeListener() { // from class: com.jingdong.common.jdreactFramework.utils.JDReactElderModeHelper.1
            @Override // com.jingdong.sdk.eldermode.util.OnElderModeChangeListener
            public void onElderModeChanged(int i2) {
                ElderModeHelper.ElderModeChangeListener elderModeChangeListener2 = elderModeChangeListener;
                if (elderModeChangeListener2 == null) {
                    return;
                }
                elderModeChangeListener2.onChange(i2);
            }
        };
        JDElderModeManager.INSTANCE.addElderModeChangeListener(onElderModeChangeListener);
        return new ElderModeHelper.Unregister() { // from class: com.jingdong.common.jdreactFramework.utils.JDReactElderModeHelper.2
            @Override // com.jingdong.common.jdreactFramework.helper.ElderModeHelper.Unregister
            public void unregister() {
                JDElderModeManager.INSTANCE.removeElderModeChangeListener(onElderModeChangeListener);
            }
        };
    }
}
