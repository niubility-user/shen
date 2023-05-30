package com.jingdong.common.web;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;
import com.jd.hybrid.downloader.j;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.BaseInfoAdapter;
import com.jd.libs.hybrid.adapter.ColorHttpAdapter;
import com.jd.libs.hybrid.adapter.DownloadAdapter;
import com.jd.libs.hybrid.adapter.RequestAdapter;
import com.jd.libs.hybrid.base.BaseGraySwitch;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.PerfMtaUtils;
import com.jd.libs.hybrid.datasnapshot.DataSnapshotSDK;
import com.jd.libs.hybrid.offlineload.temp.BuildInDisable;
import com.jd.libs.hybrid.offlineload.temp.CommonResEngine;
import com.jd.libs.hybrid.offlineload.temp.DownloadFileDisable;
import com.jd.libs.hybrid.offlineload.temp.OfflineSwitchSetting;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.offlineload.utils.JDCacheSwitch;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import com.jd.libs.hybrid.preload.CustomParamProvider;
import com.jd.libs.hybrid.preload.ExtraParamsGetter;
import com.jd.libs.xconsole.XLog;
import com.jd.libs.xwin.http.BreakPointHelper;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.xweb.util.LibraryEngine;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes.dex */
public class WebHybridUtils {
    public static final String HDB_SESSION;
    public static boolean autoHybridInViewGlobal = false;
    private static Method canUseDarkMode = null;
    private static Method getJdUaMethod = null;
    private static Method getLoadUrlIgnoreGentokenMethod = null;
    public static boolean grayLoadBuildInMore = false;
    private static String httpDnsHost = null;
    public static boolean hybridEnable = false;
    private static boolean isXStorageInit = false;
    public static boolean needSetDnsHost = false;
    private static JDLocationCacheOption option = null;
    public static boolean userAppNetAdatper = true;
    private static Class webUtilsClazz;
    private static Class webViewHelper;

