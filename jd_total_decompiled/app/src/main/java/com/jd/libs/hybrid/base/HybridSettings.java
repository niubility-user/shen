package com.jd.libs.hybrid.base;

import android.content.Context;
import android.text.TextUtils;
import com.jd.libs.hybrid.base.HybridConstants;
import com.jd.libs.hybrid.base.util.PreferenceUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes16.dex */
public class HybridSettings {
    public static int CONDITION_DOWNLOAD_MAX_THREAD = 300;
    public static int MAX_HTML_PRE_FETCH_TIME = 5000;
    public static int MAX_OFFLINE_FETCH_TIME = 500;
    private static boolean a;
    private static boolean b;

    /* renamed from: c  reason: collision with root package name */
    private static Context f5890c;
    private static String d;

    /* renamed from: e  reason: collision with root package name */
    private static List<DownloadConditionSettingListener> f5891e;
    public static Set<String> CONDITION_DOWNLOAD_NETWORK = new HashSet(Collections.singletonList("wifi"));
    public static boolean CONDITION_DOWNLOAD_NETWORK_IGNORE = false;
    public static boolean HYBRID_DOWNLOADED_OFFLINE_DISABLE = false;
    @Deprecated
    public static boolean HYBRID_BUILDIN_OFFLINE_DISABLE = false;
    public static boolean HYBRID_PRELOAD_DISABLE = false;
    public static int HYBRID_DOWNLOAD_RETRY = 0;
    public static long LAST_SET_LP_TIME = 0;
    public static int MAX_OFFLINE_PACK_COUNT = 50;
    public static float SP_RATIO = 0.4f;

    /* loaded from: classes16.dex */
    public interface DownloadConditionSettingListener {
        void onSettingChanged();
    }

    /* loaded from: classes16.dex */
    public static class Net {
        private static String a = "beta-api.m.jd.com";
        private static String b = "api.m.jd.com";

        /* renamed from: c  reason: collision with root package name */
        private static boolean f5892c = true;
        private static boolean d;

        /* renamed from: e  reason: collision with root package name */
        private static GatewaySettings f5893e;

        /* renamed from: f  reason: collision with root package name */
        private static NonGatewaySettings f5894f;

        /* renamed from: g  reason: collision with root package name */
        private static DataProviderSettings f5895g;

        /* loaded from: classes16.dex */
        public interface DataProviderSettings {
            void onPreloadRequest(String str, Map<String, String> map, Map<String, String> map2, Map<String, Object> map3, boolean z, PreloadCallback preloadCallback);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes16.dex */
        public interface GatewaySettings {
            public static final int TYPE_PREVIEW = 4097;
            public static final int TYPE_RELEASE = 4098;

            int getEnvType();
        }

        /* loaded from: classes16.dex */
        private interface NonGatewaySettings {
            public static final int TYPE_PREVIEW = 4097;
            public static final int TYPE_RELEASE = 4098;

            String getAppVer();

            int getEnvType();

            String getModel();

            String getOsVer();

            String getUuid();
        }

        /* loaded from: classes16.dex */
        public interface PreloadCallback {
            void onFail(int i2, String str);

            void onSuccess(Map<String, String> map, int i2, String str);
        }

        /* loaded from: classes16.dex */
        public static class SimpleGatewaySettings implements GatewaySettings {
            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.GatewaySettings
            public int getEnvType() {
                return 4098;
            }
        }

        /* loaded from: classes16.dex */
        public static class SimpleNonGatewaySettings implements NonGatewaySettings {
            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.NonGatewaySettings
            public String getAppVer() {
                return null;
            }

            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.NonGatewaySettings
            public int getEnvType() {
                return 4098;
            }

            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.NonGatewaySettings
            public String getModel() {
                return null;
            }

            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.NonGatewaySettings
            public String getOsVer() {
                return null;
            }

            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.NonGatewaySettings
            public String getUuid() {
                return null;
            }
        }

        public static String getApiUrl() {
            NonGatewaySettings nonGatewaySettings = f5894f;
            return (nonGatewaySettings == null || nonGatewaySettings.getEnvType() == 4098) ? HybridConstants.HYBRID_API_HOST : HybridConstants.HYBRID_API_URL_PRE;
        }

