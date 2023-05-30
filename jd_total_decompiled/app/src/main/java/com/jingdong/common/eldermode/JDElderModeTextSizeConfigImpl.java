package com.jingdong.common.eldermode;

import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.SetTextScaleModeHelper;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes5.dex */
public class JDElderModeTextSizeConfigImpl implements IElderModeTextSizeConfig {
    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    public float getScaleSize(float f2) {
        return TextScaleModeHelper.INSTANCE.getInstance().getScaleSize(JdSdk.getInstance().getApplicationContext(), f2);
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    @NotNull
    public String getTextSizeScaleMode() {
        return TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode();
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    public boolean isLargeSizeMode() {
        return ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(getTextSizeScaleMode());
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    public void setTextSizeScaleMode(@NotNull String str) {
        if (str.equalsIgnoreCase(getTextSizeScaleMode())) {
            return;
        }
        SetTextScaleModeHelper.setTextSizeScaleMode(str);
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    public void setTextSizeToLargeMode() {
        SetTextScaleModeHelper.setTextSizeScaleMode(ScaleModeConstants.TEXT_SCALE_MODE_LARGE);
    }
}
