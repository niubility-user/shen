package com.jd.libs.hybrid.engine;

import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.engine.SettingsEngine;

/* loaded from: classes16.dex */
public class SettingsEngineImpl implements SettingsEngine {
    @Override // com.jd.libs.hybrid.base.engine.SettingsEngine
    public String getSetting(String str) {
        return HybridSDK.getSetting(str);
    }
}
