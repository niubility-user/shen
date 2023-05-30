package com.jd.manto.d;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.jd.manto.login.LoginProxyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.SafetyManager;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.sdk.oklog.OKLog;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.model.A4LoginInfo;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes17.dex */
public class r implements ILogin {

    /* loaded from: classes17.dex */
    class a extends OnDataCallback<A4LoginInfo> {
        final /* synthetic */ ILogin.LoginInfoCallBack a;

        a(r rVar, ILogin.LoginInfoCallBack loginInfoCallBack) {
            this.a = loginInfoCallBack;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnDataCallback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(A4LoginInfo a4LoginInfo) {
            this.a.onSuccess(a4LoginInfo.getA4(), LoginUserBase.getUserPin());
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            this.a.onError(errorResult.getErrorCode(), errorResult.getErrorMsg());
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            this.a.onFailure(failResult.getReplyCode(), failResult.getMessage());
        }
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public void asyncWebCookies() {
        com.jd.manto.login.a.i();
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public void clearWebCookie() {
        UserUtil.getWJLoginHelper().clearA4();
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public void doLogin(Activity activity, Bundle bundle, ILogin.CallBack callBack) {
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public void doLogin(String str, Bundle bundle, ILogin.CallBack callBack) {
        OKLog.d("MantoLoginImpl", "doLogin");
        LoginProxyActivity.u(str, bundle, callBack);
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public String getA2(Context context) {
        return UserUtil.getWJLoginHelper().getA2();
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public String getCookie(Context context) {
        return SafetyManager.getCookies();
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    @NonNull
    public String getPin(Context context) {
        return LoginUserBase.getUserPin();
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public void getWebCookie(ILogin.LoginInfoCallBack loginInfoCallBack) {
        UserUtil.getWJLoginHelper().sendGetA4(new a(this, loginInfoCallBack));
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public boolean hasLogin() {
        return LoginUserBase.hasLogin();
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public boolean needSyncWebCookies() {
        return true;
    }

    @Override // com.jingdong.manto.sdk.api.ILogin
    public void syncWebCookies(String str, ILogin.WebCookieCallBack webCookieCallBack) {
        com.jd.manto.login.a.o(str, webCookieCallBack);
    }
}
