package com.jingdong.sdk.platform.lib.openapi.login;

import com.jingdong.sdk.platform.lib.LoginInfo;

/* loaded from: classes10.dex */
public interface ILoginUserBase {
    LoginInfo getLoginInfo();

    String getLoginUserName();

    boolean hasLogin();

    void setAlreadySyncCart(boolean z);
}