        public static DataProviderSettings getDataProvider() {
            return f5895g;
        }

        public static String getGatewayHost() {
            GatewaySettings gatewaySettings = f5893e;
            if (gatewaySettings != null && gatewaySettings.getEnvType() != 4098) {
                return a;
            }
            return b;
        }

        public static SimpleNonGatewaySettings getNonGatewaySettings() {
            return (SimpleNonGatewaySettings) f5894f;
        }

        public static boolean isUseGateway() {
            return f5892c;
        }

        public static boolean isUseHttp() {
            return d;
        }

        public static void setDataProvider(DataProviderSettings dataProviderSettings) {
            f5895g = dataProviderSettings;
        }

        public static void setGatewaySettings(GatewaySettings gatewaySettings) {
            f5892c = true;
            f5893e = gatewaySettings;
        }

        public static void setNonGatewaySettings(SimpleNonGatewaySettings simpleNonGatewaySettings) {
            f5892c = false;
            f5894f = simpleNonGatewaySettings;
        }

        public static void setUseGateway(boolean z) {
            f5892c = z;
        }

        public static void setUseHttp(boolean z) {
            d = z;
        }
    }

    public static void addDownloadConditionListener(DownloadConditionSettingListener downloadConditionSettingListener) {
        if (downloadConditionSettingListener == null) {
            return;
        }
        if (f5891e == null) {
            f5891e = new ArrayList();
        }
        f5891e.add(downloadConditionSettingListener);
    }

    public static Context getAppContext() {
        return f5890c;
    }

    public static String getAppKey() {
        return d;
    }

    @Deprecated
    public static boolean isBuildInOfflineDisable() {
        return HYBRID_BUILDIN_OFFLINE_DISABLE;
    }

    public static boolean isDebug() {
        return b;
    }

    public static boolean isDownloadedOfflineDisable() {
        return HYBRID_DOWNLOADED_OFFLINE_DISABLE;
    }

    public static boolean isInited() {
        return a;
    }

    public static boolean isPreloadDisable() {
        return HYBRID_PRELOAD_DISABLE;
    }

    public static void removeDownloadConditionListener(DownloadConditionSettingListener downloadConditionSettingListener) {
        List<DownloadConditionSettingListener> list;
        if (downloadConditionSettingListener == null || (list = f5891e) == null || list.isEmpty()) {
            return;
        }
        f5891e.remove(downloadConditionSettingListener);
    }

    public static void setAppContext(Context context) {
        f5890c = context;
        MAX_OFFLINE_FETCH_TIME = PreferenceUtils.getInt(context, HybridConstants.PreferenceKey.PREFERENCE_FETCH_TIME, MAX_OFFLINE_FETCH_TIME);
        CONDITION_DOWNLOAD_MAX_THREAD = PreferenceUtils.getInt(context, HybridConstants.PreferenceKey.PREFERENCE_CONDITION_THREAD, CONDITION_DOWNLOAD_MAX_THREAD);
        CONDITION_DOWNLOAD_NETWORK = PreferenceUtils.getStringSet(context, HybridConstants.PreferenceKey.PREFERENCE_CONDITION_NETWORK, CONDITION_DOWNLOAD_NETWORK);
        CONDITION_DOWNLOAD_NETWORK_IGNORE = PreferenceUtils.getBoolean(context, HybridConstants.PreferenceKey.PREFERENCE_CONDITION_NETWORK_IGNORE, CONDITION_DOWNLOAD_NETWORK_IGNORE);
        HYBRID_DOWNLOAD_RETRY = PreferenceUtils.getInt(f5890c, HybridConstants.PreferenceKey.PREFERENCE_HYBRID_DOWNLOAD_RETRY, HYBRID_DOWNLOAD_RETRY);
        LAST_SET_LP_TIME = PreferenceUtils.getLong(f5890c, HybridConstants.PreferenceKey.PREFERENCE_LAST_SET_LP_TIME, LAST_SET_LP_TIME);
        MAX_OFFLINE_PACK_COUNT = PreferenceUtils.getInt(f5890c, HybridConstants.PreferenceKey.PREFERENCE_MAX_OFFLINE_PACK, MAX_OFFLINE_PACK_COUNT);
        SP_RATIO = PreferenceUtils.getFloat(f5890c, HybridConstants.PreferenceKey.PREFERENCE_SP_RATIO, SP_RATIO);
    }

