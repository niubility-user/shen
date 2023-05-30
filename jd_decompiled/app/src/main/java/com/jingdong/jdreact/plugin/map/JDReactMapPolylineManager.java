package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import androidx.core.internal.view.SupportMenu;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes13.dex */
public class JDReactMapPolylineManager extends ViewGroupManager<JDReactMapPolyline> {
    private Context context;

    public JDReactMapPolylineManager(ReactApplicationContext reactApplicationContext) {
        this.context = reactApplicationContext;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("onPress", MapBuilder.of("registrationName", "onPress"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMapPolyline";
    }

    @ReactProp(name = "coordinates")
    public void setCoordinate(JDReactMapPolyline jDReactMapPolyline, ReadableArray readableArray) {
        jDReactMapPolyline.setCoordinates(readableArray);
    }

    @ReactProp(defaultBoolean = false, name = "dottedLine")
    public void setDottedLine(JDReactMapPolyline jDReactMapPolyline, Boolean bool) {
        jDReactMapPolyline.setDottedLine(bool.booleanValue());
    }

    @ReactProp(defaultBoolean = false, name = "geodesic")
    public void setGeodesic(JDReactMapPolyline jDReactMapPolyline, boolean z) {
        jDReactMapPolyline.setGeodesic(z);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "strokeColor")
    public void setStrokeColor(JDReactMapPolyline jDReactMapPolyline, int i2) {
        jDReactMapPolyline.setColor(i2);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(JDReactMapPolyline jDReactMapPolyline, float f2) {
        jDReactMapPolyline.setWidth(JDReactHelper.newInstance().getDensity(this.context) * f2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactMapPolyline createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactMapPolyline(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager
    @ReactProp(defaultFloat = 1.0f, name = "zIndex")
    public void setZIndex(JDReactMapPolyline jDReactMapPolyline, float f2) {
        jDReactMapPolyline.setZIndex((int) f2);
    }
}
