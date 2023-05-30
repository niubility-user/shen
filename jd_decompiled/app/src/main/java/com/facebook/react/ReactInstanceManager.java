package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NativeDeltaClient;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ProxyJavaScriptExecutor;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.DevSupportManagerImpl;
import com.facebook.react.devsupport.ReactInstanceManagerDevHelper;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.modules.fabric.ReactFabric;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@ThreadSafe
/* loaded from: classes.dex */
public class ReactInstanceManager {
    private static final String TAG = "ReactInstanceManager";
    private static ReactEmbeddedListener mReactEmbeddedListener;
    private final Context mApplicationContext;
    private final Set<ReactRootView> mAttachedRootViews;
    @Nullable
    private final NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    @Nullable
    private final JSBundleLoader mBundleLoader;
    @Nullable
    private volatile Thread mCreateReactContextThread;
    @Nullable
    private Activity mCurrentActivity;
    @Nullable
    private volatile ReactContext mCurrentReactContext;
    @ThreadConfined(ThreadConfined.UI)
    @Nullable
    private DefaultHardwareBackBtnHandler mDefaultBackButtonImpl;
    private final DevSupportManager mDevSupportManager;
    private boolean mDisableDestoryWhenFatalException;
    private volatile boolean mHasStartedCreatingInitialContext;
    private volatile Boolean mHasStartedDestroying;
    private volatile Object mHasStartedDestroyingLock;
    @Nullable
    private final JSIModulePackage mJSIModulePackage;
    @Nullable
    private String mJSMainModulePath;
    private final JavaScriptExecutorFactory mJavaScriptExecutorFactory;
    private volatile LifecycleState mLifecycleState;
    private final MemoryPressureRouter mMemoryPressureRouter;
    @Nullable
    private final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private final List<ReactPackage> mPackages;
    @ThreadConfined(ThreadConfined.UI)
    @Nullable
    private ReactContextInitParams mPendingReactContextInitParams;
    private final Object mReactContextLock;
    private final Collection<ReactInstanceEventListener> mReactInstanceEventListeners;
    private ReactInstanceProgressListener mReactInstanceProgressListener;
    private final boolean mUseDeveloperSupport;
    private List<ViewManager> mViewManagers;

    /* loaded from: classes.dex */
    public class ReactContextInitParams {
        private final JSBundleLoader mJsBundleLoader;
        private final JavaScriptExecutorFactory mJsExecutorFactory;

        public ReactContextInitParams(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
            ReactInstanceManager.this = r1;
            this.mJsExecutorFactory = (JavaScriptExecutorFactory) Assertions.assertNotNull(javaScriptExecutorFactory);
            this.mJsBundleLoader = (JSBundleLoader) Assertions.assertNotNull(jSBundleLoader);
        }

        public JSBundleLoader getJsBundleLoader() {
            return this.mJsBundleLoader;
        }

        public JavaScriptExecutorFactory getJsExecutorFactory() {
            return this.mJsExecutorFactory;
        }
    }

    /* loaded from: classes.dex */
    public interface ReactEmbeddedListener {
        void onLoadFinish(String str);

        void onLoadStart(String str, String str2);
    }

    /* loaded from: classes.dex */
    public interface ReactInstanceEventListener {
        void onReactContextInitialized(ReactContext reactContext);
    }

    /* loaded from: classes.dex */
    public interface ReactInstanceProgressListener {
        void onReactLoadCancel();

        void onReactLoadFinish();

        void onReactLoadStart();
    }

