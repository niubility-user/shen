package com.jd.libs.hybrid;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import androidx.annotation.Keep;
import com.jd.framework.json.JDJSON;
import com.jd.hybrid.downloader.c;
import com.jd.libs.hybrid.adapter.IAdapter;
import com.jd.libs.hybrid.adapter.RequestAdapter;
import com.jd.libs.hybrid.adapter.XRenderAdapter;
import com.jd.libs.hybrid.base.GlobalParamProvider;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridConstants;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.engine.ConfigEngine;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.MtaUtils;
import com.jd.libs.hybrid.base.util.PreferenceUtils;
import com.jd.libs.hybrid.engine.HybridClientImpl;
import com.jd.libs.hybrid.jdcache.JDCacheHelper;
import com.jd.libs.hybrid.offlineload.OfflineLoadController;
import com.jd.libs.hybrid.offlineload.entity.CommonEntity;
import com.jd.libs.hybrid.offlineload.processor.CleanUpService;
import com.jd.libs.hybrid.offlineload.temp.OfflineSwitchSetting;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.offlineload.utils.JDCacheSwitch;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineMtaUtils;
import com.jd.libs.hybrid.preload.PreloadController;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;
import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jd.libs.hybrid.xbehavior.lifecycle.ActivityLifeCycle;
import com.jd.libs.xconsole.XLog;
import com.jd.libs.xconsole.b;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes.dex */
public class HybridSDK {
    public static final String ACCOUNT = "pin";
    public static final String APP_VERSION = "clientVersion";
    public static final String APP_VERSION_CODE = "build";
    public static final String AREA = "area";
    private static final long BUILDIN_LOADED_GAP = 300000;
    public static final String CLIENT = "client";
    private static final long CONFIG_LOADED_GAP = 5000;
    public static final String D_BRAND = "d_brand";
    public static final String D_MODEL = "d_model";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String OS_VERSION = "osVersion";
    public static final String SWITCH_CLEAR_BUILD_IN = "switch_clear_build_in";
    public static final String SWITCH_IO_DETAIL = "switch_io_detail";
    @Deprecated
    public static final String SWITCH_NET_HYBRID = "switch_net_hybrid";
    public static final String SWITCH_UPDATE_A = "switch_update_A";
    public static final String SWITCH_XCACHE = "switch_xcache";
    public static final String SWITCH_XCACHE_RETRYDOMAIN = "H5-retry-config";
    private static final String TAG = "HybridSDK";
    public static final String USER_AGENT = "userAgent";
    public static final String UUID = "uuid";
    private static ConcurrentHashMap<String, String> settings = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Class<? extends IAdapter>> adapters = new ConcurrentHashMap<>();
    private static volatile long configLoadTime = 0;
    private static volatile long buildInConfigLoadTime = 0;
    private static boolean isDelayDownloaded = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Keep
    /* loaded from: classes16.dex */
    public static class HybridResult {
        Offline offlinePackage;
        Preload preload;
        public String preloadNew;
        PreRender prerender;

        HybridResult() {
        }

        public Offline getOfflinePackage() {
            return this.offlinePackage;
        }

        public PreRender getPreRender() {
            return this.prerender;
        }

        public Preload getPreload() {
            return this.preload;
        }

        public void setOfflinePackage(Offline offline) {
            this.offlinePackage = offline;
        }

        public void setPreRender(PreRender preRender) {
            this.prerender = preRender;
        }

