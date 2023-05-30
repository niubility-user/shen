package com.jingdong.service.impl;

import android.content.SharedPreferences;
import com.jingdong.service.BaseService;
import com.jingdong.service.service.CustomConfigService;

/* loaded from: classes10.dex */
public class IMCustomConfig extends BaseService implements CustomConfigService {
    private static final String TAG = "IMCustomConfig";

    public String getArt() {
        return "";
    }

    public int getLoginType() {
        return 0;
    }

    public String getNgwHost() {
        return null;
    }

    public SharedPreferences getSharedPreferences() {
        return null;
    }

    public int getSiteId() {
        return 0;
    }

    @Override // com.jingdong.service.service.CustomConfigService
    public int getTenantId() {
        return 0;
    }

    @Override // com.jingdong.service.service.CustomConfigService
    public int getUa() {
        return 0;
    }

    public boolean isBeta() {
        return false;
    }

    public boolean isDisplayCutout() {
        return false;
    }

    public boolean isLottieEnable() {
        return false;
    }

    public boolean isSizeScaleBig(boolean z) {
        return false;
    }
}
