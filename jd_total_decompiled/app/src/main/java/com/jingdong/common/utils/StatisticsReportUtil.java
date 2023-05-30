package com.jingdong.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.login.DeviceFinger;
import com.jingdong.common.login.SafetyManager;
import com.jingdong.common.network.ExtParamUtils;
import com.jingdong.common.network.encrypt.EncryptStatParamController;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.security.JDUUIDEncHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.language.LanguageUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import com.tencent.connect.common.Constants;
import com.tencent.smtt.sdk.ProxyConfig;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import jd.wjlogin_sdk.util.f;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class StatisticsReportUtil {
    public static final String DEVICE_INFO_UUID = "uuid";
    public static final String REPORT_PARAM_AID = "&aid=";
    public static final String REPORT_PARAM_AVIF_CONVERT = "&acs=";
    public static final String REPORT_PARAM_AVIF_SUPPORT = "&avifSupport=";
    public static final String REPORT_PARAM_EU = "&eu=";
    public static final String REPORT_PARAM_EXT = "&ext=";
    public static final String REPORT_PARAM_FV = "&fv=";
    public static final String REPORT_PARAM_HARMONYOS = "&harmonyOs=";
    public static final String REPORT_PARAM_LBS_AREA = "&area=";
    public static final String REPORT_PARAM_NETWORK_TYPE = "&networkType=";
    public static final String REPORT_PARAM_OPENUDID = "&openudid=";
    public static final String REPORT_PARAM_UEMPS = "&uemps=";
    public static final String REPORT_PARAM_UTS = "&uts=";
    public static final String REPORT_PARAM_UUID = "&uuid=";
    public static final String REPORT_PARAM_VT = "&vt=";
    public static final String REPORT_PARAM_WIFIBSSID = "&wifiBssid=";
    private static final String SHOPPING_CART_UUID = "shoppingCartUUID";
    private static final String TAG = "StatisticsReportUtil";
    private static String paramStr;
    private static String paramStrWithOutDeviceUUID;

    /* loaded from: classes.dex */
    public static class UserAgentResolver {
        private static volatile String cachedSafeUserAgent;
        private static volatile String cachedUserAgent;
        private static Object cachedUserAgentLock = new Object();
        private static Object cachedSafeUserAgentLock = new Object();

        public static String getSafeUserAgent() {
            try {
                if (TextUtils.isEmpty(cachedSafeUserAgent)) {
                    synchronized (cachedSafeUserAgentLock) {
                        if (TextUtils.isEmpty(cachedSafeUserAgent)) {
                            cachedSafeUserAgent = "okhttp/3.12.1;jdmall;android;version/" + BaseInfo.getAppVersionName() + ";build/" + String.valueOf(BaseInfo.getAppVersionCode()) + ";";
                        }
                    }
                }
                return cachedSafeUserAgent;
            } catch (Throwable unused) {
                return null;
            }
        }

        public static String getUserAgent() {
            try {
                if (TextUtils.isEmpty(cachedUserAgent)) {
                    synchronized (cachedUserAgentLock) {
                        if (TextUtils.isEmpty(cachedUserAgent)) {
                            cachedUserAgent = "okhttp/3.12.1;jdmall;android;version/" + BaseInfo.getAppVersionName() + ";build/" + String.valueOf(BaseInfo.getAppVersionCode()) + ";screen/" + BaseInfo.getDisplayMetrics().replace(ProxyConfig.MATCH_ALL_SCHEMES, JshopConst.JSHOP_PROMOTIO_X) + ";os/" + BaseInfo.getAndroidVersion() + ";";
                        }
                    }
                }
                return cachedUserAgent;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    @Deprecated
    public static String genarateDeviceUUID(Context context) {
        if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
            return readInstallationId();
        }
        return DeviceInfoHelper.genarateDeviceUUID(context);
    }

    public static String getBrand() {
        try {
            String splitSubString = splitSubString(BaseInfo.getDeviceManufacture(), 12);
            return !TextUtils.isEmpty(splitSubString) ? splitSubString.replaceAll(LangUtils.SINGLE_SPACE, "") : "";
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return "";
        }
    }

    public static String getDeviceInfoStr() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", readDeviceUUID());
            jSONObject.put(Constants.PARAM_PLATFORM, 100);
            jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, splitSubString(BaseInfo.getDeviceManufacture(), 12));
            jSONObject.put(CustomThemeConstance.NAVI_MODEL, splitSubString(BaseInfo.getDeviceModel(), 25));
            Display defaultDisplay = ((WindowManager) JdSdk.getInstance().getApplicationContext().getSystemService("window")).getDefaultDisplay();
            jSONObject.put("screen", defaultDisplay.getHeight() + ProxyConfig.MATCH_ALL_SCHEMES + defaultDisplay.getWidth());
            jSONObject.put(HybridSDK.APP_VERSION, PackageInfoUtil.getVersionName());
            jSONObject.put(HybridSDK.OS_VERSION, Build.VERSION.RELEASE);
            jSONObject.put(Configuration.PARTNER, Configuration.getProperty(Configuration.PARTNER, ""));
            jSONObject.put("nettype", getNetworkTypeName(JdSdk.getInstance().getApplicationContext()));
            if (OKLog.D) {
                jSONObject.put("versionCode", PackageInfoUtil.getVersionCode());
                OKLog.d("Temp", "getDeviceInfoStr() return -->> " + jSONObject.toString());
            }
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
        return jSONObject.toString();
    }

    private static Set<String> getFunIdList(String str) {
        HashSet hashSet = new HashSet();
        if (!TextUtils.isEmpty(str) && str.length() > 4) {
            try {
                String substring = str.substring(1, str.length() - 1);
                for (String str2 : substring.split(DYConstants.DY_REGEX_COMMA)) {
                    if (!TextUtils.isEmpty(substring) && substring.length() > 2) {
                        hashSet.add(str2.substring(1, str2.length() - 1));
                    }
                }
            } catch (Exception e2) {
                LogUtil.e("getConfigList::", LogUtil.extractThrowableInfo(e2));
            }
        }
        return hashSet;
    }

    public static String getModel() {
        try {
            String splitSubString = splitSubString(BaseInfo.getDeviceModel(), 25);
            return !TextUtils.isEmpty(splitSubString) ? splitSubString.replaceAll(LangUtils.SINGLE_SPACE, "") : "";
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return "";
        }
    }

    @Deprecated
    public static String getNetworkTypeName(Context context) {
        String str;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
            NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
            str = null;
            for (int i2 = 0; i2 < allNetworkInfo.length; i2++) {
                if (allNetworkInfo[i2].isConnected()) {
                    if (allNetworkInfo[i2].getTypeName().toUpperCase().contains("MOBILE")) {
                        str = BaseInfo.getNetworkTypeInt() + "";
                    } else {
                        str = allNetworkInfo[i2].getTypeName().toUpperCase().contains("WIFI") ? "WIFI" : "UNKNOWN";
                    }
                }
            }
        } catch (Throwable unused) {
            str = "UNKNOWN";
        }
        return str == null ? "UNKNOWN" : str;
    }

    private static String getParamStr() {
        if (!TextUtils.isEmpty(paramStr)) {
            if (OKLog.D) {
                OKLog.d("Temp", "getParamStr() -->> " + paramStr);
            }
            return paramStr;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getParamStrWithOutDeviceUUID());
        paramStr = stringBuffer.toString();
        if (OKLog.D) {
            OKLog.d("Temp", "getParamStr() create -->> " + paramStr);
        }
        return paramStr;
    }

    private static String getParamStrWithOutDeviceUUID() {
        if (!TextUtils.isEmpty(paramStrWithOutDeviceUUID)) {
            if (OKLog.D) {
                OKLog.d(TAG, "getParamStrWithOutDeviceUUID() -->> " + paramStrWithOutDeviceUUID);
            }
            return paramStrWithOutDeviceUUID;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(EncryptStatParamController.REPORT_PARAM_CLIENT_VERSION);
        stringBuffer.append(splitSubString(PackageInfoUtil.getVersionName(), 12));
        stringBuffer.append(EncryptStatParamController.REPORT_PARAM_BUILD);
        stringBuffer.append(String.valueOf(PackageInfoUtil.getVersionCode()));
        stringBuffer.append(EncryptStatParamController.REPORT_PARAM_CLIENT);
        stringBuffer.append(Configuration.getProperty("client", ""));
        try {
            String replaceAll = splitSubString(BaseInfo.getDeviceManufacture(), 12).replaceAll(LangUtils.SINGLE_SPACE, "");
            stringBuffer.append(EncryptStatParamController.REPORT_PARAM_D_BRAND);
            stringBuffer.append(replaceAll);
            String replaceAll2 = splitSubString(BaseInfo.getDeviceModel(), 25).replaceAll(LangUtils.SINGLE_SPACE, "");
            stringBuffer.append(EncryptStatParamController.REPORT_PARAM_D_MODEL);
            stringBuffer.append(replaceAll2);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        try {
            String splitSubString = splitSubString(Build.VERSION.RELEASE, 12);
            if (!TextUtils.isEmpty(splitSubString)) {
                stringBuffer.append(EncryptStatParamController.REPORT_PARAM_OS_VERSION);
                stringBuffer.append(splitSubString.replaceAll(LangUtils.SINGLE_SPACE, ""));
            }
        } catch (Exception e3) {
            OKLog.e(TAG, e3);
        }
        Display defaultDisplay = ((WindowManager) JdSdk.getInstance().getApplicationContext().getSystemService("window")).getDefaultDisplay();
        stringBuffer.append(EncryptStatParamController.REPORT_PARAM_SCREEN);
        stringBuffer.append(defaultDisplay.getHeight() + ProxyConfig.MATCH_ALL_SCHEMES + defaultDisplay.getWidth());
        stringBuffer.append(EncryptStatParamController.REPORT_PARAM_PARTNER);
        stringBuffer.append(Configuration.getProperty(Configuration.PARTNER, ""));
        stringBuffer.append(EncryptStatParamController.REPORT_PARAM_OAID);
        stringBuffer.append(DeviceInfoHelper.getOAID());
        stringBuffer.append(EncryptStatParamController.REPORT_PARAM_SDK_VERSION);
        stringBuffer.append(String.valueOf(Build.VERSION.SDK_INT));
        if (!TextUtils.isEmpty(LanguageUtil.getAppLanguageCode(JdSdk.getInstance().getApplicationContext()))) {
            stringBuffer.append(EncryptStatParamController.REPORT_PARAM_LANG);
            stringBuffer.append(LanguageUtil.getAppLanguageCode(JdSdk.getInstance().getApplicationContext()));
        }
        paramStrWithOutDeviceUUID = stringBuffer.toString();
        if (OKLog.D) {
            OKLog.d(TAG, "getParamStrWithOutDeviceUUID() create -->> " + paramStrWithOutDeviceUUID);
        }
        return paramStrWithOutDeviceUUID;
    }

    public static String getQueryParamsStr(boolean z, boolean z2, boolean z3, String str) {
        JDUUIDEncHelper.EncryptResult encrypt;
        String paramStr2 = getParamStr();
        if (z3) {
            String readDeviceUUID = readDeviceUUID();
            if (!TextUtils.equals(DeviceInfoHelper.getAid(), readDeviceUUID) && (encrypt = JDUUIDEncHelper.encrypt(readDeviceUUID)) != null) {
                if (!TextUtils.isEmpty(encrypt.eu)) {
                    paramStr2 = paramStr2 + REPORT_PARAM_EU + encrypt.eu;
                }
                if (!TextUtils.isEmpty(encrypt.fv)) {
                    paramStr2 = paramStr2 + REPORT_PARAM_FV + encrypt.fv;
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(paramStr2);
        String aid = !TextUtils.isEmpty(DeviceInfoHelper.getAid()) ? DeviceInfoHelper.getAid() : readInstallationId();
        if (!TextUtils.isEmpty(str)) {
            stringBuffer.append("&uuid=");
            stringBuffer.append(str);
            stringBuffer.append("&openudid=");
            stringBuffer.append(str);
        } else {
            stringBuffer.append("&uuid=");
            stringBuffer.append(aid);
            String openUDID = DeviceInfoHelper.getOpenUDID();
            if (!TextUtils.isEmpty(openUDID)) {
                stringBuffer.append("&openudid=");
                stringBuffer.append(openUDID);
            }
        }
        stringBuffer.append("&aid=");
        stringBuffer.append(aid);
        try {
            String urlEncodeUTF8 = urlEncodeUTF8(DeviceFinger.getFinger(JdSdk.getInstance().getApplicationContext()));
            if (!TextUtils.isEmpty(urlEncodeUTF8)) {
                stringBuffer.append(EncryptStatParamController.REPORT_PARAM_EID);
                stringBuffer.append(urlEncodeUTF8);
            }
        } catch (Throwable unused) {
        }
        if (z2) {
            String commonLbsParameter = LocManager.getCommonLbsParameter();
            if (!TextUtils.isEmpty(commonLbsParameter)) {
                String replace = commonLbsParameter.replace("-1", "0");
                stringBuffer.append("&area=");
                stringBuffer.append(replace);
            }
        }
        stringBuffer.append("&networkType=");
        stringBuffer.append(DeviceInfoHelper.getNetworkTypeAccordingPrivacy());
        stringBuffer.append("&wifiBssid=");
        stringBuffer.append(BssidFetcher.getInstance().getBssid());
        try {
            String uts = PersonalInfoManager.getInstance().getUts();
            if (!TextUtils.isEmpty(uts)) {
                stringBuffer.append("&uts=");
                stringBuffer.append(uts);
            }
        } catch (Throwable unused2) {
        }
        try {
            String uemps = JDElderModeUtils.getUemps();
            if (!TextUtils.isEmpty(uemps)) {
                stringBuffer.append("&uemps=");
                stringBuffer.append(uemps);
            }
        } catch (Throwable unused3) {
        }
        stringBuffer.append("&harmonyOs=");
        JdSdk.getInstance();
        stringBuffer.append(JdSdk.isHarmonyOS() ? "1" : "0");
        stringBuffer.append("&ext=");
        stringBuffer.append(ExtParamUtils.getExtParamsStr());
        String verifyToken = JDHttpTookit.getEngine().getGuardVerifyPlugin().getVerifyToken();
        if (!TextUtils.isEmpty(verifyToken)) {
            stringBuffer.append("&vt=");
            stringBuffer.append(verifyToken);
        }
        if (AvifImageUtils.avifEnable()) {
            stringBuffer.append("&avifSupport=");
            stringBuffer.append("1");
        }
        if (AvifImageUtils.avifBitmapConvertEnable()) {
            stringBuffer.append("&acs=");
            stringBuffer.append("1");
        }
        String stringBuffer2 = stringBuffer.toString();
        if (OKLog.D) {
            OKLog.d(TAG, "getReportString() -->> " + stringBuffer2);
        }
        return stringBuffer2;
    }

    public static String getReportStringWithEncryptUUID(String str, boolean z, boolean z2, String str2) {
        return getQueryParamsStr(z, z2, !TextUtils.isEmpty(str) && needEncrypt(str), str2);
    }

    public static Map<String, String> getUniformHeaderField(boolean z) {
        HashMap hashMap = new HashMap();
        if (z) {
            String cookies = SafetyManager.getCookies();
            if (!TextUtils.isEmpty(cookies)) {
                hashMap.put("Cookie", cookies);
            }
        }
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDRequestIdentifier", "switch", "netUASwitch"), "1")) {
            String userAgent = UserAgentResolver.getUserAgent();
            if (!TextUtils.isEmpty(userAgent)) {
                hashMap.put("User-Agent", userAgent);
            }
        }
        try {
            hashMap.put("X-Referer-Package", f.f19954c);
            if (ActivityManagerUtil.getScreenManager().currentActivity() != null) {
                hashMap.put("X-Referer-Page", ActivityManagerUtil.getScreenManager().currentActivity().getClass().getName());
            }
            if (!TextUtils.isEmpty(JDRiskHandleManager.getInstance().getRiskHandleVersion())) {
                hashMap.put("X-Rp-Client", JDRiskHandleManager.getInstance().getRiskHandleVersion());
            }
        } catch (Throwable unused) {
        }
        return hashMap;
    }

    public static boolean needEncrypt(String str) {
        if (JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_UUID_DECODE, ABTestUtils.KEY_CONFIG_ENCRYPT_ENABLE, "1").equals("1")) {
            Set<String> funIdList = getFunIdList(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_UUID_DECODE, ABTestUtils.KEY_CONFIG_FUNCTIONID_LIST, ""));
            if (funIdList != null && funIdList.size() > 0) {
                if (funIdList.contains(str)) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "needEncrypt() funcId=" + str + "\uff0c\u5728\u670d\u52a1\u4e0b\u53d1\u767d\u540d\u5355\u4e2d\uff0c\u589e\u52a0eu\u5b57\u6bb5\uff01\uff01\uff01");
                    }
                    return true;
                }
            } else if (NetworkFuncIdUtils.getInstance().getLocalFuncIdSet().contains(str)) {
                if (OKLog.D) {
                    OKLog.d(TAG, "needEncrypt() funcId=" + str + "\uff0c\u5728\u672c\u5730\u767d\u540d\u5355\u4e2d,\u589e\u52a0eu\u5b57\u6bb5\uff01\uff01\uff01");
                }
                return true;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "needEncrypt() funcId=" + str + "\uff0c\u4e0d\u5728\u767d\u540d\u5355\u4e2d\uff0c\u4e0d\u589e\u52a0eu\u5b57\u6bb5\uff01\uff01\uff01");
            }
            return false;
        }
        return false;
    }

    public static String readAndroidId() {
        return DeviceInfoHelper.getAid();
    }

    public static String readCartUUID() {
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getSharedPreferences();
        String string = sharedPreferences.getString(SHOPPING_CART_UUID, null);
        if (TextUtils.isEmpty(string)) {
            String str = "" + UUID.randomUUID();
            String config = JDMobileConfig.getInstance().getConfig("jdCart", "spSaveMode", "spSaveModeKey");
            if (!TextUtils.isEmpty(config) && config.equals("1")) {
                sharedPreferences.edit().putString(SHOPPING_CART_UUID, str).apply();
                return str;
            }
            sharedPreferences.edit().putString(SHOPPING_CART_UUID, str).commit();
            return str;
        }
        String readDeviceUUID = readDeviceUUID();
        if (TextUtils.isEmpty(readDeviceUUID)) {
            String str2 = "" + UUID.randomUUID();
            sharedPreferences.edit().putString(SHOPPING_CART_UUID, str2).apply();
            return str2;
        } else if (string.startsWith(readDeviceUUID)) {
            String replaceFirst = string.replaceFirst(readDeviceUUID, "");
            sharedPreferences.edit().putString(SHOPPING_CART_UUID, replaceFirst).apply();
            return replaceFirst;
        } else {
            return string;
        }
    }

    @Deprecated
    public static synchronized String readDeviceId() {
        synchronized (StatisticsReportUtil.class) {
            if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
                return DeviceInfoHelper.generateDeviceId(JdSdk.getInstance().getApplicationContext());
            }
            return "";
        }
    }

    public static synchronized String readDeviceUUID() {
        synchronized (StatisticsReportUtil.class) {
            if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
                return readInstallationId();
            }
            return DeviceInfoHelper.readDeviceUUID();
        }
    }

    @Deprecated
    public static synchronized JDUUIDEncHelper.EncryptResult readEncryptDeviceUUID() {
        synchronized (StatisticsReportUtil.class) {
            try {
                String readDeviceUUID = readDeviceUUID();
                if (!TextUtils.equals(DeviceInfoHelper.getAid(), readDeviceUUID)) {
                    return JDUUIDEncHelper.encrypt(readDeviceUUID);
                }
            } catch (Throwable unused) {
            }
            return null;
        }
    }

    public static String readInstallationId() {
        return DeviceInfoHelper.getInstallationId(JdSdk.getInstance().getApplicationContext());
    }

    public static String splitSubString(String str, int i2) {
        if (str != null) {
            try {
                return str.length() > i2 ? str.substring(0, i2) : str;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                    return str;
                }
                return str;
            }
        }
        return str;
    }

    private static String urlEncodeUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable unused) {
            return "";
        }
    }
}
