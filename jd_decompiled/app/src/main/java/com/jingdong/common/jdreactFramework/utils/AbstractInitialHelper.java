package com.jingdong.common.jdreactFramework.utils;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginListenerNew;
import com.jingdong.common.jdreactFramework.download.PluginLocalDownloadInfo;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateRequest;
import com.jingdong.common.jdreactFramework.lifecycle.LifecycleReporter;
import com.jingdong.common.jdreactFramework.track.RenderDataReport;
import com.jingdong.common.jdreactFramework.utils.JDFlutterUtils;

/* loaded from: classes5.dex */
public abstract class AbstractInitialHelper {
    public static final String TAG = "InitialHelper";
    protected boolean isScanEnter;
    public Activity mActivity;
    public String mDeprecatedVersion;
    public String mDownloadName;
    public boolean mLoadingCompletely;
    public PluginListenerNew mPluginListener;
    protected PluginVersion mPluginVersion;
    public String mReportDataHashCode;
    public UIHandler mUIHandler;
    public String reactBundle;
    public String reactModule;
    public boolean mForceCheckUpdate = false;
    public boolean mForceLoadAfterUpdateCheck = false;
    public boolean failed = false;
    public boolean force = false;
    public String version = "0.0";
    public String pluginCommonVersion = "0.0";

    /* loaded from: classes5.dex */
    public interface UIHandler {
        void clearFresco();

        void downloadFinish();

        boolean isOpenLoadingView();

        void onDownloadMetaData(boolean z);

        void sendLaunchMta(String str, String str2);

        void setPVMta(boolean z);

        void showMpage();

        void showProgressBar();

        void showRetry();
    }

