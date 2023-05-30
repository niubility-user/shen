package com.jd.cashier.app.jdlibcutter.impl.ui.title;

import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeEvent;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;

/* loaded from: classes13.dex */
public class JDThemeTitleChangeEventImpl implements ITitleThemeChangeEvent {
    private JDThemeTitleChangeListenerImpl mTitleThemeChangeListener;

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeEvent
    public void registerTitleThemeChangeEvent(ITitleThemeChangeListener iTitleThemeChangeListener) {
        if (this.mTitleThemeChangeListener == null) {
            this.mTitleThemeChangeListener = new JDThemeTitleChangeListenerImpl(iTitleThemeChangeListener);
        }
        ThemeTitleHelper.setThemeTitleChangeListener(ThemeTitleConstant.CHECKOUT_MODULE_ID, this.mTitleThemeChangeListener);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeEvent
    public void unRegisterTitleThemeChangeEvent(ITitleThemeChangeListener iTitleThemeChangeListener) {
        ThemeTitleHelper.removeThemeTitleChangeListener(this.mTitleThemeChangeListener);
        JDThemeTitleChangeListenerImpl jDThemeTitleChangeListenerImpl = this.mTitleThemeChangeListener;
        if (jDThemeTitleChangeListenerImpl != null) {
            jDThemeTitleChangeListenerImpl.onDestroy();
            this.mTitleThemeChangeListener = null;
        }
    }
}
