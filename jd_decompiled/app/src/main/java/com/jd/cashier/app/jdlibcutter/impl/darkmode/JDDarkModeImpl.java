package com.jd.cashier.app.jdlibcutter.impl.darkmode;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jd.cashier.app.jdlibcutter.protocol.darkmode.IDarkMode;
import com.jingdong.common.utils.DeepDarkChangeManager;

/* loaded from: classes13.dex */
public class JDDarkModeImpl implements IDarkMode {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.darkmode.IDarkMode
    public void addDeepDarkChangeListener(LifecycleOwner lifecycleOwner, Observer<Integer> observer, boolean z) {
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(lifecycleOwner, observer, z);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.darkmode.IDarkMode
    public int getDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.darkmode.IDarkMode
    public boolean isDarkMode() {
        return 1 == DeepDarkChangeManager.getInstance().getUIMode();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.darkmode.IDarkMode
    public void removeDeepDarkChangeListener(Observer<Integer> observer) {
        DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(observer);
    }
}
