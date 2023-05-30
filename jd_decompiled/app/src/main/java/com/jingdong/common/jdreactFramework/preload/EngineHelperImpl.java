package com.jingdong.common.jdreactFramework.preload;

import android.app.Activity;
import android.text.TextUtils;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JDReactJSLoadingModule;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.helper.ReactPackageFactory;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import java.io.File;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes5.dex */
public class EngineHelperImpl implements EngineHelper, NotThreadSafeBridgeIdleDebugListener {
    private Condition mHasInitCondition;
    private ReentrantLock mHasInitLock;
    private ReactInstanceManager mReactInstanceManager;
    private String mSourceName;
    private volatile AtomicBoolean mHasInit = new AtomicBoolean(false);
    private CopyOnWriteArraySet<PluginVersion> mLoadedSet = new CopyOnWriteArraySet<>();

    public EngineHelperImpl(String str, ReactPackageFactory reactPackageFactory) {
        this.mSourceName = str;
        ReactInstanceManagerBuilder addPackage = ReactInstanceManager.builder().setApplication(JDReactHelper.newInstance().getApplication()).setBundleAssetName("jdreact/JDReactCommon/JDReactCommon.jsbundle").setJSMainModulePath("jdreact/JDReactCommon/JDReactCommon.jsbundle").addPackage(new MainReactPackage());
        if (reactPackageFactory != null) {
            addPackage.addPackage(reactPackageFactory.newReactPackage());
        }
        if (AbstractJDReactInitialHelper.getCurrentMyActivity() != null) {
            addPackage.setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity());
        }
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mHasInitLock = reentrantLock;
        this.mHasInitCondition = reentrantLock.newCondition();
        ReactInstanceManager build = addPackage.setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).setBridgeIdleDebugListener(this).setDisableDestoryWhenFatalException(true).build();
        this.mReactInstanceManager = build;
        build.createReactContextInBackground();
    }

    private void loadInner(PluginVersion pluginVersion) {
        CopyOnWriteArraySet<PluginVersion> copyOnWriteArraySet;
        ReactInstanceManager reactInstanceManager;
        boolean loadJSFromFileSynchronously;
        if (pluginVersion == null || TextUtils.isEmpty(pluginVersion.pluginDir) || (copyOnWriteArraySet = this.mLoadedSet) == null || copyOnWriteArraySet.contains(pluginVersion) || (reactInstanceManager = this.mReactInstanceManager) == null || reactInstanceManager.getCurrentReactContext() == null) {
            return;
        }
        ReactContext currentReactContext = this.mReactInstanceManager.getCurrentReactContext();
        CatalystInstanceImpl catalystInstanceImpl = (CatalystInstanceImpl) currentReactContext.getCatalystInstance();
        if (catalystInstanceImpl != null) {
            catalystInstanceImpl.getJSBundleLoader().jsCommonBundle = "jdreact/JDReactCommon/JDReactCommon.jsbundle";
            if (pluginVersion.isPreset) {
                loadJSFromFileSynchronously = JDReactJSLoadingModule.loadJSFromAssetsSynchronously(currentReactContext, catalystInstanceImpl, pluginVersion.pluginDir, pluginVersion.pluginName + ".jsbundle");
            } else {
                loadJSFromFileSynchronously = JDReactJSLoadingModule.loadJSFromFileSynchronously(catalystInstanceImpl, pluginVersion.pluginDir + File.separator + pluginVersion.pluginName + ".jsbundle");
            }
            if (loadJSFromFileSynchronously) {
                this.mLoadedSet.add(pluginVersion);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.preload.EngineHelper
    public void destroy() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.destroy();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.preload.EngineHelper
    public ReactInstanceManager getEngine() {
        return this.mReactInstanceManager;
    }

    @Override // com.jingdong.common.jdreactFramework.preload.EngineHelper
    public String getSourceName() {
        return this.mSourceName;
    }

    @Override // com.jingdong.common.jdreactFramework.preload.EngineHelper
    public void load(String str) {
        while (!this.mHasInit.get()) {
            try {
                try {
                    this.mHasInitLock.lock();
                    this.mHasInitCondition.await();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            } finally {
                this.mHasInitLock.unlock();
            }
        }
        loadInner(ReactNativeFileManager.getPluginDir(this.mReactInstanceManager.getCurrentReactContext(), str));
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public void onBridgeDestroyed() {
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public void onTransitionToBridgeBusy() {
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public void onTransitionToBridgeIdle() {
        if (this.mHasInit.get()) {
            return;
        }
        try {
            this.mHasInitLock.lock();
            this.mHasInit.set(true);
            this.mHasInitCondition.signal();
        } finally {
            this.mHasInitLock.unlock();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.preload.EngineHelper
    public void resume(Activity activity) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(activity);
        }
    }
}