    public ReactInstanceManager(Context context, @Nullable Activity activity, @Nullable DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, JavaScriptExecutorFactory javaScriptExecutorFactory, @Nullable JSBundleLoader jSBundleLoader, @Nullable String str, List<ReactPackage> list, boolean z, @Nullable NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener, LifecycleState lifecycleState, @Nullable UIImplementationProvider uIImplementationProvider, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, @Nullable RedBoxHandler redBoxHandler, boolean z2, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i2, int i3, @Nullable JSIModulePackage jSIModulePackage, @Nullable Map<String, RequestHandler> map, boolean z3) {
        this.mAttachedRootViews = Collections.synchronizedSet(new HashSet());
        this.mReactContextLock = new Object();
        this.mReactInstanceEventListeners = Collections.synchronizedSet(new HashSet());
        this.mHasStartedCreatingInitialContext = false;
        this.mHasStartedDestroying = Boolean.FALSE;
        this.mHasStartedDestroyingLock = new Object();
        this.mDisableDestoryWhenFatalException = false;
        initializeSoLoaderIfNecessary(context);
        if (activity != null) {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitializedForActivity(activity);
        } else {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(context);
        }
        this.mDisableDestoryWhenFatalException = z3;
        this.mApplicationContext = context;
        this.mCurrentActivity = activity;
        this.mDefaultBackButtonImpl = defaultHardwareBackBtnHandler;
        this.mJavaScriptExecutorFactory = javaScriptExecutorFactory;
        this.mBundleLoader = jSBundleLoader;
        this.mJSMainModulePath = str;
        ArrayList arrayList = new ArrayList();
        this.mPackages = arrayList;
        this.mUseDeveloperSupport = z;
        Systrace.beginSection(0L, "ReactInstanceManager.initDevSupportManager");
        DevSupportManager create = DevSupportManagerFactory.create(context, createDevHelperInterface(), this.mJSMainModulePath, z, redBoxHandler, devBundleDownloadListener, i2, map);
        this.mDevSupportManager = create;
        Systrace.endSection(0L);
        this.mBridgeIdleDebugListener = notThreadSafeBridgeIdleDebugListener;
        this.mLifecycleState = lifecycleState;
        this.mMemoryPressureRouter = new MemoryPressureRouter(context);
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
        synchronized (arrayList) {
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: Use Split Packages");
            arrayList.add(new CoreModulesPackage(this, new DefaultHardwareBackBtnHandler() { // from class: com.facebook.react.ReactInstanceManager.1
                {
                    ReactInstanceManager.this = this;
                }

                @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
                public void invokeDefaultOnBackPressed() {
                    ReactInstanceManager.this.invokeDefaultOnBackPressed();
                }
            }, uIImplementationProvider, z2, i3));
            if (z) {
                arrayList.add(new DebugCorePackage());
            }
            arrayList.addAll(list);
        }
        this.mJSIModulePackage = jSIModulePackage;
        ReactChoreographer.initialize();
        if (z) {
            create.startInspector();
        }
    }

    public static void addReactEmbeddedListener(ReactEmbeddedListener reactEmbeddedListener) {
        mReactEmbeddedListener = reactEmbeddedListener;
    }

