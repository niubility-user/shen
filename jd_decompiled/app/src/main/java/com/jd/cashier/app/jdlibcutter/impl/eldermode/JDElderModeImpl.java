package com.jd.cashier.app.jdlibcutter.impl.eldermode;

import com.jd.cashier.app.jdlibcutter.protocol.eldermode.IElderMode;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;

/* loaded from: classes13.dex */
public class JDElderModeImpl implements IElderMode {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.eldermode.IElderMode
    public int getElderMode() {
        return JDElderModeUtils.getElderMode();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.eldermode.IElderMode
    public float getScaleTextSize(float f2) {
        return JDElderModeUtils.getTextSizeBySupportMode(f2, 5);
    }
}
