package com.jingdong.common.login;

import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.login.ILoginUserTemp;
import com.jingdong.jdsdk.network.dependency.ILoginUserController;

@Deprecated
/* loaded from: classes.dex */
public class LoginUserTemp implements ILoginUserTemp {
    @Override // com.jingdong.jdsdk.login.ILoginUserTemp
    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, runnable, str);
    }

    @Override // com.jingdong.jdsdk.login.ILoginUserTemp
    public String getLoginUserName() {
        return LoginUserBase.getLoginUserName();
    }

    @Override // com.jingdong.jdsdk.login.ILoginUserTemp
    public boolean hasLogin() {
        return LoginUserBase.hasLogin();
    }

    @Override // com.jingdong.jdsdk.login.ILoginUserTemp
    public void logoutOnCode3(ILoginUserController.ILoginStateChecker iLoginStateChecker) {
        LoginUserBase.logoutOnCode3(iLoginStateChecker);
    }

    @Override // com.jingdong.jdsdk.login.ILoginUserTemp
    public void logoutOnlineInfo() {
        LoginUserBase.logoutOnlineInfo();
    }

    @Override // com.jingdong.jdsdk.login.ILoginUserTemp
    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, runnable);
    }

    @Override // com.jingdong.jdsdk.login.ILoginUserTemp
    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, int i2) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, runnable, i2);
    }

    @Override // com.jingdong.jdsdk.login.ILoginUserTemp
    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str, boolean z) {
        LoginUserHelper.getInstance().executeLoginRunnable(iMyActivity, runnable, str, z);
    }
}
