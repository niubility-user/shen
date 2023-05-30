package com.jingdong.jdsdk.d.c.a;

import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.platform.lib.LoginInfo;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase;

/* loaded from: classes14.dex */
public class a implements ILoginUserBase {
    private static a a = new a();

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase
    public LoginInfo getLoginInfo() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase
    public String getLoginUserName() {
        return LoginUserBase.getLoginUserName();
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase
    public boolean hasLogin() {
        return LoginUserBase.hasLogin();
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase
    public void setAlreadySyncCart(boolean z) {
        LoginUserBase.setAlreadySyncCart(z);
    }
}