    public static void setAppKey(String str) {
        d = str;
    }

    @Deprecated
    public static void setBuildInOfflineDisable(boolean z) {
        HYBRID_BUILDIN_OFFLINE_DISABLE = z;
    }

    public static void setDebug(boolean z) {
        b = z;
    }

    public static void setDownloadConditionNetwork(int i2, List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet(list.size());
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                hashSet.add(str.toLowerCase());
            }
        }
        CONDITION_DOWNLOAD_NETWORK_IGNORE = i2 == 1;
        PreferenceUtils.putBoolean(f5890c, HybridConstants.PreferenceKey.PREFERENCE_CONDITION_NETWORK_IGNORE, i2 == 1);
        CONDITION_DOWNLOAD_NETWORK = hashSet;
        PreferenceUtils.putStringSet(f5890c, HybridConstants.PreferenceKey.PREFERENCE_CONDITION_NETWORK, hashSet);
        List<DownloadConditionSettingListener> list2 = f5891e;
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        Iterator<DownloadConditionSettingListener> it = f5891e.iterator();
        while (it.hasNext()) {
            it.next().onSettingChanged();
        }
    }

    public static void setDownloadConditionThread(int i2) {
        if (i2 > 0) {
            CONDITION_DOWNLOAD_MAX_THREAD = i2;
            PreferenceUtils.putInt(f5890c, HybridConstants.PreferenceKey.PREFERENCE_CONDITION_THREAD, i2);
            List<DownloadConditionSettingListener> list = f5891e;
            if (list == null || list.isEmpty()) {
                return;
            }
            Iterator<DownloadConditionSettingListener> it = f5891e.iterator();
            while (it.hasNext()) {
                it.next().onSettingChanged();
            }
        }
    }

    public static void setDownloadRetryCount(int i2) {
        HYBRID_DOWNLOAD_RETRY = i2;
        PreferenceUtils.putInt(f5890c, HybridConstants.PreferenceKey.PREFERENCE_HYBRID_DOWNLOAD_RETRY, i2);
    }

    public static void setDownloadedOfflineDisable(boolean z) {
        HYBRID_DOWNLOADED_OFFLINE_DISABLE = z;
    }

    public static void setInited(boolean z) {
        a = z;
    }

    public static void setLastSetLocalPriorityTime(long j2) {
        LAST_SET_LP_TIME = j2;
        PreferenceUtils.putLong(f5890c, HybridConstants.PreferenceKey.PREFERENCE_LAST_SET_LP_TIME, j2);
    }

    public static void setMaxHtmlPreFetchTime(int i2) {
        if (i2 > 0) {
            MAX_HTML_PRE_FETCH_TIME = i2;
        }
    }

    public static void setMaxOfflineFetchTime(int i2) {
        if (i2 > 0) {
            MAX_OFFLINE_FETCH_TIME = i2;
            PreferenceUtils.putInt(f5890c, HybridConstants.PreferenceKey.PREFERENCE_FETCH_TIME, i2);
        }
    }

    public static void setMaxOfflinePackCount(int i2) {
        if (i2 > 0) {
            MAX_OFFLINE_PACK_COUNT = i2;
            PreferenceUtils.putInt(f5890c, HybridConstants.PreferenceKey.PREFERENCE_MAX_OFFLINE_PACK, i2);
        }
    }

    public static void setPreloadDisable(boolean z) {
        HYBRID_PRELOAD_DISABLE = z;
    }

    public static void setSpRatio(float f2) {
        if (f2 < 0.0f || f2 > 1.0f) {
            return;
        }
        SP_RATIO = f2;
        PreferenceUtils.putFloat(f5890c, HybridConstants.PreferenceKey.PREFERENCE_SP_RATIO, f2);
    }
}
