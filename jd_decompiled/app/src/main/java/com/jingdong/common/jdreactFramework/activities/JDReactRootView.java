package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.JDReactPackageDemo;
import com.jingdong.common.jdreactFramework.download.PluginListenerNew;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.helper.IReactPackagesFactory;
import com.jingdong.common.jdreactFramework.helper.InitErrorProcessor;
import com.jingdong.common.jdreactFramework.helper.LoadExceptionListener;
import com.jingdong.common.jdreactFramework.preload.EngineHelper;
import com.jingdong.common.jdreactFramework.preload.JDReactCommonPreloadManager;
import com.jingdong.common.jdreactFramework.track.RenderDataReport;
import com.jingdong.common.jdreactFramework.track.TrackUtil;
import com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import com.jingdong.common.jdreactFramework.utils.SpeicalMtaUtil;
import com.jingdong.common.jdreactFramework.widgets.JDReactProgressBar;
import com.jingdong.jdreactframewok.R;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.List;

/* loaded from: classes5.dex */
public class JDReactRootView extends LinearLayout implements AbstractInitialHelper.UIHandler, InitErrorProcessor {
    private static final int DISMISS_LOADING = 1008;
    private static final int DOWNLOAD_FAILED = 1001;
    private static final int DOWNLOAD_FINISHED = 1002;
    private static final int DOWNLOAD_META_FAILED = 1005;
    private static final int DOWNLOAD_META_FINISHED = 1006;
    private static final int SHOW_LOADING = 1007;
    private static final String TAG = "JDReactFragment";
    private static ReactPackage mJDReactPackage;
    private String bundlePath;
    private String commitId;
    private String commonPath;
    boolean failed;
    private boolean isLoadedPreset;
    AbstractJDReactInitialHelper mAbstractJDReactInitialHelper;
    private Activity mActivity;
    BackEventHandle mBackEventHandle;
    private EngineHelper mEngineHelper;
    boolean mForceCheckUpdate;
    boolean mForceLoadAfterUpdateCheck;
    private Handler mHandler;
    protected JDReactCallback mJDReactCallback;
    protected JDReactLoadingCallback mJDReactLoadingCallback;
    private LoadExceptionListener mLoadExceptionListener;
    boolean mLoadingCompletely;
    boolean mLoadingDone;
    private LinearLayout mNoDataView;
    PluginListenerNew mPluginListener;
    private IReactPackagesFactory mReactPackagesFactory;
    private ReactRootView mReactRootView;
    private int mType;
    RelativeLayout modal;
    private boolean preload;
    View progressBar;
    private int progressSizeHeight;
    private int progressSizeWidth;
    TextView progressTextView;
    private String reactBundle;
    private String reactModule;
    private Bundle reactParams;
    private String reportDataHashCode;
    private boolean splitbundle;
    private String version;

    /* loaded from: classes5.dex */
    public interface BackEventHandle {
        boolean OnBackKeyPress();
    }

    /* loaded from: classes5.dex */
    public static final class Builder {
        Activity ac;
        String commonPath;
        ReactPackage jdreactPackage;
        JDReactCallback mJDReactCallback;
        JDReactLoadingCallback mJDReactLoadingCallback;
        int progressSizeHeight;
        int progressSizeWidth;
        String reactBundle;
        String reactModule;
        Bundle reactParams;
        boolean preload = false;
        int type = 0;
        String version = "0.0";
        String commitId = "0";
        boolean mForceLoadAfterUpdateCheck = false;
        boolean mForceCheckUpdate = false;

        public Builder activity(Activity activity) {
            this.ac = activity;
            return this;
        }

        public JDReactRootView build() {
            return new JDReactRootView(this);
        }

        public Builder callback(JDReactCallback jDReactCallback) {
            this.mJDReactCallback = jDReactCallback;
            return this;
        }

        public Builder commitId(String str) {
            this.commitId = str;
            return this;
        }

        public Builder commonPath(String str) {
            this.commonPath = str;
            return this;
        }

        public Builder forceCheckUpdate(boolean z) {
            this.mForceCheckUpdate = z;
            return this;
        }

        public Builder forceLoadAfterUpdateCheck(boolean z) {
            this.mForceLoadAfterUpdateCheck = z;
            return this;
        }

        public Builder loadingCallback(JDReactLoadingCallback jDReactLoadingCallback) {
            this.mJDReactLoadingCallback = jDReactLoadingCallback;
            return this;
        }

        public Builder module(String str) {
            this.reactBundle = str;
            this.reactModule = str;
            return this;
        }

        public Builder params(Bundle bundle) {
            this.reactParams = bundle;
            return this;
        }

        public Builder preload(boolean z) {
            this.preload = z;
            return this;
        }

        public Builder progressSize(int i2, int i3) {
            this.progressSizeHeight = i2;
            this.progressSizeWidth = i3;
            return this;
        }

        public Builder reactPackage(ReactPackage reactPackage) {
            this.jdreactPackage = reactPackage;
            return this;
        }

        public Builder type(int i2) {
            this.type = i2;
            return this;
        }

        public Builder version(String str) {
            this.version = str;
            return this;
        }
    }

    /* loaded from: classes5.dex */
    public interface JDReactCallback {
        void clearFresco();

        void downloadFailed();

        void enablePVMta(boolean z);

        ReactPackage getReactPackageManger();

