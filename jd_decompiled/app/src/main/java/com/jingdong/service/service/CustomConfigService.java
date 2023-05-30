package com.jingdong.service.service;

import android.content.SharedPreferences;

/* loaded from: classes10.dex */
public interface CustomConfigService {
    String getArt();

    int getLoginType();

    String getNgwHost();

    SharedPreferences getSharedPreferences();

    int getSiteId();

    int getTenantId();

    int getUa();

    boolean isBeta();

    boolean isDisplayCutout();

    boolean isLottieEnable();

    boolean isSizeScaleBig(boolean z);
}
