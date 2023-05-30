package com.jingdong.jdreact.plugin.map;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes13.dex */
public class JDReactMapCalloutManager extends ViewGroupManager<JDReactMapCallout> {
    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("onPress", MapBuilder.of("registrationName", "onPress"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMapCallout";
    }

    @ReactProp(defaultBoolean = false, name = "tooltip")
    public void setTooltip(JDReactMapCallout jDReactMapCallout, boolean z) {
        jDReactMapCallout.setTooltip(z);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactMapCallout createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactMapCallout(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void updateExtraData(JDReactMapCallout jDReactMapCallout, Object obj) {
        Map map = (Map) obj;
        float floatValue = ((Float) map.get("width")).floatValue();
        float floatValue2 = ((Float) map.get("height")).floatValue();
        jDReactMapCallout.width = (int) floatValue;
        jDReactMapCallout.height = (int) floatValue2;
    }
}
