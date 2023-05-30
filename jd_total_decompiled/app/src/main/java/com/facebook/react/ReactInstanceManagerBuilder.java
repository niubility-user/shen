package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.jscexecutor.JSCExecutorFactory;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.uimanager.UIImplementationProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ReactInstanceManagerBuilder {
    @Nullable
    private Application mApplication;
    @Nullable
    private NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    @Nullable
    private Activity mCurrentActivity;
    @Nullable
    private Map<String, RequestHandler> mCustomPackagerCommandHandlers;
    @Nullable
    private DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;
    @Nullable
    private DevBundleDownloadListener mDevBundleDownloadListener;
    @Nullable
    private LifecycleState mInitialLifecycleState;
    @Nullable
    private String mJSBundleAssetCommonUrl;
    @Nullable
    private String mJSBundleAssetUrl;
    @Nullable
    private JSBundleLoader mJSBundleLoader;
    @Nullable
    private JSIModulePackage mJSIModulesPackage;
    @Nullable
    private String mJSMainModulePath;
    @Nullable
    private JavaScriptExecutorFactory mJavaScriptExecutorFactory;
    private boolean mLazyViewManagersEnabled;
    @Nullable
    private NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    @Nullable
    private RedBoxHandler mRedBoxHandler;
    @Nullable
    private UIImplementationProvider mUIImplementationProvider;
    private boolean mUseDeveloperSupport;
    private final List<ReactPackage> mPackages = new ArrayList();
    private int mMinNumShakes = 1;
    private int mMinTimeLeftInFrameForNonBatchedOperationMs = -1;
    private boolean mDisableDestoryWhenFatalException = false;

    public ReactInstanceManagerBuilder addPackage(ReactPackage reactPackage) {
        this.mPackages.add(reactPackage);
        return this;
    }

    public ReactInstanceManagerBuilder addPackages(List<ReactPackage> list) {
        this.mPackages.addAll(list);
        return this;
    }

    public ReactInstanceManager build() {
        String str;
        Assertions.assertNotNull(this.mApplication, "Application property has not been set with this builder");
        boolean z = true;
        Assertions.assertCondition((!this.mUseDeveloperSupport && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) ? false : true, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
        if (this.mJSMainModulePath == null && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) {
            z = false;
        }
        Assertions.assertCondition(z, "Either MainModulePath or JS Bundle File needs to be provided");
        if (this.mUIImplementationProvider == null) {
            this.mUIImplementationProvider = new UIImplementationProvider();
        }
        String packageName = this.mApplication.getPackageName();
        String friendlyDeviceName = AndroidInfoHelpers.getFriendlyDeviceName();
        Application application = this.mApplication;
        Activity activity = this.mCurrentActivity;
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultHardwareBackBtnHandler;
        JavaScriptExecutorFactory javaScriptExecutorFactory = this.mJavaScriptExecutorFactory;
        if (javaScriptExecutorFactory == null) {
            javaScriptExecutorFactory = new JSCExecutorFactory(packageName, friendlyDeviceName);
        }
        JavaScriptExecutorFactory javaScriptExecutorFactory2 = javaScriptExecutorFactory;
        JSBundleLoader jSBundleLoader = this.mJSBundleLoader;
        if (jSBundleLoader == null && (str = this.mJSBundleAssetUrl) != null) {
            String str2 = this.mJSBundleAssetCommonUrl;
            jSBundleLoader = str2 != null ? JSBundleLoader.createSepAssetLoader(this.mApplication, str, str2, false) : JSBundleLoader.createAssetLoader(this.mApplication, str, false);
        }
        return new ReactInstanceManager(application, activity, defaultHardwareBackBtnHandler, javaScriptExecutorFactory2, jSBundleLoader, this.mJSMainModulePath, this.mPackages, this.mUseDeveloperSupport, this.mBridgeIdleDebugListener, (LifecycleState) Assertions.assertNotNull(this.mInitialLifecycleState, "Initial lifecycle state was not set"), this.mUIImplementationProvider, this.mNativeModuleCallExceptionHandler, this.mRedBoxHandler, this.mLazyViewManagersEnabled, this.mDevBundleDownloadListener, this.mMinNumShakes, this.mMinTimeLeftInFrameForNonBatchedOperationMs, this.mJSIModulesPackage, this.mCustomPackagerCommandHandlers, this.mDisableDestoryWhenFatalException);
    }

    public ReactInstanceManagerBuilder setApplication(Application application) {
        this.mApplication = application;
        return this;
    }

    public ReactInstanceManagerBuilder setBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleDebugListener = notThreadSafeBridgeIdleDebugListener;
        return this;
    }

    public ReactInstanceManagerBuilder setBundleAssetName(String str) {
        String str2;
        if (str == null) {
            str2 = null;
        } else {
            str2 = "assets://" + str;
        }
        this.mJSBundleAssetUrl = str2;
        this.mJSBundleLoader = null;
        return this;
    }

    public ReactInstanceManagerBuilder setCurrentActivity(Activity activity) {
        this.mCurrentActivity = activity;
        return this;
    }

    public ReactInstanceManagerBuilder setCustomPackagerCommandHandlers(Map<String, RequestHandler> map) {
        this.mCustomPackagerCommandHandlers = map;
        return this;
    }

    public ReactInstanceManagerBuilder setDefaultHardwareBackBtnHandler(DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.mDefaultHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setDevBundleDownloadListener(@Nullable DevBundleDownloadListener devBundleDownloadListener) {
        this.mDevBundleDownloadListener = devBundleDownloadListener;
        return this;
    }

    public ReactInstanceManagerBuilder setDisableDestoryWhenFatalException(boolean z) {
        this.mDisableDestoryWhenFatalException = z;
        return this;
    }

    public ReactInstanceManagerBuilder setInitialLifecycleState(LifecycleState lifecycleState) {
        this.mInitialLifecycleState = lifecycleState;
        return this;
    }

    public ReactInstanceManagerBuilder setJSBundleFile(String str) {
        if (str.startsWith("assets://")) {
            this.mJSBundleAssetUrl = str;
            this.mJSBundleLoader = null;
            return this;
        }
        return setJSBundleLoader(JSBundleLoader.createFileLoader(str));
    }

    public ReactInstanceManagerBuilder setJSBundleLoader(JSBundleLoader jSBundleLoader) {
        this.mJSBundleLoader = jSBundleLoader;
        this.mJSBundleAssetUrl = null;
        this.mJSBundleAssetCommonUrl = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSIModulesPackage(@Nullable JSIModulePackage jSIModulePackage) {
        this.mJSIModulesPackage = jSIModulePackage;
        return this;
    }

    public ReactInstanceManagerBuilder setJSMainModulePath(String str) {
        this.mJSMainModulePath = str;
        return this;
    }

    public ReactInstanceManagerBuilder setJavaScriptExecutorFactory(@Nullable JavaScriptExecutorFactory javaScriptExecutorFactory) {
        this.mJavaScriptExecutorFactory = javaScriptExecutorFactory;
        return this;
    }

    public ReactInstanceManagerBuilder setLazyViewManagersEnabled(boolean z) {
        this.mLazyViewManagersEnabled = z;
        return this;
    }

    public ReactInstanceManagerBuilder setMinNumShakes(int i2) {
        this.mMinNumShakes = i2;
        return this;
    }

    public ReactInstanceManagerBuilder setMinTimeLeftInFrameForNonBatchedOperationMs(int i2) {
        this.mMinTimeLeftInFrameForNonBatchedOperationMs = i2;
        return this;
    }

    public ReactInstanceManagerBuilder setNativeModuleCallExceptionHandler(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setRedBoxHandler(@Nullable RedBoxHandler redBoxHandler) {
        this.mRedBoxHandler = redBoxHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setSeperateBundleAssetName(String str, String str2) {
        String str3;
        String str4 = null;
        if (str == null) {
            str3 = null;
        } else {
            str3 = "assets://" + str;
        }
        if (str2 != null) {
            str4 = "assets://" + str2;
        }
        return setSeperateBundleFileName(str3, str4);
    }

    public ReactInstanceManagerBuilder setSeperateBundleCommonAsset(String str, String str2) {
        String str3;
        if (str2 == null) {
            str3 = null;
        } else {
            str3 = "assets://" + str2;
        }
        return setSeperateBundleFileName(str, str3);
    }

    public ReactInstanceManagerBuilder setSeperateBundleFileName(String str, String str2) {
        if (str2.startsWith("assets://")) {
            this.mJSBundleAssetUrl = str;
            this.mJSBundleAssetCommonUrl = str2;
            this.mJSBundleLoader = null;
            return this;
        }
        return setJSBundleLoader(JSBundleLoader.createFileLoader(this.mApplication, str, str, str2, false));
    }

    public ReactInstanceManagerBuilder setUIImplementationProvider(@Nullable UIImplementationProvider uIImplementationProvider) {
        this.mUIImplementationProvider = uIImplementationProvider;
        return this;
    }

    public ReactInstanceManagerBuilder setUseDeveloperSupport(boolean z) {
        this.mUseDeveloperSupport = z;
        return this;
    }
}
