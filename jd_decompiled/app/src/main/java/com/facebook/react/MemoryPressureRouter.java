package com.facebook.react;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.react.bridge.MemoryPressureListener;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class MemoryPressureRouter implements ComponentCallbacks2 {
    private final Set<MemoryPressureListener> mListeners = Collections.synchronizedSet(new LinkedHashSet());

    /* JADX INFO: Access modifiers changed from: package-private */
    public MemoryPressureRouter(Context context) {
        context.getApplicationContext().registerComponentCallbacks(this);
    }

    private void dispatchMemoryPressure(int i2) {
        Set<MemoryPressureListener> set = this.mListeners;
        for (MemoryPressureListener memoryPressureListener : (MemoryPressureListener[]) set.toArray(new MemoryPressureListener[set.size()])) {
            memoryPressureListener.handleMemoryPressure(i2);
        }
    }

    public void addMemoryPressureListener(MemoryPressureListener memoryPressureListener) {
        this.mListeners.add(memoryPressureListener);
    }

    public void destroy(Context context) {
        context.getApplicationContext().unregisterComponentCallbacks(this);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i2) {
        dispatchMemoryPressure(i2);
    }

    public void removeMemoryPressureListener(MemoryPressureListener memoryPressureListener) {
        this.mListeners.remove(memoryPressureListener);
    }
}
