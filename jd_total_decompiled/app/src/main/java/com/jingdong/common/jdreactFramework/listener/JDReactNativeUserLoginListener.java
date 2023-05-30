package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableNativeMap;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.UserUtil;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeUserLoginListener implements NativeUserLoginListener, JDFlutterCall {
    public static final String LOGINCHANNEL = "com.jd.jdflutter/login";
    private static final String TAG = "JDReactNativeUserLoginListener";

    private void executeLoginRunnable(final IMyActivity iMyActivity, final Runnable runnable, final String str, final String str2, final boolean z, final int i2) {
        if (iMyActivity != null) {
            if (!LoginUserBase.hasLogin()) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeUserLoginListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        JLog.d(JDReactNativeUserLoginListener.TAG, "Login ok executeLoginRunnable callback is invoked!!");
                        JDReactNativeUserLoginListener.this.startLoginActivity(iMyActivity, str, str2, z, i2);
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
            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeUserLoginListener.3
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(iMyActivity.getThisActivity(), str2, 0).show();
                }
            });
        }
        final Bundle bundle = new Bundle();
        bundle.putString("login_tag_familyNumber", str);
        iMyActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeUserLoginListener.4
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

    @Override // com.jingdong.common.jdreactFramework.listener.NativeUserLoginListener
    public HashMap getUserInfo() {
        String str;
        String str2;
        String str3;
        String str4;
        try {
            JLog.d(TAG, "getUserPin ok callback is invoked!!");
            String str5 = "";
            if (LoginUserBase.hasLogin()) {
                String loginUserName = LoginUserBase.getLoginUserName();
                str2 = LoginUserBase.getLoginName();
                PersonalInfoManager personalInfoManager = PersonalInfoManager.getInstance();
                String nickName = (personalInfoManager == null || !personalInfoManager.isAvailable() || personalInfoManager.getUserInfoSns() == null) ? "" : personalInfoManager.getNickName();
                if (nickName != null && !nickName.equals("")) {
                    str4 = nickName;
                    str3 = UserUtil.getWJLoginHelper().getA2();
                    String str6 = str4;
                    str5 = loginUserName;
                    str = str6;
                }
                str4 = str2;
                str3 = UserUtil.getWJLoginHelper().getA2();
                String str62 = str4;
                str5 = loginUserName;
                str = str62;
            } else {
                str = "";
                str2 = str;
                str3 = str2;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("pin", str5);
            hashMap.put(MobileCertConstants.USERNAME, str2);
            hashMap.put("nickname", str);
            hashMap.put("A2", str3);
            return hashMap;
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return null;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeUserLoginListener
    public String getUserPin() {
        try {
            JLog.d(TAG, "getUserPin ok callback is invoked!!");
            return LoginUserBase.hasLogin() ? LoginUserBase.getLoginUserName() : "";
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return null;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeUserLoginListener
    public boolean isLogin() {
        try {
            return LoginUserBase.hasLogin();
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeUserLoginListener
    public void login(final JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (baseActivity == null) {
                return;
            }
            LoginUserHelper.getInstance().executeLoginRunnable(baseActivity, new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeUserLoginListener.1
                @Override // java.lang.Runnable
                public void run() {
                    JLog.d(JDReactNativeUserLoginListener.TAG, "Login ok callback is invoked!!");
                    jDCallback.invoke(new Object[0]);
                }
            });
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals(LoginConstans.FREGMENT_LOGIN_FLAG)) {
            login(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeUserLoginListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeUserLoginListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("isLogin")) {
            if (isLogin()) {
                jDFlutterCallResult.success("");
            } else {
                jDFlutterCallResult.error("", "", "");
            }
        } else if (str.equals("getUserPin")) {
            String userPin = getUserPin();
            if (!TextUtils.isEmpty(userPin)) {
                jDFlutterCallResult.success("{\"pin\":" + userPin + "}");
                return;
            }
            jDFlutterCallResult.error("", "", "");
        } else if (str.equals("getUserInfo")) {
            WritableNativeMap makeNativeMap = Arguments.makeNativeMap(getUserInfo());
            if (hashMap != null) {
                jDFlutterCallResult.success(makeNativeMap);
            } else {
                jDFlutterCallResult.error("", "", "");
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeUserLoginListener
    public void login(HashMap hashMap, final JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (baseActivity == null) {
                return;
            }
            executeLoginRunnable(baseActivity, new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeUserLoginListener.5
                @Override // java.lang.Runnable
                public void run() {
                    jDCallback.invoke(Boolean.TRUE);
                }
            }, hashMap != null ? (String) hashMap.get("phoneNum") : "", null, true, 0);
        } catch (Exception unused) {
            jDCallback2.invoke(Boolean.FALSE);
        }
    }
}
