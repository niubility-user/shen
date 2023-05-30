package com.jingdong.jdsdk.login;

import com.jingdong.common.login.LoginUserTemp;

@Deprecated
/* loaded from: classes.dex */
public class LoginUserHelper {
    private static LoginUserHelper loginUserHelper;
    private ILoginUserTemp loginUser = new LoginUserTemp();

    private LoginUserHelper() {
    }

    @Deprecated
    public static synchronized LoginUserHelper getInstance() {
        LoginUserHelper loginUserHelper2;
        synchronized (LoginUserHelper.class) {
            if (loginUserHelper == null) {
                loginUserHelper = new LoginUserHelper();
            }
            loginUserHelper2 = loginUserHelper;
        }
        return loginUserHelper2;
    }

    @Deprecated
    public ILoginUserTemp getLoginUser() {
        return this.loginUser;
    }

    @Deprecated
    public void setLoginUser(ILoginUserTemp iLoginUserTemp) {
    }
}
