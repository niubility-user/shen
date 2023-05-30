package com.jingdong.common.jdreactFramework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.ReactPackage;
import com.jingdong.common.jdreactFramework.helper.ReactPackageFactory;
import com.jingdong.common.jdreactFramework.preload.JDReactCommonPreloadManager;
import com.jingdong.common.jdreactFramework.preload.JDReactPreloadInstanceManager;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperExt;

/* loaded from: classes5.dex */
public class JDReactSDK extends AresSDK {
    private static volatile JDReactSDK sInstance;

    private JDReactSDK() {
    }

    public static JDReactSDK getInstance() {
        if (sInstance == null) {
            synchronized (JDReactSDK.class) {
                if (sInstance == null) {
                    sInstance = new JDReactSDK();
                }
            }
        }
        return sInstance;
    }

    public String getJDReactFrameworkVersion() {
        return AbstractJDReactInitialHelper.getJDReactFrameworkVersion();
    }

    public void preloadReact(String str) {
        JDReactPreloadInstanceManager.newInstance().preloadReact(str);
    }

    public void setCommonPreloadEnable(boolean z) {
        JDReactCommonPreloadManager.getInstance().setEnable(z);
    }

    public void setPackageManager(ReactPackage reactPackage) {
        AbstractJDReactInitialHelper.setPackageManger(reactPackage);
    }

    public void setReactPackageFactory(ReactPackageFactory reactPackageFactory) {
        JDReactCommonPreloadManager.getInstance().setReactPackageFactory(reactPackageFactory);
    }

    public void startClassPluginActivity(Context context, Class<?> cls, Intent intent, String str) {
        ReactActivityUtilsHelperBase.startClassPluginActivity(context, cls, intent, str);
    }

    public void startJDReactActivity(Context context, Class cls, String str, Bundle bundle) {
        ReactActivityUtilsHelperExt.startJDReactActivity(context, cls, str, bundle);
    }

    public void startJDReactCommonPage(Context context, String str, Bundle bundle) {
        ReactActivityUtilsHelperBase.startJDReactCommonPage(context, str, bundle);
    }
}
