package com.jingdong.jdsdk.network.dependency;

/* loaded from: classes.dex */
public interface ILoginUserController {

    /* loaded from: classes.dex */
    public interface ILoginStateChecker {
        void onFailure();

        void onSuccess();
    }

    String getCookie();

    void logoutOnlineInfo(ILoginStateChecker iLoginStateChecker);

    void reportCode3(String str);
}
