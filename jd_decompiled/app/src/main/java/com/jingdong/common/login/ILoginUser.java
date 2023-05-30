package com.jingdong.common.login;

import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;

/* loaded from: classes.dex */
public interface ILoginUser {
    void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str, boolean z, int i2);

    void homeAutoLogin(IMyActivity iMyActivity, LoginUserBase.LoginListener loginListener, String str);

    boolean isWidgetStart(String str);

    void startLoginActivity(IMyActivity iMyActivity, String str, boolean z, int i2);
}
