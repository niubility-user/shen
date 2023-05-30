package com.jingdong.common.jdreactFramework.preload;

import android.app.Activity;
import android.graphics.Point;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.helper.ReactPackageFactory;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import org.json.JSONArray;

/* loaded from: classes5.dex */
public class JDReactCommonPreloadManager implements NotThreadSafeBridgeIdleDebugListener {
    private static volatile JDReactCommonPreloadManager sInstance;
    private String commonFilePath;
    private String commonVersion;
    private AbstractJDReactInitialHelper.JDReactNativeModuleCallExceptionHandler mCallExceptionHandler;
    private ReactInstanceManager mCommonManager;
    private ReactPackage mReactPackage;
    private ReactPackageFactory mReactPackageFactory;
    private Point oldPoint;
    private boolean isPreloadCommon = false;
    private HashSet<String> mExceptModuleNameSet = new HashSet<>();
    private volatile EngineState mState = EngineState.NOT_INIT;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class ForwardExceptionHandler implements NativeModuleCallExceptionHandler {
        private WeakReference<JDReactCommonPreloadManager> mManagerRef;

        @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
        public void handleException(Exception exc) {
            WeakReference<JDReactCommonPreloadManager> weakReference = this.mManagerRef;
            JDReactCommonPreloadManager jDReactCommonPreloadManager = weakReference == null ? null : weakReference.get();
            if (jDReactCommonPreloadManager != null) {
                if (jDReactCommonPreloadManager.mCallExceptionHandler != null) {
                    jDReactCommonPreloadManager.handleException(exc);
                } else if (exc != null) {
                    ArrayMap arrayMap = new ArrayMap();
                    arrayMap.put("moduleName", JDReactConstant.COMMON_BUNDLE_NAME);
                    arrayMap.put("commonVersion", TextUtils.equals(jDReactCommonPreloadManager.commonVersion, "0.0") ? "" : jDReactCommonPreloadManager.commonVersion);
                    arrayMap.put("moduleVersion", TextUtils.equals(jDReactCommonPreloadManager.commonVersion, "0.0") ? "" : jDReactCommonPreloadManager.commonVersion);
                    JDReactHelper.newInstance().postException(exc, arrayMap);
                }
            }
        }

        private ForwardExceptionHandler(JDReactCommonPreloadManager jDReactCommonPreloadManager) {
            this.mManagerRef = new WeakReference<>(jDReactCommonPreloadManager);
        }
    }

    private JDReactCommonPreloadManager() {
    }

    private Point getDisplay(Activity activity) {
        if (activity == null) {
            if (JDReactHelper.newInstance().getCurrentMyActivity() != null) {
                return DisplayMetricsHolder.getAppSize(JDReactHelper.newInstance().getCurrentMyActivity());
            }
            return DisplayMetricsHolder.getAppSize(JDReactHelper.newInstance().getApplication());
        }
        return DisplayMetricsHolder.getAppSize(activity);
    }

