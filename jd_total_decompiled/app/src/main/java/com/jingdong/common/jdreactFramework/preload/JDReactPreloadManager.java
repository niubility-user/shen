package com.jingdong.common.jdreactFramework.preload;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.jingdong.common.jdreactFramework.Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class JDReactPreloadManager {
    private static final String TAG = "JDReactPreloadManager";
    private static ConcurrentHashMap<String, WeakReference<JDReactPreloadManager>> reactPreloadModules = new ConcurrentHashMap<>();
    private String bundlePath;
    private ReactInstanceManager mManager;
    private JDReactModuleEntity mRnInfo;
    private ReactRootView mRootView;

    public JDReactPreloadManager(JDReactModuleEntity jDReactModuleEntity) {
        this.mRnInfo = jDReactModuleEntity;
    }

    public static void clearPreloadModules() {
        ConcurrentHashMap<String, WeakReference<JDReactPreloadManager>> concurrentHashMap = reactPreloadModules;
        if (concurrentHashMap != null) {
            Set<String> keySet = concurrentHashMap.keySet();
            if (keySet != null && keySet.size() > 0) {
                for (String str : keySet) {
                    WeakReference<JDReactPreloadManager> weakReference = reactPreloadModules.get(str);
                    reactPreloadModules.remove(str);
                    if (weakReference != null && weakReference.get() != null) {
                        weakReference.get().destroy();
                    }
                }
            }
            reactPreloadModules = null;
        }
    }

    private void clearViewReference() {
        ViewParent parent = this.mRootView.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.mRootView);
        }
    }

    public static JDReactPreloadManager getPreloadManagerByModule(String str) {
        ConcurrentHashMap<String, WeakReference<JDReactPreloadManager>> concurrentHashMap;
        WeakReference<JDReactPreloadManager> weakReference;
        if (str == null || (concurrentHashMap = reactPreloadModules) == null || !concurrentHashMap.containsKey(str) || (weakReference = reactPreloadModules.get(str)) == null || weakReference.get() == null) {
            return null;
        }
        return weakReference.get();
    }

    public static ConcurrentHashMap<String, WeakReference<JDReactPreloadManager>> getReactPreloadModules() {
        return reactPreloadModules;
    }

    private boolean preloadReact() {
        ReactInstanceManagerBuilder addPackage;
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JDReactHelper.newInstance().getApplicationContext(), this.mRnInfo.getmBundleName());
        if (pluginDir == null || TextUtils.isEmpty(pluginDir.pluginDir)) {
            return false;
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "The plugin path is " + pluginDir.pluginDir);
        }
        String str = this.mRnInfo.getmBundleName();
        String str2 = pluginDir.pluginDir + File.separator + this.mRnInfo.getmBundleName() + ".jsbundle";
        this.bundlePath = str2;
        if (pluginDir.full) {
            if (pluginDir.isPreset) {
                addPackage = ReactInstanceManager.builder().setApplication(JDReactHelper.newInstance().getApplication()).setBundleAssetName(this.bundlePath).setJSMainModulePath("jsbundles/" + str).addPackage(new MainReactPackage());
            } else {
                addPackage = ReactInstanceManager.builder().setApplication(JDReactHelper.newInstance().getApplication()).setJSBundleFile(this.bundlePath).setJSMainModulePath("jsbundles/" + str).addPackage(new MainReactPackage());
            }
            if (AbstractJDReactInitialHelper.getPackageManger() != null) {
                addPackage.addPackage(AbstractJDReactInitialHelper.getPackageManger());
            }
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                addPackage.setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity());
            }
            if (JDReactHelper.newInstance().isDebug() && this.bundlePath.contains(Constants.APPDEBUGKIT)) {
                this.mManager = addPackage.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            } else {
                this.mManager = addPackage.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            }
        } else {
            String str3 = pluginDir.pluginCommonDir;
            if (pluginDir.isPreset) {
                str2 = "assets://" + this.bundlePath;
            }
            if (TextUtils.isEmpty(str3)) {
                return false;
            }
            if (str3.startsWith(JDReactConstant.ASSERT_DIR)) {
                str3 = "assets://" + str3;
            }
            ReactInstanceManagerBuilder addPackage2 = ReactInstanceManager.builder().setApplication(JDReactHelper.newInstance().getApplication()).setSeperateBundleFileName(str2, str3).setJSMainModulePath("jsbundles/" + str).addPackage(new MainReactPackage());
            if (AbstractJDReactInitialHelper.getPackageManger() != null) {
                addPackage2.addPackage(AbstractJDReactInitialHelper.getPackageManger());
            }
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                addPackage2.setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity());
            }
            if (JDReactHelper.newInstance().isDebug() && this.bundlePath.contains(Constants.APPDEBUGKIT)) {
                this.mManager = addPackage2.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            } else {
                this.mManager = addPackage2.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            }
        }
        this.mManager.createReactContextInBackground();
        ReactRootView reactRootView = new ReactRootView(JDReactHelper.newInstance().getApplicationContext());
        this.mRootView = reactRootView;
        reactRootView.startReactApplication(this.mManager, this.mRnInfo.getmModuleName(), this.mRnInfo.getmLaunchOptions());
        return true;
    }

    public void destroy() {
        try {
            ReactInstanceManager reactInstanceManager = this.mManager;
            if (reactInstanceManager != null) {
                reactInstanceManager.onHostDestroy();
                this.mManager.destroy();
                this.mManager = null;
            }
            if (this.mRootView != null) {
                clearViewReference();
                this.mRootView.unmountReactApplication();
                this.mRootView = null;
            }
            this.mRnInfo = null;
        } catch (Throwable unused) {
        }
    }

    public String getBundlePath() {
        return this.bundlePath;
    }

    public ReactInstanceManager getReactManager() {
        return this.mManager;
    }

    public ReactRootView getReactRootView() {
        return this.mRootView;
    }

    public JDReactModuleEntity getRnInfo() {
        return this.mRnInfo;
    }

    public boolean init() {
        if (ReactModuleAvailabilityUtils.getModuleAvailability(this.mRnInfo.getmBundleName()) && preloadReact()) {
            if (reactPreloadModules == null) {
                reactPreloadModules = new ConcurrentHashMap<>();
            }
            if (reactPreloadModules.containsKey(this.mRnInfo.getmBundleName())) {
                WeakReference<JDReactPreloadManager> weakReference = reactPreloadModules.get(this.mRnInfo.getmBundleName());
                reactPreloadModules.remove(this.mRnInfo.getmBundleName());
                if (weakReference != null && weakReference.get() != null) {
                    weakReference.get().destroy();
                }
            }
            reactPreloadModules.put(this.mRnInfo.getmBundleName(), new WeakReference<>(this));
            return true;
        }
        return false;
    }
}
