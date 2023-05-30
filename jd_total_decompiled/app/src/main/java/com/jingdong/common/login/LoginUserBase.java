package com.jingdong.common.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.sec.LogoManager;
import com.jd.sec.logo.TokenManager;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.messagecenter.MessageCommonUtils;
import com.jingdong.common.messagecenter.NewBadgeUtil;
import com.jingdong.common.utils.JDGameUtil;
import com.jingdong.common.utils.JMAUtils;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.web.WebLoginHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.ILoginUserController;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.bmode.util.RequestModeCallback;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class LoginUserBase implements ILoginUser {
    private static final String TAG = "LoginUserBase";

    /* loaded from: classes.dex */
    public interface LoginListener {
        void loginCompletedNotify();
    }

    public static String getDeviceJson() {
        String logo = LogoManager.getInstance(JdSdk.getInstance().getApplication().getApplicationContext()).getLogo();
        String token = TokenManager.getToken(JdSdk.getInstance().getApplication().getApplicationContext());
        if (OKLog.D) {
            OKLog.d("DeviceFingerUtil", "finger = " + logo);
            OKLog.d("DeviceFingerUtil", "token = " + token);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("eid", logo);
            jSONObject.put("token", token);
            jSONObject.put("unionwsws", DeviceFingerUtil.getUnionFingerPrint());
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getLoginName() {
        if (hasLogin()) {
            try {
                WJLoginHelper wJLoginHelper = UserUtil.getWJLoginHelper();
                return wJLoginHelper != null ? wJLoginHelper.getUserAccount() : "";
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }

    @Deprecated
    public static JSONObject getLoginUserInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JDGameUtil.KEY_LOGIN_NAME, getLoginName());
            jSONObject.put("pin", getUserPin());
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        return jSONObject;
    }

    @Deprecated
    public static String getLoginUserName() {
        return getUserPin();
    }

    public static String getUserPin() {
        return UserUtil.getWJLoginHelper().getPin();
    }

    public static boolean hasLogin() {
        return UserUtil.getWJLoginHelper().hasLogin();
    }

    public static void init() {
        try {
            if (hasLogin()) {
                return;
            }
            UserUtil.clearWebViewCookie(BaseFrameUtil.getInstance().getCurrentMyActivity());
            WebLoginHelper.onUserLoginChange(false, 0);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void loginCallback(final IMyActivity iMyActivity, final Runnable runnable) {
        iMyActivity.addResumeListener(new IResumeListener() { // from class: com.jingdong.common.login.LoginUserBase.1
            @Override // com.jingdong.common.frame.IResumeListener
            public void onResume() {
                Runnable runnable2;
                iMyActivity.removeResumeListener(this);
                if (LoginUserBase.hasLogin() && (runnable2 = runnable) != null) {
                    runnable2.run();
                }
            }
        });
    }

    public static void logoutOnCode3(final ILoginUserController.ILoginStateChecker iLoginStateChecker) {
        if (OKLog.D) {
            OKLog.d(TAG, "logoutOnCode3 Called");
        }
        if (!hasLogin()) {
            LoginReportUtil.reportCode3("3", "checklocalerr", "checklocalerr");
            logoutOnlineInfo();
            if (iLoginStateChecker != null) {
                iLoginStateChecker.onFailure();
                return;
            }
            return;
        }
        LoginReportUtil.reportCode3("3", "check", "check");
        UserUtil.getWJLoginHelper().CheckA2(new OnCommonCallback() { // from class: com.jingdong.common.login.LoginUserBase.2
            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onError(ErrorResult errorResult) {
                LoginReportUtil.reportCode3("3", "checkerr", "checkerr");
                LoginUserBase.logoutOnlineInfo();
                ILoginUserController.ILoginStateChecker iLoginStateChecker2 = iLoginStateChecker;
                if (iLoginStateChecker2 != null) {
                    iLoginStateChecker2.onFailure();
                }
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onFail(FailResult failResult) {
                LoginReportUtil.reportCode3("3", "checkerr", "checkerr");
                LoginUserBase.logoutOnlineInfo();
                ILoginUserController.ILoginStateChecker iLoginStateChecker2 = iLoginStateChecker;
                if (iLoginStateChecker2 != null) {
                    iLoginStateChecker2.onFailure();
                }
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onSuccess() {
                LoginReportUtil.reportCode3("3", "checksuccess", "checksuccess");
                ILoginUserController.ILoginStateChecker iLoginStateChecker2 = iLoginStateChecker;
                if (iLoginStateChecker2 != null) {
                    iLoginStateChecker2.onSuccess();
                }
            }
        });
    }

    public static void logoutOnlineInfo() {
        if (OKLog.D) {
            OKLog.d(TAG, "logoutOnlineInfo Called");
        }
        WebReqCookieUtil.clearWebCookie(2);
        setUserStateOff(true);
        Constants.clearOrderInfo();
        ShoppingBaseController.clearLocalCart();
        EventBus.getDefault().post(new LoginEvent(LoginEvent.TYPE_READY_LOGOUT));
        UserUtil.getWJLoginHelper().clearLocalOnlineState();
        UserUtil.cleanData(BaseFrameUtil.getInstance().getCurrentMyActivity());
        if (JDHttpTookit.getEngine().getGuardVerifyPlugin() != null) {
            JDHttpTookit.getEngine().getGuardVerifyPlugin().onLogout();
        }
    }

    public static void saveInfoAfterLogin(int i2, int i3) {
        try {
            JDRiskHandleManager.getInstance().onLoginSuccess("");
            if (hasLogin()) {
                if (OKLog.D) {
                    String str = TAG;
                    OKLog.d(str, "saveInfoAfterLogin mode =" + i2 + " flag=" + i3 + " \u51c6\u5907\u53d1\u767b\u9646\u6210\u529f\u5e7f\u64ad\u5566");
                    StringBuilder sb = new StringBuilder();
                    sb.append("saveInfoAfterLogin LoginConstans.PARAM_DATA_VALUE=");
                    sb.append(LoginConstans.PARAM_DATA_VALUE);
                    OKLog.d(str, sb.toString());
                }
                WebReqCookieUtil.reWebCookieLogin(4);
                JMAUtils.getBlog(true);
                Bundle bundle = new Bundle();
                bundle.putInt("type", i3);
                if (LoginConstans.REFRESH_MODE_VALUE == i2) {
                    bundle.putInt(LoginConstans.REFRESH_MODE, i2);
                }
                if (!TextUtils.isEmpty(LoginConstans.PARAM_DATA_VALUE)) {
                    bundle.putString(LoginConstans.PARAM_DATA_KEY, LoginConstans.PARAM_DATA_VALUE);
                }
                LoginEvent loginEvent = new LoginEvent(LoginEvent.TYPE_LOGIN);
                loginEvent.setBundle(bundle);
                EventBus.getDefault().post(loginEvent);
                Intent intent = new Intent("com.jingdong.action.user.login.in");
                intent.putExtra("type", i3);
                if (LoginConstans.REFRESH_MODE_VALUE == i2) {
                    intent.putExtra(LoginConstans.REFRESH_MODE, i2);
                }
                if (!TextUtils.isEmpty(LoginConstans.PARAM_DATA_VALUE)) {
                    intent.putExtra(LoginConstans.PARAM_DATA_KEY, LoginConstans.PARAM_DATA_VALUE);
                }
                intent.setPackage(JdSdk.getInstance().getApplicationContext().getPackageName());
                JdSdk.getInstance().getApplication().sendBroadcast(intent);
                LoginConstans.FROM_BMODE = 0;
                LoginConstans.FROM_ROUTER_BMODE = 0;
                LoginConstans.PARAM_DATA_VALUE = null;
                LoginConstans.RESOURCE_URL_VAULE = null;
                MessageCommonUtils.Bind();
                WebLoginHelper.onUserLoginChange(true, 1);
            }
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, "saveInfoAfterLogin error: ", th);
            }
        }
    }

    public static void setAlreadySyncCart(boolean z) {
    }

    @Deprecated
    public static void setUserInfo(JSONObject jSONObject) {
    }

    @Deprecated
    public static void setUserState(int i2) {
    }

    public static void setUserStateOff(boolean z) {
        if (z) {
            MessageCommonUtils.unBind();
            NewBadgeUtil.clearBadge();
        }
    }

    public static void saveInfoAfterLogin(final int i2) {
        if (OKLog.D) {
            String str = TAG;
            OKLog.d(str, "saveInfoAfterLogin \uff08int flag\uff09 LoginConstans.FROM_BMODE =" + LoginConstans.FROM_BMODE);
            OKLog.d(str, "saveInfoAfterLogin LoginConstans.FROM_ROUTER_BMODE =" + LoginConstans.FROM_ROUTER_BMODE);
        }
        if (!JDElderModeUtils.isElderMode() && ((LoginConstans.FROM_BMODE == LoginConstans.REFRESH_MODE_VALUE && CCFLoginUtil.isOpenNativeBModel()) || (LoginConstans.FROM_ROUTER_BMODE == LoginConstans.REFRESH_MODE_VALUE && CCFLoginUtil.isOpenRouterBModel()))) {
            JDBModeUtils.requestModeWithLogin(new RequestModeCallback() { // from class: com.jingdong.common.login.LoginUserBase.3
                @Override // com.jingdong.sdk.bmode.util.RequestModeCallback
                public void fail() {
                    if (OKLog.D) {
                        OKLog.d(LoginUserBase.TAG, "saveInfoAfterLogin\uff08int flag\uff09 JDBModeUtils.requestModeWithLogin fail");
                    }
                    LoginUserBase.saveInfoAfterLogin(LoginConstans.REFRESH_MODE_VALUE, i2);
                }

                @Override // com.jingdong.sdk.bmode.util.RequestModeCallback
                public void success() {
                    if (OKLog.D) {
                        OKLog.d(LoginUserBase.TAG, "saveInfoAfterLogin\uff08int flag\uff09 JDBModeUtils.requestModeWithLogin success");
                    }
                    LoginUserBase.saveInfoAfterLogin(LoginConstans.REFRESH_MODE_VALUE, i2);
                }
            });
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "saveInfoAfterLogin\uff08int flag\uff09 LoginConstans.FROM_BMODE =" + LoginConstans.FROM_BMODE + " \u76f4\u63a5\u53d1\u767b\u9646\u6210\u529f\u5e7f\u64ad");
        }
        saveInfoAfterLogin(0, i2);
    }

    public static void saveInfoAfterLogin() {
        if (OKLog.D) {
            String str = TAG;
            OKLog.d(str, "saveInfoAfterLogin LoginConstans.FROM_BMODE =" + LoginConstans.FROM_BMODE);
            OKLog.d(str, "saveInfoAfterLogin LoginConstans.FROM_ROUTER_BMODE =" + LoginConstans.FROM_ROUTER_BMODE);
        }
        if (!JDElderModeUtils.isElderMode() && ((LoginConstans.FROM_BMODE == LoginConstans.REFRESH_MODE_VALUE && CCFLoginUtil.isOpenNativeBModel()) || (LoginConstans.FROM_ROUTER_BMODE == LoginConstans.REFRESH_MODE_VALUE && CCFLoginUtil.isOpenRouterBModel()))) {
            JDBModeUtils.requestModeWithLogin(new RequestModeCallback() { // from class: com.jingdong.common.login.LoginUserBase.4
                @Override // com.jingdong.sdk.bmode.util.RequestModeCallback
                public void fail() {
                    if (OKLog.D) {
                        OKLog.d(LoginUserBase.TAG, "saveInfoAfterLogin JDBModeUtils.requestModeWithLogin fail");
                    }
                    LoginUserBase.saveInfoAfterLogin(LoginConstans.REFRESH_MODE_VALUE, 0);
                }

                @Override // com.jingdong.sdk.bmode.util.RequestModeCallback
                public void success() {
                    if (OKLog.D) {
                        OKLog.d(LoginUserBase.TAG, "saveInfoAfterLogin JDBModeUtils.requestModeWithLogin success");
                    }
                    LoginUserBase.saveInfoAfterLogin(LoginConstans.REFRESH_MODE_VALUE, 0);
                }
            });
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "saveInfoAfterLogin LoginConstans.FROM_BMODE =" + LoginConstans.FROM_BMODE + " \u76f4\u63a5\u53d1\u767b\u9646\u6210\u529f\u5e7f\u64ad");
        }
        saveInfoAfterLogin(0, 0);
    }
}
