package com.jingdong.aura.sdk.update.config.impl;

import com.jingdong.aura.sdk.update.config.IMobileConfig;

/* loaded from: classes4.dex */
public class DefaultMobileConfig implements IMobileConfig {
    @Override // com.jingdong.aura.sdk.update.config.IMobileConfig
    public boolean isCloseUpdate() {
        return false;
    }

    @Override // com.jingdong.aura.sdk.update.config.IMobileConfig
    public boolean isWifiAutoDownload() {
        return true;
    }
}