    public static JDReactCommonPreloadManager getInstance() {
        if (sInstance == null) {
            synchronized (JDReactCommonPreloadManager.class) {
                if (sInstance == null) {
                    sInstance = new JDReactCommonPreloadManager();
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleException(Exception exc) {
        AbstractJDReactInitialHelper.JDReactNativeModuleCallExceptionHandler jDReactNativeModuleCallExceptionHandler = this.mCallExceptionHandler;
        if (jDReactNativeModuleCallExceptionHandler != null) {
            jDReactNativeModuleCallExceptionHandler.handleException(exc);
        }
    }

    private synchronized void moveToState(EngineState engineState) {
        this.mState = engineState;
    }

    public synchronized boolean canPreload(String str, ReactPackage reactPackage, ReactPackage reactPackage2) {
        return canPreload(str, reactPackage, reactPackage2, null);
    }

    public String getCommonFilePath() {
        return this.commonFilePath;
    }

    public ReactInstanceManager getCommonManager() {
        return this.mCommonManager;
    }

    public boolean isPreloadCommon() {
        return this.isPreloadCommon;
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public void onBridgeDestroyed() {
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public void onTransitionToBridgeBusy() {
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeIdle() {
        if (this.mState == EngineState.INITIALIZED) {
            moveToState(EngineState.COMMON_READY);
            try {
                ReactInstanceManager reactInstanceManager = this.mCommonManager;
                if (reactInstanceManager != null && reactInstanceManager.getCurrentReactContext() != null && this.mCommonManager.getCurrentReactContext().getCatalystInstance() != null) {
                    this.mCommonManager.getCurrentReactContext().getCatalystInstance().removeBridgeIdleDebugListener(this);
                }
            } catch (Exception unused) {
            }
        }
    }

    public void preloadCommonBundle() {
        preloadCommonBundle(null);
    }

    public synchronized void releaseCommonManager() {
        moveToState(EngineState.DESTROYED);
        this.mCommonManager = null;
        this.commonVersion = null;
        this.commonFilePath = null;
        moveToState(EngineState.NOT_INIT);
    }

    public void setEnable(boolean z) {
        boolean z2 = this.isPreloadCommon;
        if (z2 && !z) {
            releaseCommonManager();
        } else if (!z2 && z) {
            this.mReactPackage = this.mReactPackageFactory.newReactPackage();
        }
        this.isPreloadCommon = z;
    }

    public synchronized void setExceptConfigStr(String str) {
        try {
            this.mExceptModuleNameSet.clear();
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                String optString = jSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString)) {
                    this.mExceptModuleNameSet.add(optString);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setJDReactHelper(AbstractJDReactInitialHelper abstractJDReactInitialHelper) {
        this.mCallExceptionHandler = new AbstractJDReactInitialHelper.JDReactNativeModuleCallExceptionHandler(abstractJDReactInitialHelper);
    }

    public void setReactPackageFactory(ReactPackageFactory reactPackageFactory) {
        this.mReactPackageFactory = reactPackageFactory;
    }

    public synchronized boolean canPreload(String str, ReactPackage reactPackage, ReactPackage reactPackage2, Activity activity) {
        ReactPackage reactPackage3;
        Point point2;
        try {
            Point display = getDisplay(activity);
            boolean z = true;
            if (!(display != null && (point2 = this.oldPoint) != null && display.x == point2.x && display.y == point2.y)) {
                releaseCommonManager();
                return false;
            }
            if (!this.isPreloadCommon || this.mCommonManager == null || (reactPackage3 = this.mReactPackage) == null || reactPackage == null || !reactPackage3.getClass().equals(reactPackage.getClass()) || reactPackage2 != null || TextUtils.isEmpty(str) || this.mExceptModuleNameSet.contains(str) || EngineState.COMMON_READY != this.mState) {
                z = false;
            }
            if (z) {
                moveToState(EngineState.CONSUMED);
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    public void preloadCommonBundle(Activity activity) {
        String str;
        JLog.d("jdreact common", "preloadCommonBundle()  0");
        if (!this.isPreloadCommon) {
            JLog.d("jdreact common", "preloadCommonBundle()  1");
        } else if (this.mState.isAtLeast(EngineState.COMMON_READY)) {
            JLog.d("jdreact common", "preloadCommonBundle()  2");
        } else {
            try {
                PluginVersion commonPluginVersion = ReactVersionUtils.getCommonPluginVersion();
                String str2 = commonPluginVersion.pluginDir + File.separator + JDReactConstant.COMMON_BUNDLE_NAME + ".jsbundle";
                this.commonVersion = commonPluginVersion.pluginVersion;
                if (str2.startsWith(JDReactConstant.ASSERT_DIR)) {
                    str2 = "assets://" + str2;
                    str = str2;
                } else {
                    str = "jsbundles/JDReactCommon";
                }
                this.commonFilePath = str2;
                JLog.d("jdreact common", "preloadCommonBundle()  3");
                ReactInstanceManagerBuilder addPackage = ReactInstanceManager.builder().setApplication(JDReactHelper.newInstance().getApplication()).setJSBundleFile(str2).setJSMainModulePath(str).setNativeModuleCallExceptionHandler(new ForwardExceptionHandler()).addPackage(new MainReactPackage());
                ReactPackageFactory reactPackageFactory = this.mReactPackageFactory;
                if (reactPackageFactory != null) {
                    addPackage.addPackage(reactPackageFactory.newReactPackage());
                }
                if (activity != null) {
                    addPackage.setCurrentActivity(activity);
                } else if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                    addPackage.setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity());
                }
                this.mCommonManager = addPackage.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).setBridgeIdleDebugListener(this).build();
                moveToState(EngineState.INITIALIZED);
                JLog.d("jdreact common", "preloadCommonBundle()  4");
                this.mCommonManager.createReactContextInBackground(true);
                JLog.d("jdreact common", "preloadCommonBundle()  5");
                this.oldPoint = getDisplay(activity);
            } catch (Throwable unused) {
            }
        }
    }

    private Point getDisplay() {
        return getDisplay(null);
    }
}
