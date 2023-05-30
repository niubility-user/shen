package com.facebook.react.bridge;

import com.facebook.react.uimanager.common.MeasureSpecProvider;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface UIManager extends JSIModule, PerformanceCounter {
    <T extends SizeMonitoringFrameLayout & MeasureSpecProvider> int addRootView(T t, WritableMap writableMap, @Nullable String str);

    void clearJSResponder();

    void dispatchCommand(int i2, int i3, @Nullable ReadableArray readableArray);

    void removeRootView(int i2);

    void setJSResponder(int i2, boolean z);

    void updateRootLayoutSpecs(int i2, int i3, int i4);
}
