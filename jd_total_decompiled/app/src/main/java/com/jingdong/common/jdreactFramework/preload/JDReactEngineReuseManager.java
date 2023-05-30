package com.jingdong.common.jdreactFramework.preload;

import com.jingdong.common.jdreactFramework.helper.ReactPackageFactory;

/* loaded from: classes5.dex */
public class JDReactEngineReuseManager {
    private static volatile JDReactEngineReuseManager sInstance;

    private JDReactEngineReuseManager() {
    }

    private EngineHelper createEngineInner(String str, ReactPackageFactory reactPackageFactory) {
        return new EngineHelperImpl(str, reactPackageFactory);
    }

    public static JDReactEngineReuseManager getInstance() {
        if (sInstance == null) {
            synchronized (JDReactEngineReuseManager.class) {
                if (sInstance == null) {
                    sInstance = new JDReactEngineReuseManager();
                }
            }
        }
        return sInstance;
    }

    public EngineHelper createEngine(String str, ReactPackageFactory reactPackageFactory) {
        return createEngineInner(str, reactPackageFactory);
    }
}
