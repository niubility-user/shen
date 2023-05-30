package com.jingdong.app.mall.im.business;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jingdong.common.unification.uniutil.UnAndroidUtils;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.impl.IMCustomConfig;

/* loaded from: classes4.dex */
public class h extends IMCustomConfig {
    private static final String a = "h";

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public String getArt() {
        OKLog.d("bundleicssdkservice", a + "--- getArt\uff1a");
        return "";
    }

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public int getLoginType() {
        OKLog.d("bundleicssdkservice", a + "---getLoginType:4");
        return 4;
    }

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public String getNgwHost() {
        String ngwHost = Configuration.getNgwHost();
        OKLog.d("bundleicssdkservice", a + "---getNgwHost:" + ngwHost);
        return ngwHost;
    }

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public SharedPreferences getSharedPreferences() {
        OKLog.d("bundleicssdkservice", a + "---getSharedPreferences");
        return new JDSharedPreferences(JdSdk.getInstance().getApplicationContext(), "_bundle_icssdk_", 0);
    }

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public int getSiteId() {
        OKLog.d("bundleicssdkservice", a + "---getSiteId :0");
        return 0;
    }

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public boolean isBeta() {
        boolean isBeta = Configuration.isBeta();
        OKLog.d("bundleicssdkservice", a + "---isBeta\uff1a" + isBeta);
        return isBeta;
    }

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public boolean isDisplayCutout() {
        boolean isDisplayCutout = UnAndroidUtils.isDisplayCutout();
        OKLog.d("bundleicssdkservice", a + "---isDisplayCutout\uff1a" + isDisplayCutout);
        return isDisplayCutout;
    }

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public boolean isLottieEnable() {
        boolean isLottieEnable = ABTestUtils.isLottieEnable();
        OKLog.d("bundleicssdkservice", a + "---isLottieEnable:" + isLottieEnable);
        return isLottieEnable;
    }

    @Override // com.jingdong.service.impl.IMCustomConfig, com.jingdong.service.service.CustomConfigService
    public boolean isSizeScaleBig(boolean z) {
        OKLog.d("bundleicssdkservice", a + "---isSizeScaleBig isOld:" + z);
        return TextUtils.equals(ScaleModeConstants.TEXT_SCALE_MODE_LARGE, z ? ScaleModeConstants.TEXT_SCALE_MODE_LARGE : TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode());
    }
}