        boolean isOpenLoadingView();
    }

    /* loaded from: classes5.dex */
    public interface JDReactLoadingCallback {
        void dismissLoading();

        int loadingViewType();

        void showLoading();
    }

    public JDReactRootView(Activity activity, String str, String str2, Bundle bundle, int i2) {
        super(activity);
        this.mType = 0;
        this.version = "0.0";
        this.commitId = "0";
        this.mLoadingCompletely = false;
        this.mLoadingDone = false;
        this.splitbundle = false;
        this.failed = false;
        this.mForceLoadAfterUpdateCheck = false;
        this.mForceCheckUpdate = false;
        this.reportDataHashCode = hashCode() + "";
        this.mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1001:
                    case 1005:
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        JDReactCallback jDReactCallback = jDReactRootView.mJDReactCallback;
                        if (jDReactCallback == null) {
                            jDReactRootView.showRetryPage();
                            return;
                        } else {
                            jDReactCallback.downloadFailed();
                            return;
                        }
                    case 1002:
                        JDReactRootView.this.unregistLoadingListener();
                        JDReactRootView jDReactRootView2 = JDReactRootView.this;
                        jDReactRootView2.mLoadingCompletely = true;
                        jDReactRootView2.dismissProgressBar();
                        JDReactRootView jDReactRootView3 = JDReactRootView.this;
                        jDReactRootView3.openPlugin(ReactNativeFileManager.getPluginDir(jDReactRootView3.getContext(), JDReactRootView.this.reactModule));
                        return;
                    case 1003:
                    case 1004:
                    default:
                        return;
                    case 1006:
                        JDReactRootView.this.mAbstractJDReactInitialHelper.startToInit();
                        return;
                    case 1007:
                        JDReactLoadingCallback jDReactLoadingCallback = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback == null || jDReactLoadingCallback.loadingViewType() == 1) {
                            if (JDReactRootView.this.mNoDataView != null && JDReactRootView.this.mNoDataView.getParent() != null) {
                                JDReactRootView jDReactRootView4 = JDReactRootView.this;
                                jDReactRootView4.removeView(jDReactRootView4.mNoDataView);
                            }
                            JDReactRootView.this.newProgressBar();
                            if (JDReactRootView.this.modal.getParent() != null) {
                                ((ViewGroup) JDReactRootView.this.modal.getParent()).removeView(JDReactRootView.this.modal);
                            }
                            RelativeLayout relativeLayout = JDReactRootView.this.modal;
                            if (relativeLayout == null || relativeLayout.getParent() != null) {
                                return;
                            }
                            JDReactRootView jDReactRootView5 = JDReactRootView.this;
                            jDReactRootView5.addView(jDReactRootView5.modal, new ViewGroup.LayoutParams(-1, -1));
                            return;
                        }
                        JDReactLoadingCallback jDReactLoadingCallback2 = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback2 != null) {
                            jDReactLoadingCallback2.showLoading();
                            return;
                        }
                        return;
                }
            }
        };
        this.reactModule = str;
        this.reactBundle = str2;
        this.reactParams = bundle;
        this.commonPath = "";
        this.mActivity = activity;
        this.mType = i2;
        start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void newProgressBar() {
        if (this.modal == null) {
            this.modal = new RelativeLayout(getContext());
        }
        View view = this.progressBar;
        if (view != null && view.getParent() != null) {
            this.modal.removeView(this.progressBar);
            try {
                if (this.progressBar != null) {
                    JDReactHelper.newInstance().recycleLoadingLottieView(this.progressBar);
                    this.progressBar = null;
                }
            } catch (Exception unused) {
            }
        }
        try {
            int i2 = this.progressSizeHeight;
            if (i2 == 0) {
                i2 = (int) getResources().getDimension(R.dimen.jdreact_progressbar_size);
            }
            int i3 = this.progressSizeWidth;
            if (i3 == 0) {
                i3 = (int) getResources().getDimension(R.dimen.jdreact_progressbar_size);
            }
            this.progressBar = getLottieLoadingView();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i2);
            layoutParams.addRule(13);
            this.modal.addView(this.progressBar, layoutParams);
        } catch (Throwable unused2) {
            TextView textView = this.progressTextView;
            if (textView == null) {
                TextView loadingTextView = getLoadingTextView();
                this.progressTextView = loadingTextView;
                loadingTextView.setText(R.string.jdreact_net_load);
            } else {
                this.modal.removeView(textView);
            }
            this.modal.addView(this.progressTextView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openPlugin(PluginVersion pluginVersion) {
        if (pluginVersion != null && pluginVersion.pluginDir != null) {
            this.bundlePath = pluginVersion.pluginDir + File.separator + this.reactModule + ".jsbundle";
            if (!TextUtils.isEmpty(pluginVersion.pluginVersion)) {
                this.version = pluginVersion.pluginVersion;
            }
            if (!TextUtils.isEmpty(pluginVersion.commitId)) {
                this.commitId = pluginVersion.commitId;
            }
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.e(TAG, " bundlePath" + this.bundlePath);
            }
            this.mAbstractJDReactInitialHelper.setupLayout(pluginVersion);
            return;
        }
        showMpage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRetryPage() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            jDReactCallback.downloadFailed();
            return;
        }
        if (this.mNoDataView == null) {
            this.mNoDataView = new LinearLayout(getContext());
            JDReactHelper.newInstance().showErrorRetryView(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDReactRootView.this.mAbstractJDReactInitialHelper.isNetworkAvailable()) {
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        if (jDReactRootView.failed) {
                            jDReactRootView.mAbstractJDReactInitialHelper.startToGetMetaData();
                            return;
                        }
                        if (JDReactHelper.newInstance().isDebug()) {
                            JLog.e(JDReactRootView.TAG, "onfailed retry the downloading");
                        }
                        JDReactRootView.this.mAbstractJDReactInitialHelper.startToInit();
                    }
                }
            }, this.mNoDataView);
        }
        RelativeLayout relativeLayout = this.modal;
        if (relativeLayout != null && relativeLayout.getParent() != null) {
            removeView(this.modal);
        }
        if (this.mNoDataView.getParent() == null) {
            addView(this.mNoDataView, new ViewGroup.LayoutParams(-1, -1));
        }
        TrackUtil.trackLoadFail(12, this.reactModule);
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void clearFresco() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            jDReactCallback.clearFresco();
        }
    }

    public void dismissProgressBar() {
        RelativeLayout relativeLayout;
        this.mHandler.removeMessages(1007);
        JDReactLoadingCallback jDReactLoadingCallback = this.mJDReactLoadingCallback;
        if (jDReactLoadingCallback != null && jDReactLoadingCallback.loadingViewType() == 2) {
            this.mJDReactLoadingCallback.dismissLoading();
            return;
        }
        JDReactLoadingCallback jDReactLoadingCallback2 = this.mJDReactLoadingCallback;
        if ((jDReactLoadingCallback2 != null && jDReactLoadingCallback2.loadingViewType() != 1) || (relativeLayout = this.modal) == null || relativeLayout.getParent() == null) {
            return;
        }
        removeView(this.modal);
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void downloadFinish() {
        this.mHandler.removeMessages(1002);
        this.mHandler.sendEmptyMessage(1002);
    }

    public ReactPackage getCustomReactPackage() {
        return null;
    }

    public AbstractJDReactInitialHelper getJDReact() {
        return this.mAbstractJDReactInitialHelper;
    }

    public TextView getLoadingTextView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        TextView textView = new TextView(getContext());
        textView.setPadding(10, 10, 10, 10);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        textView.setBackgroundColor(getResources().getColor(R.color.jdreact_common_textview_bg_color));
        return textView;
    }

    public View getLottieLoadingView() {
        View loadingLottieView = JDReactHelper.newInstance().getLoadingLottieView();
        return loadingLottieView != null ? loadingLottieView : new JDReactProgressBar(getContext());
    }

    public ReactPackage getReactPackage() {
        if (AbstractJDReactInitialHelper.getPackageManger() != null) {
            return AbstractJDReactInitialHelper.getPackageManger();
        }
        ReactPackage reactPackage = mJDReactPackage;
        return reactPackage != null ? reactPackage : new JDReactPackageDemo();
    }

    public List<ReactPackage> getReactPackages() {
        IReactPackagesFactory iReactPackagesFactory = this.mReactPackagesFactory;
        if (iReactPackagesFactory != null) {
            return iReactPackagesFactory.createReactPackages();
        }
        return null;
    }

    public int getRootViewTag() {
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView == null) {
            return -1;
        }
        return reactRootView.getRootViewTag();
    }

    @Override // com.jingdong.common.jdreactFramework.helper.InitErrorProcessor
    public boolean interceptInitError() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public boolean isOpenLoadingView() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            return jDReactCallback.isOpenLoadingView();
        }
        JDReactLoadingCallback jDReactLoadingCallback = this.mJDReactLoadingCallback;
        if (jDReactLoadingCallback != null) {
            return jDReactLoadingCallback.loadingViewType() == 1 || this.mJDReactLoadingCallback.loadingViewType() == 2;
        }
        return false;
    }

    public boolean loadingFinished() {
        return this.mLoadingDone;
    }

    public boolean onBackPressed() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper == null || abstractJDReactInitialHelper.getReactManager() == null) {
            return false;
        }
        this.mAbstractJDReactInitialHelper.onBackPressed();
        return true;
    }

    public void onDestroy() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onDestroy();
        }
        try {
            if (this.progressBar != null) {
                JDReactHelper.newInstance().recycleLoadingLottieView(this.progressBar);
                this.progressBar = null;
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void onDownloadMetaData(boolean z) {
        if (z) {
            this.mHandler.removeMessages(1006);
            this.mHandler.sendEmptyMessage(1006);
            return;
        }
        this.mHandler.removeMessages(1005);
        this.mHandler.sendEmptyMessage(1005);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.InitErrorProcessor
    public void onJDReactInitError(int i2) {
    }

    public void onPause() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onPause();
        }
    }

    public void onResume() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onResume();
        }
    }

    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.sendEvent(str, writableMap);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void sendLaunchMta(String str, String str2) {
        if (JDReactHelper.newInstance().isIndependent()) {
            SpeicalMtaUtil.sendSpecialMta("100", str2, str);
            return;
        }
        JDReactHelper.newInstance().sendMtaData("JDReact_StartReactModule", str2 + CartConstant.KEY_YB_INFO_LINK + str);
    }

    public void setAppProperties(Bundle bundle) {
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView == null || bundle == null) {
            return;
        }
        reactRootView.setAppProperties(bundle);
    }

    public void setBackEvent(BackEventHandle backEventHandle) {
        this.mBackEventHandle = backEventHandle;
    }

    public void setJDReactCallback(JDReactCallback jDReactCallback) {
        this.mJDReactCallback = jDReactCallback;
    }

    public void setJDReactLoadingCallback(JDReactLoadingCallback jDReactLoadingCallback) {
        this.mJDReactLoadingCallback = jDReactLoadingCallback;
    }

    public void setLoadExceptionListener(LoadExceptionListener loadExceptionListener) {
        this.mLoadExceptionListener = loadExceptionListener;
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void setPVMta(boolean z) {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            jDReactCallback.enablePVMta(false);
        }
    }

    public void setProgressSizeHeight(int i2, int i3) {
        this.progressSizeHeight = i3;
        this.progressSizeWidth = i2;
    }

    public void setReactPackagesFactory(IReactPackagesFactory iReactPackagesFactory) {
        this.mReactPackagesFactory = iReactPackagesFactory;
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void showMpage() {
        this.mHandler.removeMessages(1001);
        this.mHandler.sendEmptyMessage(1001);
        RenderDataReport.getInstance().renderEnd(this.reportDataHashCode, false, "1", "download failed", JDReactCommonPreloadManager.getInstance().isPreloadCommon());
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void showProgressBar() {
        this.mHandler.removeMessages(1007);
        this.mHandler.sendEmptyMessage(1007);
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void showRetry() {
        this.mHandler.removeMessages(1001);
        this.mHandler.sendEmptyMessage(1001);
    }

    public void start() {
        RenderDataReport.getInstance().clearData();
        RenderDataReport.getInstance().loadStart(this.reportDataHashCode);
        JDReactAuraHelper.getInstance().setPackageManger();
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(this.mActivity, this.reactModule);
        if (pluginDir != null && pluginDir.pluginDir != null) {
            if (this.reactModule != null && pluginDir != null && !TextUtils.isEmpty(pluginDir.pluginVersion)) {
                this.version = pluginDir.pluginVersion;
                if (!TextUtils.isEmpty(pluginDir.commitId)) {
                    this.commitId = pluginDir.commitId;
                }
            }
            this.bundlePath = pluginDir.pluginDir + File.separator + this.reactModule + ".jsbundle";
        } else {
            this.failed = true;
            StringBuilder sb = new StringBuilder();
            sb.append(JDReactConstant.ReactDownloadPath.getAbsolutePath());
            String str = File.separator;
            sb.append(str);
            sb.append(this.reactModule);
            sb.append(str);
            sb.append(JDReactConstant.BUFF_DIR_ONE);
            this.bundlePath = sb.toString() + str + this.reactModule + ".jsbundle";
        }
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = new AbstractJDReactInitialHelper(this.mActivity, this.reactBundle, this.commonPath, this.reactModule, this.reactParams, this.bundlePath, new ReactInstanceManager.ReactInstanceProgressListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.2
            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadCancel() {
                if (JDReactRootView.this.isOpenLoadingView()) {
                    JDReactRootView.this.dismissProgressBar();
                }
                AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = JDReactRootView.this.mAbstractJDReactInitialHelper;
                if (abstractJDReactInitialHelper2 != null) {
                    abstractJDReactInitialHelper2.handleLoadException(5);
                }
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.d(JDReactRootView.TAG, "onReactLoadCancel time = " + System.currentTimeMillis());
                }
                if (TextUtils.equals("JDReactDaoJia", JDReactRootView.this.reactModule)) {
                    JDReactRootView.this.showProgressBar();
                    if (!JDReactRootView.this.isLoadedPreset) {
                        JDReactRootView.this.isLoadedPreset = true;
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        jDReactRootView.openPlugin(ReactVersionUtils.getPluginPreloadDataPath(jDReactRootView.getContext(), JDReactRootView.this.reactModule));
                        return;
                    }
                    JDReactRootView.this.showMpage();
                }
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadFinish() {
                if (JDReactRootView.this.isOpenLoadingView()) {
                    JDReactRootView.this.dismissProgressBar();
                }
                AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = JDReactRootView.this.mAbstractJDReactInitialHelper;
                if (abstractJDReactInitialHelper2 != null) {
                    abstractJDReactInitialHelper2.onResume();
                }
                JDReactRootView.this.mLoadingDone = true;
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.d(JDReactRootView.TAG, "onReactLoadFinish time = " + System.currentTimeMillis());
                }
                RenderDataReport.getInstance().renderEnd(JDReactRootView.this.reportDataHashCode, true, null, null, JDReactCommonPreloadManager.getInstance().isPreloadCommon());
                TrackUtil.trackLoadFinish(JDReactRootView.this.reactModule);
                if (JDReactRootView.this.getContext() instanceof Activity) {
                    JDReactCommonPreloadManager.getInstance().preloadCommonBundle((Activity) JDReactRootView.this.getContext());
                } else {
                    JDReactCommonPreloadManager.getInstance().preloadCommonBundle();
                }
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadStart() {
                if (JDReactRootView.this.isOpenLoadingView()) {
                    JDReactRootView.this.showProgressBar();
                }
                JDReactRootView.this.mLoadingDone = false;
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.d(JDReactRootView.TAG, "onReactLoadStart time = " + System.currentTimeMillis());
                }
                TrackUtil.trackLoadStart(JDReactRootView.this.reactModule);
            }
        }, this.splitbundle, this.mType, this.version, this.commitId, this.failed, false, this.preload, this.reportDataHashCode, this.mEngineHelper) { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.3
            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void defaultOnBackPressed() {
                BackEventHandle backEventHandle = JDReactRootView.this.mBackEventHandle;
                if (backEventHandle != null) {
                    backEventHandle.OnBackKeyPress();
                }
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getDefaultReactPackage() {
                return JDReactRootView.this.getReactPackage();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getExtendReactPackage() {
                return JDReactRootView.this.getCustomReactPackage();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected List<ReactPackage> getReactPackages() {
                return JDReactRootView.this.getReactPackages();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            public void handleInitError(int i2) {
                if (JDReactRootView.this.interceptInitError()) {
                    JDReactRootView.this.onJDReactInitError(i2);
                } else {
                    super.handleInitError(i2);
                }
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void initRootView(ReactRootView reactRootView) {
                JDReactRootView.this.addView(reactRootView, new LinearLayout.LayoutParams(-1, -1));
                JDReactRootView.this.mReactRootView = reactRootView;
            }
        };
        this.mAbstractJDReactInitialHelper = abstractJDReactInitialHelper;
        abstractJDReactInitialHelper.setIsRootView(true);
        this.mAbstractJDReactInitialHelper.setForceCheckUpdate(this.mForceCheckUpdate);
        this.mAbstractJDReactInitialHelper.setForceLoadAfterUpdateCheck(this.mForceLoadAfterUpdateCheck);
        this.mAbstractJDReactInitialHelper.setLoadExceptionListener(this.mLoadExceptionListener);
        this.mAbstractJDReactInitialHelper.setUIHandler(this);
        this.mAbstractJDReactInitialHelper.startToInit1();
    }

    public void unregistLoadingListener() {
        if (this.mPluginListener != null) {
            ReactNativeUpdateManager.getInstance().unregistForceDownloadTaskListener(this.reactModule, this.mPluginListener);
            this.mPluginListener = null;
        }
    }

    public void updateScreenSize() {
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView != null) {
            reactRootView.updateScreenSize();
        }
    }

    public JDReactRootView(Activity activity, String str, String str2, Bundle bundle) {
        super(activity);
        this.mType = 0;
        this.version = "0.0";
        this.commitId = "0";
        this.mLoadingCompletely = false;
        this.mLoadingDone = false;
        this.splitbundle = false;
        this.failed = false;
        this.mForceLoadAfterUpdateCheck = false;
        this.mForceCheckUpdate = false;
        this.reportDataHashCode = hashCode() + "";
        this.mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1001:
                    case 1005:
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        JDReactCallback jDReactCallback = jDReactRootView.mJDReactCallback;
                        if (jDReactCallback == null) {
                            jDReactRootView.showRetryPage();
                            return;
                        } else {
                            jDReactCallback.downloadFailed();
                            return;
                        }
                    case 1002:
                        JDReactRootView.this.unregistLoadingListener();
                        JDReactRootView jDReactRootView2 = JDReactRootView.this;
                        jDReactRootView2.mLoadingCompletely = true;
                        jDReactRootView2.dismissProgressBar();
                        JDReactRootView jDReactRootView3 = JDReactRootView.this;
                        jDReactRootView3.openPlugin(ReactNativeFileManager.getPluginDir(jDReactRootView3.getContext(), JDReactRootView.this.reactModule));
                        return;
                    case 1003:
                    case 1004:
                    default:
                        return;
                    case 1006:
                        JDReactRootView.this.mAbstractJDReactInitialHelper.startToInit();
                        return;
                    case 1007:
                        JDReactLoadingCallback jDReactLoadingCallback = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback == null || jDReactLoadingCallback.loadingViewType() == 1) {
                            if (JDReactRootView.this.mNoDataView != null && JDReactRootView.this.mNoDataView.getParent() != null) {
                                JDReactRootView jDReactRootView4 = JDReactRootView.this;
                                jDReactRootView4.removeView(jDReactRootView4.mNoDataView);
                            }
                            JDReactRootView.this.newProgressBar();
                            if (JDReactRootView.this.modal.getParent() != null) {
                                ((ViewGroup) JDReactRootView.this.modal.getParent()).removeView(JDReactRootView.this.modal);
                            }
                            RelativeLayout relativeLayout = JDReactRootView.this.modal;
                            if (relativeLayout == null || relativeLayout.getParent() != null) {
                                return;
                            }
                            JDReactRootView jDReactRootView5 = JDReactRootView.this;
                            jDReactRootView5.addView(jDReactRootView5.modal, new ViewGroup.LayoutParams(-1, -1));
                            return;
                        }
                        JDReactLoadingCallback jDReactLoadingCallback2 = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback2 != null) {
                            jDReactLoadingCallback2.showLoading();
                            return;
                        }
                        return;
                }
            }
        };
        this.reactModule = str;
        this.reactBundle = str2;
        this.reactParams = bundle;
        this.commonPath = "";
        this.mActivity = activity;
        start();
    }

    public JDReactRootView(Activity activity, String str, String str2, Bundle bundle, EngineHelper engineHelper) {
        super(activity);
        this.mType = 0;
        this.version = "0.0";
        this.commitId = "0";
        this.mLoadingCompletely = false;
        this.mLoadingDone = false;
        this.splitbundle = false;
        this.failed = false;
        this.mForceLoadAfterUpdateCheck = false;
        this.mForceCheckUpdate = false;
        this.reportDataHashCode = hashCode() + "";
        this.mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1001:
                    case 1005:
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        JDReactCallback jDReactCallback = jDReactRootView.mJDReactCallback;
                        if (jDReactCallback == null) {
                            jDReactRootView.showRetryPage();
                            return;
                        } else {
                            jDReactCallback.downloadFailed();
                            return;
                        }
                    case 1002:
                        JDReactRootView.this.unregistLoadingListener();
                        JDReactRootView jDReactRootView2 = JDReactRootView.this;
                        jDReactRootView2.mLoadingCompletely = true;
                        jDReactRootView2.dismissProgressBar();
                        JDReactRootView jDReactRootView3 = JDReactRootView.this;
                        jDReactRootView3.openPlugin(ReactNativeFileManager.getPluginDir(jDReactRootView3.getContext(), JDReactRootView.this.reactModule));
                        return;
                    case 1003:
                    case 1004:
                    default:
                        return;
                    case 1006:
                        JDReactRootView.this.mAbstractJDReactInitialHelper.startToInit();
                        return;
                    case 1007:
                        JDReactLoadingCallback jDReactLoadingCallback = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback == null || jDReactLoadingCallback.loadingViewType() == 1) {
                            if (JDReactRootView.this.mNoDataView != null && JDReactRootView.this.mNoDataView.getParent() != null) {
                                JDReactRootView jDReactRootView4 = JDReactRootView.this;
                                jDReactRootView4.removeView(jDReactRootView4.mNoDataView);
                            }
                            JDReactRootView.this.newProgressBar();
                            if (JDReactRootView.this.modal.getParent() != null) {
                                ((ViewGroup) JDReactRootView.this.modal.getParent()).removeView(JDReactRootView.this.modal);
                            }
                            RelativeLayout relativeLayout = JDReactRootView.this.modal;
                            if (relativeLayout == null || relativeLayout.getParent() != null) {
                                return;
                            }
                            JDReactRootView jDReactRootView5 = JDReactRootView.this;
                            jDReactRootView5.addView(jDReactRootView5.modal, new ViewGroup.LayoutParams(-1, -1));
                            return;
                        }
                        JDReactLoadingCallback jDReactLoadingCallback2 = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback2 != null) {
                            jDReactLoadingCallback2.showLoading();
                            return;
                        }
                        return;
                }
            }
        };
        this.reactModule = str;
        this.reactBundle = str2;
        this.reactParams = bundle;
        this.commonPath = "jdreact/JDReactCommon.jsbundle";
        this.mType = 3;
        this.mActivity = activity;
        this.mEngineHelper = engineHelper;
        start();
    }

    public JDReactRootView(Activity activity, String str, String str2, Bundle bundle, int i2, ReactPackage reactPackage) {
        super(activity);
        this.mType = 0;
        this.version = "0.0";
        this.commitId = "0";
        this.mLoadingCompletely = false;
        this.mLoadingDone = false;
        this.splitbundle = false;
        this.failed = false;
        this.mForceLoadAfterUpdateCheck = false;
        this.mForceCheckUpdate = false;
        this.reportDataHashCode = hashCode() + "";
        this.mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1001:
                    case 1005:
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        JDReactCallback jDReactCallback = jDReactRootView.mJDReactCallback;
                        if (jDReactCallback == null) {
                            jDReactRootView.showRetryPage();
                            return;
                        } else {
                            jDReactCallback.downloadFailed();
                            return;
                        }
                    case 1002:
                        JDReactRootView.this.unregistLoadingListener();
                        JDReactRootView jDReactRootView2 = JDReactRootView.this;
                        jDReactRootView2.mLoadingCompletely = true;
                        jDReactRootView2.dismissProgressBar();
                        JDReactRootView jDReactRootView3 = JDReactRootView.this;
                        jDReactRootView3.openPlugin(ReactNativeFileManager.getPluginDir(jDReactRootView3.getContext(), JDReactRootView.this.reactModule));
                        return;
                    case 1003:
                    case 1004:
                    default:
                        return;
                    case 1006:
                        JDReactRootView.this.mAbstractJDReactInitialHelper.startToInit();
                        return;
                    case 1007:
                        JDReactLoadingCallback jDReactLoadingCallback = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback == null || jDReactLoadingCallback.loadingViewType() == 1) {
                            if (JDReactRootView.this.mNoDataView != null && JDReactRootView.this.mNoDataView.getParent() != null) {
                                JDReactRootView jDReactRootView4 = JDReactRootView.this;
                                jDReactRootView4.removeView(jDReactRootView4.mNoDataView);
                            }
                            JDReactRootView.this.newProgressBar();
                            if (JDReactRootView.this.modal.getParent() != null) {
                                ((ViewGroup) JDReactRootView.this.modal.getParent()).removeView(JDReactRootView.this.modal);
                            }
                            RelativeLayout relativeLayout = JDReactRootView.this.modal;
                            if (relativeLayout == null || relativeLayout.getParent() != null) {
                                return;
                            }
                            JDReactRootView jDReactRootView5 = JDReactRootView.this;
                            jDReactRootView5.addView(jDReactRootView5.modal, new ViewGroup.LayoutParams(-1, -1));
                            return;
                        }
                        JDReactLoadingCallback jDReactLoadingCallback2 = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback2 != null) {
                            jDReactLoadingCallback2.showLoading();
                            return;
                        }
                        return;
                }
            }
        };
        this.reactModule = str;
        this.reactBundle = str2;
        this.reactParams = bundle;
        this.commonPath = "";
        this.mActivity = activity;
        this.mType = i2;
        mJDReactPackage = reactPackage;
        start();
    }

    public JDReactRootView(Activity activity, String str, String str2, Bundle bundle, int i2, ReactPackage reactPackage, boolean z, boolean z2) {
        super(activity);
        this.mType = 0;
        this.version = "0.0";
        this.commitId = "0";
        this.mLoadingCompletely = false;
        this.mLoadingDone = false;
        this.splitbundle = false;
        this.failed = false;
        this.mForceLoadAfterUpdateCheck = false;
        this.mForceCheckUpdate = false;
        this.reportDataHashCode = hashCode() + "";
        this.mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1001:
                    case 1005:
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        JDReactCallback jDReactCallback = jDReactRootView.mJDReactCallback;
                        if (jDReactCallback == null) {
                            jDReactRootView.showRetryPage();
                            return;
                        } else {
                            jDReactCallback.downloadFailed();
                            return;
                        }
                    case 1002:
                        JDReactRootView.this.unregistLoadingListener();
                        JDReactRootView jDReactRootView2 = JDReactRootView.this;
                        jDReactRootView2.mLoadingCompletely = true;
                        jDReactRootView2.dismissProgressBar();
                        JDReactRootView jDReactRootView3 = JDReactRootView.this;
                        jDReactRootView3.openPlugin(ReactNativeFileManager.getPluginDir(jDReactRootView3.getContext(), JDReactRootView.this.reactModule));
                        return;
                    case 1003:
                    case 1004:
                    default:
                        return;
                    case 1006:
                        JDReactRootView.this.mAbstractJDReactInitialHelper.startToInit();
                        return;
                    case 1007:
                        JDReactLoadingCallback jDReactLoadingCallback = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback == null || jDReactLoadingCallback.loadingViewType() == 1) {
                            if (JDReactRootView.this.mNoDataView != null && JDReactRootView.this.mNoDataView.getParent() != null) {
                                JDReactRootView jDReactRootView4 = JDReactRootView.this;
                                jDReactRootView4.removeView(jDReactRootView4.mNoDataView);
                            }
                            JDReactRootView.this.newProgressBar();
                            if (JDReactRootView.this.modal.getParent() != null) {
                                ((ViewGroup) JDReactRootView.this.modal.getParent()).removeView(JDReactRootView.this.modal);
                            }
                            RelativeLayout relativeLayout = JDReactRootView.this.modal;
                            if (relativeLayout == null || relativeLayout.getParent() != null) {
                                return;
                            }
                            JDReactRootView jDReactRootView5 = JDReactRootView.this;
                            jDReactRootView5.addView(jDReactRootView5.modal, new ViewGroup.LayoutParams(-1, -1));
                            return;
                        }
                        JDReactLoadingCallback jDReactLoadingCallback2 = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback2 != null) {
                            jDReactLoadingCallback2.showLoading();
                            return;
                        }
                        return;
                }
            }
        };
        this.reactModule = str;
        this.reactBundle = str2;
        this.reactParams = bundle;
        this.commonPath = "";
        this.mActivity = activity;
        this.mType = i2;
        this.mForceLoadAfterUpdateCheck = z;
        this.mForceCheckUpdate = z2;
        mJDReactPackage = reactPackage;
        start();
    }

    public JDReactRootView(Activity activity, String str, String str2, Bundle bundle, int i2, ReactPackage reactPackage, boolean z, boolean z2, boolean z3) {
        super(activity);
        this.mType = 0;
        this.version = "0.0";
        this.commitId = "0";
        this.mLoadingCompletely = false;
        this.mLoadingDone = false;
        this.splitbundle = false;
        this.failed = false;
        this.mForceLoadAfterUpdateCheck = false;
        this.mForceCheckUpdate = false;
        this.reportDataHashCode = hashCode() + "";
        this.mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1001:
                    case 1005:
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        JDReactCallback jDReactCallback = jDReactRootView.mJDReactCallback;
                        if (jDReactCallback == null) {
                            jDReactRootView.showRetryPage();
                            return;
                        } else {
                            jDReactCallback.downloadFailed();
                            return;
                        }
                    case 1002:
                        JDReactRootView.this.unregistLoadingListener();
                        JDReactRootView jDReactRootView2 = JDReactRootView.this;
                        jDReactRootView2.mLoadingCompletely = true;
                        jDReactRootView2.dismissProgressBar();
                        JDReactRootView jDReactRootView3 = JDReactRootView.this;
                        jDReactRootView3.openPlugin(ReactNativeFileManager.getPluginDir(jDReactRootView3.getContext(), JDReactRootView.this.reactModule));
                        return;
                    case 1003:
                    case 1004:
                    default:
                        return;
                    case 1006:
                        JDReactRootView.this.mAbstractJDReactInitialHelper.startToInit();
                        return;
                    case 1007:
                        JDReactLoadingCallback jDReactLoadingCallback = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback == null || jDReactLoadingCallback.loadingViewType() == 1) {
                            if (JDReactRootView.this.mNoDataView != null && JDReactRootView.this.mNoDataView.getParent() != null) {
                                JDReactRootView jDReactRootView4 = JDReactRootView.this;
                                jDReactRootView4.removeView(jDReactRootView4.mNoDataView);
                            }
                            JDReactRootView.this.newProgressBar();
                            if (JDReactRootView.this.modal.getParent() != null) {
                                ((ViewGroup) JDReactRootView.this.modal.getParent()).removeView(JDReactRootView.this.modal);
                            }
                            RelativeLayout relativeLayout = JDReactRootView.this.modal;
                            if (relativeLayout == null || relativeLayout.getParent() != null) {
                                return;
                            }
                            JDReactRootView jDReactRootView5 = JDReactRootView.this;
                            jDReactRootView5.addView(jDReactRootView5.modal, new ViewGroup.LayoutParams(-1, -1));
                            return;
                        }
                        JDReactLoadingCallback jDReactLoadingCallback2 = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback2 != null) {
                            jDReactLoadingCallback2.showLoading();
                            return;
                        }
                        return;
                }
            }
        };
        this.reactModule = str;
        this.reactBundle = str2;
        this.reactParams = bundle;
        this.commonPath = "";
        this.mActivity = activity;
        this.mType = i2;
        this.mForceLoadAfterUpdateCheck = z;
        this.mForceCheckUpdate = z2;
        mJDReactPackage = reactPackage;
        this.preload = z3;
        start();
    }

    public JDReactRootView(Builder builder) {
        super(builder.ac);
        this.mType = 0;
        this.version = "0.0";
        this.commitId = "0";
        this.mLoadingCompletely = false;
        this.mLoadingDone = false;
        this.splitbundle = false;
        this.failed = false;
        this.mForceLoadAfterUpdateCheck = false;
        this.mForceCheckUpdate = false;
        this.reportDataHashCode = hashCode() + "";
        this.mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactRootView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1001:
                    case 1005:
                        JDReactRootView jDReactRootView = JDReactRootView.this;
                        JDReactCallback jDReactCallback = jDReactRootView.mJDReactCallback;
                        if (jDReactCallback == null) {
                            jDReactRootView.showRetryPage();
                            return;
                        } else {
                            jDReactCallback.downloadFailed();
                            return;
                        }
                    case 1002:
                        JDReactRootView.this.unregistLoadingListener();
                        JDReactRootView jDReactRootView2 = JDReactRootView.this;
                        jDReactRootView2.mLoadingCompletely = true;
                        jDReactRootView2.dismissProgressBar();
                        JDReactRootView jDReactRootView3 = JDReactRootView.this;
                        jDReactRootView3.openPlugin(ReactNativeFileManager.getPluginDir(jDReactRootView3.getContext(), JDReactRootView.this.reactModule));
                        return;
                    case 1003:
                    case 1004:
                    default:
                        return;
                    case 1006:
                        JDReactRootView.this.mAbstractJDReactInitialHelper.startToInit();
                        return;
                    case 1007:
                        JDReactLoadingCallback jDReactLoadingCallback = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback == null || jDReactLoadingCallback.loadingViewType() == 1) {
                            if (JDReactRootView.this.mNoDataView != null && JDReactRootView.this.mNoDataView.getParent() != null) {
                                JDReactRootView jDReactRootView4 = JDReactRootView.this;
                                jDReactRootView4.removeView(jDReactRootView4.mNoDataView);
                            }
                            JDReactRootView.this.newProgressBar();
                            if (JDReactRootView.this.modal.getParent() != null) {
                                ((ViewGroup) JDReactRootView.this.modal.getParent()).removeView(JDReactRootView.this.modal);
                            }
                            RelativeLayout relativeLayout = JDReactRootView.this.modal;
                            if (relativeLayout == null || relativeLayout.getParent() != null) {
                                return;
                            }
                            JDReactRootView jDReactRootView5 = JDReactRootView.this;
                            jDReactRootView5.addView(jDReactRootView5.modal, new ViewGroup.LayoutParams(-1, -1));
                            return;
                        }
                        JDReactLoadingCallback jDReactLoadingCallback2 = JDReactRootView.this.mJDReactLoadingCallback;
                        if (jDReactLoadingCallback2 != null) {
                            jDReactLoadingCallback2.showLoading();
                            return;
                        }
                        return;
                }
            }
        };
        String str = builder.reactModule;
        this.reactModule = str;
        this.reactBundle = str;
        this.reactParams = builder.reactParams;
        this.commonPath = builder.commonPath;
        this.mActivity = builder.ac;
        this.mType = builder.type;
        this.mForceLoadAfterUpdateCheck = builder.mForceLoadAfterUpdateCheck;
        this.mForceCheckUpdate = builder.mForceCheckUpdate;
        mJDReactPackage = builder.jdreactPackage;
        this.preload = builder.preload;
        this.version = builder.version;
        this.commitId = builder.commitId;
        this.progressSizeHeight = builder.progressSizeHeight;
        this.progressSizeWidth = builder.progressSizeWidth;
        this.mJDReactCallback = builder.mJDReactCallback;
        this.mJDReactLoadingCallback = builder.mJDReactLoadingCallback;
        start();
    }
}