    public static NetworkInfo getActiveNetworkInfo(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return null;
        }
        try {
            return connectivityManager.getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBusinessPck(String str) {
        UIHandler uIHandler;
        PluginLocalDownloadInfo downLoadingStatus = ReactSharedPreferenceUtils.getDownLoadingStatus(this.mDownloadName);
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, " now \uff1a" + downLoadingStatus);
        }
        boolean z = false;
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(this.mActivity, this.mDownloadName, false);
        if (TextUtils.equals("success", downLoadingStatus.status) && pluginDir != null && VersionUtils.compareVersion(pluginDir.pluginVersion, str) >= 0) {
            this.mLoadingCompletely = true;
        } else if (!isNetworkAvailable() && (uIHandler = this.mUIHandler) != null) {
            uIHandler.showRetry();
            return;
        } else if (!this.isScanEnter && isShowDegradeFirst(this.mDownloadName, this.mUIHandler, this.mActivity)) {
            ReactNativeUpdateManager.getInstance().addForceDownloadTask(this.mDownloadName, true, null);
            return;
        } else if (ReactNativeUpdateManager.getInstance().addForceDownloadTaskListener(this.mDownloadName, createLoadingListener())) {
            z = true;
        } else {
            this.mLoadingCompletely = true;
        }
        if (z) {
            RenderDataReport.getInstance().hasDownload(this.mReportDataHashCode, DYConstants.DY_TRUE);
            UIHandler uIHandler2 = this.mUIHandler;
            if (uIHandler2 != null) {
                uIHandler2.showProgressBar();
                return;
            }
            return;
        }
        RenderDataReport.getInstance().hasDownload(this.mReportDataHashCode, DYConstants.DY_FALSE);
        this.mLoadingCompletely = true;
        setupUI(true);
    }

    public PluginListenerNew createLoadingListener() {
        PluginListenerNew pluginListenerNew = this.mPluginListener;
        if (pluginListenerNew != null) {
            return pluginListenerNew;
        }
        PluginListenerNew pluginListenerNew2 = new PluginListenerNew() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.4
            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onDownloadProgressChanged(int i2) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFailure(String str, String str2, boolean z, String str3) {
                AbstractInitialHelper abstractInitialHelper = AbstractInitialHelper.this;
                if (abstractInitialHelper.mUIHandler == null || abstractInitialHelper.mActivity.isFinishing()) {
                    return;
                }
                AbstractInitialHelper.this.mUIHandler.showMpage();
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                AbstractInitialHelper abstractInitialHelper = AbstractInitialHelper.this;
                if (abstractInitialHelper.mLoadingCompletely || abstractInitialHelper.mUIHandler == null || abstractInitialHelper.mActivity.isFinishing()) {
                    return;
                }
                if (TextUtils.equals(AbstractInitialHelper.this.mDownloadName, JDReactConstant.JDFLUTTER_PACKAGE_NAME)) {
                    JDFlutterUtils.exchangeFiles(new JDFlutterUtils.ExchangeLisener() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.4.1
                        @Override // com.jingdong.common.jdreactFramework.utils.JDFlutterUtils.ExchangeLisener
                        public void onLoadSuccess(boolean z) {
                            AbstractInitialHelper.this.mUIHandler.downloadFinish();
                        }
                    }, JDReactConstant.JDFLUTTER_PACKAGE_NAME);
                } else {
                    AbstractInitialHelper.this.mUIHandler.downloadFinish();
                }
            }
        };
        this.mPluginListener = pluginListenerNew2;
        return pluginListenerNew2;
    }

    public void forceUpdate(final String str) {
        UIHandler uIHandler = this.mUIHandler;
        if (uIHandler != null) {
            uIHandler.showProgressBar();
        }
        ReactSharedPreferenceUtils.putLastCheckTime();
        ReactNativeUpdateRequest.getInstance(this.mActivity).sendReactUpdateRequest(ReactVersionUtils.getMergedPluginVersionLists(), new ReactNativeUpdateRequest.RequestCallback() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.3
            @Override // com.jingdong.common.jdreactFramework.download.ReactNativeUpdateRequest.RequestCallback
            public void onResult(boolean z) {
                AbstractInitialHelper abstractInitialHelper = AbstractInitialHelper.this;
                if (abstractInitialHelper.mUIHandler == null || abstractInitialHelper.mActivity.isFinishing()) {
                    return;
                }
                if (z) {
                    String servicePluginVersion = ReactNativeUpdateManager.getInstance().getServicePluginVersion(AbstractInitialHelper.this.mDownloadName);
                    if (VersionUtils.compareVersion(str, servicePluginVersion) < 0) {
                        AbstractInitialHelper.this.handleBusinessPck(servicePluginVersion);
                        return;
                    } else {
                        AbstractInitialHelper.this.mUIHandler.showMpage();
                        return;
                    }
                }
                AbstractInitialHelper.this.mUIHandler.onDownloadMetaData(false);
            }
        }, this.reactBundle, false, true);
    }

    public ConnectivityManager getConnectivityManager() {
        try {
            return (ConnectivityManager) this.mActivity.getSystemService("connectivity");
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = getConnectivityManager();
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, " isNetworkAvailable -->> connectivityManager " + connectivityManager);
        }
        boolean z = false;
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(connectivityManager);
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, " isNetworkAvailable -->> result " + z2);
        }
        if (z2) {
            return z2;
        }
        try {
            Thread.sleep(100L);
        } catch (Exception unused) {
        }
        ConnectivityManager connectivityManager2 = getConnectivityManager();
        if (connectivityManager2 == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo2 = getActiveNetworkInfo(connectivityManager2);
        if (activeNetworkInfo2 != null && activeNetworkInfo2.isConnectedOrConnecting()) {
            z = true;
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, " isNetworkAvailable -->> retry result " + z);
        }
        return z;
    }

    public boolean isShowDegradeFirst(String str, UIHandler uIHandler, Activity activity) {
        if (ReactModuleAvailabilityUtils.isShowDegradeFirst(str)) {
            if (uIHandler == null || activity == null || activity.isFinishing()) {
                return true;
            }
            uIHandler.showMpage();
            return true;
        }
        return false;
    }

    public void setDeprecatedVersion(String str) {
        this.mDeprecatedVersion = str;
    }

    public void setForceCheckUpdate(boolean z) {
        this.mForceCheckUpdate = z;
    }

    public void setForceLoadAfterUpdateCheck(boolean z) {
        this.mForceLoadAfterUpdateCheck = z;
    }

    public void setUIHandler(UIHandler uIHandler) {
        this.mUIHandler = uIHandler;
    }

    public abstract void setupLayout(boolean z);

    public void setupUI(final boolean z) {
        JDFlutterUtils.exchangeFiles(new JDFlutterUtils.ExchangeLisener() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.1
            @Override // com.jingdong.common.jdreactFramework.utils.JDFlutterUtils.ExchangeLisener
            public void onLoadSuccess(boolean z2) {
                AbstractInitialHelper.this.setupLayout(z);
            }
        }, this.mDownloadName);
    }

    public void startToGetMetaData() {
        if (JDReactConstant.JDFLUTTER_PACKAGE_NAME.equals(this.mDownloadName)) {
            this.failed = false;
            if (this.mUIHandler == null || this.mActivity.isFinishing()) {
                return;
            }
            this.mUIHandler.onDownloadMetaData(true);
            return;
        }
        UIHandler uIHandler = this.mUIHandler;
        if (uIHandler != null) {
            uIHandler.showProgressBar();
        }
        if (ReactNativeUpdateManager.getInstance().getDownloadInfo(this.reactModule) != null) {
            startToInit();
            return;
        }
        ReactSharedPreferenceUtils.putLastCheckTime();
        ReactNativeUpdateRequest.getInstance(this.mActivity).sendReactUpdateRequest(ReactVersionUtils.getMergedPluginVersionLists(), new ReactNativeUpdateRequest.RequestCallback() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.2
            @Override // com.jingdong.common.jdreactFramework.download.ReactNativeUpdateRequest.RequestCallback
            public void onResult(boolean z) {
                AbstractInitialHelper abstractInitialHelper = AbstractInitialHelper.this;
                if (abstractInitialHelper.mUIHandler == null || abstractInitialHelper.mActivity.isFinishing()) {
                    return;
                }
                AbstractInitialHelper abstractInitialHelper2 = AbstractInitialHelper.this;
                if (abstractInitialHelper2.mForceLoadAfterUpdateCheck) {
                    abstractInitialHelper2.mUIHandler.onDownloadMetaData(true);
                    return;
                }
                abstractInitialHelper2.failed = !z;
                abstractInitialHelper2.mUIHandler.onDownloadMetaData(z);
            }
        }, this.reactBundle, true, true);
    }

    public void startToInit() {
        UIHandler uIHandler = this.mUIHandler;
        if (uIHandler != null) {
            uIHandler.clearFresco();
            this.mUIHandler.setPVMta(false);
        }
        this.mDownloadName = this.reactModule;
        if (ReactNativeUpdateManager.getInstance().isItForceUpdate(this.reactBundle)) {
            this.mDownloadName = this.reactBundle;
        } else if (ReactNativeUpdateManager.getInstance().isItForceUpdate(this.reactModule)) {
            this.mDownloadName = this.reactModule;
        } else {
            this.mDownloadName = this.reactModule;
        }
        startToInit(false);
    }

    public void startToInit1() {
        JLog.d(TAG, " starttime -->> " + System.currentTimeMillis());
        LifecycleReporter.dispatchCreate(this.reactModule);
        if (!this.mForceCheckUpdate && !this.failed) {
            startToInit();
        } else if (!isNetworkAvailable()) {
            UIHandler uIHandler = this.mUIHandler;
            if (uIHandler != null) {
                uIHandler.showRetry();
            }
        } else {
            startToGetMetaData();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void startToInit(boolean r5) {
        /*
            r4 = this;
            com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager r0 = com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager.getInstance()
            java.lang.String r1 = r4.mDownloadName
            java.lang.String r0 = r0.getServicePluginVersion(r1)
            boolean r1 = r4.isScanEnter
            if (r1 == 0) goto L12
            r4.handleBusinessPck(r0)
            return
        L12:
            android.app.Activity r1 = r4.mActivity
            java.lang.String r2 = r4.mDownloadName
            r3 = 1
            r5 = r5 ^ r3
            com.jingdong.common.jdreactFramework.download.PluginVersion r5 = com.jingdong.common.jdreactFramework.download.ReactNativeFileManager.getPluginDir(r1, r2, r5)
            r4.mPluginVersion = r5
            r1 = 0
            if (r5 == 0) goto L35
            java.lang.String r5 = r5.pluginDir
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L35
            com.jingdong.common.jdreactFramework.download.PluginVersion r5 = r4.mPluginVersion
            boolean r2 = r5.full
            if (r2 != 0) goto L33
            boolean r5 = r5.splitEnable
            if (r5 == 0) goto L35
        L33:
            r5 = 1
            goto L36
        L35:
            r5 = 0
        L36:
            if (r5 == 0) goto La7
            com.jingdong.common.jdreactFramework.JDReactHelper r5 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()
            java.lang.String r2 = r4.mDownloadName
            java.lang.String r5 = r5.getDeprecatedVersion(r2)
            java.lang.String r2 = r4.mDeprecatedVersion
            int r2 = com.jingdong.common.jdreactFramework.utils.VersionUtils.compareVersion(r2, r5)
            if (r2 < 0) goto L4c
            java.lang.String r5 = r4.mDeprecatedVersion
        L4c:
            r4.mDeprecatedVersion = r5
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L66
            java.lang.String r5 = r4.mDeprecatedVersion
            com.jingdong.common.jdreactFramework.download.PluginVersion r2 = r4.mPluginVersion
            java.lang.String r2 = r2.pluginVersion
            int r5 = com.jingdong.common.jdreactFramework.utils.VersionUtils.compareVersion(r5, r2)
            if (r5 < 0) goto L66
            java.lang.String r5 = r4.mDeprecatedVersion
            r4.forceUpdate(r5)
            goto Lb4
        L66:
            com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager r5 = com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager.getInstance()
            java.lang.String r2 = r4.mDownloadName
            boolean r5 = r5.isItForceUpdate(r2)
            if (r5 != 0) goto La3
            boolean r5 = r4.force
            if (r5 != 0) goto La3
            java.lang.String r5 = r4.mDownloadName
            int r5 = com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils.getUpdateLevel(r5)
            r2 = 2
            if (r5 != r2) goto L80
            goto La3
        L80:
            com.jingdong.common.jdreactFramework.download.PluginVersion r5 = r4.mPluginVersion
            java.lang.String r5 = r5.pluginVersion
            int r5 = com.jingdong.common.jdreactFramework.utils.VersionUtils.compareVersion(r5, r0)
            if (r5 >= 0) goto L9d
            java.lang.String r5 = r4.mDownloadName
            int r5 = com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils.getUpdateLevel(r5)
            r0 = 3
            if (r5 != r0) goto L9d
            com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager r5 = com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager.getInstance()
            java.lang.String r0 = r4.mDownloadName
            r2 = 0
            r5.addDownloadTask(r0, r2, r1, r1)
        L9d:
            r4.setupUI(r1)
            r4.mLoadingCompletely = r3
            goto Lb4
        La3:
            r4.handleBusinessPck(r0)
            goto Lb4
        La7:
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 == 0) goto Lb1
            r4.startToGetMetaData()
            goto Lb4
        Lb1:
            r4.handleBusinessPck(r0)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.startToInit(boolean):void");
    }
}
