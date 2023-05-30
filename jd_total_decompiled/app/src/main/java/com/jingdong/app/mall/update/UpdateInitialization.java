package com.jingdong.app.mall.update;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.appupdate.UpdateSharedPreferenceUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.GlobalInitialization;
import com.jingdong.common.utils.IDialogShow;
import com.jingdong.common.utils.IGrayUpdateLayerShow;
import com.jingdong.common.utils.ServerConfigFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.widget.JDToast;
import com.jingdong.sdk.jdupgrade.JDMallUpgradeInfoCallBack;
import com.jingdong.sdk.jdupgrade.JDUpgrade;
import com.jingdong.sdk.jdupgrade.VersionEntity;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes4.dex */
public class UpdateInitialization {
    private static final String KEY_GRAY_REQUEST_INTERVAL = "requestInterval";
    private static final String KEY_THRESHOLD = "threshold";
    private static final String TAG = "UpdateInitialization";
    private static GlobalInitialization globalInitialization;
    private static long lastGrayRequestTime;
    private static UpdateInitialization updateInitialization;
    public static VersionEntity upgradeEntity;
    public IDialogShow mIDialogShow;

    /* loaded from: classes4.dex */
    public class a implements ServerConfigFetcher.ServerConfigListener {
        a(UpdateInitialization updateInitialization) {
        }

        @Override // com.jingdong.common.utils.ServerConfigFetcher.ServerConfigListener
        public void onGetConfigSuccess() {
        }
    }

    /* loaded from: classes4.dex */
    public class b extends TimerTask {
        b() {
            UpdateInitialization.this = r1;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            UpdateInitialization.this.checkSoftwareUpdated(true, null, null);
            UpdateInitialization.this.reportFailEvent();
        }
    }

    /* loaded from: classes4.dex */
    public class c implements JDMallUpgradeInfoCallBack {
        final /* synthetic */ boolean a;
        final /* synthetic */ IGrayUpdateLayerShow b;

        /* renamed from: c */
        final /* synthetic */ IMyActivity f11711c;

        c(boolean z, IGrayUpdateLayerShow iGrayUpdateLayerShow, IMyActivity iMyActivity) {
            UpdateInitialization.this = r1;
            this.a = z;
            this.b = iGrayUpdateLayerShow;
            this.f11711c = iMyActivity;
        }

