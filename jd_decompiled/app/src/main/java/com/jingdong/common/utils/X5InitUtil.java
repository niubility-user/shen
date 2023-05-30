package com.jingdong.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.common.web.Web64BitFixer;
import com.jingdong.common.web.WebDebug;
import com.jingdong.common.web.WebLoginHelper;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebPerformance;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.lib.monitor.a;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsPrivacyAccess;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.utils.Md5Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class X5InitUtil {
    static final String TAG = "X5InitUtil";
    public static long mInitTime;
    public static List<WebCoreLoadListener> webCoreLoadListeners = new ArrayList();
    private static volatile boolean hasPreInited = false;
    private static boolean isInitFinished = false;
    private static volatile boolean uuidSet = false;

    /* loaded from: classes6.dex */
    public interface WebCoreLoadListener {
        void onCoreLoaded(boolean z);
    }

    public static boolean checkDisableX5Core() {
        try {
            if (isCoreUnavailable(WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication()))) {
                OKLog.d(TAG, "isCoreUnavailable is true, forceSysWebView");
                QbSdk.forceSysWebView();
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void clearImeiInfoFromSp(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("DENGTA_META", 0).edit();
            edit.putString("IMEI_DENGTA", "");
            edit.apply();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    @Deprecated
    private static void deleteCacheFile64() {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 24 && SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WB_DEL_FOR_64, false) && !SharedPreferencesUtil.getBoolean(SwitchQueryFetcher.WB_DEL_FOR_64, false)) {
            z = true;
        }
        if (z && Web64BitFixer.deleteCacheFilesFor64()) {
            SharedPreferencesUtil.putBoolean(SwitchQueryFetcher.WB_DEL_FOR_64, true);
        }
    }

    private static boolean isCoreUnavailable(int i2) {
        return (Build.VERSION.SDK_INT > 28 && i2 > 0 && i2 < 45114) || WebUtils.isX5CoreVerForbid(JdSdk.getInstance().getApplication());
    }

    public static boolean isInitFinished() {
        return isInitFinished;
    }

    public static boolean isX5FirstInitial() {
        return SharedPreferencesUtil.getBoolean("webViewFirstInitial", true);
    }

    public static void notifyWebCoreLoaded(boolean z) {
        OKLog.d(TAG, "notifyWebCoreLoaded:" + webCoreLoadListeners);
        for (int i2 = 0; i2 < webCoreLoadListeners.size(); i2++) {
            webCoreLoadListeners.get(i2).onCoreLoaded(z);
        }
        webCoreLoadListeners.clear();
    }

    public static void preloadX5(final Context context) {
        if (hasPreInited) {
            return;
        }
        if (!JDPrivacyHelper.isAcceptPrivacy(context)) {
            QbSdk.forceSysWebView();
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        setIdForX5();
        boolean isMainProcess = ProcessUtil.isMainProcess();
        String str = TAG;
        OKLog.d(str, "preloadX5,hasPreInited:" + hasPreInited + "  isMainProcess:" + isMainProcess);
        if (isMainProcess) {
            hasPreInited = true;
            if (SwitchQueryFetcher.getSwitchBooleanValue("x5Disable", false) || isCoreUnavailable(WebView.getTbsCoreVersion(context))) {
                OKLog.d(str, "preloadX5, switch or coreUnavailable is true, forceSysWebView");
                QbSdk.forceSysWebView();
            }
            if (!JdSdk.getInstance().isGoogleChannel()) {
                QbSdk.canGetAndroidId(false);
                QbSdk.canGetDeviceId(false);
                QbSdk.canGetSubscriberId(false);
                TbsPrivacyAccess.AndroidVersion.setEnabled(false);
                TbsPrivacyAccess.DeviceModel.setEnabled(false);
                if (SwitchQueryFetcher.getSwitchBooleanValue("x5_av", false)) {
                    TbsPrivacyAccess.configurePrivacy(context, TbsPrivacyAccess.ConfigurablePrivacy.ANDROID_VERSION, String.valueOf(BaseInfo.getAndroidSDKVersion()));
                }
                TbsPrivacyAccess.configurePrivacy(context, TbsPrivacyAccess.ConfigurablePrivacy.DEVICE_MODEL, Md5Utils.getMD5("jd_" + BaseInfo.getDeviceManufacture()));
            }
            QbSdk.setTbsListener(new TbsListener() { // from class: com.jingdong.common.utils.X5InitUtil.1
                @Override // com.tencent.smtt.sdk.TbsListener
                public void onDownloadFinish(int i2) {
                    OKLog.d(X5InitUtil.TAG, "onDownloadFinish");
                }

                @Override // com.tencent.smtt.sdk.TbsListener
                public void onDownloadProgress(int i2) {
                    OKLog.d(X5InitUtil.TAG, "onDownloadProgress");
                }

                @Override // com.tencent.smtt.sdk.TbsListener
                public void onInstallFinish(int i2) {
                    OKLog.d(X5InitUtil.TAG, "onInstallFinish");
                }
            });
            HashMap hashMap = new HashMap();
            Boolean bool = Boolean.TRUE;
            hashMap.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, bool);
            hashMap.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, bool);
            QbSdk.initTbsSettings(hashMap);
            QbSdk.disableAutoCreateX5Webview();
            try {
                mInitTime = System.currentTimeMillis();
                QbSdk.preInit(context, new QbSdk.PreInitCallback() { // from class: com.jingdong.common.utils.X5InitUtil.2
                    @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
                    public void onCoreInitFinished() {
                        if (OKLog.D) {
                            String str2 = X5InitUtil.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("onCoreInitFinished, on thread = ");
                            sb.append(Looper.myLooper() == Looper.getMainLooper() ? "main thread" : "sub thread");
                            OKLog.d(str2, sb.toString());
                        }
                    }

                    @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
                    public void onViewInitFinished(boolean z) {
                        if (OKLog.D) {
                            String str2 = X5InitUtil.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("onViewInitFinished, on thread = ");
                            sb.append(Looper.myLooper() == Looper.getMainLooper() ? "main thread" : "sub thread");
                            OKLog.d(str2, sb.toString());
                        }
                        if (z) {
                            z = !X5InitUtil.checkDisableX5Core();
                        }
                        boolean unused = X5InitUtil.isInitFinished = true;
                        X5InitUtil.reportCoreInitPerformance(context, currentTimeMillis, System.currentTimeMillis(), z, null);
                        WebUtils.clearCookieWhenStartup();
                        WebLoginHelper.preCreateWebView();
                        X5InitUtil.clearImeiInfoFromSp(context);
                        X5InitUtil.notifyWebCoreLoaded(z);
                        X5InitUtil.setX5HasFirstInitial();
                        X5InitUtil.saveX5CrashInfo();
                    }
                });
            } catch (Throwable th) {
                isInitFinished = true;
                ExceptionReporter.reportWebViewCommonError("async_cookie_error", "", "X5InitUtil-preloadX5", th.getMessage());
                reportCoreInitPerformance(context, currentTimeMillis, System.currentTimeMillis(), false, ExceptionReporter.getStackStringFromException(th));
                WebUtils.clearCookieWhenStartup();
            }
        }
    }

    public static void registerWebCoreLoadListener(WebCoreLoadListener webCoreLoadListener) {
        if (webCoreLoadListener != null) {
            webCoreLoadListeners.add(webCoreLoadListener);
        }
    }

    public static void reportCoreInitPerformance(Context context, long j2, long j3, boolean z, String str) {
        try {
        } catch (Throwable th) {
            if (Log.E) {
                Log.e(TAG, th.getMessage(), th);
            }
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue("wvNoReportCoreInit", false)) {
            return;
        }
        WebPerformance webPerformance = new WebPerformance(System.currentTimeMillis());
        webPerformance.appendData(WebPerfManager.PAGE_TYPE, "CoreInit");
        webPerformance.appendData("initStart", String.valueOf(j2));
        webPerformance.appendData(WebPerfManager.INIT_FINISH, String.valueOf(j3));
        webPerformance.appendData(WebPerfManager.CORE_TYPE, z ? "x5" : "system");
        webPerformance.appendData(WebPerfManager.CORE_VER, String.valueOf(QbSdk.getTbsVersion(context)));
        if (!TextUtils.isEmpty(str)) {
            webPerformance.appendExtra(IAudioPlayer.AUDIO_STATE_ERROR, str);
        }
        WebPerfManager.getInstance().reportRecord(webPerformance, false);
        WebDebug.log("webview", "X5 inited, is X5:" + z);
    }

    public static void saveX5CrashInfo() {
        SharedPreferences.Editor a = a.a();
        a.putInt("tbsSdkVersion", WebView.getTbsSDKVersion(JdSdk.getInstance().getApplication()));
        a.putInt("tbsCoreVersion", WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication()));
        a.apply();
    }

    public static void setIdForX5() {
        String str;
        if (uuidSet) {
            return;
        }
        String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
        try {
            str = Base64.encodeToString(readDeviceUUID.getBytes("UTF-8"), 1).trim();
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, "encrypt error: " + th.getMessage(), th);
            }
            str = "unknownUid";
        }
        if (OKLog.D) {
            OKLog.d(TAG, "setIdForX5, rawUid = " + readDeviceUUID + ", encryptedId = " + str);
        }
        QbSdk.setUUID(str);
        uuidSet = true;
    }

    public static void setX5HasFirstInitial() {
        if (isX5FirstInitial()) {
            SharedPreferencesUtil.putBoolean("webViewFirstInitial", false);
        }
    }
}