    private void attachRootViewToInstance(final ReactRootView reactRootView) {
        Systrace.beginSection(0L, "attachRootViewToInstance");
        UIManager uIManager = UIManagerHelper.getUIManager(this.mCurrentReactContext, reactRootView.getUIManagerType());
        Bundle appProperties = reactRootView.getAppProperties();
        final int addRootView = uIManager.addRootView(reactRootView, appProperties == null ? new WritableNativeMap() : Arguments.fromBundle(appProperties), reactRootView.getInitialUITemplate());
        reactRootView.setRootViewTag(addRootView);
        reactRootView.runApplication();
        Systrace.beginAsyncSection(0L, "pre_rootView.onAttachedToReactInstance", addRootView);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.9
            {
                ReactInstanceManager.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                Systrace.endAsyncSection(0L, "pre_rootView.onAttachedToReactInstance", addRootView);
                reactRootView.onAttachedToReactInstance();
            }
        });
        Systrace.endSection(0L);
    }

    public static ReactInstanceManagerBuilder builder() {
        return new ReactInstanceManagerBuilder();
    }

    private ReactInstanceManagerDevHelper createDevHelperInterface() {
        return new ReactInstanceManagerDevHelper() { // from class: com.facebook.react.ReactInstanceManager.2
            {
                ReactInstanceManager.this = this;
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            @Nullable
            public Activity getCurrentActivity() {
                return ReactInstanceManager.this.mCurrentActivity;
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            public void onJSBundleLoadedFromServer(@Nullable NativeDeltaClient nativeDeltaClient) {
                ReactInstanceManager.this.onJSBundleLoadedFromServer(nativeDeltaClient);
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
                ReactInstanceManager.this.onReloadWithJSDebugger(factory);
            }

            @Override // com.facebook.react.devsupport.ReactInstanceManagerDevHelper
            public void toggleElementInspector() {
                ReactInstanceManager.this.toggleElementInspector();
            }
        };
    }

    public ReactApplicationContext createReactContext(JavaScriptExecutor javaScriptExecutor, JSBundleLoader jSBundleLoader) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_REACT_CONTEXT_START, javaScriptExecutor.getName());
        ReactApplicationContext reactApplicationContext = new ReactApplicationContext(this.mApplicationContext);
        NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler = this.mNativeModuleCallExceptionHandler;
        if (nativeModuleCallExceptionHandler == null) {
            nativeModuleCallExceptionHandler = this.mDevSupportManager;
        }
        reactApplicationContext.setNativeModuleCallExceptionHandler(nativeModuleCallExceptionHandler);
        reactApplicationContext.setCurrentActivity(this.mCurrentActivity);
        CatalystInstanceImpl.Builder disableDestoryWhenFatalException = new CatalystInstanceImpl.Builder().setReactQueueConfigurationSpec(ReactQueueConfigurationSpec.createDefault()).setJSExecutor(javaScriptExecutor).setRegistry(processPackages(reactApplicationContext, this.mPackages, false)).setJSBundleLoader(jSBundleLoader).setNativeModuleCallExceptionHandler(nativeModuleCallExceptionHandler).setDisableDestoryWhenFatalException(this.mDisableDestoryWhenFatalException);
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_START);
        Systrace.beginSection(0L, "createCatalystInstance");
        try {
            CatalystInstanceImpl build = disableDestoryWhenFatalException.build();
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            JSIModulePackage jSIModulePackage = this.mJSIModulePackage;
            if (jSIModulePackage != null) {
                build.addJSIModules(jSIModulePackage.getJSIModules(reactApplicationContext, build.getJavaScriptContextHolder()));
            }
            NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = this.mBridgeIdleDebugListener;
            if (notThreadSafeBridgeIdleDebugListener != null) {
                build.addBridgeIdleDebugListener(notThreadSafeBridgeIdleDebugListener);
            }
            if (Systrace.isTracing(0L)) {
                build.setGlobalVariable("__RCTProfileIsProfiling", DYConstants.DY_TRUE);
            }
            ReactMarker.logMarker(ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START);
            Systrace.beginSection(0L, "runJSBundle");
            build.runJSBundle();
            Systrace.endSection(0L);
            reactApplicationContext.initializeWithInstance(build);
            return reactApplicationContext;
        } catch (Throwable th) {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            throw th;
        }
    }

    private void detachViewFromInstance(ReactRootView reactRootView, CatalystInstance catalystInstance) {
        UiThreadUtil.assertOnUiThread();
        if (reactRootView.getUIManagerType() == 2) {
            ((ReactFabric) catalystInstance.getJSModule(ReactFabric.class)).unmountComponentAtNode(reactRootView.getId());
        } else {
            ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).unmountApplicationComponentAtRootTag(reactRootView.getId());
        }
    }

    private static void initializeSoLoaderIfNecessary(Context context) {
        SoLoader.init(context, false);
    }

    public void invokeDefaultOnBackPressed() {
        UiThreadUtil.assertOnUiThread();
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultBackButtonImpl;
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    private synchronized void moveReactContextToCurrentLifecycleState() {
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            moveToResumedLifecycleState(true);
        }
    }

    private synchronized void moveToBeforeCreateLifecycleState() {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            if (this.mLifecycleState == LifecycleState.RESUMED) {
                currentReactContext.onHostPause();
                this.mLifecycleState = LifecycleState.BEFORE_RESUME;
            }
            if (this.mLifecycleState == LifecycleState.BEFORE_RESUME) {
                currentReactContext.onHostDestroy();
            }
        }
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
    }

    private synchronized void moveToBeforeResumeLifecycleState() {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            if (this.mLifecycleState == LifecycleState.BEFORE_CREATE) {
                currentReactContext.onHostResume(this.mCurrentActivity);
                currentReactContext.onHostPause();
            } else if (this.mLifecycleState == LifecycleState.RESUMED) {
                currentReactContext.onHostPause();
            }
        }
        this.mLifecycleState = LifecycleState.BEFORE_RESUME;
    }

    private synchronized void moveToResumedLifecycleState(boolean z) {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null && (z || this.mLifecycleState == LifecycleState.BEFORE_RESUME || this.mLifecycleState == LifecycleState.BEFORE_CREATE)) {
            currentReactContext.onHostResume(this.mCurrentActivity);
        }
        this.mLifecycleState = LifecycleState.RESUMED;
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onJSBundleLoadedFromServer(@Nullable NativeDeltaClient nativeDeltaClient) {
        JSBundleLoader createDeltaFromNetworkLoader;
        if (nativeDeltaClient == null) {
            createDeltaFromNetworkLoader = JSBundleLoader.createCachedBundleFromNetworkLoader(this.mDevSupportManager.getSourceUrl(), this.mDevSupportManager.getDownloadedJSBundleFile());
        } else {
            createDeltaFromNetworkLoader = JSBundleLoader.createDeltaFromNetworkLoader(this.mDevSupportManager.getSourceUrl(), nativeDeltaClient);
        }
        recreateReactContextInBackground(this.mJavaScriptExecutorFactory, createDeltaFromNetworkLoader);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
        recreateReactContextInBackground(new ProxyJavaScriptExecutor.Factory(factory), JSBundleLoader.createRemoteDebuggerBundleLoader(this.mDevSupportManager.getJSBundleURLForRemoteDebugging(), this.mDevSupportManager.getSourceUrl()));
    }

    private void processPackage(ReactPackage reactPackage, NativeModuleRegistryBuilder nativeModuleRegistryBuilder) {
        SystraceMessage.beginSection(0L, "processPackage").arg("className", reactPackage.getClass().getSimpleName()).flush();
        boolean z = reactPackage instanceof ReactPackageLogger;
        if (z) {
            ((ReactPackageLogger) reactPackage).startProcessPackage();
        }
        nativeModuleRegistryBuilder.processPackage(reactPackage);
        if (z) {
            ((ReactPackageLogger) reactPackage).endProcessPackage();
        }
        SystraceMessage.endSection(0L).flush();
    }

    private NativeModuleRegistry processPackages(ReactApplicationContext reactApplicationContext, List<ReactPackage> list, boolean z) {
        NativeModuleRegistryBuilder nativeModuleRegistryBuilder = new NativeModuleRegistryBuilder(reactApplicationContext, this);
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_START);
        synchronized (this.mPackages) {
            Iterator<ReactPackage> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    ReactPackage next = it.next();
                    if (!z || !this.mPackages.contains(next)) {
                        Systrace.beginSection(0L, "createAndProcessCustomReactPackage");
                        if (z) {
                            this.mPackages.add(next);
                        }
                        processPackage(next, nativeModuleRegistryBuilder);
                        Systrace.endSection(0L);
                    }
                }
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_END);
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_START);
        Systrace.beginSection(0L, "buildNativeModuleRegistry");
        try {
            return nativeModuleRegistryBuilder.build();
        } finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END);
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void recreateReactContextInBackgroundFromBundleLoader() {
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from BundleLoader");
        recreateReactContextInBackground(this.mJavaScriptExecutorFactory, this.mBundleLoader);
    }

    @ThreadConfined(ThreadConfined.UI)
    private void recreateReactContextInBackgroundInner(final boolean z) {
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: recreateReactContextInBackground");
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport && this.mJSMainModulePath != null) {
            final DeveloperSettings devSettings = this.mDevSupportManager.getDevSettings();
            if (!z && this.mDevSupportManager.hasUpToDateJSBundleInCache() && !devSettings.isRemoteJSDebugEnabled()) {
                onJSBundleLoadedFromServer(null);
                return;
            } else if (!Systrace.isTracing(0L)) {
                if (this.mBundleLoader == null) {
                    this.mDevSupportManager.handleReloadJS();
                    return;
                }
                final String str = this.mJSMainModulePath;
                this.mDevSupportManager.isPackagerRunning(new PackagerStatusCallback() { // from class: com.facebook.react.ReactInstanceManager.3
                    {
                        ReactInstanceManager.this = this;
                    }

                    @Override // com.facebook.react.devsupport.interfaces.PackagerStatusCallback
                    public void onPackagerStatusFetched(final boolean z2) {
                        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.3.1
                            {
                                AnonymousClass3.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                if (z2) {
                                    AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                                    if (!z) {
                                        ReactInstanceManager.this.mDevSupportManager.handleReloadJS();
                                        return;
                                    }
                                    devSettings.setRemoteJSDebugEnabled(false);
                                    ReactInstanceManager.this.recreateReactContextInBackgroundFromBundleLoader();
                                    return;
                                }
                                if (!str.contains(Constants.APPDEBUGKIT)) {
                                    devSettings.setRemoteJSDebugEnabled(false);
                                }
                                ReactInstanceManager.this.recreateReactContextInBackgroundFromBundleLoader();
                            }
                        });
                    }
                });
                return;
            }
        }
        recreateReactContextInBackgroundFromBundleLoader();
    }

    public static void removeReactEmbeddedListener() {
        mReactEmbeddedListener = null;
    }

    @ThreadConfined(ThreadConfined.UI)
    public void runCreateReactContextOnNewThread(final ReactContextInitParams reactContextInitParams) {
        UiThreadUtil.assertOnUiThread();
        synchronized (this.mAttachedRootViews) {
            synchronized (this.mReactContextLock) {
                if (this.mCurrentReactContext != null) {
                    tearDownReactContext(this.mCurrentReactContext);
                    this.mCurrentReactContext = null;
                }
            }
        }
        this.mCreateReactContextThread = new Thread(null, new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5
            {
                ReactInstanceManager.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_END);
                synchronized (ReactInstanceManager.this.mHasStartedDestroyingLock) {
                    while (ReactInstanceManager.this.mHasStartedDestroying.booleanValue()) {
                        try {
                            ReactInstanceManager.this.mHasStartedDestroyingLock.wait();
                        } catch (InterruptedException unused) {
                        }
                    }
                }
                ReactInstanceManager.this.mHasStartedCreatingInitialContext = true;
                if (ReactInstanceManager.this.mReactInstanceProgressListener != null) {
                    ReactInstanceManager.this.mReactInstanceProgressListener.onReactLoadStart();
                }
                try {
                    Process.setThreadPriority(-4);
                    ReactMarker.logMarker(ReactMarkerConstants.VM_INIT);
                    final ReactApplicationContext createReactContext = ReactInstanceManager.this.createReactContext(reactContextInitParams.getJsExecutorFactory().create(), reactContextInitParams.getJsBundleLoader());
                    ReactInstanceManager.this.mCreateReactContextThread = null;
                    ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_START);
                    Runnable runnable = new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5.1
                        {
                            AnonymousClass5.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (ReactInstanceManager.this.mPendingReactContextInitParams != null) {
                                ReactInstanceManager reactInstanceManager = ReactInstanceManager.this;
                                reactInstanceManager.runCreateReactContextOnNewThread(reactInstanceManager.mPendingReactContextInitParams);
                                ReactInstanceManager.this.mPendingReactContextInitParams = null;
                            }
                        }
                    };
                    createReactContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5.2
                        {
                            AnonymousClass5.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ReactInstanceManager.this.setupReactContext(createReactContext);
                            } catch (Exception e2) {
                                if (ReactInstanceManager.this.mReactInstanceProgressListener != null) {
                                    ReactInstanceManager.this.mReactInstanceProgressListener.onReactLoadCancel();
                                }
                                if (ReactInstanceManager.this.mNativeModuleCallExceptionHandler != null) {
                                    ReactInstanceManager.this.mNativeModuleCallExceptionHandler.handleException(e2);
                                } else {
                                    ReactInstanceManager.this.mDevSupportManager.handleException(e2);
                                }
                            }
                        }
                    });
                    UiThreadUtil.runOnUiThread(runnable);
                } catch (Exception e2) {
                    if (ReactInstanceManager.this.mNativeModuleCallExceptionHandler != null) {
                        ReactInstanceManager.this.mNativeModuleCallExceptionHandler.handleException(e2);
                    } else {
                        ReactInstanceManager.this.mDevSupportManager.handleException(e2);
                    }
                }
            }
        }, "create_react_context");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_START);
        this.mCreateReactContextThread.start();
    }

    public void setupReactContext(final ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_END);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_START);
        Systrace.beginSection(0L, "setupReactContext");
        synchronized (this.mAttachedRootViews) {
            synchronized (this.mReactContextLock) {
                this.mCurrentReactContext = (ReactContext) Assertions.assertNotNull(reactApplicationContext);
            }
            CatalystInstance catalystInstance = (CatalystInstance) Assertions.assertNotNull(reactApplicationContext.getCatalystInstance());
            catalystInstance.initialize();
            this.mDevSupportManager.onNewReactContextCreated(reactApplicationContext);
            this.mMemoryPressureRouter.addMemoryPressureListener(catalystInstance);
            moveReactContextToCurrentLifecycleState();
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_START);
            Iterator<ReactRootView> it = this.mAttachedRootViews.iterator();
            while (it.hasNext()) {
                attachRootViewToInstance(it.next());
            }
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_END);
        }
        final ReactInstanceEventListener[] reactInstanceEventListenerArr = (ReactInstanceEventListener[]) this.mReactInstanceEventListeners.toArray(new ReactInstanceEventListener[this.mReactInstanceEventListeners.size()]);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.6
            {
                ReactInstanceManager.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (ReactInstanceEventListener reactInstanceEventListener : reactInstanceEventListenerArr) {
                    reactInstanceEventListener.onReactContextInitialized(reactApplicationContext);
                }
            }
        });
        Systrace.endSection(0L);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_END);
        reactApplicationContext.runOnJSQueueThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.7
            {
                ReactInstanceManager.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(0);
                ReactMarker.logMarker(ReactMarkerConstants.CHANGE_THREAD_PRIORITY, "js_default");
            }
        });
        reactApplicationContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.8
            {
                ReactInstanceManager.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(0);
            }
        });
    }

    private void tearDownReactContext(ReactContext reactContext) {
        UiThreadUtil.assertOnUiThread();
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            reactContext.onHostPause();
        }
        synchronized (this.mAttachedRootViews) {
            for (ReactRootView reactRootView : this.mAttachedRootViews) {
                reactRootView.removeAllViews();
                reactRootView.setId(-1);
            }
        }
        reactContext.destroy();
        this.mDevSupportManager.onReactInstanceDestroyed(reactContext);
        this.mMemoryPressureRouter.removeMemoryPressureListener(reactContext.getCatalystInstance());
    }

    public void toggleElementInspector() {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("toggleElementInspector", null);
        }
    }

    public void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.add(reactInstanceEventListener);
    }

    public void addReactInstanceProgressListener(ReactInstanceProgressListener reactInstanceProgressListener) {
        this.mReactInstanceProgressListener = reactInstanceProgressListener;
    }

    @ThreadConfined(ThreadConfined.UI)
    public void attachRootView(ReactRootView reactRootView) {
        UiThreadUtil.assertOnUiThread();
        this.mAttachedRootViews.add(reactRootView);
        reactRootView.removeAllViews();
        reactRootView.setId(-1);
        ReactContext currentReactContext = getCurrentReactContext();
        if (this.mCreateReactContextThread != null || currentReactContext == null) {
            return;
        }
        attachRootViewToInstance(reactRootView);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void createReactContextInBackground(boolean z) {
        Assertions.assertCondition(!this.mHasStartedCreatingInitialContext, "createReactContextInBackground should only be called when creating the react application for the first time. When reloading JS, e.g. from a new file, explicitlyuse recreateReactContextInBackground");
        this.mHasStartedCreatingInitialContext = true;
        recreateReactContextInBackgroundInner(z);
    }

    @Nullable
    public ViewManager createViewManager(String str) {
        ViewManager createViewManager;
        synchronized (this.mReactContextLock) {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) getCurrentReactContext();
            if (reactApplicationContext != null && reactApplicationContext.hasActiveCatalystInstance()) {
                synchronized (this.mPackages) {
                    for (ReactPackage reactPackage : this.mPackages) {
                        if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (createViewManager = ((ViewManagerOnDemandReactPackage) reactPackage).createViewManager(reactApplicationContext, str)) != null) {
                            return createViewManager;
                        }
                    }
                    return null;
                }
            }
            return null;
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: Destroy");
        this.mHasStartedDestroying = Boolean.TRUE;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
            this.mDevSupportManager.stopInspector();
        }
        moveToBeforeCreateLifecycleState();
        if (this.mCreateReactContextThread != null) {
            this.mCreateReactContextThread = null;
        }
        this.mMemoryPressureRouter.destroy(this.mApplicationContext);
        synchronized (this.mReactContextLock) {
            if (this.mCurrentReactContext != null) {
                this.mCurrentReactContext.destroy();
                this.mCurrentReactContext = null;
            }
        }
        this.mHasStartedCreatingInitialContext = false;
        this.mCurrentActivity = null;
        ResourceDrawableIdHelper.getInstance().clear();
        this.mHasStartedDestroying = Boolean.FALSE;
        synchronized (this.mHasStartedDestroyingLock) {
            this.mHasStartedDestroyingLock.notifyAll();
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void detachRootView(ReactRootView reactRootView) {
        UiThreadUtil.assertOnUiThread();
        synchronized (this.mAttachedRootViews) {
            if (this.mAttachedRootViews.contains(reactRootView)) {
                ReactContext currentReactContext = getCurrentReactContext();
                this.mAttachedRootViews.remove(reactRootView);
                if (currentReactContext != null && currentReactContext.hasActiveCatalystInstance()) {
                    detachViewFromInstance(reactRootView, currentReactContext.getCatalystInstance());
                }
            }
        }
    }

    @Nullable
    @VisibleForTesting
    public ReactContext getCurrentReactContext() {
        ReactContext reactContext;
        synchronized (this.mReactContextLock) {
            reactContext = this.mCurrentReactContext;
        }
        return reactContext;
    }

    public DevSupportManager getDevSupportManager() {
        return this.mDevSupportManager;
    }

    public String getJsExecutorName() {
        return this.mJavaScriptExecutorFactory.toString();
    }

    public LifecycleState getLifecycleState() {
        return this.mLifecycleState;
    }

    public MemoryPressureRouter getMemoryPressureRouter() {
        return this.mMemoryPressureRouter;
    }

    public List<ViewManager> getOrCreateViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ViewManager> list;
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_START);
        Systrace.beginSection(0L, "createAllViewManagers");
        try {
            if (this.mViewManagers == null) {
                synchronized (this.mPackages) {
                    if (this.mViewManagers == null) {
                        this.mViewManagers = new ArrayList();
                        Iterator<ReactPackage> it = this.mPackages.iterator();
                        while (it.hasNext()) {
                            this.mViewManagers.addAll(it.next().createViewManagers(reactApplicationContext));
                        }
                        list = this.mViewManagers;
                    }
                }
                return list;
            }
            list = this.mViewManagers;
            return list;
        } finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
        }
    }

    @Nullable
    public List<String> getViewManagerNames() {
        ArrayList arrayList;
        List<String> viewManagerNames;
        Systrace.beginSection(0L, "ReactInstanceManager.getViewManagerNames");
        synchronized (this.mReactContextLock) {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) getCurrentReactContext();
            if (reactApplicationContext != null && reactApplicationContext.hasActiveCatalystInstance()) {
                synchronized (this.mPackages) {
                    HashSet hashSet = new HashSet();
                    for (ReactPackage reactPackage : this.mPackages) {
                        SystraceMessage.beginSection(0L, "ReactInstanceManager.getViewManagerName").arg("Package", reactPackage.getClass().getSimpleName()).flush();
                        if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (viewManagerNames = ((ViewManagerOnDemandReactPackage) reactPackage).getViewManagerNames(reactApplicationContext)) != null) {
                            hashSet.addAll(viewManagerNames);
                        }
                        SystraceMessage.endSection(0L).flush();
                    }
                    Systrace.endSection(0L);
                    arrayList = new ArrayList(hashSet);
                }
                return arrayList;
            }
            return null;
        }
    }

    public boolean hasStartedCreatingInitialContext() {
        return this.mHasStartedCreatingInitialContext;
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onActivityResult(activity, i2, i3, intent);
        }
    }

    public void onBackPressed() {
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = this.mCurrentReactContext;
        if (reactContext == null) {
            FLog.w(ReactConstants.TAG, "Instance detached from instance manager");
            invokeDefaultOnBackPressed();
            return;
        }
        ((DeviceEventManagerModule) reactContext.getNativeModule(DeviceEventManagerModule.class)).emitHardwareBackPressed();
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeCreateLifecycleState();
        this.mCurrentActivity = null;
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostPause() {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = null;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeResumeLifecycleState();
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = defaultHardwareBackBtnHandler;
        onHostResume(activity);
    }

    public void onLoadingFinished(String str) {
        ReactInstanceProgressListener reactInstanceProgressListener = this.mReactInstanceProgressListener;
        if (reactInstanceProgressListener != null) {
            reactInstanceProgressListener.onReactLoadFinish();
        }
        ReactEmbeddedListener reactEmbeddedListener = mReactEmbeddedListener;
        if (reactEmbeddedListener != null) {
            reactEmbeddedListener.onLoadFinish(str);
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onNewIntent(Intent intent) {
        UiThreadUtil.assertOnUiThread();
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext == null) {
            FLog.w(ReactConstants.TAG, "Instance detached from instance manager");
            return;
        }
        String action = intent.getAction();
        Uri data = intent.getData();
        if ("android.intent.action.VIEW".equals(action) && data != null) {
            ((DeviceEventManagerModule) currentReactContext.getNativeModule(DeviceEventManagerModule.class)).emitNewIntentReceived(data);
        }
        currentReactContext.onNewIntent(this.mCurrentActivity, intent);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void recreateReactContextInBackground() {
        Assertions.assertCondition(this.mHasStartedCreatingInitialContext, "recreateReactContextInBackground should only be called after the initial createReactContextInBackground call.");
        recreateReactContextInBackgroundInner(false);
    }

    public void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.remove(reactInstanceEventListener);
    }

    public void removeReactInstanceProgressListener() {
        this.mReactInstanceProgressListener = null;
    }

    public void setJSMainModulePath(String str) {
        this.mJSMainModulePath = str;
        DevSupportManager devSupportManager = this.mDevSupportManager;
        if (devSupportManager == null || !(devSupportManager instanceof DevSupportManagerImpl)) {
            return;
        }
        ((DevSupportManagerImpl) devSupportManager).setJSAppBundleName(str);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void showDevOptionsDialog() {
        UiThreadUtil.assertOnUiThread();
        this.mDevSupportManager.showDevOptionsDialog();
    }

    public void startReactApplication(String str, String str2) {
        ReactEmbeddedListener reactEmbeddedListener = mReactEmbeddedListener;
        if (reactEmbeddedListener != null) {
            reactEmbeddedListener.onLoadStart(str, str2);
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    private void recreateReactContextInBackground(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
        UiThreadUtil.assertOnUiThread();
        ReactContextInitParams reactContextInitParams = new ReactContextInitParams(javaScriptExecutorFactory, jSBundleLoader);
        if (this.mCreateReactContextThread == null) {
            runCreateReactContextOnNewThread(reactContextInitParams);
        } else {
            this.mPendingReactContextInitParams = reactContextInitParams;
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void createReactContextInBackground() {
        createReactContextInBackground(false);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostResume(Activity activity) {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentActivity = activity;
        if (this.mUseDeveloperSupport) {
            final View decorView = activity.getWindow().getDecorView();
            if (!ViewCompat.isAttachedToWindow(decorView)) {
                decorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.facebook.react.ReactInstanceManager.4
                    {
                        ReactInstanceManager.this = this;
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view) {
                        decorView.removeOnAttachStateChangeListener(this);
                        ReactInstanceManager.this.mDevSupportManager.setDevSupportEnabled(true);
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view) {
                    }
                });
            } else {
                this.mDevSupportManager.setDevSupportEnabled(true);
            }
        }
        moveToResumedLifecycleState(false);
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostDestroy(Activity activity) {
        if (activity == this.mCurrentActivity) {
            onHostDestroy();
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void onHostPause(Activity activity) {
        Assertions.assertNotNull(this.mCurrentActivity);
        Assertions.assertCondition(activity == this.mCurrentActivity, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + this.mCurrentActivity.getClass().getSimpleName() + " Paused activity: " + activity.getClass().getSimpleName());
        onHostPause();
    }

    ReactInstanceManager(Context context, @Nullable Activity activity, @Nullable DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, JavaScriptExecutorFactory javaScriptExecutorFactory, @Nullable JSBundleLoader jSBundleLoader, @Nullable String str, List<ReactPackage> list, boolean z, @Nullable NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener, LifecycleState lifecycleState, @Nullable UIImplementationProvider uIImplementationProvider, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, @Nullable RedBoxHandler redBoxHandler, boolean z2, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i2, int i3, @Nullable JSIModulePackage jSIModulePackage, @Nullable Map<String, RequestHandler> map) {
        this(context, activity, defaultHardwareBackBtnHandler, javaScriptExecutorFactory, jSBundleLoader, str, list, z, notThreadSafeBridgeIdleDebugListener, lifecycleState, uIImplementationProvider, nativeModuleCallExceptionHandler, redBoxHandler, z2, devBundleDownloadListener, i2, i3, jSIModulePackage, map, false);
    }
}
