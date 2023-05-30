package com.jingdong.common.eldermode;

import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig;

/* loaded from: classes5.dex */
public class JDElderModeDarkModeConfigImpl implements IElderModeDarkModeConfig {
    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig
    public boolean isDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode() == 1;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig
    public boolean isDarkModeFollowSystem() {
        return DeepDarkChangeManager.getInstance().getIsUIModeFollowSystem();
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig
    public void setDarkMode(boolean z) {
        if (isDarkMode() != z) {
            DeepDarkChangeManager.getInstance().saveDeepDarkSwitch(z ? 1 : 0);
            DeepDarkChangeManager.getInstance().notifyDeepDarkChanged(z ? 1 : 0);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig
    public void setDarkModeFollowSystem(boolean z) {
        DeepDarkChangeManager.getInstance().setIsUIModeFollowSystem(z);
    }
}
