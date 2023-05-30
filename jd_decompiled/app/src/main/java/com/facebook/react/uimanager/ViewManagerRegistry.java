package com.facebook.react.uimanager;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public final class ViewManagerRegistry {
    @Nullable
    private final UIManagerModule.ViewManagerResolver mViewManagerResolver;
    private final Map<String, ViewManager> mViewManagers;

    public ViewManagerRegistry(UIManagerModule.ViewManagerResolver viewManagerResolver) {
        this.mViewManagers = MapBuilder.newHashMap();
        this.mViewManagerResolver = viewManagerResolver;
    }

    public ViewManager get(String str) {
        ViewManager viewManager;
        ViewManager viewManager2 = this.mViewManagers.get(str);
        if (viewManager2 != null) {
            return viewManager2;
        }
        UIManagerModule.ViewManagerResolver viewManagerResolver = this.mViewManagerResolver;
        if (viewManagerResolver != null && (viewManager = viewManagerResolver.getViewManager(str)) != null) {
            this.mViewManagers.put(str, viewManager);
            return viewManager;
        }
        throw new IllegalViewOperationException("No ViewManager defined for class " + str);
    }

    public ViewManagerRegistry(List<ViewManager> list) {
        HashMap newHashMap = MapBuilder.newHashMap();
        for (ViewManager viewManager : list) {
            newHashMap.put(viewManager.getName(), viewManager);
        }
        this.mViewManagers = newHashMap;
        this.mViewManagerResolver = null;
    }

    public ViewManagerRegistry(Map<String, ViewManager> map) {
        this.mViewManagers = map == null ? MapBuilder.newHashMap() : map;
        this.mViewManagerResolver = null;
    }
}
