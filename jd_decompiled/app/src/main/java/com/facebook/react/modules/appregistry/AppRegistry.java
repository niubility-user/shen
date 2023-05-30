package com.facebook.react.modules.appregistry;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.WritableMap;

/* loaded from: classes12.dex */
public interface AppRegistry extends JavaScriptModule {
    void runApplication(String str, WritableMap writableMap);

    void startHeadlessTask(int i2, String str, WritableMap writableMap);

    void unmountApplicationComponentAtRootTag(int i2);
}
