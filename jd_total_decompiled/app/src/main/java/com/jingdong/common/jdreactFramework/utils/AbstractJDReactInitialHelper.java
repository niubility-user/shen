package com.jingdong.common.jdreactFramework.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JDReactJSLoadingModule;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.shell.MainReactPackage;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.floatingview.FloatingMagnetView;
import com.jingdong.common.jdreactFramework.floatingview.FloatingView;
import com.jingdong.common.jdreactFramework.floatingview.MagnetViewListener;
import com.jingdong.common.jdreactFramework.helper.ElderModeHelper;
import com.jingdong.common.jdreactFramework.helper.InitErrorCustomizer;
import com.jingdong.common.jdreactFramework.helper.LoadExceptionListener;
import com.jingdong.common.jdreactFramework.helper.UIModeHelper;
import com.jingdong.common.jdreactFramework.lifecycle.LifecycleReporter;
import com.jingdong.common.jdreactFramework.modules.JDReactAppearanceModule;
import com.jingdong.common.jdreactFramework.modules.JDReactElderModeModule;
import com.jingdong.common.jdreactFramework.preload.EngineHelper;
import com.jingdong.common.jdreactFramework.preload.JDReactCommonPreloadManager;
import com.jingdong.common.jdreactFramework.preload.JDReactPreloadInstanceManager;
import com.jingdong.common.jdreactFramework.preload.JDReactPreloadManager;
import com.jingdong.common.jdreactFramework.track.RenderDataReport;
import com.jingdong.common.jdreactFramework.track.TrackConstant;
import com.jingdong.common.jdreactFramework.track.TrackUtil;
import com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class AbstractJDReactInitialHelper extends AbstractInitialHelper implements DefaultHardwareBackBtnHandler {
    public static final int COMMON_ASSET_FILE = 3;
    public static final int FULL_ASSET_FILE = 1;
    private static Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final String TAG = "JDReactInitialHelper";
    private static ReactPackage mJDReactPackage;
    private String bundlePath;
    private int checkCount;
    private String commitId;
    private String commonPath;
    private ElderModeHelper.Unregister elderModeUnregister;
    private boolean isCheckError;
    private boolean isCommonPreload;
    private boolean isLoadError;
    private JDReactPreloadManager jdReactPreloadManager;
    private EngineHelper mEngineHelper;
    private boolean mISRootView;
    private LoadExceptionListener mLoadExceptionListener;
    private boolean mPreload;
    private ReactInstanceManager mReactInstanceManager;
    private ReactInstanceManager.ReactInstanceProgressListener mReactInstanceProgressListener;
    private ReactRootView mRootView;
    private AbstractInitialHelper.UIHandler mUIHandler;
    private Bundle reactParams;
    private boolean splitbundle;
    private int type;
    private UIModeHelper.Unregister uiModeUnregister;

    /* loaded from: classes5.dex */
    public static class JDReactNativeModuleCallExceptionHandler implements NativeModuleCallExceptionHandler {
        private WeakReference<AbstractJDReactInitialHelper> mInitialHelper;

        public JDReactNativeModuleCallExceptionHandler(AbstractJDReactInitialHelper abstractJDReactInitialHelper) {
            this.mInitialHelper = new WeakReference<>(abstractJDReactInitialHelper);
        }

        @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
        public void handleException(Exception exc) {
            WeakReference<AbstractJDReactInitialHelper> weakReference = this.mInitialHelper;
            AbstractJDReactInitialHelper abstractJDReactInitialHelper = weakReference == null ? null : weakReference.get();
            if (abstractJDReactInitialHelper != null && !abstractJDReactInitialHelper.isLoadError && abstractJDReactInitialHelper.isCheckError) {
                abstractJDReactInitialHelper.isLoadError = true;
                JLog.i("checkError", "isLoadError is true");
            }
            if (exc != null) {
                if (abstractJDReactInitialHelper != null) {
                    RenderDataReport.getInstance().putData(abstractJDReactInitialHelper.mReportDataHashCode, "jsRuntimeError", DYConstants.DY_TRUE);
                }
                if (abstractJDReactInitialHelper == null) {
                    JLog.d(AbstractJDReactInitialHelper.TAG, "postException 1");
                    JDReactHelper.newInstance().postException(exc, new ArrayMap());
                } else {
                    ArrayMap arrayMap = new ArrayMap();
                    arrayMap.put("moduleName", abstractJDReactInitialHelper.reactModule);
                    arrayMap.put("appName", abstractJDReactInitialHelper.reactBundle);
                    arrayMap.put("commonVersion", TextUtils.equals(abstractJDReactInitialHelper.pluginCommonVersion, "0.0") ? "" : abstractJDReactInitialHelper.pluginCommonVersion);
                    arrayMap.put("moduleVersion", TextUtils.equals(abstractJDReactInitialHelper.version, "0.0") ? "" : abstractJDReactInitialHelper.version);
                    if (abstractJDReactInitialHelper.commitId != null && !abstractJDReactInitialHelper.commitId.isEmpty()) {
                        arrayMap.put(JDReactConstant.COMMITID, abstractJDReactInitialHelper.commitId);
                    }
                    JLog.d(AbstractJDReactInitialHelper.TAG, "postException 2 " + arrayMap.toString());
                    JDReactHelper.newInstance().postException(exc, arrayMap);
                }
            }
            if (abstractJDReactInitialHelper != null) {
                abstractJDReactInitialHelper.handleLoadException(4);
            }
        }
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, String str3) {
        this(activity, str, str2, str, null, str3, null, false, 1, "0.0", false, false);
    }

    static /* synthetic */ int access$304(AbstractJDReactInitialHelper abstractJDReactInitialHelper) {
        int i2 = abstractJDReactInitialHelper.checkCount + 1;
        abstractJDReactInitialHelper.checkCount = i2;
        return i2;
    }

    private void addExtendReactPackage(ReactInstanceManagerBuilder reactInstanceManagerBuilder) {
        ReactPackage extendReactPackage = getExtendReactPackage();
        if (extendReactPackage == null || reactInstanceManagerBuilder == null) {
            return;
        }
        reactInstanceManagerBuilder.addPackage(extendReactPackage);
    }

    public void clearReactManager() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.removeReactInstanceProgressListener();
            this.mReactInstanceManager.onHostDestroy();
            this.mReactInstanceManager.destroy();
            this.mReactInstanceManager = null;
        }
        JDReactPreloadManager jDReactPreloadManager = this.jdReactPreloadManager;
        if (jDReactPreloadManager != null && jDReactPreloadManager.getReactManager() != null) {
            this.jdReactPreloadManager.getReactManager().destroy();
        }
        ReactRootView reactRootView = this.mRootView;
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            ViewParent parent = this.mRootView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeAllViews();
            }
            this.mRootView = null;
        }
        if (this.mPreload) {
            JDReactPreloadInstanceManager.newInstance().preloadReact(this.reactModule);
        }
        LifecycleReporter.dispatchDestroy(this.reactModule);
    }

    public static Activity getCurrentMyActivity() {
        return JDReactHelper.newInstance().getCurrentMyActivity();
    }

    public static String getJDReactFrameworkVersion() {
        return "0.59.9";
    }

    public static ReactPackage getPackageManger() {
        return mJDReactPackage;
    }

    private ReactInstanceManager initDebugReactManager(Activity activity, String str) {
        ReactInstanceManagerBuilder addPackage = ReactInstanceManager.builder().setApplication(activity.getApplication()).setJSMainModulePath("jsbundles/" + str).setCurrentActivity(activity).addPackage(new MainReactPackage());
        if (getDefaultReactPackage() != null) {
            addPackage.addPackage(getDefaultReactPackage());
        } else {
            ReactPackage reactPackage = mJDReactPackage;
            if (reactPackage != null) {
                addPackage.addPackage(reactPackage);
            }
        }
        addReactPackages(addPackage);
        addExtendReactPackage(addPackage);
        if (!JDReactHelper.newInstance().isDebug()) {
            addPackage.setNativeModuleCallExceptionHandler(new JDReactNativeModuleCallExceptionHandler(this));
        }
        return addPackage.setUseDeveloperSupport(true).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
    }

    private void initSeperateReactManager(Activity activity) {
        ReactInstanceManagerBuilder addPackage = ReactInstanceManager.builder().setApplication(activity.getApplication()).setSeperateBundleAssetName(this.bundlePath, "jdreact/JDReactCommon/JDReactCommon.jsbundle").setJSMainModulePath("jsbundles/" + this.reactModule).setCurrentActivity(activity).addPackage(new MainReactPackage());
        if (getDefaultReactPackage() != null) {
            addPackage.addPackage(getDefaultReactPackage());
        } else {
            ReactPackage reactPackage = mJDReactPackage;
            if (reactPackage != null) {
                addPackage.addPackage(reactPackage);
            }
        }
        addReactPackages(addPackage);
        addExtendReactPackage(addPackage);
        if (!JDReactHelper.newInstance().isDebug()) {
            addPackage.setNativeModuleCallExceptionHandler(new JDReactNativeModuleCallExceptionHandler(this));
        }
        if (JDReactHelper.newInstance().isDebug() && Constants.APPDEBUGKIT.equals(this.reactBundle)) {
            this.mReactInstanceManager = addPackage.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
        } else {
            this.mReactInstanceManager = addPackage.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
        }
    }

    public static void setPackageManger(ReactPackage reactPackage) {
        mJDReactPackage = reactPackage;
    }

    private void showFloatView() {
        String str;
        if (JDReactHelper.newInstance().showBundleInfo()) {
            FloatingView.get().attach(this.mActivity);
            if (this.mPluginVersion != null) {
                FloatingView floatingView = FloatingView.get();
                StringBuilder sb = new StringBuilder();
                sb.append("\u5305\u7c7b\u578b\uff1a");
                sb.append(this.mPluginVersion.full ? "\u5168\u5305" : "\u62c6\u5206\u5305");
                sb.append("\n\u5305\u7248\u672c\uff1a");
                sb.append(this.mPluginVersion.pluginVersion);
                sb.append("\n\u662f\u5426\u9884\u52a0\u8f7d\uff1a");
                sb.append(this.isCommonPreload ? "\u662f" : "\u5426");
                if (this.mPluginVersion.full) {
                    str = "";
                } else {
                    str = "\ncommon\u7248\u672c\uff1a" + this.mPluginVersion.pluginCommonVersion;
                }
                sb.append(str);
                floatingView.text(sb.toString());
            } else {
                FloatingView.get().text("\n\t\t\u672c\u5730\u8c03\u8bd5\t\t\n");
            }
            FloatingView.get().add();
            if (Constants.APPDEBUGKIT.equals(this.reactBundle)) {
                return;
            }
            FloatingView.get().listener(new MagnetViewListener() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.1
                {
                    AbstractJDReactInitialHelper.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.floatingview.MagnetViewListener
                public void onClick(FloatingMagnetView floatingMagnetView) {
                    Bundle bundle = new Bundle();
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.putOpt("debugBusinessId", AbstractJDReactInitialHelper.this.reactBundle);
                        bundle.putString("param", jSONObject.toString());
                    } catch (Exception unused) {
                    }
                    bundle.putString(JDReactConstant.IntentConstant.MODULE_NAME, Constants.APPDEBUGKIT);
                    bundle.putString("appname", Constants.APPDEBUGKIT);
                    ReactActivityUtilsHelperBase.startJDReactCommonActivity(AbstractJDReactInitialHelper.this.mActivity, bundle);
                }

                @Override // com.jingdong.common.jdreactFramework.floatingview.MagnetViewListener
                public void onRemove(FloatingMagnetView floatingMagnetView) {
                }
            });
        }
    }

    protected void addReactPackages(ReactInstanceManagerBuilder reactInstanceManagerBuilder) {
        List<ReactPackage> reactPackages = getReactPackages();
        if (reactPackages != null) {
            reactInstanceManagerBuilder.addPackages(reactPackages);
        }
    }

    protected abstract void defaultOnBackPressed();

    public ReactContext getCurrentReactContext() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            return reactInstanceManager.getCurrentReactContext();
        }
        return null;
    }

    protected abstract ReactPackage getDefaultReactPackage();

    protected ReactPackage getExtendReactPackage() {
        return null;
    }

    public ReactInstanceManager getReactManager() {
        return this.mReactInstanceManager;
    }

    protected List<ReactPackage> getReactPackages() {
        return null;
    }

    protected void handleError() {
        HANDLER.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.9
            {
                AbstractJDReactInitialHelper.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                int lastIndexOf;
                if (AbstractJDReactInitialHelper.this.mUIHandler != null) {
                    AbstractJDReactInitialHelper.this.mUIHandler.showProgressBar();
                }
                AbstractJDReactInitialHelper.this.clearReactManager();
                if (!TextUtils.isEmpty(AbstractJDReactInitialHelper.this.bundlePath) && AbstractJDReactInitialHelper.this.bundlePath.startsWith("/data") && (lastIndexOf = AbstractJDReactInitialHelper.this.bundlePath.lastIndexOf(47)) != -1) {
                    try {
                        FileUtil.emptyDir(new File(AbstractJDReactInitialHelper.this.bundlePath.substring(0, lastIndexOf)));
                    } catch (Exception unused) {
                        JLog.d(AbstractJDReactInitialHelper.TAG, "handleError emptyDir is fail");
                    }
                }
                AbstractJDReactInitialHelper.HANDLER.postDelayed(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.9.1
                    {
                        AnonymousClass9.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (AbstractJDReactInitialHelper.this.mReactInstanceProgressListener != null) {
                            AbstractJDReactInitialHelper.this.mReactInstanceProgressListener.onReactLoadCancel();
                        }
                    }
                }, 1500L);
            }
        });
    }

    public void handleInitError(int i2) {
        handleLoadException(i2);
        InitErrorCustomizer initErrorCustomizer = JDReactHelper.newInstance().getInitErrorCustomizer();
        if (initErrorCustomizer != null) {
            initErrorCustomizer.onJDReactInitError(i2, this.mActivity);
            return;
        }
        Activity activity = this.mActivity;
        if (activity != null) {
            activity.finish();
        }
    }

    public void handleLoadException(int i2) {
        LoadExceptionListener loadExceptionListener = this.mLoadExceptionListener;
        if (loadExceptionListener != null) {
            loadExceptionListener.onLoadException(i2, this.reactBundle, this.reactModule, this.mRootView);
        }
    }

    public void initReactManager(ReactRootView reactRootView, String str, String str2, Activity activity, String str3, String str4, Bundle bundle, boolean z, int i2, String str5) {
        ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener;
        ReactInstanceManagerBuilder addPackage;
        ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener2;
        ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener3;
        JLog.i(TAG, "initReactManager");
        if (TextUtils.isEmpty(str5)) {
            str5 = "0.0";
        }
        if (JDReactHelper.newInstance().isIndependent()) {
            SpeicalMtaUtil.sendSpecialMta("100", str3, str5);
        } else {
            JDReactHelper.newInstance().sendMtaData("JDReact_StartReactModule", str3 + CartConstant.KEY_YB_INFO_LINK + str5);
        }
        if (this.mPreload) {
            this.mReactInstanceManager = JDReactPreloadInstanceManager.newInstance().getInstance(str4);
        }
        if (this.mReactInstanceManager == null) {
            if (TextUtils.isEmpty(str)) {
                if (!JDReactHelper.newInstance().isDebug()) {
                    return;
                }
                this.mReactInstanceManager = initDebugReactManager(activity, str4);
            } else if (z && i2 == 3) {
                EngineHelper engineHelper = this.mEngineHelper;
                if (engineHelper == null) {
                    return;
                }
                engineHelper.load(str4);
                this.mReactInstanceManager = this.mEngineHelper.getEngine();
                this.reactModule = this.mEngineHelper.getSourceName();
                this.reactBundle = this.mEngineHelper.getSourceName();
                this.version = "1.0";
            } else {
                PluginVersion pluginVersion = this.mPluginVersion;
                if (pluginVersion == null) {
                    if (z) {
                        int lastIndexOf = str.lastIndexOf(47);
                        if (JDReactCommonPreloadManager.getInstance().canPreload(str3, getDefaultReactPackage(), getExtendReactPackage(), activity) && lastIndexOf != -1) {
                            Activity activity2 = this.mActivity;
                            if (activity2 != null && !activity2.isFinishing() && (reactInstanceProgressListener3 = this.mReactInstanceProgressListener) != null) {
                                reactInstanceProgressListener3.onReactLoadStart();
                            }
                            try {
                                try {
                                    this.mReactInstanceManager = JDReactCommonPreloadManager.getInstance().getCommonManager();
                                    if (JDReactJSLoadingModule.loadJSFromAssetsSynchronously(activity, (CatalystInstanceImpl) this.mReactInstanceManager.getCurrentReactContext().getCatalystInstance(), lastIndexOf != -1 ? str.substring(0, lastIndexOf) : "", str4 + ".jsbundle")) {
                                        Activity activity3 = this.mActivity;
                                        if (activity3 != null && !activity3.isFinishing() && (reactInstanceProgressListener2 = this.mReactInstanceProgressListener) != null) {
                                            reactInstanceProgressListener2.onReactLoadStart();
                                        }
                                        ((CatalystInstanceImpl) this.mReactInstanceManager.getCurrentReactContext().getCatalystInstance()).setSourceURL("assets://" + str);
                                        JDReactCommonPreloadManager.getInstance().setJDReactHelper(this);
                                    } else {
                                        initSeperateReactManager(activity);
                                    }
                                } finally {
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                initSeperateReactManager(activity);
                            }
                        } else {
                            initSeperateReactManager(activity);
                        }
                    } else {
                        ReactInstanceManagerBuilder addPackage2 = ReactInstanceManager.builder().setApplication(activity.getApplication()).setJSBundleFile(str).setJSMainModulePath("jsbundles/" + str4).setCurrentActivity(activity).addPackage(new MainReactPackage());
                        if (getDefaultReactPackage() != null) {
                            addPackage2.addPackage(getDefaultReactPackage());
                        } else {
                            ReactPackage reactPackage = mJDReactPackage;
                            if (reactPackage != null) {
                                addPackage2.addPackage(reactPackage);
                            }
                        }
                        addReactPackages(addPackage2);
                        addExtendReactPackage(addPackage2);
                        if (!JDReactHelper.newInstance().isDebug()) {
                            addPackage2.setNativeModuleCallExceptionHandler(new JDReactNativeModuleCallExceptionHandler(this));
                        }
                        if (JDReactHelper.newInstance().isDebug() && Constants.APPDEBUGKIT.equals(this.reactBundle)) {
                            this.mReactInstanceManager = addPackage2.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
                        } else {
                            this.mReactInstanceManager = addPackage2.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
                        }
                    }
                } else if (pluginVersion.full) {
                    if (pluginVersion.isPreset) {
                        addPackage = ReactInstanceManager.builder().setApplication(activity.getApplication()).setBundleAssetName(str).setJSMainModulePath("jsbundles/" + str4).setCurrentActivity(activity).addPackage(new MainReactPackage());
                    } else {
                        addPackage = ReactInstanceManager.builder().setApplication(activity.getApplication()).setJSBundleFile(str).setJSMainModulePath("jsbundles/" + str4).setCurrentActivity(activity).addPackage(new MainReactPackage());
                    }
                    if (getDefaultReactPackage() != null) {
                        addPackage.addPackage(getDefaultReactPackage());
                    } else {
                        ReactPackage reactPackage2 = mJDReactPackage;
                        if (reactPackage2 != null) {
                            addPackage.addPackage(reactPackage2);
                        }
                    }
                    addReactPackages(addPackage);
                    addExtendReactPackage(addPackage);
                    if (!JDReactHelper.newInstance().isDebug()) {
                        addPackage.setNativeModuleCallExceptionHandler(new JDReactNativeModuleCallExceptionHandler(this));
                    }
                    if (JDReactHelper.newInstance().isDebug() && Constants.APPDEBUGKIT.equals(this.reactBundle)) {
                        this.mReactInstanceManager = addPackage.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
                    } else {
                        this.mReactInstanceManager = addPackage.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
                    }
                } else if (JDReactCommonPreloadManager.getInstance().canPreload(str3, getDefaultReactPackage(), getExtendReactPackage())) {
                    Activity activity4 = this.mActivity;
                    if (activity4 != null && !activity4.isFinishing() && (reactInstanceProgressListener = this.mReactInstanceProgressListener) != null) {
                        reactInstanceProgressListener.onReactLoadStart();
                    }
                    try {
                        try {
                            ReactInstanceManager commonManager = JDReactCommonPreloadManager.getInstance().getCommonManager();
                            this.mReactInstanceManager = commonManager;
                            ReactContext currentReactContext = commonManager.getCurrentReactContext();
                            if (currentReactContext == null) {
                                JLog.e(TAG, "reactContext == null");
                                initSeperateReactManager(activity, str2);
                            } else {
                                CatalystInstanceImpl catalystInstanceImpl = (CatalystInstanceImpl) currentReactContext.getCatalystInstance();
                                JSBundleLoader jSBundleLoader = catalystInstanceImpl.getJSBundleLoader();
                                jSBundleLoader.jsCommonBundle = JDReactCommonPreloadManager.getInstance().getCommonFilePath();
                                if (this.mPluginVersion.isPreset) {
                                    str = "assets://" + str;
                                    jSBundleLoader.jsBundle = str;
                                    catalystInstanceImpl.loadJSFromAssetsSynchronously(activity.getAssets(), str);
                                } else {
                                    jSBundleLoader.jsBundle = str;
                                    catalystInstanceImpl.loadJSFromFileSynchronously(str, str, true);
                                }
                                catalystInstanceImpl.setSourceURL(str);
                                this.mReactInstanceManager.setJSMainModulePath("jsbundles/" + str4);
                                if (!JDReactHelper.newInstance().isDebug()) {
                                    JDReactCommonPreloadManager.getInstance().setJDReactHelper(this);
                                }
                                this.isCommonPreload = true;
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            initSeperateReactManager(activity, str2);
                        }
                    } finally {
                    }
                } else {
                    initSeperateReactManager(activity, str2);
                }
            }
        }
        this.isLoadError = false;
        this.checkCount = 0;
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.addReactInstanceProgressListener(new ReactInstanceManager.ReactInstanceProgressListener() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.7
                {
                    AbstractJDReactInitialHelper.this = this;
                }

                @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
                public void onReactLoadCancel() {
                    Activity activity5 = AbstractJDReactInitialHelper.this.mActivity;
                    if (activity5 == null || activity5.isFinishing() || AbstractJDReactInitialHelper.this.mReactInstanceProgressListener == null) {
                        return;
                    }
                    AbstractJDReactInitialHelper.this.mReactInstanceProgressListener.onReactLoadCancel();
                }

                @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
                public void onReactLoadFinish() {
                    Activity activity5 = AbstractJDReactInitialHelper.this.mActivity;
                    if (activity5 == null || activity5.isFinishing() || AbstractJDReactInitialHelper.this.mReactInstanceProgressListener == null) {
                        return;
                    }
                    if (!AbstractJDReactInitialHelper.this.isCheckError || !AbstractJDReactInitialHelper.this.isLoadError) {
                        AbstractJDReactInitialHelper.this.mReactInstanceProgressListener.onReactLoadFinish();
                        if (AbstractJDReactInitialHelper.this.isCheckError) {
                            AbstractJDReactInitialHelper.this.postCheckError();
                            return;
                        }
                        return;
                    }
                    AbstractJDReactInitialHelper.this.handleError();
                }

                @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
                public void onReactLoadStart() {
                    Activity activity5 = AbstractJDReactInitialHelper.this.mActivity;
                    if (activity5 == null || activity5.isFinishing() || AbstractJDReactInitialHelper.this.mReactInstanceProgressListener == null) {
                        return;
                    }
                    AbstractJDReactInitialHelper.this.mReactInstanceProgressListener.onReactLoadStart();
                }
            });
        }
        JLog.d(TAG, " startReactApplication -->> " + System.currentTimeMillis());
        try {
            if (bundle != null) {
                reactRootView.startReactApplication(this.mReactInstanceManager, str3, bundle);
            } else {
                reactRootView.startReactApplication(this.mReactInstanceManager, str3, null);
            }
        } catch (Throwable th) {
            JLog.i(TAG, th);
        }
    }

    public ReactRootView initRootView(boolean z) {
        ReactRootView reactRootView;
        JDReactPreloadManager jDReactPreloadManager;
        if (TextUtils.isEmpty(this.reactBundle)) {
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.e(TAG, "module name can't be null");
            }
            return null;
        }
        String str = this.bundlePath;
        if (str != null && !str.startsWith(JDReactConstant.ASSERT_DIR) && !this.force && !new File(this.bundlePath).exists()) {
            ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener = this.mReactInstanceProgressListener;
            if (reactInstanceProgressListener != null) {
                reactInstanceProgressListener.onReactLoadCancel();
            }
            TrackUtil.trackLoadFail(1, this.reactModule);
            return null;
        }
        AbstractInitialHelper.UIHandler uIHandler = this.mUIHandler;
        if (uIHandler != null) {
            uIHandler.clearFresco();
        }
        this.jdReactPreloadManager = JDReactPreloadManager.getPreloadManagerByModule(this.reactBundle);
        if (!TextUtils.isEmpty(this.bundlePath) && (jDReactPreloadManager = this.jdReactPreloadManager) != null && this.bundlePath.equals(jDReactPreloadManager.getBundlePath())) {
            reactRootView = this.jdReactPreloadManager.getReactRootView();
        } else {
            final ReactRootView reactRootView2 = new ReactRootView(this.mActivity);
            if (!this.mISRootView) {
                reactRootView2.setWindowChangeListener(new ReactRootView.WindowChangeListener() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.2
                    {
                        AbstractJDReactInitialHelper.this = this;
                    }

                    @Override // com.facebook.react.ReactRootView.WindowChangeListener
                    public void onWindowChange(int i2, int i3) {
                        if (reactRootView2 != null) {
                            AbstractJDReactInitialHelper.this.onDestroy();
                            AbstractJDReactInitialHelper.this.setupLayout(true);
                            reactRootView2.setWindowChangeListener(null);
                        }
                    }
                });
            }
            initReactManager(reactRootView2, this.bundlePath, this.commonPath, this.mActivity, this.reactBundle, this.reactModule, this.reactParams, z, this.type, this.version);
            reactRootView = reactRootView2;
        }
        initRootView(reactRootView);
        this.mRootView = reactRootView;
        if (this.mReactInstanceManager == null) {
            this.mRootView = null;
            return null;
        }
        reactRootView.setEventListener(new ReactRootView.ReactRootViewEventListener() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.3
            {
                AbstractJDReactInitialHelper.this = this;
            }

            @Override // com.facebook.react.ReactRootView.ReactRootViewEventListener
            public void onAttachedToReactInstance(ReactRootView reactRootView3) {
                RenderDataReport.getInstance().attached(AbstractJDReactInitialHelper.this.mReportDataHashCode);
                JLog.e(AbstractJDReactInitialHelper.TAG, "onReactLoadFinish time = " + System.currentTimeMillis());
            }
        });
        this.mRootView.setMtaCallback(new ReactRootView.MtaCallback() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.4
            {
                AbstractJDReactInitialHelper.this = this;
            }

            @Override // com.facebook.react.ReactRootView.MtaCallback
            public void setExposureMta(ReadableMap readableMap, String str2) {
                JDReactHelper.newInstance().setExposureMta(readableMap.toHashMap(), str2);
            }
        });
        if (JDReactHelper.newInstance().getUIModeHelper() != null) {
            this.uiModeUnregister = JDReactHelper.newInstance().getUIModeHelper().onRegisterUIModeChangeListener(new UIModeHelper.UIModeChangeListener() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.5
                {
                    AbstractJDReactInitialHelper.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.helper.UIModeHelper.UIModeChangeListener
                public void onChange(int i2, String str2) {
                    ReactContext currentReactContext = AbstractJDReactInitialHelper.this.getCurrentReactContext();
                    if (currentReactContext == null || currentReactContext.getNativeModule(JDReactAppearanceModule.class) == null) {
                        return;
                    }
                    ((JDReactAppearanceModule) currentReactContext.getNativeModule(JDReactAppearanceModule.class)).sendEvent(i2, str2);
                }
            });
        }
        if (JDReactHelper.newInstance().getElderModeHelper() != null) {
            this.elderModeUnregister = JDReactHelper.newInstance().getElderModeHelper().onRegisterElderModeChangeListener(new ElderModeHelper.ElderModeChangeListener() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.6
                {
                    AbstractJDReactInitialHelper.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.helper.ElderModeHelper.ElderModeChangeListener
                public void onChange(int i2) {
                    ReactContext currentReactContext = AbstractJDReactInitialHelper.this.getCurrentReactContext();
                    if (currentReactContext == null || currentReactContext.getNativeModule(JDReactElderModeModule.class) == null) {
                        return;
                    }
                    ((JDReactElderModeModule) currentReactContext.getNativeModule(JDReactElderModeModule.class)).sendEvent(i2);
                }
            });
        }
        return reactRootView;
    }

    protected abstract void initRootView(ReactRootView reactRootView);

    @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
    public void invokeDefaultOnBackPressed() {
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        defaultOnBackPressed();
    }

    public boolean loadSepFile() {
        return false;
    }

    public void onBackPressed() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onBackPressed();
            return;
        }
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        this.mActivity.onBackPressed();
    }

    public void onDestroy() {
        AbstractInitialHelper.UIHandler uIHandler = this.mUIHandler;
        if (uIHandler != null) {
            uIHandler.clearFresco();
        }
        UIModeHelper.Unregister unregister = this.uiModeUnregister;
        if (unregister != null) {
            unregister.unregister();
        }
        ElderModeHelper.Unregister unregister2 = this.elderModeUnregister;
        if (unregister2 != null) {
            unregister2.unregister();
        }
        clearReactManager();
        HANDLER.removeCallbacksAndMessages(null);
    }

    public boolean onMenuKeyUp() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.showDevOptionsDialog();
        }
        JDReactPreloadManager jDReactPreloadManager = this.jdReactPreloadManager;
        if (jDReactPreloadManager == null || jDReactPreloadManager.getReactManager() == null) {
            return true;
        }
        this.jdReactPreloadManager.getReactManager().showDevOptionsDialog();
        return true;
    }

    public void onPause() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause();
        }
        JDReactPreloadManager jDReactPreloadManager = this.jdReactPreloadManager;
        if (jDReactPreloadManager != null && jDReactPreloadManager.getReactManager() != null) {
            this.jdReactPreloadManager.getReactManager().onHostPause();
        }
        ReactRootView reactRootView = this.mRootView;
        if (reactRootView != null) {
            reactRootView.setExposureMta();
        }
        LifecycleReporter.dispatchPause(this.reactModule);
    }

    public void onResume() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(this.mActivity, this);
        }
        JDReactPreloadManager jDReactPreloadManager = this.jdReactPreloadManager;
        if (jDReactPreloadManager != null && jDReactPreloadManager.getReactManager() != null) {
            this.jdReactPreloadManager.getReactManager().onHostResume(this.mActivity, this);
        }
        LifecycleReporter.dispatchResume(this.reactModule);
        showFloatView();
    }

    public void onStart() {
        if (JDReactHelper.newInstance().isDebug()) {
            FloatingView.get().attach(this.mActivity);
        }
    }

    public void onStop() {
        if (JDReactHelper.newInstance().isDebug()) {
            FloatingView.get().detach(this.mActivity);
        }
    }

    protected void postCheckError() {
        HANDLER.postDelayed(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.8
            {
                AbstractJDReactInitialHelper.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                String str = "currentError is " + AbstractJDReactInitialHelper.this.isLoadError;
                if (AbstractJDReactInitialHelper.this.isLoadError) {
                    AbstractJDReactInitialHelper.this.handleError();
                } else if (AbstractJDReactInitialHelper.access$304(AbstractJDReactInitialHelper.this) < 3) {
                    AbstractJDReactInitialHelper.this.postCheckError();
                }
            }
        }, 500L);
    }

    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        if (this.mReactInstanceManager == null) {
            return;
        }
        if ("screenSizeChanged".equals(str)) {
            if (writableMap.getBoolean("force")) {
                if (this.mRootView != null) {
                    onDestroy();
                    setupLayout(true);
                    ViewGroup viewGroup = (ViewGroup) this.mRootView.getParent();
                    viewGroup.removeView(this.mRootView);
                    viewGroup.addView(this.mRootView, new FrameLayout.LayoutParams(-1, -1));
                    return;
                }
            } else {
                ReactRootView reactRootView = this.mRootView;
                if (reactRootView != null) {
                    reactRootView.updateScreenSize();
                }
            }
        }
        ReactContext currentReactContext = this.mReactInstanceManager.getCurrentReactContext();
        if (currentReactContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    public void setIsRootView(boolean z) {
        this.mISRootView = z;
    }

    public void setLoadExceptionListener(LoadExceptionListener loadExceptionListener) {
        this.mLoadExceptionListener = loadExceptionListener;
    }

    public void setScanEnter(boolean z) {
        this.isScanEnter = z;
    }

    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper
    public void setUIHandler(AbstractInitialHelper.UIHandler uIHandler) {
        super.setUIHandler(uIHandler);
        this.mUIHandler = uIHandler;
    }

    public void setupLayout(PluginVersion pluginVersion) {
        this.mPluginVersion = pluginVersion;
        setupLayout(false);
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, Bundle bundle, String str3) {
        this(activity, str, str2, str, bundle, str3, null, false, 1, "0.0", false, false, false);
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, Bundle bundle, String str3, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener) {
        this(activity, str, str2, str, bundle, str3, reactInstanceProgressListener, false, 1, "0.0", false, false, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:229:0x0188, code lost:
        if (r1 == 3) goto L232;
     */
    @Override // com.jingdong.common.jdreactFramework.utils.AbstractInitialHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setupLayout(boolean z) {
        String str;
        RenderDataReport.getInstance().renderStart(this.mReportDataHashCode);
        this.isCheckError = TextUtils.equals(this.reactModule, "JDReactDaoJia");
        if (z) {
            this.mPluginVersion = ReactNativeFileManager.getPluginDir(this.mActivity, this.reactModule);
        }
        PluginVersion pluginVersion = this.mPluginVersion;
        if (pluginVersion != null && pluginVersion.pluginDir != null) {
            this.bundlePath = this.mPluginVersion.pluginDir + File.separator + this.reactModule + ".jsbundle";
            if (!TextUtils.isEmpty(this.mPluginVersion.pluginVersion)) {
                this.version = this.mPluginVersion.pluginVersion;
            } else {
                this.version = "0.0";
            }
            if (!TextUtils.isEmpty(this.mPluginVersion.pluginCommonVersion)) {
                this.pluginCommonVersion = this.mPluginVersion.pluginCommonVersion;
            } else {
                this.pluginCommonVersion = "0.0";
            }
        }
        boolean z2 = true;
        if (TextUtils.isEmpty(this.reactModule)) {
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.e(TAG, "module name can't be null");
            }
            handleInitError(1);
            TrackUtil.trackLoadFail(9, this.reactModule);
            RenderDataReport.getInstance().renderEnd(this.mReportDataHashCode, false, "9", TrackConstant.getErrorMsg(9), JDReactCommonPreloadManager.getInstance().isPreloadCommon());
        } else if (this.bundlePath != null && this.mPluginVersion != null) {
            String unzipStatus = ReactSharedPreferenceUtils.getUnzipStatus(this.reactModule + "---" + this.version);
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(TAG, "pluginUnZipStatus--->" + unzipStatus);
            }
            if (!JDReactConstant.UNZIP_IN_PROGRESS.equals(unzipStatus)) {
                long j2 = 0;
                try {
                    if (this.bundlePath.startsWith("/data")) {
                        j2 = FileUtil.getFileSize(this.bundlePath);
                    } else {
                        try {
                            j2 = this.mActivity.getResources().getAssets().open(this.bundlePath).available();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    RenderDataReport renderDataReport = RenderDataReport.getInstance();
                    String str2 = this.mReportDataHashCode;
                    String str3 = this.mDownloadName;
                    String str4 = this.version;
                    PluginVersion pluginVersion2 = this.mPluginVersion;
                    String str5 = "1";
                    String str6 = pluginVersion2.full ? "1" : "2";
                    if (!pluginVersion2.isPreset) {
                        str5 = "2";
                    }
                    renderDataReport.initData(str2, str3, str4, str6, str5, DataReportUtils.getAppVersion(this.mActivity), DataReportUtils.getNetworkStateStr(this.mActivity), j2 + "");
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                boolean z3 = this.splitbundle;
                int i2 = this.type;
                if (i2 == 0) {
                    this.type = 1;
                    if (this.bundlePath.startsWith("/data") && (str = this.commonPath) != null && str.startsWith(JDReactConstant.ASSERT_DIR)) {
                        this.type = 3;
                    }
                    z2 = z3;
                }
                if (initRootView(z2) == null) {
                    handleInitError(3);
                    TrackUtil.trackLoadFail(11, this.reactModule);
                    return;
                }
                showFloatView();
                return;
            }
            ReactSharedPreferenceUtils.putUnzipStatus(this.reactModule + "---" + this.version, "");
            String str7 = this.bundlePath;
            FileUtil.emptyDir(new File(str7.substring(0, str7.lastIndexOf("/"))));
            String str8 = this.reactModule;
            PluginVersion pluginVersion3 = this.mPluginVersion;
            ReactSharedPreferenceUtils.putDownLoadingStatus(str8, pluginVersion3.full, pluginVersion3.pluginCommonVersion, JDReactConstant.FAILED);
            RenderDataReport.getInstance().renderEnd(this.mReportDataHashCode, false, "16", this.bundlePath + "-->unzip file interrupted", JDReactCommonPreloadManager.getInstance().isPreloadCommon());
            startToInit1();
        } else {
            handleInitError(2);
            TrackUtil.trackLoadFail(21, this.reactModule);
            RenderDataReport.getInstance().renderEnd(this.mReportDataHashCode, false, "21", TrackConstant.getErrorMsg(21), JDReactCommonPreloadManager.getInstance().isPreloadCommon());
        }
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, String str3, Bundle bundle, String str4, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener) {
        this(activity, str, str2, str, bundle, str4, reactInstanceProgressListener, false, 1, "0.0", false, false, false);
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, Bundle bundle, String str3, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener, boolean z) {
        this(activity, str, null, str2, bundle, str3, reactInstanceProgressListener, false, 1, "0.0", false, false, false);
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, Bundle bundle, String str3, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener, boolean z, int i2) {
        this(activity, str, null, str2, bundle, str3, reactInstanceProgressListener, false, i2, "0.0", false, false, false);
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, Bundle bundle, String str3, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener, boolean z, int i2, boolean z2) {
        this(activity, str, null, str2, bundle, str3, reactInstanceProgressListener, false, i2, "0.0", false, false, z2);
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, String str3, Bundle bundle, String str4, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener, boolean z, int i2, String str5, boolean z2, boolean z3) {
        this(activity, str, null, str3, bundle, str4, reactInstanceProgressListener, false, i2, "0.0", false, false, false);
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, String str3, Bundle bundle, String str4, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener, boolean z, int i2, String str5, boolean z2, boolean z3, boolean z4) {
        this.mReactInstanceManager = null;
        this.jdReactPreloadManager = null;
        this.mRootView = null;
        this.mISRootView = false;
        this.isCommonPreload = false;
        this.checkCount = 0;
        this.reactParams = bundle;
        this.reactBundle = str;
        this.commonPath = str2;
        this.bundlePath = str4;
        this.reactModule = str3;
        this.mReactInstanceProgressListener = reactInstanceProgressListener;
        this.splitbundle = z;
        this.type = i2;
        this.mActivity = activity;
        this.version = str5;
        this.failed = z2;
        this.force = z3;
        this.mPreload = z4;
        this.mReportDataHashCode = hashCode() + "";
    }

    private void initSeperateReactManager(Activity activity, String str) {
        String str2 = this.bundlePath;
        if (TextUtils.isEmpty(str)) {
            str = this.mPluginVersion.pluginCommonDir;
        }
        if (this.mPluginVersion.isPreset) {
            str2 = "assets://" + this.bundlePath;
        }
        if (str == null) {
            ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener = this.mReactInstanceProgressListener;
            if (reactInstanceProgressListener != null) {
                reactInstanceProgressListener.onReactLoadCancel();
                return;
            }
            return;
        }
        if (str.startsWith(JDReactConstant.ASSERT_DIR)) {
            str = "assets://" + str;
        }
        ReactInstanceManagerBuilder addPackage = ReactInstanceManager.builder().setApplication(activity.getApplication()).setSeperateBundleFileName(str2, str).setJSMainModulePath("jsbundles/" + this.reactModule).setCurrentActivity(activity).addPackage(new MainReactPackage());
        if (getDefaultReactPackage() != null) {
            addPackage.addPackage(getDefaultReactPackage());
        } else {
            ReactPackage reactPackage = mJDReactPackage;
            if (reactPackage != null) {
                addPackage.addPackage(reactPackage);
            }
        }
        addReactPackages(addPackage);
        addExtendReactPackage(addPackage);
        if (!JDReactHelper.newInstance().isDebug()) {
            addPackage.setNativeModuleCallExceptionHandler(new JDReactNativeModuleCallExceptionHandler(this));
        }
        if (JDReactHelper.newInstance().isDebug() && Constants.APPDEBUGKIT.equals(this.reactBundle)) {
            this.mReactInstanceManager = addPackage.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
        } else {
            this.mReactInstanceManager = addPackage.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
        }
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, String str3, Bundle bundle, String str4, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener, boolean z, int i2, String str5, String str6, boolean z2, boolean z3, boolean z4, String str7) {
        this(activity, str, str2, str3, bundle, str4, reactInstanceProgressListener, z, i2, str5, str6, z2, z3, z4, str7, null);
    }

    public AbstractJDReactInitialHelper(Activity activity, String str, String str2, String str3, Bundle bundle, String str4, ReactInstanceManager.ReactInstanceProgressListener reactInstanceProgressListener, boolean z, int i2, String str5, String str6, boolean z2, boolean z3, boolean z4, String str7, EngineHelper engineHelper) {
        this.mReactInstanceManager = null;
        this.jdReactPreloadManager = null;
        this.mRootView = null;
        this.mISRootView = false;
        this.isCommonPreload = false;
        this.checkCount = 0;
        this.reactParams = bundle;
        this.reactBundle = str;
        this.commonPath = str2;
        this.bundlePath = str4;
        this.reactModule = str3;
        this.mReactInstanceProgressListener = reactInstanceProgressListener;
        this.splitbundle = z;
        this.type = i2;
        this.mActivity = activity;
        this.version = str5;
        this.commitId = str6;
        this.failed = z2;
        this.force = z3;
        this.mPreload = z4;
        this.mReportDataHashCode = str7;
        this.mEngineHelper = engineHelper;
    }
}
