package com.facebook.react.fabric.mounting;

import android.view.View;
import androidx.annotation.UiThread;
import com.facebook.react.common.ClearableSynchronizedPool;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerRegistry;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public final class ViewPool {
    private static final int POOL_SIZE = 512;
    private final ViewManagerRegistry mViewManagerRegistry;
    private final Map<String, ClearableSynchronizedPool<View>> mViewPool = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPool(ViewManagerRegistry viewManagerRegistry) {
        this.mViewManagerRegistry = viewManagerRegistry;
    }

    private ClearableSynchronizedPool<View> getViewPoolForComponent(String str) {
        ClearableSynchronizedPool<View> clearableSynchronizedPool = this.mViewPool.get(str);
        if (clearableSynchronizedPool == null) {
            ClearableSynchronizedPool<View> clearableSynchronizedPool2 = new ClearableSynchronizedPool<>(512);
            this.mViewPool.put(str, clearableSynchronizedPool2);
            return clearableSynchronizedPool2;
        }
        return clearableSynchronizedPool;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @UiThread
    public void createView(String str, ThemedReactContext themedReactContext) {
        getViewPoolForComponent(str).release(this.mViewManagerRegistry.get(str).createView(themedReactContext, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @UiThread
    public View getOrCreateView(String str, ThemedReactContext themedReactContext) {
        ClearableSynchronizedPool<View> viewPoolForComponent = getViewPoolForComponent(str);
        View acquire = viewPoolForComponent.acquire();
        if (acquire == null) {
            createView(str, themedReactContext);
            return viewPoolForComponent.acquire();
        }
        return acquire;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @UiThread
    public void returnToPool(String str, View view) {
        ClearableSynchronizedPool<View> clearableSynchronizedPool = this.mViewPool.get(str);
        if (clearableSynchronizedPool != null) {
            clearableSynchronizedPool.release(view);
        }
    }
}
