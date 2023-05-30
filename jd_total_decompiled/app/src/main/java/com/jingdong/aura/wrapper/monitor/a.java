package com.jingdong.aura.wrapper.monitor;

import com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener;

/* loaded from: classes4.dex */
public class a implements AuraMonitorConfigListener {
    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean bundleLocationNullCheck() {
        return false;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean bundleSoInfoCheck() {
        return false;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean classNotFoundRunningTaskCheck() {
        return true;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public int dynamicBundleInfoListAbTest() {
        return 0;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean forceCheckBundleSoInfo() {
        return false;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean isMonitorProvidedInstallFail() {
        return false;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean providedBundleActivityResultCheck() {
        return false;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean splitApkConfig() {
        return false;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean startBundleByBackUp() {
        return true;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean updateMetaConfig() {
        return false;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener
    public boolean verityBundleZipSign() {
        return false;
    }
}
