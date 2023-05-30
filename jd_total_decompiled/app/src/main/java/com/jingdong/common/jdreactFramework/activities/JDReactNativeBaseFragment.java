package com.jingdong.common.jdreactFramework.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.common.jdreactFramework.Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.JDReactPackageDemo;
import com.jingdong.common.jdreactFramework.download.PluginListenerNew;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.floatingview.FloatingMagnetView;
import com.jingdong.common.jdreactFramework.floatingview.FloatingView;
import com.jingdong.common.jdreactFramework.floatingview.MagnetViewListener;
import com.jingdong.common.jdreactFramework.helper.IReactPackagesFactory;
import com.jingdong.common.jdreactFramework.helper.LoadExceptionListener;
import com.jingdong.common.jdreactFramework.helper.LoadListener;
import com.jingdong.common.jdreactFramework.preload.JDReactCommonPreloadManager;
import com.jingdong.common.jdreactFramework.track.RenderDataReport;
import com.jingdong.common.jdreactFramework.track.TrackUtil;
import com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.JumpUtils;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import com.jingdong.common.jdreactFramework.utils.SpeicalMtaUtil;
import com.jingdong.common.jdreactFramework.widgets.JDReactProgressBar;
import com.jingdong.jdreactframewok.R;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDReactNativeBaseFragment extends Fragment implements AbstractInitialHelper.UIHandler {
    private static String BUNDLE_PATCH = "bundlepath";
    private static String COMMIT_ID = "commitId";
    private static String COMMON_PATH = "commonpatch";
    private static String DEBUG_MODE = "debugMode";
    private static String DEPRECATED_VERSION = "deprecatedVersion";
    private static String DEST_BUNDLE_PATH = "destBundlePath";
    private static final int DISMISS_PROGRESS = 1004;
    private static final int DOWNLOAD_FAILED = 1001;
    private static final int DOWNLOAD_FINISHED = 1002;
    private static final int DOWNLOAD_META_FAILED = 1005;
    private static final int DOWNLOAD_META_FINISHED = 1006;
    private static String EXTRA_REUSE = "reuse";
    private static String FORCE_PRELOAD = "preload";
    private static String FORCE_UPDATE = "force";
    private static String IS_FAILED = "failed";
    private static String IS_SCAN_ENTER = "force_download_mode";
    private static String JS_VERSION = "jsVersion";
    private static String LOCAL_DEBUG = "localDebug";
    private static String REACT_BUNDLE = "reactBundle";
    private static String REACT_IS_HIDDEN = "reacthide";
    private static String REACT_MOUDLE = "reactMoudle";
    private static String REACT_PARAM = "reactParams";
    private static String REACT_TITLE = "reacttitle";
    private static String REACT_TYPE = "type";
    private static final int SHOW_M_PAGE = 1007;
    private static final int SHOW_PROGRESS = 1003;
    private static String SPLIT_BUNDLE = "split";
    private static final String TAG = "JDReactFragment";
    private String bundlePath;
    private String commonPath;
    private String destBundlePath;
    private boolean hasCallStart;
    private boolean isLoadedPreset;
    private boolean isLocalDebug;
    LinearLayout ll_debug_lock;
    AbstractJDReactInitialHelper mAbstractJDReactInitialHelper;
    Fragment mBaseFragment;
    private String mDeprecatedVersion;
    private String mDownloadName;
    private String mH5Params;
    private Intent mIntent;
    private String mIntentBackupUrl;
    private JDReactCallback mJDReactCallback;
    private LoadExceptionListener mLoadExceptionListener;
    private LoadListener mLoadListener;
    LinearLayout mNoDataView;
    private Bundle mParam;
    PluginListenerNew mPluginListener;
    private int mProgressBarSize;
    private IReactPackagesFactory mReactPackagesFactory;
    private String mReportDataHashCode;
    private boolean mReuse;
    private int mType;
    RelativeLayout modal;
    View progressBar;
    TextView progressTextView;
    private String reactBundle;
    private boolean reactIsHidden;
    private String reactModule;
    private Bundle reactParams;
    private String reactTitle;
    private View root;
    private ViewGroup rootFrameLayout;
    private boolean isFirst = false;
    private boolean mIsDebug = false;
    private boolean isScanEnter = false;
    private boolean failed = false;
    private boolean force = false;
    private String version = "0.0";
    private String commitId = "0";
    private boolean preload = false;
    private boolean mForceCheckUpdate = false;
    private boolean mForceLoadAfterUpdateCheck = false;
    boolean mLoadingCompletely = false;
    private boolean splitbundle = true;
    private Handler mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.1
        {
            JDReactNativeBaseFragment.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1001:
                case 1005:
                    JDReactNativeBaseFragment.this.dismissProgressBar();
                    JDReactNativeBaseFragment.this.showRetryDialog();
                    return;
                case 1002:
                    JDReactNativeBaseFragment.this.unregistLoadingListener();
                    JDReactNativeBaseFragment jDReactNativeBaseFragment = JDReactNativeBaseFragment.this;
                    jDReactNativeBaseFragment.mLoadingCompletely = true;
                    jDReactNativeBaseFragment.dismissProgressBar();
                    JDReactNativeBaseFragment jDReactNativeBaseFragment2 = JDReactNativeBaseFragment.this;
                    jDReactNativeBaseFragment2.openPlugin(ReactNativeFileManager.getPluginDir(jDReactNativeBaseFragment2.getContext(), JDReactNativeBaseFragment.this.mDownloadName));
                    return;
                case 1003:
                    JDReactNativeBaseFragment.this.showProgressBarNow();
                    return;
                case 1004:
                    JDReactNativeBaseFragment.this.dismissProgressBarNow();
                    return;
                case 1006:
                    JDReactNativeBaseFragment.this.dismissProgressBar();
                    JDReactNativeBaseFragment.this.mAbstractJDReactInitialHelper.startToInit();
                    return;
                case 1007:
                    String appendParamsToUrl = ReactModuleAvailabilityUtils.appendParamsToUrl(ReactModuleAvailabilityUtils.getModuleBackupUrl(JDReactNativeBaseFragment.this.mDownloadName, JDReactNativeBaseFragment.this.mIntentBackupUrl), JDReactNativeBaseFragment.this.mH5Params);
                    if (!TextUtils.isEmpty(appendParamsToUrl)) {
                        String str = appendParamsToUrl + "&privacyConfirmShowed=1";
                        if (JumpUtils.isJumpProtocalHeader(str)) {
                            if (JDReactNativeBaseFragment.this.mJDReactCallback != null) {
                                JDReactNativeBaseFragment.this.dismissProgressBar();
                                JDReactNativeBaseFragment.this.mJDReactCallback.lunchOpenApp(str);
                                return;
                            }
                        } else {
                            JDReactNativeBaseFragment jDReactNativeBaseFragment3 = JDReactNativeBaseFragment.this;
                            if (jDReactNativeBaseFragment3.mBaseFragment != null) {
                                return;
                            }
                            if (jDReactNativeBaseFragment3.mJDReactCallback != null) {
                                JDReactNativeBaseFragment jDReactNativeBaseFragment4 = JDReactNativeBaseFragment.this;
                                jDReactNativeBaseFragment4.mBaseFragment = jDReactNativeBaseFragment4.mJDReactCallback.createWebFragement(str);
                            }
                            JDReactNativeBaseFragment jDReactNativeBaseFragment5 = JDReactNativeBaseFragment.this;
                            if (jDReactNativeBaseFragment5.mBaseFragment == null) {
                                if (jDReactNativeBaseFragment5.mJDReactCallback != null) {
                                    JDReactNativeBaseFragment.this.dismissProgressBar();
                                    JDReactNativeBaseFragment.this.mJDReactCallback.lunchWebPage(str);
                                    JDReactNativeBaseFragment.this.getActivity().finish();
                                    return;
                                }
                            } else {
                                jDReactNativeBaseFragment5.dismissProgressBar();
                                if (JDReactNativeBaseFragment.this.getActivity() == null || JDReactNativeBaseFragment.this.getActivity().getSupportFragmentManager() == null) {
                                    return;
                                }
                                if (JDReactNativeBaseFragment.this.mJDReactCallback != null) {
                                    JDReactNativeBaseFragment.this.mJDReactCallback.refreshStatusBar(true);
                                }
                                JDReactNativeBaseFragment.this.getActivity().getSupportFragmentManager().beginTransaction().add(JDReactNativeBaseFragment.this.getRootViewHolderId(), JDReactNativeBaseFragment.this.mBaseFragment).commitAllowingStateLoss();
                                return;
                            }
                        }
                        TrackUtil.trackLoadFail(7, JDReactNativeBaseFragment.this.reactModule);
                    }
                    JDReactNativeBaseFragment.this.mHandler.removeMessages(1001);
                    JDReactNativeBaseFragment.this.mHandler.sendEmptyMessage(1001);
                    return;
                default:
                    return;
            }
        }
    };

    /* loaded from: classes5.dex */
    public interface JDReactCallback {
        void addRootView(ReactRootView reactRootView, String str, boolean z, String str2, String str3);

        void clearFresco();

        Fragment createWebFragement(String str);

        void enablePVMta(boolean z);

        int getLayoutID();

        ReactPackage getReactPackageManger();

        int getRootViewHolderId();

        boolean isOpenLoadingView();

        void lunchOpenApp(String str);

        void lunchWebPage(String str);

        void onBackPressedCalled();

        void refreshStatusBar(boolean z);
    }

    private ViewGroup getModal() {
        RelativeLayout relativeLayout = this.modal;
        if (relativeLayout != null) {
            return relativeLayout;
        }
        if (getContext() == null) {
            return null;
        }
        RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
        this.modal = relativeLayout2;
        relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.5
            {
                JDReactNativeBaseFragment.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return this.modal;
    }

    public int getRootViewHolderId() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        int rootViewHolderId = jDReactCallback != null ? jDReactCallback.getRootViewHolderId() : 0;
        return rootViewHolderId <= 0 ? R.id.reactRootViewHolder : rootViewHolderId;
    }

    public static Bundle initData(String str, String str2, Bundle bundle, boolean z, String str3, String str4, boolean z2, boolean z3, String str5, String str6, boolean z4, String str7, boolean z5, int i2, boolean z6) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(REACT_BUNDLE, str);
        bundle2.putString(REACT_MOUDLE, str2);
        bundle2.putBundle(REACT_PARAM, bundle);
        bundle2.putBoolean(DEBUG_MODE, z);
        bundle2.putString(JS_VERSION, str3);
        if (!TextUtils.isEmpty(str4)) {
            bundle2.putString(COMMIT_ID, str4);
        }
        bundle2.putBoolean(IS_FAILED, z2);
        bundle2.putBoolean(FORCE_UPDATE, z3);
        bundle2.putBoolean(FORCE_PRELOAD, z6);
        bundle2.putString(BUNDLE_PATCH, str5);
        bundle2.putInt(REACT_TYPE, i2);
        if (!TextUtils.isEmpty(str6)) {
            bundle2.putString(COMMON_PATH, str6);
        }
        bundle2.putBoolean(SPLIT_BUNDLE, z4);
        bundle2.putString(REACT_TITLE, str7);
        bundle2.putBoolean(REACT_IS_HIDDEN, z5);
        return bundle2;
    }

    public void inspector() {
        ReactContext currentReactContext;
        if (getReactManager() == null || getReactManager().getDevSupportManager() == null || (currentReactContext = getReactManager().getCurrentReactContext()) == null) {
            return;
        }
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("toggleElementInspector", null);
    }

    public static JDReactNativeBaseFragment newInstance(String str, String str2, Bundle bundle, boolean z, String str3, boolean z2, boolean z3, String str4, String str5, boolean z4, String str6, boolean z5, int i2, boolean z6) {
        JDReactNativeBaseFragment jDReactNativeBaseFragment = new JDReactNativeBaseFragment();
        jDReactNativeBaseFragment.setArguments(initData(str, str2, bundle, z, str3, "0", z2, z3, str4, str5, z4, str6, z5, i2, z6));
        return jDReactNativeBaseFragment;
    }

    private void newProgressBar() {
        RelativeLayout relativeLayout;
        RelativeLayout.LayoutParams layoutParams;
        RelativeLayout relativeLayout2 = this.modal;
        if (relativeLayout2 != null) {
            relativeLayout2.removeAllViews();
        }
        View view = this.progressBar;
        if (view != null && view != null) {
            try {
                JDReactHelper.newInstance().recycleLoadingLottieView(this.progressBar);
                this.progressBar = null;
            } catch (Exception unused) {
            }
        }
        try {
            this.mProgressBarSize = this.mParam.getInt(JDReactConstant.IntentConstant.PROGRESSBAR_SIZE);
            int i2 = this.mParam.getInt(JDReactConstant.IntentConstant.PROGRESSBAR_SIZE_HEIGHT);
            int i3 = this.mParam.getInt(JDReactConstant.IntentConstant.PROGRESSBAR_SIZE_WIDTH);
            if (this.mProgressBarSize > 0) {
                int i4 = this.mProgressBarSize;
                layoutParams = new RelativeLayout.LayoutParams(i4, i4);
            } else if (i2 > 0 && i3 > 0) {
                layoutParams = new RelativeLayout.LayoutParams(i3, i2);
            } else {
                Resources resources = getResources();
                int i5 = R.dimen.jdreact_progressbar_size;
                layoutParams = new RelativeLayout.LayoutParams((int) resources.getDimension(i5), (int) getResources().getDimension(i5));
            }
            this.progressBar = getLottieLoadingView();
            layoutParams.addRule(13);
            this.modal.addView(this.progressBar, layoutParams);
        } catch (Throwable unused2) {
            TextView textView = this.progressTextView;
            if (textView == null) {
                TextView loadingTextView = getLoadingTextView();
                this.progressTextView = loadingTextView;
                if (loadingTextView != null) {
                    loadingTextView.setText(R.string.jdreact_net_load);
                }
            } else {
                this.modal.removeView(textView);
            }
            TextView textView2 = this.progressTextView;
            if (textView2 == null || (relativeLayout = this.modal) == null) {
                return;
            }
            relativeLayout.addView(textView2);
        }
    }

    public void openPlugin(PluginVersion pluginVersion) {
        if (pluginVersion != null && pluginVersion.pluginDir != null) {
            this.bundlePath = pluginVersion.pluginDir + File.separator + this.mDownloadName + ".jsbundle";
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

    public void showProgressBarNow() {
        JLog.i(TAG, "showProgressBarNow");
        ViewGroup rootFrameLayout = getRootFrameLayout();
        ViewGroup modal = getModal();
        if (modal == null || rootFrameLayout == null) {
            return;
        }
        newProgressBar();
        if (modal.getParent() == null) {
            rootFrameLayout.addView(modal, new ViewGroup.LayoutParams(-1, -1));
        }
        rootFrameLayout.invalidate();
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void clearFresco() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            jDReactCallback.clearFresco();
        }
    }

    public Fragment createJDMFragment(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return showWebView(str);
    }

    public void dismissProgressBar() {
        JLog.i(TAG, "dismissProgressBar");
        this.mHandler.removeMessages(1003);
        this.mHandler.removeMessages(1004);
        this.mHandler.sendEmptyMessage(1004);
    }

    public void dismissProgressBarNow() {
        JLog.i(TAG, "dismissProgressBarNow");
        ViewGroup rootFrameLayout = getRootFrameLayout();
        ViewGroup modal = getModal();
        if (modal == null || rootFrameLayout == null) {
            return;
        }
        rootFrameLayout.removeView(modal);
        rootFrameLayout.invalidate();
    }

    public void doDebugTool() {
        if (!JDReactHelper.newInstance().isDebug() || Constants.APPDEBUGKIT.equals(this.reactBundle) || JDReactHelper.newInstance().isUserRNTools()) {
            return;
        }
        String str = ReactSharedPreferenceUtils.getBlockPath(JDReactConstant.BLOCK) + File.separator + this.reactBundle + ".jsbundle";
        JLog.d("debug_tools:", "path====      :" + str);
        JLog.d("debug_tools:", "bundlePath====:" + this.bundlePath);
        if (this.ll_debug_lock != null) {
            String str2 = this.bundlePath;
            if (str2 != null && str2.equals(str) && !TextUtils.isEmpty(this.version)) {
                this.ll_debug_lock.setVisibility(0);
            } else {
                this.ll_debug_lock.setVisibility(8);
            }
        }
        FloatingView.get().attach(getActivity());
        FloatingView.get().icon(R.drawable.jdreact_debug_icon);
        FloatingView.get().add();
        FloatingView.get().listener(new MagnetViewListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.6
            {
                JDReactNativeBaseFragment.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.floatingview.MagnetViewListener
            public void onClick(FloatingMagnetView floatingMagnetView) {
                Bundle bundle = new Bundle();
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.putOpt("debugBusinessId", JDReactNativeBaseFragment.this.reactBundle);
                    bundle.putString("param", jSONObject.toString());
                } catch (Exception unused) {
                }
                bundle.putString(JDReactConstant.IntentConstant.MODULE_NAME, Constants.APPDEBUGKIT);
                bundle.putString("appname", Constants.APPDEBUGKIT);
                ReactActivityUtilsHelperBase.startJDReactCommonActivity(JDReactNativeBaseFragment.this.getContext(), bundle);
            }

            @Override // com.jingdong.common.jdreactFramework.floatingview.MagnetViewListener
            public void onRemove(FloatingMagnetView floatingMagnetView) {
            }
        });
        if (Constants.isReload) {
            handleReloadJS();
            Constants.isReload = false;
            this.isFirst = true;
        }
        if (Constants.isJDReactDebug) {
            handleReloadJS();
            Constants.isJDReactDebug = false;
        }
        if (getReactManager() == null || getReactManager().getDevSupportManager() == null) {
            return;
        }
        getReactManager().getDevSupportManager().setIsShowDevOptionsDialog(false);
        if (Constants.isInspector) {
            inspector();
            Constants.isInspector = false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void downloadFinish() {
        JLog.i(TAG, "downloadFinish");
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
        if (getContext() == null) {
            return null;
        }
        TextView textView = new TextView(getContext());
        textView.setPadding(10, 10, 10, 10);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        textView.setBackgroundColor(getResources().getColor(R.color.jdreact_common_textview_bg_color));
        return textView;
    }

    public View getLottieLoadingView() {
        View view;
        try {
            view = JDReactHelper.newInstance().getLoadingLottieView();
        } catch (Exception unused) {
            view = null;
        }
        return view != null ? view : new JDReactProgressBar(getContext());
    }

    public ReactInstanceManager getReactManager() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            return abstractJDReactInitialHelper.getReactManager();
        }
        return null;
    }

    public ReactPackage getReactPackage() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            return jDReactCallback.getReactPackageManger();
        }
        return new JDReactPackageDemo();
    }

    public List<ReactPackage> getReactPackages() {
        IReactPackagesFactory iReactPackagesFactory = this.mReactPackagesFactory;
        if (iReactPackagesFactory != null) {
            return iReactPackagesFactory.createReactPackages();
        }
        return null;
    }

    public ViewGroup getRootFrameLayout() {
        return this.rootFrameLayout;
    }

    public void handleReloadJS() {
        if (getReactManager() == null || getReactManager().getDevSupportManager() == null) {
            return;
        }
        getReactManager().getDevSupportManager().handleReloadJS();
    }

    public void invokeDefaultOnBackKey() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        try {
            JDReactCallback jDReactCallback = this.mJDReactCallback;
            if (jDReactCallback != null) {
                jDReactCallback.onBackPressedCalled();
            }
        } catch (Exception e2) {
            JLog.e(TAG, "invokeDefaultOnBackKey: " + e2.getMessage());
        }
    }

    public boolean isNoAppDebugKite() {
        return JDReactHelper.newInstance().isDebug() && !Constants.APPDEBUGKIT.equals(this.reactBundle);
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public boolean isOpenLoadingView() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            return jDReactCallback.isOpenLoadingView();
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!this.mReuse || !this.hasCallStart) {
            start();
        }
        this.hasCallStart = true;
    }

    public void onActivityForResult(int i2, int i3, Intent intent) {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper == null || abstractJDReactInitialHelper.getReactManager() == null) {
            return;
        }
        this.mAbstractJDReactInitialHelper.getReactManager().onActivityResult(getActivity(), i2, i3, intent);
    }

    public boolean onBackPressed() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            if (abstractJDReactInitialHelper != null) {
                abstractJDReactInitialHelper.onBackPressed();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mParam = arguments;
        this.reactBundle = arguments.getString(REACT_BUNDLE);
        this.reactModule = this.mParam.getString(REACT_MOUDLE);
        this.reactParams = this.mParam.getBundle(REACT_PARAM);
        this.mIsDebug = this.mParam.getBoolean(DEBUG_MODE);
        this.version = this.mParam.getString(JS_VERSION);
        this.commitId = this.mParam.getString(COMMIT_ID);
        this.failed = this.mParam.getBoolean(IS_FAILED);
        this.force = this.mParam.getBoolean(FORCE_UPDATE);
        this.preload = this.mParam.getBoolean(FORCE_PRELOAD);
        this.bundlePath = this.mParam.getString(BUNDLE_PATCH);
        this.commonPath = this.mParam.getString(COMMON_PATH);
        this.splitbundle = this.mParam.getBoolean(SPLIT_BUNDLE);
        this.reactTitle = this.mParam.getString(REACT_TITLE);
        this.reactIsHidden = this.mParam.getBoolean(REACT_IS_HIDDEN);
        this.mType = this.mParam.getInt(REACT_TYPE);
        this.isLocalDebug = this.mParam.getBoolean(LOCAL_DEBUG);
        this.destBundlePath = this.mParam.getString(DEST_BUNDLE_PATH);
        this.mDownloadName = this.reactModule;
        this.mForceCheckUpdate = this.mParam.getBoolean(JDReactConstant.IntentConstant.FORCE_CHECK_UPDATE);
        this.mForceLoadAfterUpdateCheck = this.mParam.getBoolean(JDReactConstant.IntentConstant.FORCE_LOAD_AFTER_UPDATE_CHECK);
        this.mIntentBackupUrl = this.mParam.getString(JDReactConstant.IntentConstant.BACKUP_URL);
        Bundle bundle2 = this.reactParams;
        if (bundle2 != null) {
            this.mDeprecatedVersion = bundle2.getString(DEPRECATED_VERSION);
            this.isScanEnter = this.reactParams.getBoolean(IS_SCAN_ENTER);
        }
        this.mReuse = this.mParam.getBoolean(EXTRA_REUSE);
        if (JDReactHelper.newInstance().isDebug() && !Constants.APPDEBUGKIT.equals(this.reactBundle)) {
            Bundle bundle3 = this.mParam;
            if (bundle3 != null) {
                ReactSharedPreferenceUtils.setDebugInfo("param", bundle3.toString());
            }
            ReactSharedPreferenceUtils.setDebugInfo(JDReactConstant.IntentConstant.MODULE_NAME, this.reactModule);
            ReactSharedPreferenceUtils.setDebugInfo("version", this.version);
            this.isFirst = true;
        }
        if (ReactNativeUpdateManager.getInstance().isItForceUpdate(this.reactBundle)) {
            this.mDownloadName = this.reactBundle;
        } else if (ReactNativeUpdateManager.getInstance().isItForceUpdate(this.reactModule)) {
            this.mDownloadName = this.reactModule;
        } else {
            this.mDownloadName = this.reactModule;
        }
        this.mReportDataHashCode = hashCode() + "";
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        FragmentActivity activity = getActivity();
        this.mReportDataHashCode = hashCode() + "";
        RenderDataReport.getInstance().loadStart(this.mReportDataHashCode);
        if (this.mJDReactCallback == null && activity != null && (activity instanceof JDReactCallback)) {
            JLog.e("test", "seems callback is null");
            this.mJDReactCallback = (JDReactCallback) getActivity();
        }
        if (this.rootFrameLayout == null || !this.mReuse) {
            this.rootFrameLayout = new FrameLayout(getContext());
            this.root = layoutInflater.inflate(this.mJDReactCallback.getLayoutID(), viewGroup, false);
            if (JDReactHelper.newInstance().isDebug() && !Constants.APPDEBUGKIT.equals(this.reactBundle) && !JDReactHelper.newInstance().isUserRNTools()) {
                LinearLayout linearLayout = (LinearLayout) this.root.findViewById(R.id.ll_jdreact_debug_lock);
                this.ll_debug_lock = linearLayout;
                if (linearLayout != null) {
                    TextView textView = (TextView) linearLayout.findViewById(R.id.text_view);
                    String str = ReactSharedPreferenceUtils.getBlockPath(JDReactConstant.BLOCK) + File.separator + this.reactBundle + ".jsbundle";
                    JLog.d("debug_tools:", "onCreateView path====      :" + str);
                    JLog.d("debug_tools:", "onCreateView bundlePath====:" + this.bundlePath);
                    String str2 = this.bundlePath;
                    if (str2 != null && str2.equals(str) && !TextUtils.isEmpty(this.version)) {
                        if (textView != null) {
                            textView.setText("\u4e1a\u52a1\u7248\u672c\u53f7\uff1a" + this.version);
                        }
                        this.ll_debug_lock.setVisibility(0);
                    } else {
                        this.ll_debug_lock.setVisibility(8);
                    }
                }
            }
            if (this.mJDReactCallback.getLayoutID() == R.layout.jdreactnative_layout_common && this.reactIsHidden) {
                this.root.findViewById(R.id.rlTop).setVisibility(8);
            }
            this.rootFrameLayout.addView(this.root, new FrameLayout.LayoutParams(-1, -1));
        }
        return this.rootFrameLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        RenderDataReport.getInstance().clearData();
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
        this.mHandler.removeMessages(1003);
        this.mHandler.removeMessages(1004);
        this.mHandler.removeMessages(1001);
        this.mHandler.removeMessages(1002);
        this.mHandler.removeMessages(1005);
        this.mHandler.removeMessages(1006);
        this.mHandler.removeMessages(1007);
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

    public boolean onMenuKeyUp() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            return abstractJDReactInitialHelper.onMenuKeyUp();
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onPause();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onResume();
        }
        doDebugTool();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (!Constants.APPDEBUGKIT.equals(this.reactBundle) && JDReactHelper.newInstance().isDebug() && !JDReactHelper.newInstance().isUserRNTools()) {
            FloatingView.get().attach(getActivity());
        }
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        if (!Constants.APPDEBUGKIT.equals(this.reactBundle) && JDReactHelper.newInstance().isDebug() && !JDReactHelper.newInstance().isUserRNTools()) {
            FloatingView.get().detach(getActivity());
        }
        super.onStop();
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onStop();
        }
    }

    public void removeDialog() {
        LinearLayout linearLayout;
        ViewGroup rootFrameLayout = getRootFrameLayout();
        if (rootFrameLayout == null || (linearLayout = this.mNoDataView) == null || linearLayout.getParent() == null) {
            return;
        }
        rootFrameLayout.removeView(this.mNoDataView);
    }

    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        if ("screenSizeChanged".equals(str)) {
            ViewGroup viewGroup = (ViewGroup) this.rootFrameLayout.getParent();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            if (viewGroup != null) {
                viewGroup.removeView(this.rootFrameLayout);
                viewGroup.addView(this.rootFrameLayout, layoutParams);
            }
            this.rootFrameLayout.removeView(this.root);
            this.rootFrameLayout.addView(this.root, new FrameLayout.LayoutParams(-1, -1));
            this.modal = null;
        }
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

    public void setH5Params(String str) {
        this.mH5Params = str;
    }

    public void setJDReactCallback(JDReactCallback jDReactCallback) {
        this.mJDReactCallback = jDReactCallback;
    }

    public void setLoadExceptionListener(LoadExceptionListener loadExceptionListener) {
        this.mLoadExceptionListener = loadExceptionListener;
    }

    public void setLoadListener(LoadListener loadListener) {
        this.mLoadListener = loadListener;
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void setPVMta(boolean z) {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            jDReactCallback.enablePVMta(false);
        }
    }

    public void setParam(String str, boolean z) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putBoolean(str, z);
        setArguments(arguments);
    }

    public void setReactPackagesFactory(IReactPackagesFactory iReactPackagesFactory) {
        this.mReactPackagesFactory = iReactPackagesFactory;
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void showMpage() {
        this.mHandler.removeMessages(1007);
        this.mHandler.sendEmptyMessage(1007);
        RenderDataReport.getInstance().renderEnd(this.mReportDataHashCode, false, "1", "download failed", JDReactCommonPreloadManager.getInstance().isPreloadCommon());
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void showProgressBar() {
        JLog.i(TAG, "showProgressBar");
        this.mHandler.removeMessages(1004);
        this.mHandler.removeMessages(1003);
        this.mHandler.sendEmptyMessage(1003);
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public void showRetry() {
        JLog.i(TAG, "showRetry");
        showRetryDialog();
    }

    public void showRetryDialog() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        RenderDataReport.getInstance().showRetry(this.mReportDataHashCode);
        if (this.failed) {
            JLog.d(TAG, "Download name : " + this.mDownloadName);
            String appendParamsToUrl = ReactModuleAvailabilityUtils.appendParamsToUrl(ReactModuleAvailabilityUtils.getModuleBackupUrl(this.mDownloadName, this.mIntentBackupUrl), this.mH5Params);
            if (!TextUtils.isEmpty(appendParamsToUrl)) {
                if (!JumpUtils.isJumpProtocalHeader(appendParamsToUrl)) {
                    removeDialog();
                    if (this.mBaseFragment != null) {
                        return;
                    }
                    JDReactCallback jDReactCallback = this.mJDReactCallback;
                    if (jDReactCallback != null) {
                        this.mBaseFragment = jDReactCallback.createWebFragement(appendParamsToUrl);
                    }
                    if (this.mBaseFragment != null) {
                        JDReactCallback jDReactCallback2 = this.mJDReactCallback;
                        if (jDReactCallback2 != null) {
                            jDReactCallback2.refreshStatusBar(true);
                        }
                        getActivity().getSupportFragmentManager().beginTransaction().add(getRootViewHolderId(), this.mBaseFragment).commitAllowingStateLoss();
                        return;
                    }
                    JDReactCallback jDReactCallback3 = this.mJDReactCallback;
                    if (jDReactCallback3 != null) {
                        jDReactCallback3.lunchWebPage(appendParamsToUrl);
                    }
                    TrackUtil.trackLoadFail(7, this.reactModule);
                } else if (this.mJDReactCallback != null) {
                    dismissProgressBar();
                    this.mJDReactCallback.lunchOpenApp(appendParamsToUrl);
                    return;
                } else {
                    return;
                }
            }
        }
        if (this.mNoDataView == null) {
            this.mNoDataView = new LinearLayout(getContext());
            JDReactHelper.newInstance().showErrorRetryView(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.4
                {
                    JDReactNativeBaseFragment.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDReactNativeBaseFragment.this.mAbstractJDReactInitialHelper.isNetworkAvailable()) {
                        JDReactNativeBaseFragment.this.removeDialog();
                        if (JDReactNativeBaseFragment.this.failed) {
                            JDReactNativeBaseFragment.this.mAbstractJDReactInitialHelper.startToGetMetaData();
                            return;
                        }
                        if (JDReactHelper.newInstance().isDebug()) {
                            JLog.e(JDReactNativeBaseFragment.TAG, "onfailed retry the downloading");
                        }
                        JDReactNativeBaseFragment.this.mAbstractJDReactInitialHelper.startToInit();
                    }
                }
            }, this.mNoDataView);
            if (getRootFrameLayout() == null) {
                return;
            }
        }
        if (this.mNoDataView.getParent() == null) {
            this.mNoDataView.setVisibility(0);
            this.rootFrameLayout.addView(this.mNoDataView, new ViewGroup.LayoutParams(-1, -1));
        }
        this.rootFrameLayout.invalidate();
        TrackUtil.trackLoadFail(12, this.reactModule);
    }

    public Fragment showWebView(String str) {
        return null;
    }

    public void start() {
        JLog.i(TAG, "start");
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = new AbstractJDReactInitialHelper(getActivity(), this.reactBundle, this.commonPath, this.reactModule, this.reactParams, this.bundlePath, new ReactInstanceManager.ReactInstanceProgressListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.2
            {
                JDReactNativeBaseFragment.this = this;
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadCancel() {
                if (JDReactNativeBaseFragment.this.isOpenLoadingView()) {
                    JDReactNativeBaseFragment.this.dismissProgressBar();
                }
                AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = JDReactNativeBaseFragment.this.mAbstractJDReactInitialHelper;
                if (abstractJDReactInitialHelper2 != null) {
                    abstractJDReactInitialHelper2.handleLoadException(5);
                }
                JLog.i(JDReactNativeBaseFragment.TAG, "onReactLoadCancel time = " + System.currentTimeMillis());
                if (TextUtils.equals("JDReactDaoJia", JDReactNativeBaseFragment.this.reactModule)) {
                    JDReactNativeBaseFragment.this.showProgressBar();
                    if (!JDReactNativeBaseFragment.this.isLoadedPreset) {
                        JDReactNativeBaseFragment.this.isLoadedPreset = true;
                        JDReactNativeBaseFragment jDReactNativeBaseFragment = JDReactNativeBaseFragment.this;
                        jDReactNativeBaseFragment.openPlugin(ReactVersionUtils.getPluginPreloadDataPath(jDReactNativeBaseFragment.getContext(), JDReactNativeBaseFragment.this.reactModule));
                        return;
                    }
                    JDReactNativeBaseFragment.this.showMpage();
                }
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadFinish() {
                if (JDReactNativeBaseFragment.this.isOpenLoadingView()) {
                    JDReactNativeBaseFragment.this.dismissProgressBar();
                }
                AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = JDReactNativeBaseFragment.this.mAbstractJDReactInitialHelper;
                if (abstractJDReactInitialHelper2 != null) {
                    abstractJDReactInitialHelper2.onResume();
                }
                RenderDataReport.getInstance().renderEnd(JDReactNativeBaseFragment.this.mReportDataHashCode, true, null, null, JDReactCommonPreloadManager.getInstance().isPreloadCommon());
                JLog.i(JDReactNativeBaseFragment.TAG, "onReactLoadFinish time = " + System.currentTimeMillis());
                TrackUtil.trackLoadFinish(JDReactNativeBaseFragment.this.reactModule);
                JDReactCommonPreloadManager.getInstance().preloadCommonBundle(JDReactNativeBaseFragment.this.getActivity());
                if (JDReactNativeBaseFragment.this.isNoAppDebugKite() && JDReactNativeBaseFragment.this.isFirst && JDReactNativeBaseFragment.this.getReactManager().getDevSupportManager().getDevSettings() != null && JDReactNativeBaseFragment.this.getReactManager().getDevSupportManager().getDevSettings().isElementInspectorEnabled()) {
                    JDReactNativeBaseFragment.this.isFirst = false;
                    JDReactNativeBaseFragment.this.inspector();
                }
                if (JDReactNativeBaseFragment.this.mLoadListener != null) {
                    JDReactNativeBaseFragment.this.mLoadListener.onLoadFinish(JDReactNativeBaseFragment.this.reactModule, JDReactNativeBaseFragment.this.version, JDReactNativeBaseFragment.this.reactBundle);
                }
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadStart() {
                if (JDReactNativeBaseFragment.this.isOpenLoadingView()) {
                    JDReactNativeBaseFragment.this.showProgressBar();
                }
                JLog.i(JDReactNativeBaseFragment.TAG, "onReactLoadStart time = " + System.currentTimeMillis());
                TrackUtil.trackLoadStart(JDReactNativeBaseFragment.this.reactModule);
                if (JDReactNativeBaseFragment.this.mLoadListener != null) {
                    JDReactNativeBaseFragment.this.mLoadListener.onLoadStart(JDReactNativeBaseFragment.this.reactModule, JDReactNativeBaseFragment.this.version, JDReactNativeBaseFragment.this.reactBundle);
                }
            }
        }, this.splitbundle, this.mType, this.version, this.commitId, this.failed, this.force, this.preload, this.mReportDataHashCode) { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.3
            {
                JDReactNativeBaseFragment.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void defaultOnBackPressed() {
                JDReactNativeBaseFragment.this.invokeDefaultOnBackKey();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getDefaultReactPackage() {
                return JDReactNativeBaseFragment.this.getReactPackage();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getExtendReactPackage() {
                return JDReactNativeBaseFragment.this.getCustomReactPackage();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected List<ReactPackage> getReactPackages() {
                return JDReactNativeBaseFragment.this.getReactPackages();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void initRootView(ReactRootView reactRootView) {
                JDReactNativeBaseFragment.this.mJDReactCallback.addRootView(reactRootView, JDReactNativeBaseFragment.this.reactTitle, JDReactNativeBaseFragment.this.reactIsHidden, this.reactModule, this.reactBundle);
            }
        };
        this.mAbstractJDReactInitialHelper = abstractJDReactInitialHelper;
        abstractJDReactInitialHelper.setScanEnter(this.isScanEnter);
        this.mAbstractJDReactInitialHelper.setLoadExceptionListener(this.mLoadExceptionListener);
        this.mAbstractJDReactInitialHelper.setUIHandler(this);
        this.mAbstractJDReactInitialHelper.setForceCheckUpdate(this.mForceCheckUpdate);
        this.mAbstractJDReactInitialHelper.setForceLoadAfterUpdateCheck(this.mForceLoadAfterUpdateCheck);
        this.mAbstractJDReactInitialHelper.setDeprecatedVersion(this.mDeprecatedVersion);
        if (!TextUtils.isEmpty(this.destBundlePath)) {
            showProgressBarNow();
            if (this.destBundlePath.startsWith("jdreact/")) {
                this.mAbstractJDReactInitialHelper.setupLayout(ReactVersionUtils.getPluginPreloadDataPath(getContext(), this.reactModule));
            } else {
                this.mAbstractJDReactInitialHelper.setupLayout(ReactVersionUtils.getPluginVersionForPath(this.destBundlePath, this.reactModule));
            }
        } else if (this.isLocalDebug) {
            this.mAbstractJDReactInitialHelper.initRootView(false);
        } else {
            this.mAbstractJDReactInitialHelper.startToInit1();
        }
    }

    public void unregistLoadingListener() {
        if (this.mPluginListener != null) {
            ReactNativeUpdateManager.getInstance().unregistForceDownloadTaskListener(this.mDownloadName, this.mPluginListener);
            this.mPluginListener = null;
        }
    }

    public static JDReactNativeBaseFragment newInstance(String str, String str2, Bundle bundle, boolean z, String str3, String str4, boolean z2, boolean z3, String str5, String str6, boolean z4, String str7, boolean z5, int i2, boolean z6) {
        JDReactNativeBaseFragment jDReactNativeBaseFragment = new JDReactNativeBaseFragment();
        jDReactNativeBaseFragment.setArguments(initData(str, str2, bundle, z, str3, str4, z2, z3, str5, str6, z4, str7, z5, i2, z6));
        return jDReactNativeBaseFragment;
    }

    public void setParam(String str, String str2) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putString(str, str2);
        setArguments(arguments);
    }

    public static JDReactNativeBaseFragment newInstance(String str, String str2, Bundle bundle, boolean z, String str3, boolean z2, boolean z3, String str4, String str5, boolean z4, String str6, boolean z5, int i2) {
        return newInstance(str, str2, bundle, z, str3, z2, z3, str4, str5, z4, str6, z5, i2, false);
    }

    public static JDReactNativeBaseFragment newInstance(String str, String str2, Bundle bundle, boolean z, String str3, boolean z2, boolean z3, String str4, String str5, boolean z4, String str6, boolean z5) {
        return newInstance(str, str2, bundle, z, str3, z2, z3, str4, str5, z4, str6, z5, 0, false);
    }

    public static JDReactNativeBaseFragment newInstance(String str, String str2, Bundle bundle, boolean z, String str3, boolean z2, String str4) {
        JDReactNativeBaseFragment jDReactNativeBaseFragment = new JDReactNativeBaseFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(REACT_BUNDLE, str);
        bundle2.putString(REACT_MOUDLE, str2);
        bundle2.putBundle(REACT_PARAM, bundle);
        bundle2.putBoolean(SPLIT_BUNDLE, z);
        bundle2.putString(REACT_TITLE, str3);
        bundle2.putBoolean(LOCAL_DEBUG, z2);
        bundle2.putString(DEST_BUNDLE_PATH, str4);
        bundle2.putBoolean(REACT_IS_HIDDEN, true);
        jDReactNativeBaseFragment.setArguments(bundle2);
        return jDReactNativeBaseFragment;
    }

    public void setParam(String str, int i2) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putInt(str, i2);
        setArguments(arguments);
    }
}
