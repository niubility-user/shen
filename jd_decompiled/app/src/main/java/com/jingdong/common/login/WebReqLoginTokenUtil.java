package com.jingdong.common.login;

import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class WebReqLoginTokenUtil {
    private static final String H5_REQLOGINTOKEN_KEY = "wb-token";
    private static final String H5_REQLOGINTOKEN_SCAPE = "JDLogin";
    private static final String H5_REQLOGINTOKEN_SWITCH_KEY = "switchType";
    private static final String TAG = "BasicConfig.JDLogin.WebReqLoginTokenUtil";

    public static boolean reqLoginTokenConfig() {
        String config = JDMobileConfig.getInstance().getConfig(H5_REQLOGINTOKEN_SCAPE, H5_REQLOGINTOKEN_KEY, H5_REQLOGINTOKEN_SWITCH_KEY, "100");
        if (OKLog.D) {
            OKLog.d(TAG, "reqLoginTokenConfig webSwitchType= " + config);
        }
        if (!"0".equals(config) && "1".equals(config)) {
            return LoginUserBase.hasLogin();
        }
        return true;
    }
}
