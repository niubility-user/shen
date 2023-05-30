package com.jd.cashier.app.jdlibcutter.impl.ui.title;

import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleChangeEventCreator;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeEvent;

/* loaded from: classes13.dex */
public class JDThemeTitleChangeEventCreatorImpl implements ITitleChangeEventCreator {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleChangeEventCreator
    public ITitleThemeChangeEvent instanceTitleThemeChangeEvent() {
        return new JDThemeTitleChangeEventImpl();
    }
}
