package com.jingdong.common.login;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.aips.verify.VerifyEngine;
import com.jdjr.risk.identity.verify.IdentityVerityCallback;
import com.jdjr.risk.identity.verify.IdentityVerityEngine;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkFaceloginLivenessHelper;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.model.CheckFaceLoginResp;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.SuccessResult;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FaceLoginHelper {
    public static final String FORCE_UPGRADE_JD_APP = "force_upgrade_jd_app";
    public static final String GET_PERMISSON_JD_APP = "get_permisson_jd_app";
    private static String TAG = "FaceLoginHelper";
    public static final int TYPE_FACE_LOGIN = 0;
    public static final int TYPE_FACE_LOGIN_SETTING = 1;
    public static final int TYPE_FACE_LOGIN_SETTING_AND_LOGIN = 2;
    private static boolean isFromMyJDCaidan;
    private static Activity mActivity;
    static OnCommonCallback mFaceLoginCallbackInJDLib = new OnCommonCallback(new FaceLoginFailProcessor() { // from class: com.jingdong.common.login.FaceLoginHelper.6
        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void accountNotExist(FailResult failResult) {
            FaceLoginHelper.showOneBtnDialog(failResult.getMessage(), "\u786e\u5b9a", 2);
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void faceDataError(FailResult failResult) {
            FaceLoginHelper.showOneBtnDialog(failResult.getMessage(), "\u786e\u5b9a", 2);
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void getBackPassword(FailResult failResult) {
            if (!TextUtils.isEmpty(failResult.getJumpResult().getUrl())) {
                FaceLoginHelper.showDialogToM(failResult.getMessage(), failResult.getJumpResult().getUrl(), "\u786e\u5b9a", "\u53d6\u6d88", "toFindPwd");
            } else {
                ToastUtil.showToast(failResult.getMessage());
            }
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void handle0x6a0x640x8(FailResult failResult) {
            FaceLoginHelper.showOneBtnDialog(failResult.getMessage(), "\u786e\u5b9a", 2);
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void handle0xaf(FailResult failResult) {
            FaceLoginHelper.showOneBtnDialog(failResult.getMessage(), "\u786e\u5b9a", 1);
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void handle0xb2(FailResult failResult) {
            FaceLoginHelper.showOneBtnDialog(failResult.getMessage(), "\u786e\u5b9a", 1);
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void handleBetween0x77And0x7a(FailResult failResult) {
            if (TextUtils.isEmpty(failResult.getJumpResult().getUrl())) {
                FaceLoginHelper.showOneBtnDialog(failResult.getMessage(), "\u786e\u5b9a", 2);
            } else {
                FaceLoginHelper.showDialogToM(failResult.getMessage(), failResult.getJumpResult().getUrl(), "\u786e\u5b9a", "\u53d6\u6d88", FaceLoginHelper.FORCE_UPGRADE_JD_APP);
            }
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void handleBetween0x7bAnd0x7e(FailResult failResult) {
            FaceLoginHelper.showOneBtnDialog(failResult.getMessage(), "\u786e\u5b9a", 2);
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void onCommonHandler(FailResult failResult) {
            if (Log.D) {
                String unused = FaceLoginHelper.TAG;
                String unused2 = FaceLoginHelper.TAG;
                String str = "faceLogin onFail MSG=" + failResult.getMessage() + "CODE=" + ((int) failResult.getReplyCode());
            }
            String message = failResult.getMessage();
            if (TextUtils.isEmpty(message)) {
                message = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86!";
            }
            ToastUtils.showToast(message);
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void onSendMsg(FailResult failResult) {
            FaceLoginHelper.handleRiskCheck(failResult);
        }

        @Override // jd.wjlogin_sdk.common.listener.FaceLoginFailProcessor
        public void onSendMsgWithoutDialog(FailResult failResult) {
            String url = failResult.getJumpResult().getUrl();
            String token = failResult.getJumpResult().getToken();
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(token)) {
                FaceLoginHelper.showOneBtnDialog(failResult.getMessage(), "\u786e\u5b9a", 2);
                return;
            }
            FaceLoginHelper.intentWeb(FaceLoginHelper.jumpFengkongM(failResult, url, token));
            SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY_TEMP, LoginConstans.LOGIN_LAST_WAY_FACE_SENDMSG);
        }
    }) { // from class: com.jingdong.common.login.FaceLoginHelper.7
        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            if (Log.D) {
                String unused = FaceLoginHelper.TAG;
            }
            String errorMsg = errorResult.getErrorMsg();
            if (TextUtils.isEmpty(errorMsg)) {
                errorMsg = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86!";
            }
            ToastUtils.showToast(errorMsg);
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            String string = SafetyManager.getString("loginType", "0");
            if (OKLog.D) {
                OKLog.d(FaceLoginHelper.TAG, " faceLogin onSuccess  loginType=" + string);
            }
            if ("2".equals(string)) {
                if (OKLog.D) {
                    OKLog.d(FaceLoginHelper.TAG, "mFaceLoginCallback maidian= NewLogin_FaceLoginSucces");
                }
                JDMtaUtils.onClickWithPageId(JdSdk.getInstance().getApplication(), "NewLogin_FaceLoginSucces", FaceLoginHelper.TAG, "NewLogin_FaceLogin");
            } else {
                if (OKLog.D) {
                    OKLog.d(FaceLoginHelper.TAG, "mFaceLoginCallback maidian=NewLogin_ScanFaceLoginSuccess");
                }
                JDMtaUtils.onClickWithPageId(JdSdk.getInstance().getApplication(), "NewLogin_ScanFaceLoginSuccess", FaceLoginHelper.TAG, "NewLogin_FaceLogin");
            }
            SafetyManager.putBoolean(LoginConstans.FACELOGIN_EGG_SWITCH, true);
            SafetyManager.putString(LoginConstans.FACELOGIN_USERACCOUNT, UserUtil.getWJLoginHelper().getUserAccount());
            SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_FACE);
            SafetyManager.putString("loginType", "");
            LoginUserBase.saveInfoAfterLogin();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static void gotoRecFaceWithToken(final String str, final Activity activity, String str2, String str3, String str4, final String str5, final int i2, final OnCommonCallback onCommonCallback, final boolean z, final OnCommonCallback onCommonCallback2) {
        if (PermissionHelper.hasGrantedPermissions(activity, PermissionHelper.generateBundle(null, str2, str3, str4, new String[]{"\u6444\u50cf\u5934"}, new String[]{"\u4eac\u4e1c\u9700\u7533\u8bf7\u6444\u50cf\u5934\u62cd\u6444\u6743\u9650\u4ee5\u4fbf\u60a8\u80fd\u901a\u8fc7\u4eba\u8138\u8bc6\u522b\u5b9e\u73b0\u5237\u8138\u767b\u5f55"}, true), new String[]{"android.permission.CAMERA"}, true, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.login.FaceLoginHelper.2
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
                if (onCommonCallback != null) {
                    onCommonCallback.onError(new ErrorResult(4, "\u4e3a\u4fdd\u8bc1\u60a8\u6b63\u5e38\u5730\u4f7f\u7528\u6b64\u529f\u80fd\uff0c\u9700\u8981\u83b7\u53d6\u60a8\u7684\u76f8\u673a\u4f7f\u7528\u6743\u9650\uff0c\u8bf7\u5141\u8bb8", new Exception()));
                }
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
                if (onCommonCallback != null) {
                    onCommonCallback.onError(new ErrorResult(4, "\u4e3a\u4fdd\u8bc1\u60a8\u6b63\u5e38\u5730\u4f7f\u7528\u6b64\u529f\u80fd\uff0c\u9700\u8981\u83b7\u53d6\u60a8\u7684\u76f8\u673a\u4f7f\u7528\u6743\u9650\uff0c\u8bf7\u5141\u8bb8", new Exception()));
                }
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                FaceLoginHelper.jsonOpenSDK(activity, str, onCommonCallback, str5, i2, z, onCommonCallback2);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
            }
        })) {
            jsonOpenSDK(activity, str, onCommonCallback, str5, i2, z, onCommonCallback2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRiskCheck(FailResult failResult) {
        if (Log.D) {
            String str = "faceLogin jumpToMWithToken MSG=" + failResult.getMessage() + "CODE=" + ((int) failResult.getReplyCode());
            String str2 = "faceLogin jumpToMWithToken JumpResult url=" + failResult.getJumpResult().getUrl() + "CODE=" + failResult.getJumpResult().getToken();
        }
        String url = failResult.getJumpResult().getUrl();
        String token = failResult.getJumpResult().getToken();
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(token)) {
            showDialogToM(failResult.getMessage(), jumpFengkongM(failResult, url, token), "\u786e\u5b9a", "\u53d6\u6d88", "toSMS");
            return;
        }
        ToastUtil.showToast(failResult.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void intentWeb(String str) {
        DeepLinkMHelper.startWebActivity(mActivity, str);
    }

    public static boolean isShowEgg() {
        boolean z = (LoginUserBase.hasLogin() || TextUtils.isEmpty(UserUtil.getWJLoginHelper().getUserAccount())) ? false : true;
        boolean isOpenEgg = CCFLoginUtil.isOpenEgg();
        boolean z2 = SafetyManager.getBoolean(LoginConstans.FACELOGIN_EGG_SWITCH, false);
        if (OKLog.D) {
            OKLog.d(TAG, "isShowEgg FACELOGIN_EGG_SWITCH =" + z2);
            OKLog.d(TAG, "isShowEgg isExistUser = " + z);
            OKLog.d(TAG, "isShowEgg sdk isOpenEgg = " + isOpenEgg);
        }
        return isOpenEgg && z && isTheSameAccountWithOld() && z2;
    }

    public static boolean isShowFaceLogin(String str) {
        return CCFLoginUtil.isOpenFaceLogin() && DeeplinkFaceloginLivenessHelper.isAuraSuccess() && !TextUtils.isEmpty(str);
    }

    public static boolean isTheSameAccountWithOld() {
        String string = SafetyManager.getString(LoginConstans.FACELOGIN_USERACCOUNT, "");
        String userAccount = UserUtil.getWJLoginHelper().getUserAccount();
        if (OKLog.D) {
            OKLog.d(TAG, "isTheSameAccountWithOld FACELOGIN_USERACCOUNT =" + string);
            OKLog.d(TAG, "isTheSameAccountWithOld getUserAccount = " + userAccount);
        }
        return !TextUtils.isEmpty(userAccount) && userAccount.equals(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void jsonOpenSDK(Activity activity, final String str, final OnCommonCallback onCommonCallback, final String str2, final int i2, boolean z, final OnCommonCallback onCommonCallback2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("businessId", "JD-SHOP-APP-FACE-LOGIN");
            jSONObject2.put("appName", "JD_LOGIN_SERVER");
            jSONObject2.put("appAuthorityKey", "GaFJT2YpBjlJySCD/a5JvA==");
            jSONObject2.put("verifyToken", str);
            jSONObject.put("IdentityParams", jSONObject2);
            jSONObject.put("type", VerifyEngine.JDJR_WEB_JS_TYPE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        IdentityVerityEngine.getInstance().checkIdentityVerity(activity, null, jSONObject.toString(), new IdentityVerityCallback() { // from class: com.jingdong.common.login.FaceLoginHelper.5
            @Override // com.jdjr.risk.identity.verify.IdentityVerityCallback
            public void onVerifyResult(int i3, String str3, String str4, Bundle bundle, String str5) {
                Log.e("IdentityVerityCallback", str5);
                try {
                    IdentityVerityEngine.getInstance().release();
                } catch (Exception unused) {
                }
                if (i3 == 0) {
                    int i4 = i2;
                    if (i4 == 0) {
                        UserUtil.getWJLoginHelper().faceLoginV2(str2, str, onCommonCallback2);
                    } else if (i4 == 1) {
                        UserUtil.getWJLoginHelper().openFaceSwitchV2(str, OnCommonCallback.this);
                    } else if (i4 != 2) {
                    } else {
                        UserUtil.getWJLoginHelper().openSwitchAndFaceLogin(str2, str, onCommonCallback2);
                    }
                } else if (i3 == 1) {
                    ToastUtils.showToast("\u8bc6\u522b\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
                    if (OnCommonCallback.this != null) {
                        OnCommonCallback.this.onError(new ErrorResult(i3, "\u8bc6\u522b\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", new Exception()));
                    }
                } else if (i3 == 2) {
                    ToastUtils.showToast("\u7cfb\u7edf\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
                    if (OnCommonCallback.this != null) {
                        OnCommonCallback.this.onError(new ErrorResult(i3, "\u7cfb\u7edf\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", new Exception()));
                    }
                } else if (i3 == 3) {
                    if (OnCommonCallback.this != null) {
                        OnCommonCallback.this.onError(new ErrorResult(i3, "\u60a8\u5df2\u53d6\u6d88\u5237\u8138", new Exception()));
                    }
                } else if (i3 == 4) {
                    FaceLoginHelper.showDialogToM("\u4e3a\u4fdd\u8bc1\u60a8\u6b63\u5e38\u5730\u4f7f\u7528\u6b64\u529f\u80fd\uff0c\u9700\u8981\u83b7\u53d6\u60a8\u7684\u76f8\u673a\u4f7f\u7528\u6743\u9650\uff0c\u8bf7\u5141\u8bb8", "", "\u786e\u5b9a", "\u53d6\u6d88", FaceLoginHelper.GET_PERMISSON_JD_APP);
                    if (OnCommonCallback.this != null) {
                        OnCommonCallback.this.onError(new ErrorResult(i3, "\u4e3a\u4fdd\u8bc1\u60a8\u6b63\u5e38\u5730\u4f7f\u7528\u6b64\u529f\u80fd\uff0c\u9700\u8981\u83b7\u53d6\u60a8\u7684\u76f8\u673a\u4f7f\u7528\u6743\u9650\uff0c\u8bf7\u5141\u8bb8", new Exception()));
                    }
                } else if (i3 != 5) {
                    ToastUtils.showToast("\u7cfb\u7edf\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
                    if (OnCommonCallback.this != null) {
                        OnCommonCallback.this.onError(new ErrorResult(i3, "\u7cfb\u7edf\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", new Exception()));
                    }
                } else {
                    ToastUtils.showToast("\u7cfb\u7edf\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
                    if (OnCommonCallback.this != null) {
                        OnCommonCallback.this.onError(new ErrorResult(i3, "\u7cfb\u7edf\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", new Exception()));
                    }
                }
            }
        });
    }

    public static void jumpEgg(final BaseActivity baseActivity, final String str, final String str2, final String str3) {
        if (OKLog.D) {
            OKLog.e(TAG, "checkFaceLogin begin");
        }
        mActivity = baseActivity;
        if (CCFLoginUtil.isOpenFaceLogin()) {
            final String userAccount = UserUtil.getWJLoginHelper().getUserAccount();
            UserUtil.getWJLoginHelper().isOpenFaceLogin(userAccount, new OnDataCallback<CheckFaceLoginResp>() { // from class: com.jingdong.common.login.FaceLoginHelper.4
                @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
                public void onError(ErrorResult errorResult) {
                    if (OKLog.D) {
                        OKLog.e(FaceLoginHelper.TAG, "isOpenFaceLogin onError s=");
                    }
                    FaceLoginHelper.jumpToLogin(baseActivity, false);
                }

                @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
                public void onFail(FailResult failResult) {
                    if (OKLog.D) {
                        OKLog.e(FaceLoginHelper.TAG, "isOpenFaceLogin onFail " + failResult.getMessage());
                    }
                    FaceLoginHelper.jumpToLogin(baseActivity, true);
                }

                @Override // jd.wjlogin_sdk.common.listener.OnDataCallback
                public void onSuccess(CheckFaceLoginResp checkFaceLoginResp) {
                    if (checkFaceLoginResp == null || TextUtils.isEmpty(checkFaceLoginResp.getStatusJson())) {
                        FaceLoginHelper.jumpToLogin(baseActivity, true);
                        return;
                    }
                    try {
                        if (OKLog.D) {
                            OKLog.e(FaceLoginHelper.TAG, "isOpenFaceLogin onSuccess userAccount=" + userAccount);
                            OKLog.e(FaceLoginHelper.TAG, "isOpenFaceLogin onSuccess status=" + checkFaceLoginResp.getStatusJson());
                        }
                        JSONObject jSONObject = new JSONObject(checkFaceLoginResp.getStatusJson());
                        int optInt = jSONObject.optInt("switchStatus");
                        if (1 == jSONObject.optInt("realNameStatus") && 1 == optInt) {
                            SafetyManager.putBoolean(LoginConstans.FACELOGIN_EGG_SWITCH, true);
                            SafetyManager.putString(LoginConstans.FACELOGIN_USERACCOUNT, userAccount);
                            FaceLoginHelper.jumpFaceLogin(baseActivity, str, str2, str3, userAccount, 0, null, true, FaceLoginHelper.mFaceLoginCallbackInJDLib);
                            return;
                        }
                        SafetyManager.putBoolean(LoginConstans.FACELOGIN_EGG_SWITCH, false);
                        SafetyManager.putString(LoginConstans.FACELOGIN_USERACCOUNT, userAccount);
                        FaceLoginHelper.jumpToLogin(baseActivity, true);
                    } catch (Exception e2) {
                        FaceLoginHelper.jumpToLogin(baseActivity, true);
                        e2.printStackTrace();
                    }
                }
            });
            return;
        }
        jumpToLogin(baseActivity, true);
    }

    public static void jumpFaceLogin(Activity activity, String str, String str2, String str3, String str4, int i2, OnCommonCallback onCommonCallback) {
        mActivity = activity;
        jumpFaceLogin(activity, str, str2, str3, str4, i2, onCommonCallback, false, mFaceLoginCallbackInJDLib);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String jumpFengkongM(FailResult failResult, String str, String str2) {
        return String.format("%1$s?appid=%2$s&token=%3$s&returnurl=openApp.jdMobile://communication", str, Short.valueOf(UserUtil.getClientInfo().getDwAppID()), str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void jumpFindPwd(String str) {
        if (Log.D) {
            Log.e(TAG + " findPdUrl===", str);
        }
        String format = String.format("%1$s?appid=%2$s&show_title=%3$s&client_type=%4$s&os_version=%5$s&app_client_ver=%6$s&uuid=%7$s&account=%8$s&returnurl=%9$s", str, Short.valueOf(UserUtil.getClientInfo().getDwAppID()), "0", "android", Build.VERSION.RELEASE, PackageInfoUtil.getVersionName(), StatisticsReportUtil.readDeviceUUID(), UserUtil.getWJLoginHelper().getUserAccount(), LoginConstans.FROMREGIST);
        if (Log.D) {
            Log.e(TAG + " formatUrl===", format);
            Uri parse = Uri.parse(format);
            Log.e(TAG + " formatUrl.getScheme()===", parse.getScheme());
            Log.e(TAG + " formatUrl.getHost===", parse.getHost());
            Log.e(TAG + "formatUrl uri.getPath()===", parse.getPath());
            Log.e(TAG + "formmatUrl uri.getQueryParameter===", parse.getQueryParameter("appid"));
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", format);
        bundle.putBoolean("isRegist", true);
        DeepLinkMHelper.startWebActivity(mActivity, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void jumpToLogin(BaseActivity baseActivity, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(LoginConstans.KEY_TOLOGIN_FLAG, LoginConstans.BUS_FROMEGG);
        bundle.putBoolean(LoginConstans.KEY_FACELOGIN_SWITCH_CLOSE, z);
        DeepLinkLoginHelper.startLoginActivity(baseActivity, bundle, new ILogin() { // from class: com.jingdong.common.login.FaceLoginHelper.3
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
            }
        }, "eggJumpToLogin");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showDialogToM(final String str, final String str2, final String str3, final String str4, final String str5) {
        try {
            BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.login.FaceLoginHelper.9
                @Override // java.lang.Runnable
                public void run() {
                    final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(FaceLoginHelper.mActivity, str, str4, str3);
                    createJdDialogWithStyle2.setCancelable(false);
                    createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.login.FaceLoginHelper.9.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            createJdDialogWithStyle2.dismiss();
                        }
                    });
                    createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.login.FaceLoginHelper.9.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if ("toSMS".equals(str5)) {
                                if (!TextUtils.isEmpty(str2)) {
                                    FaceLoginHelper.intentWeb(str2);
                                }
                                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY_TEMP, LoginConstans.LOGIN_LAST_WAY_FACE_SENDMSG);
                                createJdDialogWithStyle2.dismiss();
                            } else if ("toFindPwd".equals(str5)) {
                                if (!TextUtils.isEmpty(str2)) {
                                    FaceLoginHelper.jumpFindPwd(str2);
                                }
                                createJdDialogWithStyle2.dismiss();
                            } else if (FaceLoginHelper.FORCE_UPGRADE_JD_APP.equals(str5)) {
                                FaceLoginHelper.intentWeb(str2);
                                createJdDialogWithStyle2.dismiss();
                            } else if (FaceLoginHelper.GET_PERMISSON_JD_APP.equals(str5)) {
                                PermissionHelper.hasGrantedPermissions(FaceLoginHelper.mActivity, PermissionHelper.generateBundle(null, "FaceLogin", FaceLoginHelper.TAG, "opencamera", new String[]{"\u6444\u50cf\u5934"}, new String[]{"\u4eac\u4e1c\u9700\u7533\u8bf7\u6444\u50cf\u5934\u62cd\u6444\u6743\u9650\u4ee5\u4fbf\u60a8\u80fd\u901a\u8fc7\u4eba\u8138\u8bc6\u522b\u5b9e\u73b0\u5237\u8138\u767b\u5f55"}, true), new String[]{"android.permission.CAMERA"}, true, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.login.FaceLoginHelper.9.2.1
                                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                                    public void onCanceled() {
                                    }

                                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                                    public void onDenied() {
                                    }

                                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                                    public void onGranted() {
                                    }

                                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                                    public void onIgnored() {
                                    }

                                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                                    public void onOpenSetting() {
                                    }
                                });
                                createJdDialogWithStyle2.dismiss();
                            }
                        }
                    });
                    createJdDialogWithStyle2.show();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showOneBtnDialog(final String str, final String str2, int i2) {
        try {
            if (mActivity == null) {
                return;
            }
            BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.login.FaceLoginHelper.8
                @Override // java.lang.Runnable
                public void run() {
                    final JDDialog createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1(FaceLoginHelper.mActivity, str, str2);
                    createJdDialogWithStyle1.setCancelable(false);
                    createJdDialogWithStyle1.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.login.FaceLoginHelper.8.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            createJdDialogWithStyle1.dismiss();
                        }
                    });
                    createJdDialogWithStyle1.show();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void jumpFaceLogin(final Activity activity, final String str, final String str2, final String str3, final String str4, final int i2, final OnCommonCallback onCommonCallback, final boolean z, final OnCommonCallback onCommonCallback2) {
        isFromMyJDCaidan = z;
        mActivity = activity;
        UserUtil.getWJLoginHelper().getFaceLoginTokenV2(str4, new OnDataCallback<SuccessResult>() { // from class: com.jingdong.common.login.FaceLoginHelper.1
            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onError(ErrorResult errorResult) {
                if (Log.D) {
                    String unused = FaceLoginHelper.TAG;
                    String str5 = "getFaceLoginTokenV2 onError MSG=" + errorResult;
                }
                String errorMsg = errorResult.getErrorMsg();
                if (TextUtils.isEmpty(errorMsg)) {
                    errorMsg = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86!";
                }
                ToastUtils.showToastInCenter(errorMsg);
            }

            @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
            public void onFail(FailResult failResult) {
                ToastUtils.showToast(failResult.getMessage());
            }

            @Override // jd.wjlogin_sdk.common.listener.OnDataCallback
            public void onSuccess(SuccessResult successResult) {
                if (successResult != null) {
                    FaceLoginHelper.gotoRecFaceWithToken(successResult.getStrVal(), activity, str, str2, str3, str4, i2, onCommonCallback, z, onCommonCallback2);
                }
            }
        });
    }
}
