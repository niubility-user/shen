package com.jingdong.common.eldermode;

import com.jingdong.common.utils.inter.JDOverseasUtil;
import com.jingdong.sdk.eldermode.helper.IElderModeOverseasConfig;

/* loaded from: classes5.dex */
public class JDElderModeOverseasConfigImpl implements IElderModeOverseasConfig {
    @Override // com.jingdong.sdk.eldermode.helper.IElderModeOverseasConfig
    public int getOverseasArea() {
        return JDOverseasUtil.getCurrentOverseasArea();
    }
}