    static {
        hybridEnable = !SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_HYBRID_DISABLE, false) && Build.VERSION.SDK_INT >= 23;
        grayLoadBuildInMore = SwitchQueryFetcher.getSwitchBooleanValue("x_lbm", true);
        autoHybridInViewGlobal = false;
        HDB_SESSION = String.valueOf(SystemClock.elapsedRealtime());
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        option = jDLocationCacheOption;
        httpDnsHost = null;
        needSetDnsHost = false;
        jDLocationCacheOption.setBusinessId(WebConstants.LBS_ID);
        option = WebUtils.getLBSOptionWithBaseSceneId(option);
        isXStorageInit = false;
    }

    public static void changeBySwitch() {
        JDCacheSwitch.saveJdCacheSwitch(SwitchQueryFetcher.getSwitchBooleanValue("hybridUseJDCache", false));
        XRender.getInstance().updateModeAndType(SwitchQueryFetcher.getSwitchStringValue("xrender_ab", null));
        grayLoadBuildInMore = SwitchQueryFetcher.getSwitchBooleanValue("x_lbm", true);
        HybridSDK.updateSettings(HybridSDK.SWITCH_UPDATE_A, SwitchQueryFetcher.getSwitchBooleanValue(HybridSDK.SWITCH_UPDATE_A, false) ? "1" : "0");
        GraySwitch.fixBuildInPatch = SwitchQueryFetcher.getSwitchBooleanValue("hybridBuildInPatch", true);
        HybridSDK.updateSettings(HybridSDK.SWITCH_CLEAR_BUILD_IN, SwitchQueryFetcher.getSwitchBooleanValue("h_use_buildin", true) ? "0" : "1");
        BreakPointHelper.breakPointSwitch = SwitchQueryFetcher.getSwitchBooleanValue("breakPoint", false);
        GraySwitch.zipNewNoUse = SwitchQueryFetcher.getSwitchBooleanValue("hybridZipNewNoUse", false);
        GraySwitch.useZipInputStream = SwitchQueryFetcher.getSwitchBooleanValue("useZipInputStream", false);
        GraySwitch.fileForPreloadHtml = SwitchQueryFetcher.getSwitchBooleanValue("h_filePreHtml", false);
        GraySwitch.oldPreloadOffline = SwitchQueryFetcher.getSwitchBooleanValue("isOldPreloadOffline", false);
        BaseGraySwitch.offlineDownloadNoCheckValidate = SwitchQueryFetcher.getSwitchBooleanValue("isCheckValidated", false);
        BaseGraySwitch.loadHtmlUsePlanB = SwitchQueryFetcher.getSwitchBooleanValue("hybridTTTHtmlPlanB", false);
        BaseGraySwitch.loadHtmlUseDefaultParam = SwitchQueryFetcher.getSwitchBooleanValue("loadHtmlUseDefaultParam", false);
        BaseGraySwitch.isHybridNewPreloadOn = SwitchQueryFetcher.getSwitchBooleanValue("isHybridNewPreloadOn", false);
        BaseGraySwitch.bugfix12dd3bOn = SwitchQueryFetcher.getSwitchBooleanValue("bugfix12dd3bOn", false);
        GraySwitch.TYPE_4_GET_CONFIG_ASYNC = SwitchQueryFetcher.getSwitchBooleanValue("hybridConfigAsync", false);
        changeLatestVersionMap(SwitchQueryFetcher.getSwitchStringValue("hybrid_item_config_android", null));
        HybridSDK.enableBuildInOffline(!SwitchQueryFetcher.getSwitchBooleanValue("innerHybridOff", false));
        BuildInDisable.buildInTargetDisable = SwitchQueryFetcher.getSwitchBooleanValue("x_f", false);
        OfflineSwitchSetting.TYPE_4_OFF = SwitchQueryFetcher.getSwitchBooleanValue("hybridType4Off", false);
        OfflineSwitchSetting.TYPE_4_PIC_COMPRESS_OFF = SwitchQueryFetcher.getSwitchBooleanValue("hybridType4PicOff", false);
        if (!isXTime()) {
            DownloadFileDisable.offlineDownloadDisable = false;
            DownloadFileDisable.commonDownloadDisable = false;
            return;
        }
        int switchIntValue = SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.HYBRID_DOWNLOAD_FILE, 0);
        if (switchIntValue == 0) {
            DownloadFileDisable.offlineDownloadDisable = false;
            DownloadFileDisable.commonDownloadDisable = false;
        } else if (switchIntValue == 1) {
            DownloadFileDisable.offlineDownloadDisable = false;
            DownloadFileDisable.commonDownloadDisable = true;
        } else if (switchIntValue == 2) {
            DownloadFileDisable.offlineDownloadDisable = true;
            DownloadFileDisable.commonDownloadDisable = true;
        }
    }

    private static void changeLatestVersionMap(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    JSONObject jSONObject = new JSONObject();
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        if (jSONObject2 != null) {
                            String optString = jSONObject2.optString("appId");
                            int optInt = jSONObject2.optInt(JDReactConstant.ModuleCode, -1);
                            if (!TextUtils.isEmpty(optString) && optInt > -1) {
                                jSONObject.put(optString, optInt);
                            }
                        }
                    }
                    if (jSONObject.length() > 0) {
                        HybridSDK.updateSettings("latestVersionMap", jSONObject.toString());
                        return;
                    }
                }
            } catch (JSONException e2) {
                ExceptionReporter.reportWebViewCommonError("changeLatestVersionMapError", "", "Error in changeLatestVersionMap", e2.getMessage());
                if (OKLog.E) {
                    OKLog.e("JDHybrid", "changeLatestVersionMap Error", e2);
                }
            }
        }
        HybridSDK.updateSettings("latestVersionMap", null);
    }

    public static boolean degradeOfflineFromQuery(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return degradeOfflineFromQuery(Uri.parse(str));
    }

    public static String getArea() {
        String commonLbsParameter = LocManager.getCommonLbsParameter();
        return commonLbsParameter == null ? "" : commonLbsParameter;
    }

    private static boolean getDarkModeForUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (webUtilsClazz == null) {
                webUtilsClazz = Class.forName("com.jingdong.common.web.util.WebUtils");
            }
            if (canUseDarkMode == null) {
                canUseDarkMode = webUtilsClazz.getMethod("canUseDarkMode", String.class);
            }
            return ((Boolean) canUseDarkMode.invoke(null, str)).booleanValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("JDHybrid", e2);
            }
            return false;
        }
    }

    public static String getJdUserAgent(String str) {
        try {
            String string = SharedPreferencesUtil.getString(MBaseKeyNames.KEY_PRELOAD_ORI_UA, "");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            if (webViewHelper == null) {
                webViewHelper = Class.forName("com.jingdong.common.utils.WebViewHelper");
            }
            if (getJdUaMethod == null) {
                getJdUaMethod = webViewHelper.getMethod("getJdUa", Boolean.TYPE);
            }
            StringBuffer stringBuffer = (StringBuffer) getJdUaMethod.invoke(null, Boolean.valueOf(isEncryptUaBlackUrl(str)));
            if (stringBuffer != null && !TextUtils.isEmpty(stringBuffer.toString())) {
                if (TextUtils.isEmpty(str)) {
                    return stringBuffer.toString() + string;
                }
                boolean z = getDarkModeForUrl(str) && DeepDarkChangeManager.getInstance().getUIMode() == 1;
                StringBuilder sb = new StringBuilder();
                sb.append(stringBuffer.toString());
                sb.append("jdSupportDarkMode/");
                sb.append(z ? 1 : 0);
                sb.append(";");
                sb.append(string);
                return sb.toString();
            }
            return null;
        } catch (Exception e2) {
            ExceptionReporter.reportWebViewCommonError("hybridGetUaError", "", "Error in getJdUserAgent", e2.getMessage());
            OKLog.e("JDHybrid", "getJdUserAgent Error", e2);
            return null;
        }
    }

    public static String getLat() {
        JDLocation location = JDLocationCache.getInstance().getLocation(option);
        if (location == null) {
            return "0.0";
        }
        return "" + location.getLat();
    }

    public static String getLng() {
        JDLocation location = JDLocationCache.getInstance().getLocation(option);
        if (location == null) {
            return "0.0";
        }
        return "" + location.getLng();
    }

    public static String getMergedUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            if (webUtilsClazz == null) {
                webUtilsClazz = Class.forName("com.jingdong.common.web.util.WebUtils");
            }
            if (getLoadUrlIgnoreGentokenMethod == null) {
                getLoadUrlIgnoreGentokenMethod = webUtilsClazz.getMethod("getLoadUrlIgnoreGentoken", String.class);
            }
            return (String) getLoadUrlIgnoreGentokenMethod.invoke(null, str);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("JDHybrid", e2);
                return "";
            }
            return "";
        }
    }

    public static boolean hostList(String str, String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                if (str != null && !TextUtils.isEmpty(str2) && str.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hostListWithKeyWord(String str, String str2, String[] strArr) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            for (String str3 : strArr) {
                String[] split = str3.split(DYConstants.DY_REGEX_COMMA);
                if (split.length == 1 && !TextUtils.isEmpty(split[0])) {
                    if (str2.contains(split[0])) {
                        return true;
                    }
                } else if (split.length == 2 && !TextUtils.isEmpty(split[0]) && str2.contains(split[0]) && str.contains(split[1])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void initHybrid(boolean z) {
        if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
            if (OKLog.D) {
                OKLog.d("JDHybrid", "JDHybrid is disable, because privacy agreement is not accepted yet.");
            }
            HybridSDK.setDebug(z);
            return;
        }
        HybridSDK.registerAdapter(DownloadAdapter.NAME, DownloadAdapterImpl.class);
        HybridSDK.registerAdapter(RequestAdapter.NAME, RequestAdapterImpl.class);
        if (!hybridEnable) {
            if (OKLog.D) {
                OKLog.d("JDHybrid", "JDHybrid is disable by switch.");
            }
            if (Build.VERSION.SDK_INT < 23) {
                HashMap hashMap = new HashMap();
                hashMap.put(HybridSDK.SWITCH_XCACHE, SwitchQueryFetcher.getSwitchBooleanValue("h_xcache", false) ? "1" : "0");
                hashMap.put(HybridBase.SWITCH_DOWNLOAD_ADAPTER, SwitchQueryFetcher.getSwitchBooleanValue(HybridBase.SWITCH_DOWNLOAD_ADAPTER, false) ? "1" : "0");
                HybridSDK.initSettings(hashMap);
                com.jd.libs.x5.hybrid.HybridSDK.initSDK(JdSdk.getInstance().getApplication(), "d8c21d54-8f01-462c-8878-989f89b74b2a", z);
                SwitchQueryFetcher.getFetcher().addFetchListener(new SwitchQueryFetcher.FetchListener() { // from class: com.jingdong.common.web.WebHybridUtils.1
                    @Override // com.jingdong.common.utils.SwitchQueryFetcher.FetchListener
                    public void onFetchEnd(boolean z2) {
                        HybridSDK.updateSettings(HybridSDK.SWITCH_XCACHE_RETRYDOMAIN, SwitchQueryFetcher.getSwitchStringValue(HybridSDK.SWITCH_XCACHE_RETRYDOMAIN, ""));
                        HybridSDK.updateSettings(HybridSDK.SWITCH_XCACHE, SwitchQueryFetcher.getSwitchBooleanValue("h_xcache", false) ? "1" : "0");
                        HybridSDK.updateSettings(HybridBase.SWITCH_DOWNLOAD_ADAPTER, SwitchQueryFetcher.getSwitchBooleanValue(HybridBase.SWITCH_DOWNLOAD_ADAPTER, false) ? "1" : "0");
                        j.l().o("switchQuery", SwitchQueryFetcher.getSwitchStringValue("XCacheConfig", ""));
                        WebHybridUtils.setWebViewDnsHost();
                    }
                });
                return;
            }
            return;
        }
        if (!SwitchQueryFetcher.getSwitchBooleanValue("soloader", false)) {
            LibraryEngine.registerLibraryLoader(SoLoaderAdapter.class);
            XLog.d("\u4f7f\u7528facebook \u5e93\u52a0\u8f7d\u5dee\u5206\u5305 so");
        }
        HybridSDK.setGatewaySettings(new HybridSettings.Net.SimpleGatewaySettings() { // from class: com.jingdong.common.web.WebHybridUtils.2
            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.SimpleGatewaySettings, com.jd.libs.hybrid.base.HybridSettings.Net.GatewaySettings
            public int getEnvType() {
                return ("beta-api.m.jd.com".equals(Configuration.getPortalHost()) || "api.m.jd.care".equals(Configuration.getPortalHost())) ? 4097 : 4098;
            }
        });
        WebPreLoadHelper.setPreloadExtraParamsGetter(new ExtraParamsGetter() { // from class: com.jingdong.common.web.WebHybridUtils.3
            @Override // com.jd.libs.hybrid.preload.ExtraParamsGetter
            public String getValueByKey(String str) {
                if ("lat".equals(str) || HybridSDK.LNG.equals(str)) {
                    JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                    jDLocationCacheOption.setBusinessId(WebConstants.LBS_ID);
                    JDLocation location = JDLocationCache.getInstance().getLocation(WebUtils.getLBSOptionWithBaseSceneId(jDLocationCacheOption));
                    if (location != null) {
                        if ("lat".equals(str)) {
                            return String.valueOf(location.getLat());
                        }
                        if (HybridSDK.LNG.equals(str)) {
                            return String.valueOf(location.getLng());
                        }
                        return null;
                    }
                    return null;
                }
                return null;
            }
        });
        WebPreLoadHelper.setPreloadCustomParamsGetter(new CustomParamProvider.ParamGetter() { // from class: com.jingdong.common.web.WebHybridUtils.4
            @Override // com.jd.libs.hybrid.preload.CustomParamProvider.ParamGetter, com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
            public String getAppVer() {
                return PackageInfoUtil.getVersionName();
            }

            @Override // com.jd.libs.hybrid.preload.CustomParamProvider.ParamGetter, com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
            public String getArea() {
                String commonLbsParameter = LocManager.getCommonLbsParameter();
                return (TextUtils.isEmpty(commonLbsParameter) || "0_0_0_0".equals(commonLbsParameter)) ? "2_2824_51918_0" : commonLbsParameter;
            }

            @Override // com.jd.libs.hybrid.preload.CustomParamProvider.ParamGetter, com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
            public String getBuildNumber() {
                return String.valueOf(PackageInfoUtil.getVersionCode());
            }

            @Override // com.jd.libs.hybrid.preload.CustomParamProvider.ParamGetter, com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
            public String getDeviceId() {
                return BaseInfo.getAndroidId();
            }

            @Override // com.jd.libs.hybrid.preload.CustomParamProvider.ParamGetter, com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
            public String getLat() {
                JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                jDLocationCacheOption.setBusinessId(WebConstants.LBS_ID);
                JDLocation location = JDLocationCache.getInstance().getLocation(WebUtils.getLBSOptionWithBaseSceneId(jDLocationCacheOption));
                return location != null ? String.valueOf(location.getLat()) : "";
            }

            @Override // com.jd.libs.hybrid.preload.CustomParamProvider.ParamGetter, com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
            public String getLng() {
                JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                jDLocationCacheOption.setBusinessId(WebConstants.LBS_ID);
                JDLocation location = JDLocationCache.getInstance().getLocation(WebUtils.getLBSOptionWithBaseSceneId(jDLocationCacheOption));
                return location != null ? String.valueOf(location.getLng()) : "";
            }

            @Override // com.jd.libs.hybrid.preload.CustomParamProvider.ParamGetter, com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
            public String getOsVer() {
                return Build.VERSION.RELEASE;
            }
        });
        HashMap hashMap2 = new HashMap();
        hashMap2.put(HybridSDK.SWITCH_XCACHE, SwitchQueryFetcher.getSwitchBooleanValue("h_xcache", false) ? "1" : "0");
        hashMap2.put(HybridSDK.SWITCH_IO_DETAIL, SwitchQueryFetcher.getSwitchBooleanValue(HybridSDK.SWITCH_IO_DETAIL, false) ? "1" : "0");
        hashMap2.put(HybridBase.SWITCH_DOWNLOAD_ADAPTER, SwitchQueryFetcher.getSwitchBooleanValue(HybridBase.SWITCH_DOWNLOAD_ADAPTER, false) ? "1" : "0");
        hashMap2.put(HybridSDK.APP_VERSION_CODE, String.valueOf(BaseInfo.getAppVersionCode()));
        hashMap2.put(HybridSDK.APP_VERSION, BaseInfo.getAppVersionName());
        hashMap2.put("uuid", BaseInfo.getAndroidId());
        hashMap2.put(HybridSDK.D_MODEL, BaseInfo.getDeviceModel());
        hashMap2.put(HybridSDK.D_BRAND, BaseInfo.getDeviceBrand());
        hashMap2.put(HybridSDK.OS_VERSION, BaseInfo.getAndroidVersion());
        hashMap2.put("lat", getLat());
        hashMap2.put(HybridSDK.LNG, getLng());
        hashMap2.put("area", getArea());
        hashMap2.put("client", "android");
        String orgUserAgent = WebViewHelper.getOrgUserAgent(SharedPreferencesUtil.getString(MBaseKeyNames.KEY_PRELOAD_ORI_UA, ""));
        StringBuffer jdUa = WebViewHelper.getJdUa(false);
        if (jdUa != null && !TextUtils.isEmpty(orgUserAgent)) {
            jdUa.append(orgUserAgent);
            orgUserAgent = jdUa.toString();
        }
        hashMap2.put("userAgent", orgUserAgent);
        HybridSDK.initSettings(hashMap2);
        if (userAppNetAdatper) {
            HybridSDK.registerAdapter(ColorHttpAdapter.NAME, ColorHttpAdapterImpl.class);
        }
        HybridSDK.registerAdapter(BaseInfoAdapter.NAME, BaseInfoAdapterImpl.class);
        com.jd.libs.x5.hybrid.HybridSDK.initSDK(JdSdk.getInstance().getApplication(), "d8c21d54-8f01-462c-8878-989f89b74b2a", z);
        initXStorage();
        registerActivityCallback();
        SwitchQueryFetcher.getFetcher().addFetchListener(new SwitchQueryFetcher.FetchListener() { // from class: com.jingdong.common.web.WebHybridUtils.5
            @Override // com.jingdong.common.utils.SwitchQueryFetcher.FetchListener
            public void onFetchEnd(boolean z2) {
                WebHybridUtils.changeBySwitch();
                HybridSDK.updateSettings(HybridSDK.SWITCH_XCACHE_RETRYDOMAIN, SwitchQueryFetcher.getSwitchStringValue(HybridSDK.SWITCH_XCACHE_RETRYDOMAIN, ""));
                HybridSDK.updateSettings(HybridSDK.SWITCH_XCACHE, SwitchQueryFetcher.getSwitchBooleanValue("h_xcache", false) ? "1" : "0");
                HybridSDK.updateSettings(HybridSDK.SWITCH_IO_DETAIL, SwitchQueryFetcher.getSwitchBooleanValue(HybridSDK.SWITCH_IO_DETAIL, false) ? "1" : "0");
                HybridSDK.updateSettings(HybridBase.SWITCH_DOWNLOAD_ADAPTER, SwitchQueryFetcher.getSwitchBooleanValue(HybridBase.SWITCH_DOWNLOAD_ADAPTER, false) ? "1" : "0");
                j.l().o("switchQuery", SwitchQueryFetcher.getSwitchStringValue("XCacheConfig", ""));
                WebHybridUtils.initXStorage();
                WebHybridUtils.setWebViewDnsHost();
            }
        });
        changeBySwitch();
        CommonResEngine.available = !SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.HYBRID_COMMON_RESOURCE_INTERNAL, false);
        PerfMtaUtils.setCustomMtaSender(new PerfMtaUtils.CustomMtaSender() { // from class: com.jingdong.common.web.WebHybridUtils.6
            @Override // com.jd.libs.hybrid.base.util.PerfMtaUtils.CustomMtaSender
            public void onConfigGot(HashMap<String, String> hashMap3) {
                try {
                    hashMap3.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
                } catch (Throwable unused) {
                }
                hashMap3.put("typeId", "7");
                hashMap3.put("chId", "5");
                hashMap3.put("hbdsession", WebHybridUtils.HDB_SESSION);
                PerformanceReporter.reportData(hashMap3);
            }

            @Override // com.jd.libs.hybrid.base.util.PerfMtaUtils.CustomMtaSender
            public void onDownloaded(HashMap<String, String> hashMap3) {
                hashMap3.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
                hashMap3.put("typeId", "7");
                hashMap3.put("chId", "6");
                hashMap3.put("hbdsession", WebHybridUtils.HDB_SESSION);
                PerformanceReporter.reportData(hashMap3);
            }
        });
    }

    public static void initXStorage() {
        if (isXStorageInit || !SwitchQueryFetcher.getSwitchBooleanValue("XStorageEnabled", false)) {
            return;
        }
        DataSnapshotSDK.INSTANCE.getInstance().init();
        isXStorageInit = true;
    }

    private static boolean isEncryptUaBlackUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (webUtilsClazz == null) {
                webUtilsClazz = Class.forName("com.jingdong.common.web.util.WebUtils");
            }
            return ((Boolean) webUtilsClazz.getMethod("isEncryptUaBlackUrl", String.class).invoke(null, str)).booleanValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("JDHybrid", e2);
            }
            return false;
        }
    }

    public static boolean isXTime() {
        return false;
    }

    public static void loadBuildInConfig() {
        if (!hybridEnable) {
            if (OKLog.D) {
                OKLog.d("JDHybrid", "JDHybrid is disable by switch.");
                return;
            }
            return;
        }
        HybridSDK.loadBuildInConfig();
    }

    public static void loadConfig() {
        if (!hybridEnable) {
            if (OKLog.D) {
                OKLog.d("JDHybrid", "JDHybrid is disable by switch.");
            }
        } else if (isXTime()) {
        } else {
            HybridSDK.loadConfig();
        }
    }

    private static void registerActivityCallback() {
        JdSdk.getInstance().getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.jingdong.common.web.WebHybridUtils.7
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(@NonNull Activity activity) {
                if (activity != null) {
                    try {
                        Intent intent = activity.getIntent();
                        if (intent != null) {
                            String stringExtra = intent.hasExtra(MBaseKeyNames.KEY_OFFLINE_ID) ? intent.getStringExtra(MBaseKeyNames.KEY_OFFLINE_ID) : "";
                            String stringExtra2 = intent.hasExtra(MBaseKeyNames.KEY_PRELOAD_ID) ? intent.getStringExtra(MBaseKeyNames.KEY_PRELOAD_ID) : "";
                            if (OKLog.D) {
                                OKLog.d("JDHybrid", "activity(" + activity.getClass().getName() + ")destroyed, offlineId = " + stringExtra);
                                OKLog.d("JDHybrid", "activity(" + activity.getClass().getName() + ")destroyed, preLoadId = " + stringExtra2);
                            }
                            if (WebHybridUtils.hybridEnable) {
                                WebOfflineLoaderManager.deleteOfflineLoader(stringExtra);
                                WebPreLoadHelper.clearPreLoadData(stringExtra2);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(@NonNull Activity activity) {
            }
        });
    }

    public static void setWebViewDnsHost() {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("HybridDnsHost", "");
        if (switchStringValue.equals(httpDnsHost) || needSetDnsHost) {
            return;
        }
        httpDnsHost = switchStringValue;
        needSetDnsHost = true;
    }

    public static boolean degradeOfflineFromQuery(Uri uri) {
        if (uri == null) {
            return false;
        }
        try {
            if (!"0".equals(uri.getQueryParameter(OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR))) {
                if (!hostListWithKeyWord(uri.toString(), uri.getHost(), SwitchQueryFetcher.getSwitchStringValue("hybridBanUrlList", "").split(";"))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
