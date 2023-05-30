package com.jd.cashier.app.jdlibcutter.impl.login;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.login.ILogin;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.ICancelLogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.SafetyManager;
import com.jingdong.common.utils.UserUtil;

/* loaded from: classes13.dex */
public class LoginImpl implements ILogin {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.login.ILogin
    public void doLogin(Context context, Bundle bundle, final Runnable runnable, final Runnable runnable2) {
        if (context != null) {
            IConfig sdkConfig = DependInitializer.getSdkConfig();
            final String loginBusinessId = sdkConfig != null ? sdkConfig.getLoginBusinessId() : "";
            DeepLinkLoginHelper.startLoginActivity(context, bundle, new ICancelLogin() { // from class: com.jd.cashier.app.jdlibcutter.impl.login.LoginImpl.1
                @Override // com.jingdong.common.login.ICancelLogin
                public void onCancel(String str) {
                    Runnable runnable3;
                    if (!TextUtils.equals(str, loginBusinessId) || (runnable3 = runnable2) == null) {
                        return;
                    }
                    runnable3.run();
                }

                @Override // com.jingdong.common.login.ILogin
                public void onSuccess(String str) {
                    Runnable runnable3;
                    if (!TextUtils.equals(str, loginBusinessId) || (runnable3 = runnable) == null) {
                        return;
                    }
                    runnable3.run();
                }
            }, loginBusinessId);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.login.ILogin
    public String getCookie() {
        return SafetyManager.getCookies();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.login.ILogin
    public String getSession() {
        return UserUtil.getWJLoginHelper().getA2();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.login.ILogin
    public void logout() {
        try {
            LoginUserBase.logoutOnlineInfo();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
