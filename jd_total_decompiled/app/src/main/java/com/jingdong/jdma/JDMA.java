package com.jingdong.jdma;

import android.content.Context;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.minterface.AppMode;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.jdma.minterface.ClickInterfaceParam;
import com.jingdong.jdma.minterface.CustomInterfaceParam;
import com.jingdong.jdma.minterface.DomainInterface;
import com.jingdong.jdma.minterface.ExposureInterfaceParam;
import com.jingdong.jdma.minterface.MaInitCommonInfo;
import com.jingdong.jdma.minterface.OrderInterfaceParam;
import com.jingdong.jdma.minterface.PropertyInterfaceParam;
import com.jingdong.jdma.minterface.PvInterfaceParam;
import java.util.Map;

/* loaded from: classes12.dex */
public class JDMA {
    private static final String TAG = "JDMA";
    private static volatile boolean isStartWithConfig;

    public static void acceptPrivacyProtocol(boolean z) {
        JDMaInterface.acceptPrivacyProtocol(z);
    }

    public static void clearMtaSource() {
        JDMaInterface.clearMtaSource();
    }

    public static void clipboard(boolean z) {
        JDMaInterface.clipboard(z);
    }

    public static void destroy() {
        JDMaInterface.destroy();
    }

    public static String getJda() {
        return JDMaInterface.getJda();
    }

    public static String getJdv() {
        return JDMaInterface.getJdv();
    }

    public static long getOpen_count() {
        return JDMaInterface.getOpen_count();
    }

    public static long getSeq() {
        return JDMaInterface.getSeq();
    }

    public static String getSessionInfo(Context context) {
        return JDMaInterface.getSessionInfo(context);
    }

    public static String getSourceType() {
        return JDMaInterface.getSourceType();
    }

    public static String getSourceValue() {
        return JDMaInterface.getSourceValue();
    }

    public static String getUnpl() {
        return JDMaInterface.getUnpl();
    }

    public static void openWebView(WebView webView) {
        JDMaInterface.openWebView(webView);
    }

    public static void openX5WebView(Object obj) {
        JDMaInterface.openX5WebView(obj);
    }

    public static void parseTextOnMobileCheckMode(String str) {
        JDMaInterface.parseTextOnMobileCheckMode(str);
    }

    public static void removeUserProperty(@NonNull String str) {
        JDMaInterface.removeUserProperty(str);
    }

    public static void sendClickData(Context context, ClickInterfaceParam clickInterfaceParam) {
        if (!isStartWithConfig) {
            LogUtil.w(TAG, "please call the 'startWithConfig' method first");
        } else {
            JDMaInterface.sendClickData(context, null, clickInterfaceParam);
        }
    }

    public static void sendCustomData(Context context, CustomInterfaceParam customInterfaceParam) {
        if (!isStartWithConfig) {
            LogUtil.w(TAG, "please call the 'startWithConfig' method first");
        } else {
            JDMaInterface.sendCustomData(context, null, customInterfaceParam);
        }
    }

    public static void sendData(Context context, BaseEvent baseEvent) {
        if (!isStartWithConfig) {
            LogUtil.w(TAG, "please call the 'startWithConfig' method first");
        } else {
            JDMaInterface.sendData(context, null, baseEvent);
        }
    }

    public static void sendExposureData(Context context, ExposureInterfaceParam exposureInterfaceParam) {
        if (!isStartWithConfig) {
            LogUtil.w(TAG, "please call the 'startWithConfig' method first");
        } else {
            JDMaInterface.sendExposureData(context, null, exposureInterfaceParam);
        }
    }

    public static void sendOrderData(Context context, OrderInterfaceParam orderInterfaceParam) {
        if (!isStartWithConfig) {
            LogUtil.w(TAG, "please call the 'startWithConfig' method first");
        } else {
            JDMaInterface.sendOrderData(context, null, orderInterfaceParam);
        }
    }

