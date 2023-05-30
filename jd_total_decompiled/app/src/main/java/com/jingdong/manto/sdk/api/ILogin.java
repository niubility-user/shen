package com.jingdong.manto.sdk.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface ILogin extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface CallBack {
        void onFailure();

        void onSuccess();
    }

    /* loaded from: classes16.dex */
    public interface LoginInfoCallBack {
        void onError(int i2, String str);

        void onFailure(int i2, String str);

        void onSuccess(String str, String str2);
    }

    /* loaded from: classes16.dex */
    public interface WebCookieCallBack extends IMantoSdkBase {
        void onFailure();

        void onSuccess(String str);
    }

    void asyncWebCookies();

    void clearWebCookie();

    void doLogin(Activity activity, Bundle bundle, CallBack callBack);

    void doLogin(String str, Bundle bundle, CallBack callBack);

    String getA2(Context context);

    String getCookie(Context context);

    @NonNull
    String getPin(Context context);

    void getWebCookie(LoginInfoCallBack loginInfoCallBack);

    boolean hasLogin();

    boolean needSyncWebCookies();

    void syncWebCookies(String str, WebCookieCallBack webCookieCallBack);
}
