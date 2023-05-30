package com.jingdong.common.login;

import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CCFLoginUtil {
    private static final String CHINAMOBILE_LOGINSWITCH_FLAG = "implictLoginSwitch";
    private static final String CHINATELECOM_LOGINSWITCH_FLAG = "chinaTelecomLoginSwitch";
    private static final String CHINATELECOM_UNICOM_FLAG = "chinaUnicomSwitch";
    private static final String EGG_FLAG = "eggSwitch";
    private static final String FACELOFIN_FLAG = "facelogin";
    private static final String FIND_PWD_URL = "newfindpwd";
    private static final String NATIVE_BMODE_FLAG = "nativeRefreshModeSwitch";
    private static final String NATIVE_BUSINESS_REGIST_FLAG = "nativeBusinessRegist";
    private static final String POPUPLOGIN_FLAG = "popUpLogin";
    private static final String POPUPLOGIN_ONEKEY_FLAG = "popUpOneClick";
    private static final String ROUTER_BMODE_FLAG = "routerRefreshModeSwitch";
    private static final String SHOW_ACTIVE_LOGO = "showActiveLogo";
    private static final String TAG = "WJLogin.ConfigUtilInLib";
    private static final String TELECOM_LOGIN_SWITCH = "telecomSwitch";

    public static String getFindPwdUrl() {
        String str;
        str = "";
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            str = config != null ? config.optString("newfindpwd") : "";
            if (Log.D) {
                Log.i(TAG, "find password url = " + str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    public static boolean isOpenChinaMobileLoginSwitch() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(CHINAMOBILE_LOGINSWITCH_FLAG, 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "chineMobileLoginSwitch = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenChinaTelecomLoginSwitch() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(CHINATELECOM_LOGINSWITCH_FLAG, 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "isOpenChinaTelecomLoginSwitch = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenEgg() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(EGG_FLAG, 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "openEgg = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenFaceLogin() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt("facelogin", 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "openFaceLogin = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenNativeBModel() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(NATIVE_BMODE_FLAG, 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "isOpenNativeBModel = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenNativeBusinessRegist() {
        boolean z = true;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(NATIVE_BUSINESS_REGIST_FLAG, 1) != 1) {
                z = false;
            }
            if (Log.D) {
                Log.i(TAG, "isOpenNativeBusinessRegist = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenPopUpLogin() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(POPUPLOGIN_FLAG, 1) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "isOpenPopUpLogin = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenPopUpOneClick() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(POPUPLOGIN_ONEKEY_FLAG, 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "isOpenPopUpOneClick = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenRouterBModel() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(ROUTER_BMODE_FLAG, 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "isOpenRouterBModel = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenTelecomLoginSwitch() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(TELECOM_LOGIN_SWITCH, 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "chinaTelecomLoginSwitch = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean isOpenUnicom() {
        boolean z = false;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(CHINATELECOM_UNICOM_FLAG, 0) == 1) {
                z = true;
            }
            if (Log.D) {
                Log.i(TAG, "isOpenUnicom = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public static boolean showActiveLogo() {
        boolean z = true;
        try {
            JSONObject config = UserUtil.getWJLoginHelper().getConfig();
            if (config != null && config.optInt(SHOW_ACTIVE_LOGO, 1) != 1) {
                z = false;
            }
            if (Log.D) {
                Log.i(TAG, "showActiveLogo = " + z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }
}
