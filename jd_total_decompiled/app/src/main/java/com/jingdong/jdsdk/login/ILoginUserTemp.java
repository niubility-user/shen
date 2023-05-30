package com.jingdong.jdsdk.login;

import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.dependency.ILoginUserController;

@Deprecated
/* loaded from: classes.dex */
public interface ILoginUserTemp {
    @Deprecated
    void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable);

    @Deprecated
    void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, int i2);

    @Deprecated
    void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str);

    @Deprecated
    void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str, boolean z);

    @Deprecated
    String getLoginUserName();

    @Deprecated
    boolean hasLogin();

    @Deprecated
    void logoutOnCode3(ILoginUserController.ILoginStateChecker iLoginStateChecker);

    @Deprecated
    void logoutOnlineInfo();
}
