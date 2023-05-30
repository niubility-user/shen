package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.login.BusinessRegistObserverManager;
import com.jingdong.common.login.CCFLoginUtil;
import com.jingdong.common.login.IBusinessRegist;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.IRegist;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginObserverManager;
import com.jingdong.common.login.LoginParamsManager;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.MobileLoginUtil;
import com.jingdong.common.login.RegistObserverManager;
import com.jingdong.common.utils.JMAUtils;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.wjlogin.onekey.sdk.util.MobileDeviceUtil;
import jd.wjlogin_sdk.model.CheckFaceLoginResp;
import jd.wjlogin_sdk.model.FaceLoginSwitch;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DeepLinkLoginHelper {
    private static final String TAG = "WJLoginDeepLinkLoginHelper";
    private static long requestTime = System.currentTimeMillis();
    private static LoginParamsManager sLoginParamsManager;

    private static void checkActive(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString(LoginConstans.RESOURCE_URL_KEY);
            if (OKLog.D) {
                OKLog.d(TAG, "checkActive reourceUrl=" + string);
            }
            if (!TextUtils.isEmpty(string) && isEnterLoginActivity() && OKLog.D) {
                OKLog.d(TAG, "checkActive return");
            }
        }
    }

    private static void closeRes() {
        LoginParamsManager loginParamsManager = sLoginParamsManager;
        if (loginParamsManager != null) {
            loginParamsManager.closeRes();
            sLoginParamsManager = null;
        }
    }

    private static void getSecurityPhone() {
        try {
            if (Log.D) {
                Log.d(TAG, "getSecurityPhone ");
            }
            boolean canGoToTelecom = MobileLoginUtil.canGoToTelecom();
            boolean z = CCFLoginUtil.isOpenChinaMobileLoginSwitch() && "CM".equals(MobileDeviceUtil.getOperateType(JdSdk.getInstance().getApplication()));
            boolean canGoToUnicom = MobileLoginUtil.canGoToUnicom();
            if (Log.D) {
                Log.d(TAG, " telecomLoginSwitch=" + canGoToTelecom + "openChinaMobileLoginSwitch=" + z + "cuLoginSwitch =" + canGoToUnicom);
            }
            if (!z && !canGoToTelecom && !canGoToUnicom) {
                toLogin(null);
                return;
            }
            if (Log.D) {
                Log.d(TAG, " lastSecurityPhone= " + MobileLoginUtil.getUnicomSecurityPhone());
            }
            if (MobileLoginUtil.checkPreGetMobile()) {
                toChinaMobileLogin(LoginConstans.ONEKEY_LOGIN_PHONENUMBER, LoginConstans.ONEKEY_LOGIN_OPERATETYPE, LoginConstans.ONEKEY_LOGIN_ACCESSCODE);
            } else {
                toLogin(null);
            }
        } catch (Exception unused) {
            if (Log.D) {
                Log.d(TAG, "getSecurityPhone Exception");
            }
        }
    }

    private static void getTelecomAccessToken() {
        try {
            if (Log.D) {
                Log.d(TAG, "getTelecomAccessToken ");
            }
            if (MobileLoginUtil.canGoToTelecom()) {
                if (Log.D) {
                    Log.d(TAG, "getTelecomAccessToken lastSecurityPhone= " + LoginConstans.TELECOM_LOGIN_PHONENUMBER + " lastOperaterType = " + LoginConstans.TELECOM_LOGIN_OPERATETYPE);
                }
                if (!TextUtils.isEmpty(LoginConstans.TELECOM_LOGIN_PHONENUMBER) && !TextUtils.isEmpty(LoginConstans.TELECOM_LOGIN_OPERATETYPE)) {
                    toChinaMobileLogin(LoginConstans.TELECOM_LOGIN_PHONENUMBER, LoginConstans.TELECOM_LOGIN_OPERATETYPE, "");
                    return;
                } else {
                    toLogin(null);
                    return;
                }
            }
            toLogin(null);
        } catch (Exception unused) {
        }
    }

    private static void initAuthn() {
        Bundle bundle;
        try {
            LoginParamsManager loginParamsManager = sLoginParamsManager;
            if (loginParamsManager != null && (bundle = loginParamsManager.getBundle()) != null) {
                String string = bundle.getString(LoginConstans.PARAM_DATA_KEY);
                if (!TextUtils.isEmpty(string)) {
                    LoginConstans.PARAM_DATA_VALUE = string;
                } else {
                    LoginConstans.PARAM_DATA_VALUE = null;
                }
                if (LoginConstans.BUS_FROMEGG.equals(bundle.getString(LoginConstans.KEY_BUS_FLAG))) {
                    toLogin(null);
                    return;
                }
            }
            getSecurityPhone();
        } catch (Exception unused) {
            if (Log.D) {
                Log.d(TAG, "initAuthn Exception ");
            }
        }
    }

    private static boolean isEnterLoginActivity() {
        return UserUtil.getWJLoginHelper().isEnterLogined();
    }

    public static void startBusinessRegisterActivity(Context context, Bundle bundle, IBusinessRegist iBusinessRegist, String str) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Exception unused) {
                return;
            }
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("busregisteractivity").toString(), bundle);
        BusinessRegistObserverManager.getInstance().registerRegistListener(iBusinessRegist, str);
    }

    public static void startLoginActivity(Context context, Bundle bundle) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity1");
            }
            checkActive(bundle);
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i2 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startLoginActivity getBModel=" + i2);
                }
                LoginConstans.FROM_BMODE = i2;
            }
            startLoginActivity(context, bundle, null, "");
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity1 Exception");
            }
        }
    }

    public static void startLoginActivityForResult(Activity activity, Bundle bundle, int i2) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity4");
            }
            if (LoginUserBase.hasLogin()) {
                return;
            }
            checkActive(bundle);
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i3 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startLoginActivity4 getBModel=" + i3);
                }
                LoginConstans.FROM_BMODE = i3;
                if (!TextUtils.isEmpty(bundle.getString("login_tag_familyNumber", ""))) {
                    String str = DeepLinkCommonHelper.HOST_LOGININ;
                    if (JDElderModeUtils.isElderMode()) {
                        str = "elderloginin";
                    }
                    DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle, i2);
                    return;
                }
            }
            sLoginParamsManager = new LoginParamsManager(activity, bundle, i2, 2);
            initAuthn();
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity4 Exception");
            }
        }
    }

    public static void startRegisterActivity(Context context, Bundle bundle) {
        try {
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i2 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startRegisterActivity getBModel=" + i2);
                }
                LoginConstans.FROM_BMODE = i2;
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("isDirectToRegister", 1);
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("registeractivity").toString(), bundle);
        } catch (Exception unused) {
        }
    }

    public static void startRegisterActivityForResult(Activity activity, Bundle bundle, IRegist iRegist, String str, int i2, int i3) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, " startRegisterActivityForResult");
            }
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i4 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startRegisterActivityForResult getBModel=" + i4);
                }
                LoginConstans.FROM_BMODE = i4;
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            Bundle bundle2 = bundle;
            bundle2.putInt("isDirectToRegister", 1);
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host("registeractivity").toString(), bundle2, i3, (Bundle) null, i2);
            RegistObserverManager.getInstance().registerRegistListener(iRegist, str);
        } catch (Exception unused) {
        }
    }

    public static void startScanCodeLoginForPush(Context context, Bundle bundle, int i2) {
        if (bundle != null) {
            try {
                JSONObject jSONObject = new JSONObject(bundle.getString("key"));
                String optString = jSONObject.optString("qrCodeKey");
                if (!TextUtils.equals("qrPhoneLogin", jSONObject.optString("action")) || TextUtils.isEmpty(optString)) {
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("key", optString);
                bundle2.putBoolean("fromPush", true);
                if (OKLog.I) {
                    OKLog.i("DeepLinkLoginHelper", " startScanLoginActivity codeKey = " + optString);
                }
                DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_SCAN_CODE_LOGIN_ACTIVITY).toString(), bundle2, i2);
            } catch (Exception unused) {
            }
        }
    }

    public static void startScanLoginActivity(Context context, Bundle bundle) {
        try {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_SCAN_CODE_LOGIN_ACTIVITY).toString(), bundle);
        } catch (Exception unused) {
        }
    }

    public static void startScanLoginActivityForResult(Activity activity, Bundle bundle, int i2) {
        try {
            DeepLinkCommonHelper.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_SCAN_CODE_LOGIN_ACTIVITY).toString(), bundle, i2);
        } catch (Exception unused) {
        }
    }

    private static void toChinaMobileLogin(String str, String str2, String str3) {
        try {
            LoginParamsManager loginParamsManager = sLoginParamsManager;
            if (loginParamsManager != null) {
                Bundle bundle = loginParamsManager.getBundle();
                int loginType = sLoginParamsManager.getLoginType();
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString("securityPhone", str);
                bundle.putString("operatorType", str2);
                bundle.putString("accessCode", str3);
                if (Log.D) {
                    Log.d(TAG, "ChinaMobile securityPhone" + str);
                }
                if (loginType == 0) {
                    DeepLinkDispatch.startActivityDirect(sLoginParamsManager.getContext(), new DeepLinkUri.Builder().scheme("jingdong").host("ChinaMobileLoginActivity").toString(), bundle);
                    LoginObserverManager.getInstance().registerLoginListener(sLoginParamsManager.getLogin(), sLoginParamsManager.getCalltag());
                } else if (loginType == 1) {
                    DeepLinkDispatch.startActivityDirect(sLoginParamsManager.getContext(), new DeepLinkUri.Builder().scheme("jingdong").host("ChinaMobileLoginActivity").toString(), bundle, sLoginParamsManager.getIntentFlag());
                    LoginObserverManager.getInstance().registerLoginListener(sLoginParamsManager.getLogin(), sLoginParamsManager.getCalltag());
                } else if (loginType == 2) {
                    DeepLinkDispatch.startActivityForResult(sLoginParamsManager.getActivity(), new DeepLinkUri.Builder().scheme("jingdong").host("ChinaMobileLoginActivity").toString(), bundle, sLoginParamsManager.getRequestCode());
                }
            }
            closeRes();
            JMAUtils.JMAReportForScene("basicShoppingProcess", "03b05847ea061aff507a5eedda9b5e29");
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d(TAG, "toChinaMobileLogin Exception");
            }
        }
    }

    private static void toLogin(String str, CheckFaceLoginResp checkFaceLoginResp) {
        if (checkFaceLoginResp != null) {
            try {
                String url = checkFaceLoginResp.getUrl();
                FaceLoginSwitch faceLoginSwitch = checkFaceLoginResp.getFaceLoginSwitch();
                short specialSwitch = faceLoginSwitch != null ? faceLoginSwitch.getSpecialSwitch() : (short) 0;
                LoginParamsManager loginParamsManager = sLoginParamsManager;
                if (loginParamsManager != null) {
                    Bundle bundle = loginParamsManager.getBundle();
                    if (bundle == null) {
                        bundle = new Bundle();
                    }
                    bundle.putString(LoginConstans.FACELOGINRESP_URL, url);
                    bundle.putShort(LoginConstans.FACELOGIN_SPECIAL_SWITCH, specialSwitch);
                }
            } catch (Exception unused) {
                if (OKLog.D) {
                    OKLog.d(TAG, "toLogin Exception");
                    return;
                }
                return;
            }
        }
        toLogin(str);
    }

    public static void startScanLoginActivity(final Context context, final Bundle bundle, final int i2) {
        try {
            if (!LoginUserBase.hasLogin()) {
                startLoginActivity(context, null, new ILogin() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper.1
                    @Override // com.jingdong.common.login.ILogin
                    public void onSuccess(String str) {
                        if (TextUtils.equals("startScanLoginActivityForPush", str)) {
                            DeepLinkLoginHelper.startScanCodeLoginForPush(context, bundle, i2);
                        }
                    }
                }, "startScanLoginActivityForPush");
            } else {
                startScanCodeLoginForPush(context, bundle, i2);
            }
        } catch (Exception unused) {
        }
    }

    public static void startRegisterActivity(Context context, Bundle bundle, int i2) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, " startRegisterActivity1");
            }
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i3 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startRegisterActivity1 getBModel=" + i3);
                }
                LoginConstans.FROM_BMODE = i3;
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("isDirectToRegister", 1);
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("registeractivity").toString(), bundle, i2);
        } catch (Exception unused) {
        }
    }

    private static void toLogin(String str) {
        try {
            LoginParamsManager loginParamsManager = sLoginParamsManager;
            if (loginParamsManager != null) {
                int loginType = loginParamsManager.getLoginType();
                Bundle bundle = sLoginParamsManager.getBundle();
                if (!TextUtils.isEmpty(str)) {
                    if (bundle == null) {
                        bundle = new Bundle();
                    }
                    bundle.putString(LoginConstans.JUMP_DES, str);
                }
                String str2 = DeepLinkCommonHelper.HOST_LOGININ;
                if (JDElderModeUtils.isElderMode()) {
                    str2 = "elderloginin";
                }
                if (loginType == 0) {
                    DeepLinkDispatch.startActivityDirect(sLoginParamsManager.getContext(), new DeepLinkUri.Builder().scheme("jingdong").host(str2).toString(), bundle);
                    LoginObserverManager.getInstance().registerLoginListener(sLoginParamsManager.getLogin(), sLoginParamsManager.getCalltag());
                } else if (loginType == 1) {
                    DeepLinkDispatch.startActivityDirect(sLoginParamsManager.getContext(), new DeepLinkUri.Builder().scheme("jingdong").host(str2).toString(), bundle, sLoginParamsManager.getIntentFlag());
                    LoginObserverManager.getInstance().registerLoginListener(sLoginParamsManager.getLogin(), sLoginParamsManager.getCalltag());
                } else if (loginType == 2) {
                    DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(str2);
                    StringBuilder sb = new StringBuilder();
                    sb.append(sLoginParamsManager.getRequestCode());
                    bundle.putString(LoginConstans.JUMP_REQUEST, sb.toString());
                    DeepLinkDispatch.startActivityForResult(sLoginParamsManager.getActivity(), host.toString(), bundle, sLoginParamsManager.getRequestCode());
                }
            }
            closeRes();
            JMAUtils.JMAReportForScene("basicShoppingProcess", "03b05847ea061aff507a5eedda9b5e29");
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d(TAG, "toLogin Exception");
            }
        }
    }

    public static void startLoginActivity(Context context, Bundle bundle, ILogin iLogin, String str) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity2");
            }
            if (LoginUserBase.hasLogin()) {
                if (iLogin != null) {
                    iLogin.onSuccess(str);
                    return;
                }
                return;
            }
            checkActive(bundle);
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i2 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startLoginActivity2 getBModel=" + i2);
                }
                LoginConstans.FROM_BMODE = i2;
                String string = bundle.getString("login_tag_familyNumber", "");
                String string2 = bundle.getString(LoginConstans.DIALOGLOGIN_2_ACCOUNT, "");
                if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2)) {
                    String str2 = DeepLinkCommonHelper.HOST_LOGININ;
                    if (JDElderModeUtils.isElderMode()) {
                        str2 = "elderloginin";
                    }
                    DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(str2).toString(), bundle);
                    LoginObserverManager.getInstance().registerLoginListener(iLogin, str);
                    return;
                }
            }
            sLoginParamsManager = new LoginParamsManager(context, bundle, iLogin, str, 0);
            initAuthn();
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity2 Exception");
            }
        }
    }

    public static void startRegisterActivityForResult(Activity activity, Bundle bundle, int i2) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, " startRegisterActivityForResult2");
            }
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i3 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startRegisterActivityForResult2 getBModel=" + i3);
                }
                LoginConstans.FROM_BMODE = i3;
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("isDirectToRegister", 1);
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host("registeractivity").toString(), bundle, i2);
            RegistObserverManager.getInstance().registerRegistListener(null, "");
        } catch (Exception unused) {
        }
    }

    public static void startLoginActivityForResult(Activity activity, Bundle bundle, int i2, int i3) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivityForResult");
            }
            if (LoginUserBase.hasLogin()) {
                return;
            }
            checkActive(bundle);
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i4 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startLoginActivityForResult getBModel=" + i4);
                }
                LoginConstans.FROM_BMODE = i4;
                if (!TextUtils.isEmpty(bundle.getString("login_tag_familyNumber", ""))) {
                    String str = DeepLinkCommonHelper.HOST_LOGININ;
                    if (JDElderModeUtils.isElderMode()) {
                        str = "elderloginin";
                    }
                    DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle, i3, (Bundle) null, i2);
                    return;
                }
            }
            sLoginParamsManager = new LoginParamsManager(activity, bundle, i3, 2);
            initAuthn();
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivityForResult Exception");
            }
        }
    }

    public static void startRegisterActivity(Context context, Bundle bundle, IRegist iRegist, String str) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, " startRegisterActivity2");
            }
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i2 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startRegisterActivity2 getBModel=" + i2);
                }
                LoginConstans.FROM_BMODE = i2;
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("isDirectToRegister", 1);
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("registeractivity").toString(), bundle);
            RegistObserverManager.getInstance().registerRegistListener(iRegist, str);
        } catch (Exception unused) {
        }
    }

    public static void startLoginActivity(Context context, Bundle bundle, ILogin iLogin, String str, int i2) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity3");
            }
            if (LoginUserBase.hasLogin()) {
                if (iLogin != null) {
                    iLogin.onSuccess(str);
                    return;
                }
                return;
            }
            checkActive(bundle);
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i3 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startLoginActivity3 getBModel=" + i3);
                }
                LoginConstans.FROM_BMODE = i3;
                if (!TextUtils.isEmpty(bundle.getString("login_tag_familyNumber", ""))) {
                    String str2 = DeepLinkCommonHelper.HOST_LOGININ;
                    if (JDElderModeUtils.isElderMode()) {
                        str2 = "elderloginin";
                    }
                    DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(str2).toString(), bundle);
                    LoginObserverManager.getInstance().registerLoginListener(iLogin, str);
                    return;
                }
            }
            sLoginParamsManager = new LoginParamsManager(context, bundle, iLogin, str, i2, 1);
            initAuthn();
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity3 Exception");
            }
        }
    }

    public static void startRegisterActivity(Context context, Bundle bundle, IRegist iRegist, String str, int i2) {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, " startRegisterActivity3");
            }
            LoginConstans.FROM_BMODE = 0;
            LoginConstans.FROM_ROUTER_BMODE = 0;
            if (bundle != null) {
                int i3 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d(TAG, "startRegisterActivity3 getBModel=" + i3);
                }
                LoginConstans.FROM_BMODE = i3;
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("isDirectToRegister", 1);
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("registeractivity").toString(), bundle, i2);
            RegistObserverManager.getInstance().registerRegistListener(iRegist, str);
        } catch (Exception unused) {
        }
    }
}
