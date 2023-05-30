package com.jd.libs.hybrid.engine;

import com.jd.libs.hybrid.base.engine.BaseInfoEngine;
import com.jd.libs.hybrid.base.engine.ConfigEngine;
import com.jd.libs.hybrid.base.engine.CookieEngine;
import com.jd.libs.hybrid.base.engine.DownloadEngine;
import com.jd.libs.hybrid.base.engine.HybridClient;
import com.jd.libs.hybrid.base.engine.RequestPreloadEngine;
import com.jd.libs.hybrid.base.engine.SettingsEngine;

/* loaded from: classes16.dex */
public class HybridClientImpl implements HybridClient {
    @Override // com.jd.libs.hybrid.base.engine.HybridClient
    public BaseInfoEngine getBaseInfoEngine() {
        return new BaseInfoEngineImpl();
    }

    @Override // com.jd.libs.hybrid.base.engine.HybridClient
    public ConfigEngine getConfigEngine() {
        return new ConfigEngineImpl();
    }

    @Override // com.jd.libs.hybrid.base.engine.HybridClient
    public CookieEngine getCookieEngine() {
        return new CookieEngineImpl();
    }

    @Override // com.jd.libs.hybrid.base.engine.HybridClient
    public DownloadEngine getDownloadEngine() {
        return new DownloadEngineImpl();
    }

    @Override // com.jd.libs.hybrid.base.engine.HybridClient
    public RequestPreloadEngine getRequestPreloadEngine() {
        return new RequestPreloadEngineImpl();
    }

    @Override // com.jd.libs.hybrid.base.engine.HybridClient
    public SettingsEngine getSettingsEngine() {
        return new SettingsEngineImpl();
    }
}
