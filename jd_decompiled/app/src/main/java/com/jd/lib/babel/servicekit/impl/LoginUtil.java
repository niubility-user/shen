package com.jd.lib.babel.servicekit.impl;

import android.content.Context;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jd.lib.babel.servicekit.iservice.ILogin;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;

/* loaded from: classes13.dex */
public class LoginUtil implements ILogin {
    @Override // com.jd.lib.babel.servicekit.iservice.ILogin
    public void executeLogin(Context context, BabelLoginCallback babelLoginCallback) {
        try {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, null);
            if (babelLoginCallback != null) {
                babelLoginCallback.onSuccess(null);
            }
        } catch (Exception unused) {
            if (babelLoginCallback != null) {
                babelLoginCallback.onError();
            }
        }
    }

    @Override // com.jd.lib.babel.servicekit.iservice.ILogin
    public String getCookie() {
        return null;
    }

    @Override // com.jd.lib.babel.servicekit.iservice.ILogin
    public String getUserPin() {
        return LoginUserBase.getUserPin();
    }

    @Override // com.jd.lib.babel.servicekit.iservice.ILogin
    public boolean hasLogin() {
        return LoginUserBase.hasLogin();
    }
}
