package com.jingdong.app.mall.dynamicImpl;

import com.jd.dynamic.base.interfaces.IDynamicDark;
import com.jingdong.common.utils.DeepDarkChangeManager;

/* loaded from: classes3.dex */
public class q implements IDynamicDark {
    @Override // com.jd.dynamic.base.interfaces.IDynamicDark
    public boolean isDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode() == 1;
    }
}
