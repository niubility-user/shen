package com.jingdong.app.mall.utils;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.jingdong.app.mall.widget.WidgetUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes4.dex */
public class LoginUser extends LoginUserBase {
    private static final String TAG = "LoginUser";
    private static LoginUser loginUser;

    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ IMyActivity f11764g;

        /* renamed from: h */
        final /* synthetic */ String f11765h;

        a(LoginUser loginUser, IMyActivity iMyActivity, String str) {
            this.f11764g = iMyActivity;
            this.f11765h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.f11764g.getThisActivity(), this.f11765h, 0).show();
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f11766g;

        /* renamed from: h */
        final /* synthetic */ IMyActivity f11767h;

        /* renamed from: i */
        final /* synthetic */ boolean f11768i;

        b(LoginUser loginUser, int i2, IMyActivity iMyActivity, boolean z) {
            this.f11766g = i2;
            this.f11767h = iMyActivity;
            this.f11768i = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f11766g > 0) {
                if (Log.D) {
                    Log.d(LoginUser.TAG, "startLoginActivity -->>1 context : " + this.f11767h);
                }
                DeepLinkLoginHelper.startLoginActivityForResult(this.f11767h.getThisActivity(), null, this.f11766g);
            } else if (this.f11768i) {
                Bundle bundle = new Bundle();
                bundle.putInt("com.360buy:navigationDisplayFlag", -1);
                DeepLinkLoginHelper.startLoginActivity(this.f11767h.getThisActivity(), bundle);
                if (Log.D) {
                    Log.d(LoginUser.TAG, "startLoginActivity -->>2 context : " + this.f11767h);
                }
            } else {
                DeepLinkLoginHelper.startLoginActivity(this.f11767h.getThisActivity(), null);
                if (Log.D) {
                    Log.d(LoginUser.TAG, "startLoginActivity -->>3 context : " + this.f11767h);
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ IMyActivity f11769g;

        /* renamed from: h */
        final /* synthetic */ String f11770h;

        /* renamed from: i */
        final /* synthetic */ boolean f11771i;

        /* renamed from: j */
        final /* synthetic */ int f11772j;

        /* renamed from: k */
        final /* synthetic */ Runnable f11773k;

        c(IMyActivity iMyActivity, String str, boolean z, int i2, Runnable runnable) {
            LoginUser.this = r1;
            this.f11769g = iMyActivity;
            this.f11770h = str;
            this.f11771i = z;
            this.f11772j = i2;
            this.f11773k = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            LoginUser.this.startLoginActivity(this.f11769g, this.f11770h, this.f11771i, this.f11772j);
            LoginUserBase.loginCallback(this.f11769g, this.f11773k);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends OnCommonCallback {
        final /* synthetic */ LoginUserBase.LoginListener a;
        final /* synthetic */ boolean b;

        d(LoginUser loginUser, LoginUserBase.LoginListener loginListener, boolean z) {
            this.a = loginListener;
            this.b = z;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (Log.E) {
                Log.e(LoginUser.TAG, "Refresh A2 Error: " + errorResult.getErrorMsg());
            }
            LoginUserBase.LoginListener loginListener = this.a;
            if (loginListener == null || this.b) {
                return;
            }
            loginListener.loginCompletedNotify();
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            if (Log.E) {
                Log.e(LoginUser.TAG, "Refresh A2 Failed: code=" + ((int) failResult.getReplyCode()) + ", msg=" + failResult.getMessage());
            }
            LoginUserBase.LoginListener loginListener = this.a;
            if (loginListener == null || this.b) {
                return;
            }
            loginListener.loginCompletedNotify();
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            LoginUserBase.saveInfoAfterLogin();
            LoginUserBase.LoginListener loginListener = this.a;
            if (loginListener == null || this.b) {
                return;
            }
            loginListener.loginCompletedNotify();
        }
    }

    private LoginUser() {
    }

    public static LoginUser getInstance() {
        if (loginUser == null) {
            loginUser = new LoginUser();
        }
        return loginUser;
    }

    public void executeLoginRunnable(BaseActivity baseActivity, Runnable runnable, String str) {
        executeLoginRunnable(baseActivity, runnable, str, true, 0);
    }

    @Override // com.jingdong.common.login.ILoginUser
    public void homeAutoLogin(IMyActivity iMyActivity, LoginUserBase.LoginListener loginListener, String str) {
        if (Log.D) {
            Log.d(TAG, " homeAutoLogin() -->> ");
        }
        if (UserUtil.getWJLoginHelper().isExistsA2()) {
            boolean isWidgetStart = isWidgetStart(str);
            if (loginListener != null && isWidgetStart) {
                loginListener.loginCompletedNotify();
            }
            UserUtil.getWJLoginHelper().refreshA2(new d(this, loginListener, isWidgetStart));
        }
    }

    @Override // com.jingdong.common.login.ILoginUser
    public boolean isWidgetStart(String str) {
        return WidgetUtils.k(str);
    }

    @Override // com.jingdong.common.login.ILoginUser
    public void startLoginActivity(IMyActivity iMyActivity, String str, boolean z, int i2) {
        if (!TextUtils.isEmpty(str)) {
            iMyActivity.post(new a(this, iMyActivity, str));
        }
        if (Log.D) {
            Log.d(TAG, "startLoginActivity -->> context : " + iMyActivity);
        }
        iMyActivity.post(new b(this, i2, iMyActivity, z));
    }

    public void executeLoginRunnable(BaseActivity baseActivity, Runnable runnable) {
        executeLoginRunnable(baseActivity, runnable, (String) null, true, 0);
    }

    public void executeLoginRunnable(BaseActivity baseActivity, Runnable runnable, int i2) {
        executeLoginRunnable(baseActivity, runnable, (String) null, true, i2);
    }

    public void executeLoginRunnable(BaseActivity baseActivity, Runnable runnable, String str, boolean z) {
        executeLoginRunnable(baseActivity, runnable, str, z, 0);
    }

    public void executeLoginRunnable(BaseActivity baseActivity, Runnable runnable, String str, boolean z, int i2) {
        executeLoginRunnable((IMyActivity) baseActivity, runnable, str, z, i2);
    }

    @Override // com.jingdong.common.login.ILoginUser
    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str, boolean z, int i2) {
        if (!LoginUserBase.hasLogin()) {
            iMyActivity.post(new c(iMyActivity, str, z, i2, runnable));
            return;
        }
        if (Thread.currentThread() != BaseApplication.getUiThread()) {
            iMyActivity.post(runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }
}
