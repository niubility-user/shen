package com.jingdong.manto.jsapi.auth.tools;

/* loaded from: classes15.dex */
public interface AuthorizeCallBack {
    void onAuth();

    void onConfirm(AuthInfo authInfo, String str);

    void onFailure(String str);
}
