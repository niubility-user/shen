package com.facebook.react.fabric.mounting;

import android.view.View;
import androidx.annotation.UiThread;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerRegistry;
import java.util.WeakHashMap;

/* loaded from: classes12.dex */
public final class ContextBasedViewPool {
    private final WeakHashMap<ThemedReactContext, ViewPool> mContextViewPoolHashMap = new WeakHashMap<>();
    private final ViewManagerRegistry mViewManagerRegistry;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContextBasedViewPool(ViewManagerRegistry viewManagerRegistry) {
        this.mViewManagerRegistry = viewManagerRegistry;
    }

    @UiThread
    private ViewPool getViewPool(ThemedReactContext themedReactContext) {
        ViewPool viewPool = this.mContextViewPoolHashMap.get(themedReactContext);
        if (viewPool == null) {
            ViewPool viewPool2 = new ViewPool(this.mViewManagerRegistry);
            this.mContextViewPoolHashMap.put(themedReactContext, viewPool2);
            return viewPool2;
        }
        return viewPool;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @UiThread
    public void createView(ThemedReactContext themedReactContext, String str) {
        UiThreadUtil.assertOnUiThread();
        getViewPool(themedReactContext).createView(str, themedReactContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @UiThread
    public View getOrCreateView(String str, ThemedReactContext themedReactContext) {
        UiThreadUtil.assertOnUiThread();
        return getViewPool(themedReactContext).getOrCreateView(str, themedReactContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @UiThread
    public void returnToPool(ThemedReactContext themedReactContext, String str, View view) {
        UiThreadUtil.assertOnUiThread();
        getViewPool(themedReactContext).returnToPool(str, view);
    }
}