        @Override // com.jingdong.sdk.jdupgrade.JDMallUpgradeInfoCallBack
        public void onResult(VersionEntity versionEntity) {
            if (Log.D) {
                Log.d(UpdateInitialization.TAG, "JDUpgrade.requestJDMallUpgradeInfo= " + versionEntity);
            }
            try {
                if (versionEntity == null) {
                    UpdateInitialization.upgradeEntity = new VersionEntity();
                } else {
                    UpdateInitialization.upgradeEntity = versionEntity;
                }
                VersionEntity versionEntity2 = UpdateInitialization.upgradeEntity;
                versionEntity2.isAutoCheck = this.a;
                if (UpdateInitialization.isNoUpdate(versionEntity2)) {
                    UpdateInitialization.upgradeEntity.state = 300;
                }
                if (UpdateInitialization.upgradeEntity.state == 301) {
                    long unused = UpdateInitialization.lastGrayRequestTime = System.currentTimeMillis();
                    IGrayUpdateLayerShow iGrayUpdateLayerShow = this.b;
                    if (iGrayUpdateLayerShow != null) {
                        iGrayUpdateLayerShow.showGrayUpdateLayer(true);
                    }
                } else {
                    IGrayUpdateLayerShow iGrayUpdateLayerShow2 = this.b;
                    if (iGrayUpdateLayerShow2 != null) {
                        iGrayUpdateLayerShow2.showGrayUpdateLayer(false);
                    }
                }
                ApplicationUpgradeHelper.setDialogShow(UpdateInitialization.this.mIDialogShow);
                VersionEntity versionEntity3 = UpdateInitialization.upgradeEntity;
                if (versionEntity3.state == 300) {
                    UpdateInitialization.this.cleanCache();
                } else {
                    CommonBase.putStringToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_URL, versionEntity3.url);
                    CommonBase.putStringToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_VERSION, UpdateInitialization.upgradeEntity.version);
                    CommonBase.putLongToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_APK_SIZE, UpdateInitialization.upgradeEntity.size);
                    CommonBase.putStringToPreference("safeModeUsedApkUrl", UpdateInitialization.upgradeEntity.url);
                    CommonBase.putStringToPreference("safeModeUsedApkVersion", UpdateInitialization.upgradeEntity.version);
                }
                IMyActivity iMyActivity = this.f11711c;
                if (iMyActivity == null) {
                    iMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
                }
                ApplicationUpgradeHelper.tryUpgrade(iMyActivity, UpdateInitialization.upgradeEntity);
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(new Exception("JDUpgrade.requestJDMallUpgradeInfo-" + e2));
            }
        }
    }

    /* loaded from: classes4.dex */
    class d implements Runnable {
        d(UpdateInitialization updateInitialization) {
        }

        @Override // java.lang.Runnable
        public void run() {
            JDToast jDToast = new JDToast((Context) JdSdk.getInstance().getApplication(), (byte) 2);
            jDToast.setText("\u8be5\u7248\u672c\u5df2\u662f\u6700\u65b0\u7248\u672c");
            jDToast.setDuration(0);
            jDToast.show();
        }
    }

    public static void callBackDialogShowing() {
        if (Log.D) {
            Log.d(TAG, "callBackDialogShowing(true)-mIDialogShow = " + getUpdateInitializationInstance().mIDialogShow);
        }
        if (getUpdateInitializationInstance().mIDialogShow == null) {
            return;
        }
        getUpdateInitializationInstance().mIDialogShow.DialogDismiss(true);
    }

    private boolean checkAPK(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        List asList = Arrays.asList(str.split(DYConstants.DY_REGEX_COMMA));
        if (Log.D) {
            Log.d(TAG, "\u5c4f\u853d\u5e94\u7528\u5546\u5e97\u5217\u8868= " + asList.toString());
        }
        if (asList == null || asList.isEmpty()) {
            return false;
        }
        Iterator it = asList.iterator();
        while (it.hasNext()) {
            if (isAppExist((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static void checkDialogIsShowing(IDialogShow iDialogShow) {
        if (Log.D) {
            Log.d(TAG, "checkDialogIsShowing(false)-iDialogShow = " + iDialogShow);
        }
        if (iDialogShow == null) {
            return;
        }
        getUpdateInitializationInstance().mIDialogShow = iDialogShow;
        iDialogShow.DialogDismiss(false);
    }

    public static void checkMyJdGrayUpdate(IMyActivity iMyActivity) {
        VersionEntity versionEntity = upgradeEntity;
        if (versionEntity == null || iMyActivity == null) {
            return;
        }
        versionEntity.isAutoCheck = false;
        if (isNoUpdate(versionEntity)) {
            return;
        }
        ApplicationUpgradeHelper.setDialogShow(getUpdateInitializationInstance().mIDialogShow);
        ApplicationUpgradeHelper.tryUpgrade(iMyActivity, upgradeEntity);
    }

    public void checkSoftwareUpdated(boolean z, IMyActivity iMyActivity, IGrayUpdateLayerShow iGrayUpdateLayerShow) {
        if (JdSdk.getInstance().isGoogleChannel()) {
            return;
        }
        if (Configuration.getBooleanProperty(Configuration.APPLICATION_UPGRADE).booleanValue() || !z) {
            JDUpgrade.requestJDMallUpgradeInfo(new c(z, iGrayUpdateLayerShow, iMyActivity));
        }
    }

    public static void checkUpdateDialogIsShowing(IDialogShow iDialogShow) {
        if (iDialogShow == null) {
            return;
        }
        getUpdateInitializationInstance().mIDialogShow = iDialogShow;
    }

    public static void checkVersion(IMyActivity iMyActivity) {
        if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
            getUpdateInitializationInstance().checkSoftwareUpdated(false, iMyActivity, null);
        }
    }

    public void cleanCache() {
        CommonBase.putStringToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_URL, "");
        CommonBase.putStringToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_VERSION, "");
        CommonBase.putLongToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_APK_SIZE, -1L);
    }

    public static void cleanDialog() {
        getUpdateInitializationInstance().mIDialogShow = null;
    }

    private void deleteApkFile() {
        PackageInfo packageArchiveInfo;
        try {
            if (UpdateSharedPreferenceUtil.getBoolean("app_install_dialog_delete_status", false)) {
                String stringFromPreference = CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_INSTALL_FILE, "");
                File file = new File(stringFromPreference);
                if (file.exists() && (packageArchiveInfo = JdSdk.getInstance().getApplication().getPackageManager().getPackageArchiveInfo(stringFromPreference, 1)) != null) {
                    if (TextUtils.equals(PackageInfoUtil.getVersionName(), packageArchiveInfo.versionName)) {
                        if (PackageInfoUtil.getVersionCode() >= packageArchiveInfo.versionCode) {
                            file.delete();
                        }
                    } else if (isApkFileOlder(packageArchiveInfo.versionName, PackageInfoUtil.getVersionName())) {
                        file.delete();
                    }
                }
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    public static String getMyJdLayerImgUrl() {
        VersionEntity versionEntity = upgradeEntity;
        return versionEntity != null ? versionEntity.icon : "";
    }

    public static String getRemoteApkVersion() {
        VersionEntity versionEntity = upgradeEntity;
        return versionEntity != null ? versionEntity.version : "";
    }

    public static int getRequestIntervalValue() {
        try {
            String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, KEY_GRAY_REQUEST_INTERVAL, KEY_THRESHOLD);
            if (TextUtils.isEmpty(config)) {
                return 0;
            }
            return Integer.valueOf(config).intValue();
        } catch (NumberFormatException unused) {
            return 1800000;
        }
    }

    public static synchronized UpdateInitialization getUpdateInitializationInstance() {
        UpdateInitialization updateInitialization2;
        synchronized (UpdateInitialization.class) {
            if (updateInitialization == null) {
                updateInitialization = new UpdateInitialization();
                globalInitialization = GlobalInitialization.getGlobalInitializationInstance();
            }
            updateInitialization2 = updateInitialization;
        }
        return updateInitialization2;
    }

    private boolean isApkFileOlder(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        for (int i2 = 0; i2 < Math.max(split.length, split2.length) && i2 < split.length; i2++) {
            if (i2 >= split2.length) {
                return false;
            }
            try {
                int stringParseToInt = stringParseToInt(split[i2]);
                int stringParseToInt2 = stringParseToInt(split2[i2]);
                if (stringParseToInt != stringParseToInt2) {
                    return stringParseToInt2 > stringParseToInt;
                }
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDialogTextException(VersionEntity versionEntity) {
        return TextUtils.isEmpty(versionEntity.downloadTitle) || TextUtils.isEmpty(versionEntity.downloadText) || TextUtils.isEmpty(versionEntity.downloadConfirm) || TextUtils.isEmpty(versionEntity.downloadCancel) || TextUtils.isEmpty(versionEntity.installTitle) || TextUtils.isEmpty(versionEntity.installText) || TextUtils.isEmpty(versionEntity.installConfirm) || TextUtils.isEmpty(versionEntity.installCancel);
    }

    public static boolean isGrayWifiAutoDownload() {
        if (Log.D) {
            Log.d(TAG, "isGrayWifiAutoDownload()=" + ApplicationUpgradeHelper.isGrayWifiAutoDownload);
        }
        return ApplicationUpgradeHelper.isGrayWifiAutoDownload;
    }

    public static boolean isNoUpdate(VersionEntity versionEntity) {
        int i2;
        if (versionEntity != null && (i2 = versionEntity.state) > 300 && i2 <= 303 && !isDialogTextException(versionEntity)) {
            if ((versionEntity.state == 302 && versionEntity.isAutoCheck && versionEntity.isAutoDownload && NetUtils.isWifi() && UpdateSharedPreferenceUtil.getBoolean(Constants.UPGRADE_WIFI_AUTO_KEY, true, 1) && TextUtils.isEmpty(versionEntity.toast)) || TextUtils.isEmpty(versionEntity.url) || TextUtils.isEmpty(versionEntity.version) || versionEntity.size <= 0 || TextUtils.isEmpty(versionEntity.fileMd5) || TextUtils.isEmpty(versionEntity.md5)) {
                return true;
            }
            if (versionEntity.state == 301 && getUpdateInitializationInstance().checkAPK(versionEntity.packageList)) {
                return true;
            }
            return !com.jingdong.app.mall.f.a.b(versionEntity.url, versionEntity.md5);
        }
        return true;
    }

    private synchronized void networkInitialization() {
        if (Log.D) {
            Log.d(TAG, "GlobalInitialization networkInitialization() -->> ");
        }
        BaseFrameUtil baseFrameUtil = BaseFrameUtil.getInstance();
        if (Log.D) {
            Log.d(TAG, "GlobalInitialization myApplication.networkInitializationState -->> " + baseFrameUtil.networkInitializationState);
        }
        if (baseFrameUtil.networkInitializationState == 0) {
            baseFrameUtil.networkInitializationState = 1;
            GlobalInitialization globalInitialization2 = globalInitialization;
            globalInitialization2.alreadyDevice = false;
            globalInitialization2.alreadyConfig = false;
            networkInitializationStart();
            GlobalInitialization globalInitialization3 = globalInitialization;
            if (globalInitialization3.alreadyDevice && globalInitialization3.alreadyConfig) {
                baseFrameUtil.networkInitializationState = 2;
            }
        }
    }

    private void networkInitializationStart() {
        if (Log.D) {
            Log.d(TAG, "GlobalInitialization networkInitializationStart() -->> ");
        }
        if (GlobalInitialization.canDoDevice()) {
            globalInitialization.registerDevice(GlobalInitialization.isDeviceFirstCalled());
            if (Log.D) {
                Log.d(TAG, "ready to do device CMD-->> ");
            }
        } else {
            globalInitialization.alreadyDevice = true;
        }
        globalInitialization.setTaskBeginFlag(1);
        globalInitialization.serverConfig(new a(this));
        if (GlobalInitialization.canDoABTest()) {
            if (Log.D) {
                Log.d(TAG, "ready to ABTest CMD -->> ");
            }
            ABTestUtils.initABData(globalInitialization.getHttpGroup());
        }
        new Timer().schedule(new b(), Configuration.getIntegerProperty(Configuration.ROUTINE_CHECK_DELAY_TIME).intValue());
    }

    public void reportFailEvent() {
        long longFromPreference = CommonBase.getLongFromPreference("app_install_time", 0L);
        long appLastModified = ApplicationUpgradeHelper.getAppLastModified(JdSdk.getInstance().getApplication());
        String stringFromPreference = CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_VERSION, "");
        if (Log.D) {
            Log.d(TAG, "reportFailEvent-installTime=" + longFromPreference + " currentInstallTime=" + appLastModified + " apkVersion=" + stringFromPreference);
        }
        if (longFromPreference == 0 || appLastModified == 0 || longFromPreference != appLastModified) {
            return;
        }
        int versionCode = PackageInfoUtil.getVersionCode();
        if (CommonBase.getBooleanFromPreference("app_install_code" + versionCode, Boolean.TRUE).booleanValue()) {
            ApplicationUpgradeHelper.reportFailEvent(stringFromPreference, "install fail");
            CommonBase.putBooleanToPreference("app_install_code" + versionCode, Boolean.FALSE);
        }
    }

    public static void showMyJdGrayUpdateLayer(IMyActivity iMyActivity, IGrayUpdateLayerShow iGrayUpdateLayerShow) {
        if (!showMyJdLayer()) {
            if (iGrayUpdateLayerShow != null) {
                iGrayUpdateLayerShow.showGrayUpdateLayer(false);
                return;
            }
            return;
        }
        long requestIntervalValue = getRequestIntervalValue();
        if (Log.D) {
            Log.d(TAG, "showMyJdGrayUpdateLayer()-requestInterval = " + requestIntervalValue);
        }
        if (requestIntervalValue > 0) {
            if (System.currentTimeMillis() - lastGrayRequestTime >= requestIntervalValue) {
                if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
                    getUpdateInitializationInstance().checkSoftwareUpdated(true, iMyActivity, iGrayUpdateLayerShow);
                }
            } else if (iGrayUpdateLayerShow != null) {
                iGrayUpdateLayerShow.showGrayUpdateLayer(true);
            }
        } else if (requestIntervalValue != 0 || BaseFrameUtil.getInstance().getCurrentMyActivity() == null) {
        } else {
            getUpdateInitializationInstance().checkSoftwareUpdated(true, iMyActivity, iGrayUpdateLayerShow);
        }
    }

    public static boolean showMyJdLayer() {
        VersionEntity versionEntity = upgradeEntity;
        return (versionEntity == null || versionEntity.state != 301 || isNoUpdate(versionEntity)) ? false : true;
    }

    public static boolean showMyJdRedPoint() {
        VersionEntity versionEntity = upgradeEntity;
        return (versionEntity == null || !versionEntity.myJdSettings || isNoUpdate(versionEntity)) ? false : true;
    }

    public static boolean showMyJdUserSettingsRedPoint() {
        VersionEntity versionEntity = upgradeEntity;
        return (versionEntity == null || !versionEntity.myJdUserSettings || isNoUpdate(versionEntity)) ? false : true;
    }

    private void showNoUpdate(IMyActivity iMyActivity) {
        iMyActivity.post(new d(this));
    }

    private int stringParseToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public void initNetwork() {
        if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
            if (Log.D) {
                Log.d(TAG, "GlobalInitialization initNetwork() -->> ");
            }
            networkInitialization();
            deleteApkFile();
        }
    }

    public boolean isAppExist(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            PackageManager packageManager = JdSdk.getInstance().getApplicationContext().getPackageManager();
            if (packageManager != null) {
                packageManager.getPackageInfo(str, 256);
                z = true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (Log.D) {
            Log.d(TAG, "isAppExist=" + z);
        }
        return z;
    }
}