        public void setPreload(Preload preload) {
            this.preload = preload;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Keep
    /* loaded from: classes16.dex */
    public static class Offline {
        List<CommonEntity> common;
        int maxThread;
        List<String> network;
        int networkIgnore;
        int timeout;
        int retry = 0;
        int dUpper = 50;
        float spRatio = 0.4f;
        int delay_time = 10;

        Offline() {
        }

        public List<CommonEntity> getCommon() {
            return this.common;
        }

        public int getDelay_time() {
            return this.delay_time;
        }

        public int getMaxThread() {
            return this.maxThread;
        }

        public List<String> getNetwork() {
            return this.network;
        }

        public int getNetworkIgnore() {
            return this.networkIgnore;
        }

        public int getRetry() {
            return this.retry;
        }

        public float getSpRatio() {
            return this.spRatio;
        }

        public int getTimeout() {
            return this.timeout;
        }

        public int getdUpper() {
            return this.dUpper;
        }

        public void setCommon(List<CommonEntity> list) {
            this.common = list;
        }

        public void setDelay_time(int i2) {
            this.delay_time = i2;
        }

        public void setMaxThread(int i2) {
            this.maxThread = i2;
        }

        public void setNetwork(List<String> list) {
            this.network = list;
        }

        public void setNetworkIgnore(int i2) {
            this.networkIgnore = i2;
        }

        public void setRetry(int i2) {
            this.retry = i2;
        }

        public void setSpRatio(float f2) {
            this.spRatio = f2;
        }

        public void setTimeout(int i2) {
            this.timeout = i2;
        }

        public void setdUpper(int i2) {
            this.dUpper = i2;
        }
    }

    @Keep
    /* loaded from: classes16.dex */
    public static class PreRender {
        int maxUseTimes;
        List<PreRenderModule> modules;
        long reserveTime;

        public int getMaxUseTimes() {
            return this.maxUseTimes;
        }

        public List<PreRenderModule> getModules() {
            return this.modules;
        }

        public long getReserveTime() {
            return this.reserveTime;
        }

        public void setMaxUseTimes(int i2) {
            this.maxUseTimes = i2;
        }

        public void setModules(List<PreRenderModule> list) {
            this.modules = list;
        }

        public void setReserveTime(long j2) {
            this.reserveTime = j2;
        }
    }

    @Keep
    /* loaded from: classes16.dex */
    public static class PreRenderModule {
        String appMax;
        String appMin;
        String appid;
        String[] demandClasses;
        int maxUseTimes;
        String originalUrl;
        long reserveTime;
        int xrenderType;

        public String getAppMax() {
            return this.appMax;
        }

        public String getAppMin() {
            return this.appMin;
        }

        public String getAppid() {
            return this.appid;
        }

        public String[] getDemandClasses() {
            return this.demandClasses;
        }

        public int getMaxUseTimes() {
            return this.maxUseTimes;
        }

        public String getOriginalUrl() {
            return this.originalUrl;
        }

        public long getReserveTime() {
            return this.reserveTime;
        }

        public int getXrenderType() {
            return this.xrenderType;
        }

        public void setAppMax(String str) {
            this.appMax = str;
        }

        public void setAppMin(String str) {
            this.appMin = str;
        }

        public void setAppid(String str) {
            this.appid = str;
        }

        public void setDemandClasses(String[] strArr) {
            this.demandClasses = strArr;
        }

        public void setMaxUseTimes(int i2) {
            this.maxUseTimes = i2;
        }

        public void setOriginalUrl(String str) {
            this.originalUrl = str;
        }

        public void setReserveTime(long j2) {
            this.reserveTime = j2;
        }

        public void setXrenderType(int i2) {
            this.xrenderType = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Keep
    /* loaded from: classes16.dex */
    public static class Preload {
        List<PreloadInfoEntity> modules;

        Preload() {
        }

        public List<PreloadInfoEntity> getModules() {
            return this.modules;
        }

        public void setModules(List<PreloadInfoEntity> list) {
            this.modules = list;
        }
    }

    private static void delayDownload(final int i2) {
        if (isDelayDownloaded) {
            return;
        }
        Log.d("download delay: " + i2);
        isDelayDownloaded = true;
        new Thread(new Runnable() { // from class: com.jd.libs.hybrid.HybridSDK.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(i2 * 1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                Log.d("start download for 'S' level");
                OfflineLoadController.getInstance(HybridSettings.getAppContext()).startDownload("S");
            }
        }, "hybrid-download-delay").start();
    }

    @Deprecated
    public static void enableBuildInOffline(boolean z) {
        HybridSettings.setBuildInOfflineDisable(!z);
    }

    public static IAdapter getAdapter(String str) {
        Class<? extends IAdapter> cls = adapters.get(str);
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            } catch (InstantiationException e3) {
                e3.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static <T extends IAdapter> T getAdapterByType(String str) {
        try {
            return (T) adapters.get(str).newInstance();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getLastPageName() {
        return ActivityLifeCycle.getLastPageName();
    }

    public static int getMaxOfflineFetchTime() {
        return HybridSettings.MAX_OFFLINE_FETCH_TIME;
    }

    public static String getSetting(String str) {
        String str2;
        ConcurrentHashMap<String, String> concurrentHashMap = settings;
        return (concurrentHashMap == null || (str2 = concurrentHashMap.get(str)) == null) ? "" : str2;
    }

    public static void initSDK(Application application, String str) {
        if (isInited()) {
            Log.d(TAG, "Hybrid was inited before.");
        } else if (Build.VERSION.SDK_INT < 23) {
            Log.d(TAG, "Web Hybrid SDK does NOT support SDK less than 23(API < M).");
            HybridSettings.setAppContext(application);
            c.g(c.h(application));
            HybridBase.getInstance().setLoaderClient(new HybridClientImpl());
            application.registerActivityLifecycleCallbacks(new ActivityLifeCycle());
        } else {
            HybridSettings.setInited(true);
            HybridSettings.setAppContext(application);
            HybridSettings.setAppKey(str);
            c.g(c.h(application));
            HybridBase.getInstance().setLoaderClient(new HybridClientImpl());
            application.registerActivityLifecycleCallbacks(new ActivityLifeCycle());
            CleanUpService.deleteTempFiles();
            RequestPreloadSDK.INSTANCE.getInstance().init(application);
            b.a(application);
            if (JDCacheSwitch.useJdCache.booleanValue()) {
                JDCacheHelper.INSTANCE.init(application);
            }
        }
    }

    public static void initSettings(Map<String, String> map) {
        settings.clear();
        settings.putAll(map);
    }

    public static boolean isDebug() {
        return HybridSettings.isDebug();
    }

    public static boolean isInited() {
        return HybridSettings.isInited();
    }

    public static void loadBuildInConfig() {
        if (!isInited()) {
            Log.e("Hybrid SDK is not initialized!");
            return;
        }
        Context appContext = HybridSettings.getAppContext();
        if (appContext == null) {
            return;
        }
        if (System.currentTimeMillis() - buildInConfigLoadTime < 300000) {
            Log.d("Try to load build-in config too many times, skip this time.");
            return;
        }
        buildInConfigLoadTime = System.currentTimeMillis();
        if (OfflineSwitchSetting.TYPE_4_OFF) {
            return;
        }
        OfflineLoadController.getInstance(appContext).refreshModuleFromBuildIn();
    }

    public static void loadConfig() {
        loadConfig(true);
    }

    private static void parseError(boolean z, String str) {
        XLog.e(TAG, "\u83b7\u53d6JDHybrid\u603b\u914d\u7f6e\u6570\u636e\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str);
        Context appContext = HybridSettings.getAppContext();
        if (!z && appContext != null && PreferenceUtils.getInt(appContext, HybridConstants.PreferenceKey.PREFERENCE_LOAD_CONFIG_BEFORE, 0) == 1) {
            Log.e("Fail to fetch hybrid config but won't disable functions by setting, use old data.");
            Log.xLogEForDev(TAG, "\u7ee7\u7eed\u4f7f\u7528\u672c\u5730\u6570\u636e\u3002");
            HybridSettings.setPreloadDisable(false);
            HybridSettings.setDownloadedOfflineDisable(false);
            return;
        }
        HybridSettings.setPreloadDisable(true);
        HybridSettings.setDownloadedOfflineDisable(true);
        Log.e("Fail to fetch hybrid config, disable offline/preload functions.");
        Log.xLogEForDev(TAG, "\u5173\u95ed\u63a5\u53e3\u4e0b\u53d1\u7684\u79bb\u7ebf\u5305\u548c\u9884\u52a0\u8f7d\u529f\u80fd\u3002");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parseResult(String str, HybridResult hybridResult) {
        List<PreloadInfoEntity> list;
        Log.d(TAG, "loadConfig success");
        if (Log.isDebug()) {
            Log.xLogD(TAG, "\u6210\u529f\u83b7\u53d6JDHybrid\u603b\u914d\u7f6e\u6570\u636e\uff1a", str);
        }
        HybridSettings.setPreloadDisable(false);
        HybridSettings.setDownloadedOfflineDisable(false);
        Context appContext = HybridSettings.getAppContext();
        if (appContext != null) {
            OfflineLoadController offlineLoadController = OfflineLoadController.getInstance(appContext);
            PreloadController preloadController = !GraySwitch.oldPreloadOffline ? new PreloadController(appContext) : null;
            if (hybridResult != null) {
                if (preloadController != null) {
                    Preload preload = hybridResult.preload;
                    if (preload != null && (list = preload.modules) != null) {
                        preloadController.refresh(list);
                    } else {
                        Log.d(TAG, "No Preload config, delete preload's all database.");
                        preloadController.deleteAll();
                    }
                }
                RequestPreloadSDK.INSTANCE.getInstance().parse(hybridResult.preloadNew);
                Offline offline = hybridResult.offlinePackage;
                if (offline != null) {
                    HybridSettings.setMaxOfflineFetchTime(offline.timeout);
                    HybridSettings.setDownloadConditionThread(offline.maxThread);
                    HybridSettings.setDownloadRetryCount(offline.retry);
                    HybridSettings.setDownloadConditionNetwork(offline.networkIgnore, offline.network);
                    HybridSettings.setMaxOfflinePackCount(offline.dUpper);
                    HybridSettings.setSpRatio(offline.spRatio);
                    List<CommonEntity> list2 = offline.common;
                    if (list2 == null) {
                        list2 = Collections.emptyList();
                    }
                    offlineLoadController.refreshDownloadedCommonOffline(list2);
                    if (!OfflineSwitchSetting.TYPE_4_OFF) {
                        try {
                            JSONArray optJSONArray = new JSONObject(str).getJSONObject("offline_package").optJSONArray("modules_new");
                            offlineLoadController.refreshModuleFromNet(optJSONArray, str);
                            if (optJSONArray != null && optJSONArray.length() > 0) {
                                delayDownload(offline.getDelay_time());
                            }
                        } catch (JSONException e2) {
                            Log.e(TAG, e2);
                            OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_CODE, "HybridSDK#parseResult", (String) null, e2);
                        }
                    }
                } else {
                    Log.d(TAG, "No download-offline config, delete its database and local files.");
                    offlineLoadController.deleteAllDownloaded();
                    OfflineMtaUtils.sendFetchConfigMta(3);
                }
            } else {
                Log.d(TAG, "fetched config is null/empty, delete all preload's and download-offline's database and local files.");
                if (preloadController != null) {
                    preloadController.deleteAll();
                }
                offlineLoadController.deleteAllDownloaded();
                OfflineMtaUtils.sendFetchConfigMta(3);
            }
            PreferenceUtils.putInt(appContext, HybridConstants.PreferenceKey.PREFERENCE_LOAD_CONFIG_BEFORE, 1);
            return;
        }
        Log.e(TAG, "Internal error, app context is null.");
        OfflineExceptionUtils.reportConfigError("Internal error", "parseResult", (String) null, "app context is null");
    }

    public static void registerAdapter(String str, Class<? extends IAdapter> cls) {
        adapters.put(str, cls);
    }

    public static <S, E> RequestAdapter<S, E> requestUrl(String str, String str2, JSONObject jSONObject, boolean z, int i2, RequestAdapter.RequestCallback<S, E> requestCallback) {
        RequestAdapter<S, E> requestAdapter = (RequestAdapter) getAdapterByType(RequestAdapter.NAME);
        if (requestAdapter != null) {
            requestAdapter.request(str, str2, IMantoServerRequester.POST, jSONObject, z, i2, getSetting("userAgent"), HybridBase.getInstance().getCookieString(str), requestCallback);
        }
        return requestAdapter;
    }

    public static void setDebug(boolean z) {
        HybridSettings.setDebug(z);
    }

    public static void setExceptionReporter(ExceptionUtils.CustomExceptionReporter customExceptionReporter) {
        ExceptionUtils.setExceptionReporter(customExceptionReporter);
    }

    public static void setForceHttp(boolean z) {
        HybridSettings.Net.setUseHttp(z);
    }

    public static void setGatewaySettings(HybridSettings.Net.SimpleGatewaySettings simpleGatewaySettings) {
        HybridSettings.Net.setGatewaySettings(simpleGatewaySettings);
    }

    public static void setGlobalParamProvider(GlobalParamProvider.IGlobalParamProvider iGlobalParamProvider) {
        GlobalParamProvider.setGlobalParamProvider(iGlobalParamProvider);
    }

    public static void setMaxOfflineFetchTime(int i2) {
        if (i2 > 0) {
            HybridSettings.setMaxOfflineFetchTime(i2);
        }
    }

    public static void setMtaSender(MtaUtils.CustomMtaSender customMtaSender) {
        MtaUtils.setMtaSender(customMtaSender);
    }

    public static void setXTimeData(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setXTimeData: ");
        sb.append(z ? "success" : "fail");
        sb.append(", data= ");
        sb.append(str);
        Log.d(TAG, sb.toString());
        if (!isInited()) {
            Log.e("Hybrid SDK is not initialized!");
        } else if (!z) {
            parseError(false, "XTime\u63a5\u53e3\u5931\u8d25");
        } else {
            try {
                HybridResult hybridResult = (HybridResult) JDJSON.parseObject(str, HybridResult.class);
                if (hybridResult != null) {
                    parseResult(str, hybridResult);
                }
            } catch (Exception e2) {
                parseError(false, "\u6570\u636e\u89e3\u6790\u5931\u8d25\uff0cerror=" + e2.getMessage());
            }
        }
    }

    public static void unregisterAdapter(String str) {
        adapters.remove(str);
    }

    public static void updateSettings(String str, String str2) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            settings.remove(str);
        } else {
            settings.put(str, str2);
        }
    }

    public static void loadConfig(boolean z) {
        if (!isInited()) {
            Log.e("Hybrid SDK is not initialized!");
        } else if (HybridSettings.isDownloadedOfflineDisable() && HybridSettings.isPreloadDisable()) {
            Log.d("Hybrid offline/preload functions are disable, won't load config until next start app.");
            Log.xLogDForDev(TAG, "\u63a5\u53e3\u4e0b\u53d1\u7684\u79bb\u7ebf\u5305\u548c\u9884\u52a0\u8f7d\u529f\u80fd\u5df2\u5173\u95ed\uff0c\u4e0d\u518d\u62c9\u53d6\u914d\u7f6e\uff0c\u8bf7\u91cd\u542fapp\u518d\u8bd5\u6216\u8054\u7cfb\u7ba1\u7406\u5458");
        } else if (System.currentTimeMillis() - configLoadTime < 5000) {
            Log.d("Try to load config too many times, skip this time.");
        } else {
            configLoadTime = System.currentTimeMillis();
            HybridBase.getInstance().getAllConfig(new ConfigEngine.Callback<String>() { // from class: com.jd.libs.hybrid.HybridSDK.1
                @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
                public void onFail(int i2, String str) {
                    OfflineMtaUtils.sendFetchConfigMta(-1);
                    OfflineExceptionUtils.reportConfigError(i2, "Hybrid\u63a5\u53e3\u5931\u8d25", (String) null, str);
                    XRenderAdapter xRenderAdapter = (XRenderAdapter) HybridSDK.getAdapter(XRenderAdapter.NAME);
                    if (xRenderAdapter != null) {
                        xRenderAdapter.getPreRenderData(false, null);
                    }
                    Log.e("\u81ea\u5b9a\u4e49\u7f51\u7edc\u6846\u67b6\u83b7\u53d6\u6570\u636e\u5931\u8d25:" + str);
                }

                @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
                public void onSuccess(String str) {
                    HybridResult hybridResult;
                    try {
                        hybridResult = (HybridResult) JDJSON.parseObject(str, HybridResult.class);
                    } catch (Exception e2) {
                        onFail(-2, e2.toString());
                        hybridResult = null;
                    }
                    if (hybridResult != null) {
                        HybridSDK.parseResult(str, hybridResult);
                    }
                    XRenderAdapter xRenderAdapter = (XRenderAdapter) HybridSDK.getAdapter(XRenderAdapter.NAME);
                    if (hybridResult != null && xRenderAdapter != null) {
                        xRenderAdapter.getPreRenderData(true, hybridResult.prerender);
                    }
                    Log.d("\u81ea\u5b9a\u4e49\u7f51\u7edc\u6846\u67b6\u83b7\u53d6\u6570\u636e\u6210\u529f:" + str);
                }
            });
            Log.xLogD(TAG, "\u6b63\u5728\u83b7\u53d6JDHybrid\u603b\u914d\u7f6e\u6570\u636e...");
        }
    }

    public static void initSDK(Application application, String str, boolean z) {
        setDebug(z);
        initSDK(application, str);
    }
}
