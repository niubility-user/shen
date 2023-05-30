package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeLoginHelper implements JDFlutterCall {
    public static final String LOGINCHANNEL = "com.jd.jdflutter/login";

    private void executeLoginRunnable(final IMyActivity iMyActivity, final Runnable runnable, final String str, final String str2, final boolean z, final int i2) {
        if (iMyActivity != null) {
            if (!LoginUserBase.hasLogin()) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.7
                    @Override // java.lang.Runnable
                    public void run() {
                        JDReactNativeLoginHelper.this.startLoginActivity(iMyActivity, str, str2, z, i2);
                        LoginUserBase.loginCallback(iMyActivity, runnable);
                    }
                });
                return;
            }
            if (Thread.currentThread() != BaseApplication.getUiThread()) {
                iMyActivity.post(runnable);
            } else if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoginActivity(final IMyActivity iMyActivity, String str, final String str2, final boolean z, final int i2) {
        if (iMyActivity == null) {
            return;
        }
        if (!TextUtils.isEmpty(str2)) {
            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.8
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(iMyActivity.getThisActivity(), str2, 0).show();
                }
            });
        }
        final Bundle bundle = new Bundle();
        bundle.putString("login_tag_familyNumber", str);
        iMyActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.9
            @Override // java.lang.Runnable
            public void run() {
                int i3 = i2;
                if (i3 > 0) {
                    DeepLinkLoginHelper.startLoginActivityForResult((Activity) iMyActivity, bundle, i3);
                    return;
                }
                if (z) {
                    bundle.putInt("com.360buy:navigationDisplayFlag", -1);
                }
                DeepLinkLoginHelper.startLoginActivity((Activity) iMyActivity, bundle);
            }
        });
    }

    public void isLogin(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            if (LoginUserBase.hasLogin()) {
                jDCallback.invoke(new Object[0]);
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception unused) {
            jDCallback2.invoke(new Object[0]);
        }
    }

    public void login(HashMap hashMap, final JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (baseActivity == null) {
                return;
            }
            executeLoginRunnable(baseActivity, new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.5
                @Override // java.lang.Runnable
                public void run() {
                    jDCallback.invoke(Boolean.TRUE);
                }
            }, hashMap != null ? (String) hashMap.get("phoneNum") : "", null, true, 0);
        } catch (Exception unused) {
            jDCallback2.invoke(Boolean.FALSE);
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("islogin")) {
            isLogin(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.1
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(Boolean.TRUE);
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(Boolean.FALSE);
                }
            });
        } else if (str.equals(LoginConstans.FREGMENT_LOGIN_FLAG)) {
            login(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(Boolean.TRUE);
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(Boolean.FALSE);
                }
            });
        }
    }

    public void login(final JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (baseActivity == null) {
                return;
            }
            LoginUserHelper.getInstance().executeLoginRunnable(baseActivity, new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeLoginHelper.6
                @Override // java.lang.Runnable
                public void run() {
                    jDCallback.invoke(Boolean.TRUE);
                }
            });
        } catch (Exception unused) {
            jDCallback2.invoke(Boolean.FALSE);
        }
    }
}
