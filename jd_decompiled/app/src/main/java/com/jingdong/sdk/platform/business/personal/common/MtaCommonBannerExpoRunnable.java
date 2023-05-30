package com.jingdong.sdk.platform.business.personal.common;

import com.jingdong.sdk.platform.mta.MtaParams;
import com.jingdong.sdk.platform.utils.PlatformEventUtils;

/* loaded from: classes10.dex */
public class MtaCommonBannerExpoRunnable implements Runnable {
    private final String eventId;
    private final String eventParam;
    private final String manageKey;

    public MtaCommonBannerExpoRunnable(String str, String str2, String str3) {
        this.manageKey = str;
        this.eventId = str2;
        this.eventParam = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        PlatformEventUtils.sendMtaEvent(this.manageKey, new MtaParams(this.eventId, this.eventParam, null, "2"));
    }
}
