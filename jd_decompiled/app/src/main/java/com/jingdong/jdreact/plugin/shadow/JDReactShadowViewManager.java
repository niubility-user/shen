package com.jingdong.jdreact.plugin.shadow;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jd.dynamic.DYConstants;

/* loaded from: classes14.dex */
public class JDReactShadowViewManager extends ViewGroupManager<ShadowView> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactShadowView";
    }

    @ReactProp(name = DYConstants.DY_SHADOW_COLOR)
    public void setShadowColor(ShadowView shadowView, String str) {
        shadowView.setShadowColor(str);
    }

    @ReactProp(name = "shadowCornerRadius")
    public void setShadowCornerRadius(ShadowView shadowView, int i2) {
        shadowView.setCornerRadius(i2);
    }

    @ReactProp(name = DYConstants.DY_SHADOW_OFFSET)
    public void setShadowOffset(ShadowView shadowView, ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        try {
            shadowView.setOffset(readableMap.hasKey("dx") ? readableMap.getInt("dx") : 0, readableMap.hasKey("dy") ? readableMap.getInt("dy") : 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @ReactProp(name = DYConstants.DY_SHADOW_RADIUS)
    public void setShadowRadius(ShadowView shadowView, int i2) {
        shadowView.setShadowRadius(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ShadowView createViewInstance(ThemedReactContext themedReactContext) {
        return new ShadowView(themedReactContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ShadowView shadowView) {
        super.onAfterUpdateTransaction((JDReactShadowViewManager) shadowView);
        shadowView.updateShow();
    }
}
