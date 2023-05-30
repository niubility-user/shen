package com.jingdong.jdreact.plugin.map;

import android.widget.FrameLayout;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* loaded from: classes13.dex */
public class JDReactMapManagerWith18 extends ViewGroupManager<FrameLayout> {
    private void emitMapError(ThemedReactContext themedReactContext, String str, String str2) {
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMap";
    }

    @ReactProp(name = "userLocationAnnotationTitle")
    public void seLocationTitle(FrameLayout frameLayout, @Nullable String str) {
    }

    @ReactProp(defaultBoolean = false, name = "handlePanDrag")
    public void setHandlePanDrag(FrameLayout frameLayout, boolean z) {
    }

    @ReactProp(name = "initialRegion")
    public void setInitialRegion(FrameLayout frameLayout, ReadableMap readableMap) {
    }

    @ReactProp(name = "lbsParam")
    public void setLbsParam(FrameLayout frameLayout, ReadableMap readableMap) {
    }

    @ReactProp(name = "legalLabelInsets")
    public void setLegalLabelInsets(FrameLayout frameLayout, int i2) {
    }

    @ReactProp(name = "mapType")
    public void setMapType(FrameLayout frameLayout, @Nullable String str) {
    }

    @ReactProp(defaultBoolean = true, name = "moveOnMarkerPress")
    public void setMoveOnMarkerPress(FrameLayout frameLayout, boolean z) {
    }

    @ReactProp(name = "region")
    public void setRegion(FrameLayout frameLayout, ReadableMap readableMap) {
    }

    @ReactProp(defaultBoolean = false, name = "satelliteEnabled")
    public void setSatelliteEnabled(FrameLayout frameLayout, boolean z) {
    }

    @ReactProp(defaultBoolean = false, name = "scrollEnabled")
    public void setScrollEnabled(FrameLayout frameLayout, boolean z) {
    }

    @ReactProp(defaultBoolean = false, name = "showsTraffic")
    public void setShowTraffic(FrameLayout frameLayout, boolean z) {
    }

    @ReactProp(defaultBoolean = true, name = "showsScale")
    public void setShowsScacle(FrameLayout frameLayout, boolean z) {
    }

    @ReactProp(defaultBoolean = false, name = "showsUserLocation")
    public void setShowsUserLocation(FrameLayout frameLayout, boolean z) {
    }

    @ReactProp(name = "userTrackingMode")
    public void setTrackingMode(FrameLayout frameLayout, @Nullable String str) {
    }

    @ReactProp(name = "zoomLevel")
    public void setZoom(FrameLayout frameLayout, int i2) {
    }

    @ReactProp(defaultBoolean = false, name = "zoomEnabled")
    public void setZoomGesturesEnabled(FrameLayout frameLayout, boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public FrameLayout createViewInstance(ThemedReactContext themedReactContext) {
        return new FrameLayout(themedReactContext);
    }
}
