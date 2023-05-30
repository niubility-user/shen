package com.jingdong.common.jdreactFramework.utils;

import com.jingdong.common.jdreactFramework.helper.UIModeHelper;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes5.dex */
public class JDReactUIModeHelper implements UIModeHelper {
    @Override // com.jingdong.common.jdreactFramework.helper.UIModeHelper
    public int getCurrentUIMode() {
        return DeepDarkChangeManager.getInstance().getUIMode();
    }

    @Override // com.jingdong.common.jdreactFramework.helper.UIModeHelper
    public String getUIModeName(int i2) {
        return i2 == 1 ? CustomThemeConstance.NAVI_IMAGE_DARK_TAG : FontsUtil.KEY_MULTI_LIGHT;
    }

    @Override // com.jingdong.common.jdreactFramework.helper.UIModeHelper
    public UIModeHelper.Unregister onRegisterUIModeChangeListener(final UIModeHelper.UIModeChangeListener uIModeChangeListener) {
        final DeepDarkChangeManager.OnUIModeChangeListener onUIModeChangeListener = new DeepDarkChangeManager.OnUIModeChangeListener() { // from class: com.jingdong.common.jdreactFramework.utils.JDReactUIModeHelper.1
            @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
            public void onUIModeChanged(int i2) {
                UIModeHelper.UIModeChangeListener uIModeChangeListener2 = uIModeChangeListener;
                if (uIModeChangeListener2 == null) {
                    return;
                }
                uIModeChangeListener2.onChange(i2, JDReactUIModeHelper.this.getUIModeName(i2));
            }
        };
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(onUIModeChangeListener);
        return new UIModeHelper.Unregister() { // from class: com.jingdong.common.jdreactFramework.utils.JDReactUIModeHelper.2
            @Override // com.jingdong.common.jdreactFramework.helper.UIModeHelper.Unregister
            public void unregister() {
                DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(onUIModeChangeListener);
            }
        };
    }
}
