package com.jingdong.manto.q;

import com.jingdong.common.jdmiaosha.view.WebContainerUtil;

/* loaded from: classes16.dex */
public enum m {
    APP_LAUNCH("appLaunch"),
    NAVIGATE_TO("navigateTo"),
    NAVIGATE_BACK("navigateBack"),
    REDIRECT_TO("redirectTo"),
    RE_LAUNCH("reLaunch"),
    AUTO_RE_LAUNCH("autoReLaunch"),
    SWITCH_TAB(WebContainerUtil.EVENT_SWITCH_TAB);
    
    private final String a;

    m(String str) {
        this.a = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.a;
    }
}
