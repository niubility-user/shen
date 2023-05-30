package com.jingdong.common.jdreactFramework.preload;

import android.text.TextUtils;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.jingdong.common.jdreactFramework.Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class JDReactPreloadInstanceManager {
    private static JDReactPreloadInstanceManager mJDReactPreloadInstanceManager;
    private static ConcurrentHashMap<String, WeakReference<ReactInstanceManager>> reactPreloadModules = new ConcurrentHashMap<>();

    public static JDReactPreloadInstanceManager newInstance() {
        if (mJDReactPreloadInstanceManager == null) {
            mJDReactPreloadInstanceManager = new JDReactPreloadInstanceManager();
        }
        return mJDReactPreloadInstanceManager;
    }

    public static void removeModule(String str) {
        WeakReference<ReactInstanceManager> remove;
        ReactInstanceManager reactInstanceManager;
        if (!reactPreloadModules.contains(str) || (remove = reactPreloadModules.remove(str)) == null || (reactInstanceManager = remove.get()) == null) {
            return;
        }
        JLog.e("", "ttt");
        reactInstanceManager.onHostDestroy();
        reactInstanceManager.destroy();
    }

    public ReactInstanceManager getInstance(String str) {
        WeakReference<ReactInstanceManager> remove = reactPreloadModules.remove(str);
        if (remove != null) {
            return remove.get();
        }
        return null;
    }

    public boolean preloadReact(String str) {
        String str2;
        ReactInstanceManager build;
        ReactInstanceManagerBuilder addPackage;
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JDReactHelper.newInstance().getApplicationContext(), str);
        if (pluginDir == null || TextUtils.isEmpty(pluginDir.pluginDir)) {
            return false;
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d("test", "The plugin path is " + pluginDir.pluginDir);
        }
        String str3 = pluginDir.pluginDir + File.separator + str + ".jsbundle";
        if (pluginDir.full) {
            if (pluginDir.isPreset) {
                addPackage = ReactInstanceManager.builder().setApplication(JDReactHelper.newInstance().getApplication()).setBundleAssetName(str3).setJSMainModulePath("jsbundles/" + str).addPackage(new MainReactPackage());
            } else {
                addPackage = ReactInstanceManager.builder().setApplication(JDReactHelper.newInstance().getApplication()).setJSBundleFile(str3).setJSMainModulePath("jsbundles/" + str).addPackage(new MainReactPackage());
            }
            if (AbstractJDReactInitialHelper.getPackageManger() != null) {
                addPackage.addPackage(AbstractJDReactInitialHelper.getPackageManger());
            }
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                addPackage.setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity());
            }
            if (JDReactHelper.newInstance().isDebug() && str3.contains(Constants.APPDEBUGKIT)) {
                build = addPackage.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            } else {
                build = addPackage.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            }
        } else {
            String str4 = pluginDir.pluginCommonDir;
            if (pluginDir.isPreset) {
                str2 = "assets://" + str3;
            } else {
                str2 = str3;
            }
            if (TextUtils.isEmpty(str4)) {
                return false;
            }
            if (str4.startsWith(JDReactConstant.ASSERT_DIR)) {
                str4 = "assets://" + str4;
            }
            ReactInstanceManagerBuilder addPackage2 = ReactInstanceManager.builder().setApplication(JDReactHelper.newInstance().getApplication()).setSeperateBundleFileName(str2, str4).setJSMainModulePath("jsbundles/" + str).addPackage(new MainReactPackage());
            if (AbstractJDReactInitialHelper.getPackageManger() != null) {
                addPackage2.addPackage(AbstractJDReactInitialHelper.getPackageManger());
            }
            if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
                addPackage2.setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity());
            }
            if (JDReactHelper.newInstance().isDebug() && str3.contains(Constants.APPDEBUGKIT)) {
                build = addPackage2.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            } else {
                build = addPackage2.setUseDeveloperSupport(JDReactHelper.newInstance().isDebug()).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            }
        }
        build.createReactContextInBackground();
        JLog.d("kris", "created");
        reactPreloadModules.put(str, new WeakReference<>(build));
        return true;
    }
}
