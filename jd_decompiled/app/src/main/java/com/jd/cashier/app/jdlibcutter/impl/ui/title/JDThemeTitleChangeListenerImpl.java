package com.jd.cashier.app.jdlibcutter.impl.ui.title;

import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener;
import com.jingdong.common.unification.title.theme.IThemeChangeListener;

/* loaded from: classes13.dex */
public class JDThemeTitleChangeListenerImpl implements IThemeChangeListener {
    private ITitleThemeChangeListener mTitleThemeChangeListener;

    public JDThemeTitleChangeListenerImpl(ITitleThemeChangeListener iTitleThemeChangeListener) {
        this.mTitleThemeChangeListener = iTitleThemeChangeListener;
    }

    public void onDestroy() {
        if (this.mTitleThemeChangeListener != null) {
            this.mTitleThemeChangeListener = null;
        }
    }

    @Override // com.jingdong.common.unification.title.theme.IThemeChangeListener
    public void onThemeChange(boolean z, String str) {
        ITitleThemeChangeListener iTitleThemeChangeListener = this.mTitleThemeChangeListener;
        if (iTitleThemeChangeListener != null) {
            iTitleThemeChangeListener.onThemeChange(z, str);
        }
    }
}