    public static void sendPropertyData(Context context, PropertyInterfaceParam propertyInterfaceParam) {
        if (!isStartWithConfig) {
            LogUtil.w(TAG, "please call the 'startWithConfig' method first");
        } else {
            JDMaInterface.sendPropertyData(context, null, propertyInterfaceParam);
        }
    }

    public static void sendPvData(Context context, PvInterfaceParam pvInterfaceParam) {
        if (!isStartWithConfig) {
            LogUtil.w(TAG, "please call the 'startWithConfig' method first");
        } else {
            JDMaInterface.sendPvData(context, null, pvInterfaceParam);
        }
    }

    public static void setAppMode(AppMode appMode) {
        JDMaInterface.setAppMode(appMode);
    }

    public static void setAreaCode(String str) {
        JDMaInterface.setAreaCode(str);
    }

    public static void setExternalMPageParam(String str) {
        JDMaInterface.setExternalMPageParam(str);
    }

    public static void setImeiTag(String str) {
        JDMaInterface.setImeiTag(str);
    }

    public static void setJdv(String str) {
        JDMaInterface.setJdv(str);
    }

    public static void setModeTag(String str) {
        JDMaInterface.setModeTag(str);
    }

    public static void setMtaContent4Inside(String str) {
        JDMaInterface.setMtaContent4Inside(str);
    }

    public static void setMtaContent4OpenApp(Context context, String str) {
        JDMaInterface.setMtaContent4OpenApp(context, str);
    }

    public static void setReportDomain(String str) {
        JDMaInterface.setReportDomain(str);
    }

    public static void setSessionInfo(Context context, String str) {
        JDMaInterface.setSessionInfo(context, str);
    }

    public static void setShowLog(boolean z) {
        JDMaInterface.setShowLog(z);
    }

    public static void setSourceData(String str, String str2, Context context) {
        JDMaInterface.setSourceData(str, str2, context);
    }

    public static void setUserProperty(@NonNull String str, String str2) {
        JDMaInterface.setUserProperty(str, str2);
    }

    public static void startWithConfig(Context context, JDMAConfig jDMAConfig) {
        LogUtil.i(TAG, "startWithConfig()");
        MaInitCommonInfo maInitCommonInfo = new MaInitCommonInfo();
        maInitCommonInfo.guid = jDMAConfig.getUid();
        maInitCommonInfo.site_id = jDMAConfig.getSiteId();
        maInitCommonInfo.app_device = jDMAConfig.getAppDevice();
        maInitCommonInfo.channel = jDMAConfig.getChannel();
        maInitCommonInfo.proj_id = jDMAConfig.getProjectId();
        maInitCommonInfo.appv = jDMAConfig.getAppVersionName();
        maInitCommonInfo.appc = jDMAConfig.getAppVersionCode();
        maInitCommonInfo.build = jDMAConfig.getAppBuildId();
        maInitCommonInfo.zipFlag = jDMAConfig.isDataEncrypted() ? 1 : 0;
        maInitCommonInfo.domainInterface = new DomainInterface(jDMAConfig.getStrategyDomain(), jDMAConfig.getReportDomain(), jDMAConfig.getH5Url());
        maInitCommonInfo.installationId = jDMAConfig.getInstallationId();
        maInitCommonInfo.setHttpDns(jDMAConfig.getHttpDns());
        maInitCommonInfo.setJDMABaseInfo(jDMAConfig.getJdmaBaseInfo());
        maInitCommonInfo.setISwitchQuery(jDMAConfig.getSwitchQuery());
        JDMaInterface.init(context, maInitCommonInfo);
        isStartWithConfig = true;
    }

    public static void updateUnpl(String str, String str2, String str3) {
        JDMaInterface.updateUnpl(str, str2, str3);
    }

    public static void removeUserProperty(String[] strArr) {
        JDMaInterface.removeUserProperty(strArr);
    }

    public static void setUserProperty(Map<String, String> map) {
        JDMaInterface.setUserProperty(map);
    }
}
