package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes13.dex */
public class JDReactMapUrlTileManager extends ViewGroupManager<JDReactMapUrlTile> {
    private Context context;

    public JDReactMapUrlTileManager(ReactApplicationContext reactApplicationContext) {
        this.context = reactApplicationContext;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMapUrlTile";
    }

    @ReactProp(name = "urlTemplate")
    public void setUrlTemplate(JDReactMapUrlTile jDReactMapUrlTile, String str) {
        jDReactMapUrlTile.setUrlTemplate(str);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactMapUrlTile createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactMapUrlTile(themedReactContext);
    }
}
