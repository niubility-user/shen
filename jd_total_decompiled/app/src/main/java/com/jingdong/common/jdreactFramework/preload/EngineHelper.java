package com.jingdong.common.jdreactFramework.preload;

import android.app.Activity;
import com.facebook.react.ReactInstanceManager;

/* loaded from: classes5.dex */
public interface EngineHelper {
    void destroy();

    ReactInstanceManager getEngine();

    String getSourceName();

    void load(String str);

    void resume(Activity activity);
}
