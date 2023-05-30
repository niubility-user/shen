package com.jingdong.common.runTimeConfig;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jingdong.common.utils.CommonBase;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class ConfigUtil {
    public static final String ANDROID_ACTIVITY_ANIM = "androidActivityAnim";
    public static final String AURA_BUNDLE_CONTROL = "auraCtrl";
    public static final String CLIENT_CONFIG_TAG = "_clientConfig";
    public static final int CONFIG_10_USE_OLD_HTTP = 10;
    public static final int CONFIG_11_MARKET_PRICE_FLAG = 11;
    public static final int CONFIG_12_EASY_BUY_CONFIRM = 12;
    public static final int CONFIG_13_EASY_BUY_SWITCH = 13;
    public static final int CONFIG_14_PAISWITCH = 14;
    public static final int CONFIG_15_CTRL_DNS = 15;
    public static final int CONFIG_16_CTRL_AURA = 16;
    public static final int CONFIG_17_USE_FACEBOOK_FRESCO = 17;
    public static final int CONFIG_18_LOGSYS_MAIN_SWITCH = 18;
    public static final int CONFIG_19_NEWFEATURE_WIZARD = 19;
    public static final int CONFIG_1_SHOW_REGISTER_LINK = 1;
    public static final int CONFIG_21_HARDWAREACCELERATE_SWITCH = 21;
    public static final String CONFIG_2_SHOW_FACELOGINSWITCH_LINK = "2";
    public static final int CONFIG_2_SHOW_FIND_PASSWORD = 2;
    public static final String CONFIG_3_SHOW_FACELOGINSWITCH_LINK = "3";
    public static final int CONFIG_3_SHOW_WEIXIN_LOGIN = 3;
    public static final int CONFIG_4_SHOW_QQ_LOGIN = 4;
    public static final int CONFIG_5_SHOW_OTHER_LOGIN = 5;
    public static final int CONFIG_6_SHOW_VOICE_SERVICE = 6;
    public static final int CONFIG_7_CTRL_ATCTIVITY_NUM = 7;
    public static final int CONFIG_8_CTRL_LOADING_TOUCHEVENT = 8;
    public static final String EXIT_TYPE = "exitType";
    public static final String FACELOGINSWITCH = "FaceLoginSwitch";
    public static final String FIND_PWD_NEW = "newfindpwd";
    public static final String FIND_PWD_NEW_DEFAULT_URL = "https://plogin.m.jd.com/cgi-bin/m/mfindpwd";
    public static final String HELP_URL = "helpurl";
    public static final String KEY_800_ABTEST = "800UIStyle";
    public static final String KEY_ADDRESS_LIST_AB = "addressListAB";
    public static final String KEY_BUILD_IN_IP_DEGRADE = "buildInIpDegrade";
    public static final String KEY_COUPON_ACTIVITY_URL = "couponActivityUrl";
    public static final String KEY_COUPON_TAB_VALUE = "couponTabValue";
    public static final String KEY_DISPLAY_COUPON_MARKET = "displayCouponMarket";
    public static final String KEY_DISPLAY_CUTOUT_PHONE_MODEL = "displayCutout";
    public static final String KEY_HARDWARE_ACCELERATE = "hardwareAccelerate";
    public static final String KEY_HTTP2_PING_CONFIG = "http2pingConfig";
    public static final String KEY_HTTP2_PING_CONFIG_ENABLE = "enable";
    public static final String KEY_HTTP2_PING_CONFIG_INTERVAL = "interval";
    public static final String KEY_LOTTIE_LOADING_ENABLE = "lottieLoadingEnable";
    public static final String KEY_LOTTIE_SWITCHER = "lottieSwitcher";
    public static final String KEY_NAVI_ANIME = "naviAnime";
    public static final String KEY_NEEDCHECKHTTPSCA_FLAG = "needCheckHttpsCA";
    public static final String KEY_NETWORK_KEEPALIVE_SWITCH = "keepAliveSwitch";
    public static final String KEY_NFC_CONFIG = "nfcConfig";
    public static final String KEY_OKHTTP_FLAG = "okhttpFlag";
    public static final String KEY_RELINKER_SWITCH = "relinkerSwitch";
    public static final String KEY_REPORT_FLOAT_DATA_FLAG = "reportFloatData";
    public static final String KEY_REPORT_START_UP_FLAG = "reportStartUpData";
    public static final String KEY_SERVER_CONFIG = "ServerConfig";
    public static final String KEY_TRANSITION_ANIM_SWITCH = "TransitionAnimSwitch";
    public static final String KEY_UNITED_JUMP_DEGRADE_SWITCH = "isUnitedJumpDegrade";
    public static final String KEY_UN_STATUS_BAR_TINT = "statusBarTint";
    public static final String KEY_UPGRADE_BY_WIFI = "upgradeByWifi";
    public static final String KEY_USE_NEW_MYJDPAGE = "useNewMyJdPage";
    public static final String KEY_VOICE_SDK_CONFIG = "VoiceSdkConfig";
    public static final String KV_CONFIG_SAVED_TIMESTAMP = "kvConfigTimestamp";
    public static final String MAX_IMAGEJ_SIZE = "maxImageSize";
    public static final String MAX_IMAGE_RESOLUTION = "maxImageResolution";
    public static final String NEW_CONTENT = "newContent";
    public static final String NEW_ICON = "newIcon";
    public static final String NO_BARCODE_MODELS = "noBarcodeModels";
    private static final char POWER_SWITCH = '1';
    public static final String REGITER_AGREEMENT_URL = "regiterAgreementUrl";
    public static final String REGITER_AGREEMENT_URL_DEFAULT = "https://in.m.jd.com/help/app/register_info.html";
    public static final String REGITER_POLICY_URL = "registerPolicyUrl";
    public static final String REGITER_POLICY_URL_DEFAULT = "https://in.m.jd.com/help/app/private_policy.html";
    public static final String REMIMDER_TIME = "remindertime";
    public static final String SERVER_CONFIG_CRASH_LIMIT = "crashLimit";
    public static final String SERVER_CONFIG_SAVED_TIMESTAMP = "serverConfigTimestamp";
    public static final String SERVER_CONFIG_TAG = "_serverConfig";
    public static final String SHARED_CACHE_TIME = "shared_cache_time_clientConfig";
    public static final String SHARED_CONTROL_ACTIVITY = "shared_control_activity_clientConfig";
    public static final String SHARED_DATA_VERSION = "shared_data_version_clientConfig";
    public static final String SHARED_MAX_STACK_NUM = "shared_max_stack_clientConfig";
    public static final String SHARED_NOT_STACK_ACTIVITY = "shared_not_statck_activity_clientConfig";
    private static final String SHARED_PERFERENCES_ANDROID_CONFIG = "androidConfig";
    private static final String TAG = "ConfigUtil";
    public static final ConcurrentHashMap<String, String> sServerConfigHashMap = new ConcurrentHashMap<>();

    public static void change(int i2, String str) {
        SharedPreferences jdSharedPreferences;
        if (i2 >= 1 && (jdSharedPreferences = CommonBase.getJdSharedPreferences()) != null) {
            String string = jdSharedPreferences.getString(SHARED_PERFERENCES_ANDROID_CONFIG, "");
            if (TextUtils.isEmpty(string) || i2 > string.length()) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer(string);
            stringBuffer.replace(i2 - 1, i2, str);
            String stringBuffer2 = stringBuffer.toString();
            SharedPreferences.Editor edit = jdSharedPreferences.edit();
            edit.putString(SHARED_PERFERENCES_ANDROID_CONFIG, stringBuffer2);
            edit.commit();
        }
    }

    public static boolean get(int i2) {
        return get(i2, false);
    }

    public static String getAndroidConfig() {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        if (jdSharedPreferences == null) {
            return null;
        }
        String string = jdSharedPreferences.getString(SHARED_PERFERENCES_ANDROID_CONFIG, "");
        return TextUtils.isEmpty(string) ? jdSharedPreferences.getString("androidConfig_serverConfig", "") : string;
    }

    public static int getIntFromPreference(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        return jdSharedPreferences != null ? jdSharedPreferences.getInt(str, i2) : i2;
    }

    public static boolean getKeySwitchState(String str) {
        return "1".equals(getStringFromPreference(str));
    }

    public static boolean getKeySwitchState2(String str) {
        String stringFromPreference = getStringFromPreference(str);
        if (TextUtils.isEmpty(stringFromPreference)) {
            return true;
        }
        return TextUtils.equals("1", stringFromPreference);
    }

    public static long getLongFromPreference(String str) {
        SharedPreferences jdSharedPreferences;
        if (TextUtils.isEmpty(str) || (jdSharedPreferences = CommonBase.getJdSharedPreferences()) == null) {
            return 0L;
        }
        return jdSharedPreferences.getLong(str, 0L);
    }

    public static String getStringFromPreference(String str) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        ConcurrentHashMap<String, String> concurrentHashMap = sServerConfigHashMap;
        if (concurrentHashMap.containsKey(str)) {
            return concurrentHashMap.get(str);
        }
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        if (jdSharedPreferences != null) {
            str2 = jdSharedPreferences.getString(str, "");
            if (!TextUtils.isEmpty(str2)) {
                concurrentHashMap.put(str, str2);
            }
        }
        return str2;
    }

    public static boolean get(int i2, boolean z) {
        if (i2 < 1) {
            return false;
        }
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        if (jdSharedPreferences != null) {
            String string = jdSharedPreferences.getString(SHARED_PERFERENCES_ANDROID_CONFIG, "");
            if (TextUtils.isEmpty(string)) {
                string = jdSharedPreferences.getString("androidConfig_serverConfig", "");
            }
            return (TextUtils.isEmpty(string) || i2 > string.length()) ? z : '1' == string.charAt(i2 - 1);
        }
        return z;
    }

    public static long getLongFromPreference(String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        return jdSharedPreferences != null ? jdSharedPreferences.getLong(str, j2) : j2;
    }

    public static String getStringFromPreference(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String stringFromPreference = getStringFromPreference(str);
        return !TextUtils.isEmpty(stringFromPreference) ? stringFromPreference : str2;
    }
}
