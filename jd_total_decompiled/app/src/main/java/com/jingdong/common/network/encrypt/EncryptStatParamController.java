package com.jingdong.common.network.encrypt;

import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.login.DeviceFinger;
import com.jingdong.common.network.ExtParamUtils;
import com.jingdong.common.utils.AvifImageUtils;
import com.jingdong.common.utils.BssidFetcher;
import com.jingdong.common.utils.DeviceInfoHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.language.LanguageUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class EncryptStatParamController {
    public static final String REPORT_PARAM_AID = "&aid=";
    public static final String REPORT_PARAM_AVIF_CONVERT = "&acs=";
    public static final String REPORT_PARAM_AVIF_SUPPORT = "&avifSupport=";
    public static final String REPORT_PARAM_ENCRYPT_FLAG = "&ef=";
    public static final String REPORT_PARAM_ENCRYPT_PARAM = "&ep=";
    public static final String REPORT_PARAM_EXT = "&ext=";
    public static final String REPORT_PARAM_HARMONYOS = "&harmonyOs=";
    public static final String REPORT_PARAM_LBS_AREA = "&area=";
    public static final String REPORT_PARAM_NETWORK_TYPE = "&networkType=";
    public static final String REPORT_PARAM_OPENUDID = "&openudid=";
    public static final String REPORT_PARAM_UEMPS = "&uemps=";
    public static final String REPORT_PARAM_UTS = "&uts=";
    public static final String REPORT_PARAM_UUID = "&uuid=";
    public static final String REPORT_PARAM_VT = "&vt=";
    public static final String REPORT_PARAM_WIFIBSSID = "&wifiBssid=";
    private static final String TAG = "EncryptStatParamController";
    public static HashMap<String, IParamProducer> sParamProducerMap;
    private static String sUnchangeableParamStr;
    public static final String REPORT_PARAM_CLIENT_VERSION = "&clientVersion=";
    public static final String REPORT_PARAM_BUILD = "&build=";
    public static final String REPORT_PARAM_CLIENT = "&client=";
    public static final String REPORT_PARAM_D_BRAND = "&d_brand=";
    public static final String REPORT_PARAM_D_MODEL = "&d_model=";
    public static final String REPORT_PARAM_OS_VERSION = "&osVersion=";
    public static final String REPORT_PARAM_SCREEN = "&screen=";
    public static final String REPORT_PARAM_PARTNER = "&partner=";
    public static final String REPORT_PARAM_OAID = "&oaid=";
    public static final String REPORT_PARAM_SDK_VERSION = "&sdkVersion=";
    public static final String REPORT_PARAM_LANG = "&lang=";
    public static final String[] UNCHANGEABLE_PARAM_LIST = {REPORT_PARAM_CLIENT_VERSION, REPORT_PARAM_BUILD, REPORT_PARAM_CLIENT, REPORT_PARAM_D_BRAND, REPORT_PARAM_D_MODEL, REPORT_PARAM_OS_VERSION, REPORT_PARAM_SCREEN, REPORT_PARAM_PARTNER, REPORT_PARAM_OAID, REPORT_PARAM_SDK_VERSION, REPORT_PARAM_LANG, "&harmonyOs="};
    public static final String REPORT_PARAM_EID = "&eid=";
    public static final String[] CHANGEABLE_PARAM_LIST = {"&area=", "&uuid=", "&aid=", "&openudid=", "&networkType=", "&wifiBssid=", "&uts=", "&uemps=", "&ext=", "&vt=", "&avifSupport=", "&acs=", REPORT_PARAM_EID};
    public static final Set<String> SENSITIVE_PARAMS_DEFAULT = new HashSet(Arrays.asList("&uuid=", "&aid=", "&openudid=", "&wifiBssid=", REPORT_PARAM_D_BRAND, REPORT_PARAM_D_MODEL, REPORT_PARAM_OS_VERSION, REPORT_PARAM_SCREEN));
    private static Map<String, String> sUnchangeableSensitiveMap = new ConcurrentHashMap();

    /* loaded from: classes5.dex */
    public interface IParamProducer {
        String produce();
    }

    static {
        HashMap<String, IParamProducer> hashMap = new HashMap<>();
        sParamProducerMap = hashMap;
        hashMap.put(REPORT_PARAM_CLIENT_VERSION, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.1
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return StatisticsReportUtil.splitSubString(PackageInfoUtil.getVersionName(), 12);
            }
        });
        sParamProducerMap.put(REPORT_PARAM_BUILD, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.2
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return String.valueOf(PackageInfoUtil.getVersionCode());
            }
        });
        sParamProducerMap.put(REPORT_PARAM_CLIENT, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.3
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return Configuration.getProperty("client", "");
            }
        });
        sParamProducerMap.put(REPORT_PARAM_D_BRAND, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.4
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return StatisticsReportUtil.splitSubString(BaseInfo.getDeviceManufacture(), 12).replaceAll(LangUtils.SINGLE_SPACE, "");
            }
        });
        sParamProducerMap.put(REPORT_PARAM_D_MODEL, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.5
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return StatisticsReportUtil.splitSubString(BaseInfo.getDeviceModel(), 25).replaceAll(LangUtils.SINGLE_SPACE, "");
            }
        });
        sParamProducerMap.put(REPORT_PARAM_OS_VERSION, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.6
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                String splitSubString = StatisticsReportUtil.splitSubString(Build.VERSION.RELEASE, 12);
                return !TextUtils.isEmpty(splitSubString) ? splitSubString.replaceAll(LangUtils.SINGLE_SPACE, "") : splitSubString;
            }
        });
        sParamProducerMap.put(REPORT_PARAM_SCREEN, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.7
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                Display defaultDisplay = ((WindowManager) JdSdk.getInstance().getApplicationContext().getSystemService("window")).getDefaultDisplay();
                return defaultDisplay.getHeight() + ProxyConfig.MATCH_ALL_SCHEMES + defaultDisplay.getWidth();
            }
        });
        sParamProducerMap.put(REPORT_PARAM_PARTNER, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.8
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return Configuration.getProperty(Configuration.PARTNER, "");
            }
        });
        sParamProducerMap.put(REPORT_PARAM_OAID, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.9
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return DeviceInfoHelper.getOAID();
            }
        });
        sParamProducerMap.put("&openudid=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.10
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return DeviceInfoHelper.getOpenUDID();
            }
        });
        sParamProducerMap.put(REPORT_PARAM_EID, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.11
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                try {
                    return URLEncoder.encode(DeviceFinger.getFinger(JdSdk.getInstance().getApplicationContext()), "UTF-8");
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                    return "";
                }
            }
        });
        sParamProducerMap.put(REPORT_PARAM_SDK_VERSION, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.12
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return String.valueOf(Build.VERSION.SDK_INT);
            }
        });
        sParamProducerMap.put(REPORT_PARAM_LANG, new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.13
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return LanguageUtil.getAppLanguageCode(JdSdk.getInstance().getApplicationContext());
            }
        });
        sParamProducerMap.put("&uuid=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.14
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return StatisticsReportUtil.readDeviceUUID();
            }
        });
        sParamProducerMap.put("&aid=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.15
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return DeviceInfoHelper.getAid();
            }
        });
        sParamProducerMap.put("&area=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.16
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                String commonLbsParameter = LocManager.getCommonLbsParameter();
                if (TextUtils.isEmpty(commonLbsParameter)) {
                    return null;
                }
                return commonLbsParameter.replace("-1", "0");
            }
        });
        sParamProducerMap.put("&networkType=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.17
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return DeviceInfoHelper.getNetworkTypeAccordingPrivacy();
            }
        });
        sParamProducerMap.put("&wifiBssid=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.18
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return BssidFetcher.getInstance().getBssid();
            }
        });
        sParamProducerMap.put("&uts=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.19
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return PersonalInfoManager.getInstance().getUts();
            }
        });
        sParamProducerMap.put("&uemps=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.20
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return JDElderModeUtils.getUemps();
            }
        });
        sParamProducerMap.put("&harmonyOs=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.21
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                JdSdk.getInstance();
                return JdSdk.isHarmonyOS() ? "1" : "0";
            }
        });
        sParamProducerMap.put("&ext=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.22
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return ExtParamUtils.getExtParamsStr();
            }
        });
        sParamProducerMap.put("&vt=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.23
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return JDHttpTookit.getEngine().getGuardVerifyPlugin().getVerifyToken();
            }
        });
        sParamProducerMap.put("&avifSupport=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.24
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return AvifImageUtils.avifEnable() ? "1" : "";
            }
        });
        sParamProducerMap.put("&acs=", new IParamProducer() { // from class: com.jingdong.common.network.encrypt.EncryptStatParamController.25
            @Override // com.jingdong.common.network.encrypt.EncryptStatParamController.IParamProducer
            public String produce() {
                return AvifImageUtils.avifBitmapConvertEnable() ? "1" : "";
            }
        });
    }

    private static String getChangeableParamStr(Map<String, String> map, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : CHANGEABLE_PARAM_LIST) {
            if (z || !TextUtils.equals("&area=", str)) {
                String safelyGet = safelyGet(str);
                if (getSensitiveParams().contains(str)) {
                    if (!TextUtils.isEmpty(safelyGet)) {
                        String substring = str.substring(1, str.length() - 1);
                        if (map != null) {
                            map.put(substring, safelyGet);
                        }
                    }
                } else if (!TextUtils.isEmpty(safelyGet)) {
                    stringBuffer.append(str);
                    stringBuffer.append(safelyGet);
                }
            }
        }
        return stringBuffer.toString();
    }

    public static Map<String, String> getColorQueryParams(boolean z, boolean z2, boolean z3, Map<String, String> map, String str) {
        HashMap hashMap = new HashMap();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        HashMap hashMap2 = new HashMap();
        String staticParamStr = getStaticParamStr();
        stringBuffer.append(staticParamStr);
        stringBuffer2.append(staticParamStr);
        hashMap2.putAll(sUnchangeableSensitiveMap);
        String changeableParamStr = getChangeableParamStr(hashMap2, z2);
        stringBuffer.append(changeableParamStr);
        stringBuffer2.append(changeableParamStr);
        if (!TextUtils.isEmpty(str)) {
            hashMap2.put("uuid", str);
            hashMap2.put("openudid", str);
        }
        if (z3) {
            HashMap hashMap3 = new HashMap();
            hashMap3.putAll(hashMap2);
            if (map != null && !map.isEmpty()) {
                hashMap3.putAll(map);
            }
            stringBuffer.append(REPORT_PARAM_ENCRYPT_FLAG);
            stringBuffer.append("1");
            stringBuffer.append(REPORT_PARAM_ENCRYPT_PARAM);
            stringBuffer.append(EncryptTool.encryptAndEncode(hashMap3));
            hashMap.put(IStatInfoConfig.KEY_ENCRYPT, stringBuffer.toString());
        }
        if (!hashMap2.isEmpty()) {
            stringBuffer2.append(urlEncodeUTF8(hashMap2));
        }
        hashMap.put(IStatInfoConfig.KEY_CLEARTEXT, stringBuffer2.toString());
        if (OKLog.D) {
            OKLog.d(TAG, "getColorQueryParams() -->> " + hashMap);
        }
        return hashMap;
    }

    public static String getQueryParamsStr(boolean z, boolean z2, Map<String, String> map, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        HashMap hashMap = new HashMap();
        stringBuffer.append(getStaticParamStr());
        hashMap.putAll(sUnchangeableSensitiveMap);
        stringBuffer.append(getChangeableParamStr(hashMap, z2));
        if (map != null && !map.isEmpty()) {
            hashMap.putAll(map);
        }
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("uuid", str);
            hashMap.put("openudid", str);
        }
        stringBuffer.append(REPORT_PARAM_ENCRYPT_FLAG);
        stringBuffer.append("1");
        stringBuffer.append(REPORT_PARAM_ENCRYPT_PARAM);
        stringBuffer.append(EncryptTool.encryptAndEncode(hashMap));
        String stringBuffer2 = stringBuffer.toString();
        if (OKLog.D) {
            OKLog.d(TAG, "getQueryParamsStr() -->> " + stringBuffer2);
        }
        return stringBuffer2;
    }

    public static Set<String> getSensitiveParams() {
        String[] split;
        HashSet hashSet = new HashSet();
        String config = JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "network", "sensitiveParams");
        if (!TextUtils.isEmpty(config) && (split = config.split(DYConstants.DY_REGEX_COMMA)) != null && split.length > 0) {
            for (String str : split) {
                hashSet.add(ContainerUtils.FIELD_DELIMITER + str + ContainerUtils.KEY_VALUE_DELIMITER);
            }
        }
        hashSet.addAll(SENSITIVE_PARAMS_DEFAULT);
        return hashSet;
    }

    private static String getStaticParamStr() {
        if (!TextUtils.isEmpty(sUnchangeableParamStr)) {
            if (OKLog.D) {
                OKLog.d(TAG, "getStaticParamStr() -->> " + sUnchangeableParamStr);
            }
            return sUnchangeableParamStr;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : UNCHANGEABLE_PARAM_LIST) {
            String safelyGet = safelyGet(str);
            if (getSensitiveParams().contains(str)) {
                if (!TextUtils.isEmpty(safelyGet)) {
                    sUnchangeableSensitiveMap.put(str.substring(1, str.length() - 1), safelyGet);
                }
            } else if (!TextUtils.isEmpty(safelyGet)) {
                stringBuffer.append(str);
                stringBuffer.append(safelyGet);
            }
        }
        sUnchangeableParamStr = stringBuffer.toString();
        if (OKLog.D) {
            OKLog.d(TAG, "getStaticParamStr() create -->> " + sUnchangeableParamStr);
        }
        return sUnchangeableParamStr;
    }

    public static String safelyGet(String str) {
        try {
            if (sParamProducerMap.containsKey(str)) {
                return sParamProducerMap.get(str).produce();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String urlEncodeUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String urlEncodeUTF8(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append(String.format("%s=%s", entry.getKey(), urlEncodeUTF8(entry.getValue())));
        }
        return sb.toString();
    }
}
