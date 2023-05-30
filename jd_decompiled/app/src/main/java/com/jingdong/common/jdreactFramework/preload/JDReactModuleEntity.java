package com.jingdong.common.jdreactFramework.preload;

import android.os.Bundle;

/* loaded from: classes5.dex */
public class JDReactModuleEntity {
    private String mBundleName;
    private Bundle mLaunchOptions;
    private String mModuleName;
    private boolean mTransparentEnable = false;

    public JDReactModuleEntity(String str, String str2) {
        this.mBundleName = str;
        this.mModuleName = str2;
    }

    public boolean getTransparentEnable() {
        return this.mTransparentEnable;
    }

    public String getmBundleName() {
        return this.mBundleName;
    }

    public Bundle getmLaunchOptions() {
        return this.mLaunchOptions;
    }

    public String getmModuleName() {
        return this.mModuleName;
    }

    public void setTransparentEnable(boolean z) {
        this.mTransparentEnable = z;
    }

    public JDReactModuleEntity(String str, String str2, Bundle bundle) {
        this.mBundleName = str;
        this.mModuleName = str2;
        this.mLaunchOptions = bundle;
    }
}
