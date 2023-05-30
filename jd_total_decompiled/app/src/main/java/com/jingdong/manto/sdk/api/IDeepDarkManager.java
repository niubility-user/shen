package com.jingdong.manto.sdk.api;

import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IDeepDarkManager extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface IDeepDarkLisener extends IMantoSdkBase {
        void deepDarkModeChanged(int i2);
    }

    int getCurreentDeepDarkMode();

    void registerDeepDarkListener(IDeepDarkLisener iDeepDarkLisener);
}
