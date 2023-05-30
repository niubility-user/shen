package com.jd.libs.hybrid.base.engine;

import androidx.annotation.Keep;

@Keep
/* loaded from: classes16.dex */
public interface HybridClient {
    BaseInfoEngine getBaseInfoEngine();

    ConfigEngine getConfigEngine();

    CookieEngine getCookieEngine();

    DownloadEngine getDownloadEngine();

    RequestPreloadEngine getRequestPreloadEngine();

    SettingsEngine getSettingsEngine();
}
