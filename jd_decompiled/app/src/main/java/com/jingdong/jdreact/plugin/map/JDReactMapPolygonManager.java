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
public class JDReactMapPolygonManager extends ViewGroupManager<JDReactMapPolygon> {
    private Context context;

    public JDReactMapPolygonManager(ReactApplicationContext reactApplicationContext) {
        this.context = reactApplicationContext;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("onPress", MapBuilder.of("registrationName", "onPress"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMapPolygon";
    }

    @ReactProp(name = "coordinates")
    public void setCoordinate(JDReactMapPolygon jDReactMapPolygon, ReadableArray readableArray) {
        jDReactMapPolygon.setCoordinates(readableArray);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "fillColor")
    public void setFillColor(JDReactMapPolygon jDReactMapPolygon, int i2) {
        jDReactMapPolygon.setFillColor(i2);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "strokeColor")
    public void setStrokeColor(JDReactMapPolygon jDReactMapPolygon, int i2) {
        jDReactMapPolygon.setStrokeColor(i2);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(JDReactMapPolygon jDReactMapPolygon, float f2) {
        jDReactMapPolygon.setStrokeWidth(JDReactHelper.newInstance().getDensity(this.context) * f2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactMapPolygon createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactMapPolygon(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager
    @ReactProp(defaultFloat = 1.0f, name = "zIndex")
    public void setZIndex(JDReactMapPolygon jDReactMapPolygon, float f2) {
        jDReactMapPolygon.setZIndex((int) f2);
    }
}
