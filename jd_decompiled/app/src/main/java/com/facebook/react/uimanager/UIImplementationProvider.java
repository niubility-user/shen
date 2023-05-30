package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.List;

@Deprecated
/* loaded from: classes12.dex */
public class UIImplementationProvider {
    public UIImplementation createUIImplementation(ReactApplicationContext reactApplicationContext, UIManagerModule.ViewManagerResolver viewManagerResolver, EventDispatcher eventDispatcher, int i2) {
        return new UIImplementation(reactApplicationContext, viewManagerResolver, eventDispatcher, i2);
    }

    public UIImplementation createUIImplementation(ReactApplicationContext reactApplicationContext, List<ViewManager> list, EventDispatcher eventDispatcher, int i2) {
        return new UIImplementation(reactApplicationContext, list, eventDispatcher, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UIImplementation createUIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int i2) {
        return new UIImplementation(reactApplicationContext, viewManagerRegistry, eventDispatcher, i2);
    }
}
