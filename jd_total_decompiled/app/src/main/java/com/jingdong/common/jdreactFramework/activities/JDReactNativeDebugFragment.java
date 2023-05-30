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
import androidx.annotation.NonNull;
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
public class JDReactNativeDebugFragment extends Fragment implements AbstractInitialHelper.UIHandler {
    private static String BUNDLE_PATCH = "bundlepath";
    private static final int DISMISS_PROGRESS = 1004;
    private static final int DOWNLOAD_FAILED = 1001;
    private static final int DOWNLOAD_FINISHED = 1002;
    private static final int DOWNLOAD_META_FAILED = 1005;
    private static final int DOWNLOAD_META_FINISHED = 1006;
    private static String EXTRA_REUSE = "reuse";
    private static String IS_LOCAL_DEBUG = "localDebug";
    private static String IS_SCAN_ENTER = "force_download_mode";
    private static String REACT_IS_HIDDEN = "reacthide";
    private static String REACT_MOUDLE = "reactMoudle";
    private static String REACT_PARAM = "reactParams";
    private static String REACT_TITLE = "reacttitle";
    private static final int SHOW_M_PAGE = 1007;
    private static final int SHOW_PROGRESS = 1003;
    private static final String TAG = "JDReactFragment";
    private String bundlePath;
    private boolean hasCallStart;
    private boolean isLoadedPreset;
    AbstractJDReactInitialHelper mAbstractJDReactInitialHelper;
    Fragment mBaseFragment;
    private String mH5Params;
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
    RelativeLayout modal;
    View progressBar;
    TextView progressTextView;
    private boolean reactIsHidden;
    private String reactModule;
    private Bundle reactParams;
    private String reactTitle;
    private View root;
    private ViewGroup rootFrameLayout;
    private boolean isFirst = false;
    private boolean isScanEnter = false;
    private boolean isLocalDebug = false;
    boolean mLoadingCompletely = false;
    private boolean splitbundle = true;
    private Handler mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1001:
                case 1005:
                    JDReactNativeDebugFragment.this.showRetryDialog();
                    return;
                case 1002:
                    JDReactNativeDebugFragment.this.unregistLoadingListener();
                    JDReactNativeDebugFragment jDReactNativeDebugFragment = JDReactNativeDebugFragment.this;
                    jDReactNativeDebugFragment.mLoadingCompletely = true;
                    jDReactNativeDebugFragment.openPlugin(ReactNativeFileManager.getPluginDir(jDReactNativeDebugFragment.getContext(), JDReactNativeDebugFragment.this.reactModule));
                    return;
                case 1003:
                    JDReactNativeDebugFragment.this.showProgressBarNow();
                    return;
                case 1004:
                    JDReactNativeDebugFragment.this.dismissProgressBarNow();
                    return;
                case 1006:
                    JDReactNativeDebugFragment.this.mAbstractJDReactInitialHelper.startToInit();
                    return;
                case 1007:
                    String appendParamsToUrl = ReactModuleAvailabilityUtils.appendParamsToUrl(ReactModuleAvailabilityUtils.getModuleBackupUrl(JDReactNativeDebugFragment.this.reactModule, JDReactNativeDebugFragment.this.mIntentBackupUrl), JDReactNativeDebugFragment.this.mH5Params);
                    if (!TextUtils.isEmpty(appendParamsToUrl)) {
                        if (JumpUtils.isJumpProtocalHeader(appendParamsToUrl)) {
                            if (JDReactNativeDebugFragment.this.mJDReactCallback != null) {
                                JDReactNativeDebugFragment.this.dismissProgressBar();
                                JDReactNativeDebugFragment.this.mJDReactCallback.lunchOpenApp(appendParamsToUrl);
                                return;
                            }
                        } else {
                            JDReactNativeDebugFragment jDReactNativeDebugFragment2 = JDReactNativeDebugFragment.this;
                            if (jDReactNativeDebugFragment2.mBaseFragment != null) {
                                return;
                            }
                            if (jDReactNativeDebugFragment2.mJDReactCallback != null) {
                                JDReactNativeDebugFragment jDReactNativeDebugFragment3 = JDReactNativeDebugFragment.this;
                                jDReactNativeDebugFragment3.mBaseFragment = jDReactNativeDebugFragment3.mJDReactCallback.createWebFragement(appendParamsToUrl);
                            }
                            JDReactNativeDebugFragment jDReactNativeDebugFragment4 = JDReactNativeDebugFragment.this;
                            if (jDReactNativeDebugFragment4.mBaseFragment == null) {
                                if (jDReactNativeDebugFragment4.mJDReactCallback != null) {
                                    JDReactNativeDebugFragment.this.dismissProgressBar();
                                    JDReactNativeDebugFragment.this.mJDReactCallback.lunchWebPage(appendParamsToUrl);
                                    JDReactNativeDebugFragment.this.getActivity().finish();
                                    return;
                                }
                            } else {
                                jDReactNativeDebugFragment4.dismissProgressBar();
                                if (JDReactNativeDebugFragment.this.getActivity() == null || JDReactNativeDebugFragment.this.getActivity().getSupportFragmentManager() == null) {
                                    return;
                                }
                                if (JDReactNativeDebugFragment.this.mJDReactCallback != null) {
                                    JDReactNativeDebugFragment.this.mJDReactCallback.refreshStatusBar(true);
                                }
                                JDReactNativeDebugFragment.this.getActivity().getSupportFragmentManager().beginTransaction().add(JDReactNativeDebugFragment.this.getRootViewHolderId(), JDReactNativeDebugFragment.this.mBaseFragment).commitAllowingStateLoss();
                                return;
                            }
                        }
                        TrackUtil.trackLoadFail(7, JDReactNativeDebugFragment.this.reactModule);
                    }
                    JDReactNativeDebugFragment.this.mHandler.removeMessages(1001);
                    JDReactNativeDebugFragment.this.mHandler.sendEmptyMessage(1001);
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
        relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return this.modal;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRootViewHolderId() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        int rootViewHolderId = jDReactCallback != null ? jDReactCallback.getRootViewHolderId() : 0;
        return rootViewHolderId <= 0 ? R.id.reactRootViewHolder : rootViewHolderId;
    }

    public static Bundle initData(String str, Bundle bundle, String str2, String str3, boolean z) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(REACT_MOUDLE, str);
        bundle2.putBundle(REACT_PARAM, bundle);
        bundle2.putString(BUNDLE_PATCH, str2);
        bundle2.putString(REACT_TITLE, str3);
        bundle2.putBoolean(REACT_IS_HIDDEN, z);
        return bundle2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inspector() {
        ReactContext currentReactContext;
        if (getReactManager() == null || getReactManager().getDevSupportManager() == null || (currentReactContext = getReactManager().getCurrentReactContext()) == null) {
            return;
        }
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("toggleElementInspector", null);
    }

    public static JDReactNativeDebugFragment newInstance(String str, Bundle bundle, String str2, String str3, boolean z) {
        JDReactNativeDebugFragment jDReactNativeDebugFragment = new JDReactNativeDebugFragment();
        jDReactNativeDebugFragment.setArguments(initData(str, bundle, str2, str3, z));
        return jDReactNativeDebugFragment;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void openPlugin(PluginVersion pluginVersion) {
        if (pluginVersion != null && pluginVersion.pluginDir != null) {
            this.bundlePath = pluginVersion.pluginDir + File.separator + this.reactModule + ".jsbundle";
            this.mAbstractJDReactInitialHelper.setupLayout(pluginVersion);
            return;
        }
        showMpage();
    }

    /* JADX INFO: Access modifiers changed from: private */
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
        if (!JDReactHelper.newInstance().isDebug() || Constants.APPDEBUGKIT.equals(this.reactModule) || JDReactHelper.newInstance().isUserRNTools()) {
            return;
        }
        String str = ReactSharedPreferenceUtils.getBlockPath(JDReactConstant.BLOCK) + File.separator + this.reactModule + ".jsbundle";
        FloatingView.get().attach(getActivity());
        FloatingView.get().icon(R.drawable.jdreact_debug_icon);
        FloatingView.get().add();
        FloatingView.get().listener(new MagnetViewListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.6
            @Override // com.jingdong.common.jdreactFramework.floatingview.MagnetViewListener
            public void onClick(FloatingMagnetView floatingMagnetView) {
                Bundle bundle = new Bundle();
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.putOpt("debugBusinessId", JDReactNativeDebugFragment.this.reactModule);
                    bundle.putString("param", jSONObject.toString());
                } catch (Exception unused) {
                }
                bundle.putString(JDReactConstant.IntentConstant.MODULE_NAME, Constants.APPDEBUGKIT);
                bundle.putString("appname", Constants.APPDEBUGKIT);
                ReactActivityUtilsHelperBase.startJDReactCommonActivity(JDReactNativeDebugFragment.this.getContext(), bundle);
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
        return JDReactHelper.newInstance().isDebug() && !Constants.APPDEBUGKIT.equals(this.reactModule);
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper.UIHandler
    public boolean isOpenLoadingView() {
        JDReactCallback jDReactCallback = this.mJDReactCallback;
        if (jDReactCallback != null) {
            return jDReactCallback.isOpenLoadingView();
        }
        return false;
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
        this.reactModule = arguments.getString(REACT_MOUDLE);
        this.reactParams = this.mParam.getBundle(REACT_PARAM);
        this.bundlePath = this.mParam.getString(BUNDLE_PATCH);
        this.reactTitle = this.mParam.getString(REACT_TITLE);
        this.reactIsHidden = this.mParam.getBoolean(REACT_IS_HIDDEN);
        this.mIntentBackupUrl = this.mParam.getString(JDReactConstant.IntentConstant.BACKUP_URL);
        Bundle bundle2 = this.reactParams;
        if (bundle2 != null) {
            this.isScanEnter = bundle2.getBoolean(IS_SCAN_ENTER);
            this.isLocalDebug = this.reactParams.getBoolean(IS_LOCAL_DEBUG);
        }
        this.mReuse = this.mParam.getBoolean(EXTRA_REUSE);
        if (JDReactHelper.newInstance().isDebug() && !Constants.APPDEBUGKIT.equals(this.reactModule)) {
            Bundle bundle3 = this.mParam;
            if (bundle3 != null) {
                ReactSharedPreferenceUtils.setDebugInfo("param", bundle3.toString());
            }
            ReactSharedPreferenceUtils.setDebugInfo(JDReactConstant.IntentConstant.MODULE_NAME, this.reactModule);
            this.isFirst = true;
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
        if (!Constants.APPDEBUGKIT.equals(this.reactModule) && JDReactHelper.newInstance().isDebug() && !JDReactHelper.newInstance().isUserRNTools()) {
            FloatingView.get().attach(getActivity());
        }
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        if (!Constants.APPDEBUGKIT.equals(this.reactModule) && JDReactHelper.newInstance().isDebug() && !JDReactHelper.newInstance().isUserRNTools()) {
            FloatingView.get().detach(getActivity());
        }
        super.onStop();
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onStop();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        JLog.d("opentime", "onViewCreated" + System.currentTimeMillis() + "");
        super.onViewCreated(view, bundle);
        if (!this.mReuse || !this.hasCallStart) {
            start();
        }
        this.hasCallStart = true;
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
        dismissProgressBar();
        RenderDataReport.getInstance().showRetry(this.mReportDataHashCode);
        if (this.mNoDataView == null) {
            this.mNoDataView = new LinearLayout(getContext());
            JDReactHelper.newInstance().showErrorRetryView(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (JDReactNativeDebugFragment.this.mAbstractJDReactInitialHelper.isNetworkAvailable()) {
                        JDReactNativeDebugFragment.this.removeDialog();
                        JDReactNativeDebugFragment.this.mAbstractJDReactInitialHelper.startToInit1();
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
        FragmentActivity activity = getActivity();
        String str = this.reactModule;
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = new AbstractJDReactInitialHelper(activity, str, "", str, this.reactParams, this.bundlePath, new ReactInstanceManager.ReactInstanceProgressListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.2
            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadCancel() {
                if (JDReactNativeDebugFragment.this.isOpenLoadingView()) {
                    JDReactNativeDebugFragment.this.dismissProgressBar();
                }
                AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = JDReactNativeDebugFragment.this.mAbstractJDReactInitialHelper;
                if (abstractJDReactInitialHelper2 != null) {
                    abstractJDReactInitialHelper2.handleLoadException(5);
                }
                JLog.i(JDReactNativeDebugFragment.TAG, "onReactLoadCancel time = " + System.currentTimeMillis());
                if (TextUtils.equals("JDReactDaoJia", JDReactNativeDebugFragment.this.reactModule)) {
                    JDReactNativeDebugFragment.this.showProgressBar();
                    if (!JDReactNativeDebugFragment.this.isLoadedPreset) {
                        JDReactNativeDebugFragment.this.isLoadedPreset = true;
                        JDReactNativeDebugFragment jDReactNativeDebugFragment = JDReactNativeDebugFragment.this;
                        jDReactNativeDebugFragment.openPlugin(ReactVersionUtils.getPluginPreloadDataPath(jDReactNativeDebugFragment.getContext(), JDReactNativeDebugFragment.this.reactModule));
                        return;
                    }
                    JDReactNativeDebugFragment.this.showMpage();
                }
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadFinish() {
                JLog.d("opentime", "onReactLoadFinish" + System.currentTimeMillis() + "");
                if (JDReactNativeDebugFragment.this.isOpenLoadingView()) {
                    JDReactNativeDebugFragment.this.dismissProgressBar();
                }
                AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = JDReactNativeDebugFragment.this.mAbstractJDReactInitialHelper;
                if (abstractJDReactInitialHelper2 != null) {
                    abstractJDReactInitialHelper2.onResume();
                }
                RenderDataReport.getInstance().renderEnd(JDReactNativeDebugFragment.this.mReportDataHashCode, true, null, null, JDReactCommonPreloadManager.getInstance().isPreloadCommon());
                JLog.i(JDReactNativeDebugFragment.TAG, "onReactLoadFinish time = " + System.currentTimeMillis());
                TrackUtil.trackLoadFinish(JDReactNativeDebugFragment.this.reactModule);
                JDReactCommonPreloadManager.getInstance().preloadCommonBundle(JDReactNativeDebugFragment.this.getActivity());
                if (JDReactNativeDebugFragment.this.isNoAppDebugKite() && JDReactNativeDebugFragment.this.isFirst && JDReactNativeDebugFragment.this.getReactManager().getDevSupportManager().getDevSettings() != null && JDReactNativeDebugFragment.this.getReactManager().getDevSupportManager().getDevSettings().isElementInspectorEnabled()) {
                    JDReactNativeDebugFragment.this.isFirst = false;
                    JDReactNativeDebugFragment.this.inspector();
                }
                if (JDReactNativeDebugFragment.this.mLoadListener != null) {
                    JDReactNativeDebugFragment.this.mLoadListener.onLoadFinish(JDReactNativeDebugFragment.this.reactModule, "", JDReactNativeDebugFragment.this.reactModule);
                }
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadStart() {
                JLog.d("opentime", "onReactLoadStart" + System.currentTimeMillis() + "");
                if (JDReactNativeDebugFragment.this.isOpenLoadingView()) {
                    JDReactNativeDebugFragment.this.showProgressBar();
                }
                JLog.i(JDReactNativeDebugFragment.TAG, "onReactLoadStart time = " + System.currentTimeMillis());
                TrackUtil.trackLoadStart(JDReactNativeDebugFragment.this.reactModule);
                if (JDReactNativeDebugFragment.this.mLoadListener != null) {
                    JDReactNativeDebugFragment.this.mLoadListener.onLoadStart(JDReactNativeDebugFragment.this.reactModule, "", JDReactNativeDebugFragment.this.reactModule);
                }
            }
        }, this.splitbundle, 4, "", "", false, false, false, this.mReportDataHashCode) { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.3
            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void defaultOnBackPressed() {
                JDReactNativeDebugFragment.this.invokeDefaultOnBackKey();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getDefaultReactPackage() {
                return JDReactNativeDebugFragment.this.getReactPackage();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getExtendReactPackage() {
                return JDReactNativeDebugFragment.this.getCustomReactPackage();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected List<ReactPackage> getReactPackages() {
                return JDReactNativeDebugFragment.this.getReactPackages();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void initRootView(ReactRootView reactRootView) {
                JDReactCallback jDReactCallback = JDReactNativeDebugFragment.this.mJDReactCallback;
                String str2 = JDReactNativeDebugFragment.this.reactTitle;
                boolean z = JDReactNativeDebugFragment.this.reactIsHidden;
                String str3 = this.reactModule;
                jDReactCallback.addRootView(reactRootView, str2, z, str3, str3);
            }
        };
        this.mAbstractJDReactInitialHelper = abstractJDReactInitialHelper;
        abstractJDReactInitialHelper.setScanEnter(this.isScanEnter);
        this.mAbstractJDReactInitialHelper.setLoadExceptionListener(this.mLoadExceptionListener);
        this.mAbstractJDReactInitialHelper.setUIHandler(this);
        if (this.isScanEnter) {
            this.mAbstractJDReactInitialHelper.startToInit1();
        } else if (this.isLocalDebug) {
            this.mAbstractJDReactInitialHelper.initRootView(false);
        } else {
            showProgressBarNow();
            if (!TextUtils.isEmpty(this.bundlePath)) {
                this.mAbstractJDReactInitialHelper.setupLayout(ReactVersionUtils.getPluginVersionForPath(this.bundlePath, this.reactModule));
            } else {
                this.mAbstractJDReactInitialHelper.setupLayout(ReactVersionUtils.getPluginPreloadDataPath(getContext(), this.reactModule));
            }
        }
    }

    public void unregistLoadingListener() {
        if (this.mPluginListener != null) {
            ReactNativeUpdateManager.getInstance().unregistForceDownloadTaskListener(this.reactModule, this.mPluginListener);
            this.mPluginListener = null;
        }
    }

    public void setParam(String str, String str2) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putString(str, str2);
        setArguments(arguments);
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
