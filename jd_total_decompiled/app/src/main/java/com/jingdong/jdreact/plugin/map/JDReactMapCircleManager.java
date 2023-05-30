package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import androidx.core.internal.view.SupportMenu;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes13.dex */
public class JDReactMapCircleManager extends ViewGroupManager<JDReactMapCircle> {
    private Context context;

    public JDReactMapCircleManager(ReactApplicationContext reactApplicationContext) {
        this.context = reactApplicationContext;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMapCircle";
    }

    @ReactProp(name = DYConstants.DY_CENTER)
    public void setCenter(JDReactMapCircle jDReactMapCircle, ReadableMap readableMap) {
        jDReactMapCircle.setCenter(new LatLng(readableMap.getDouble(PdLVBody.LATITUDE), readableMap.getDouble(PdLVBody.LONGITUDE)));
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "fillColor")
    public void setFillColor(JDReactMapCircle jDReactMapCircle, int i2) {
        jDReactMapCircle.setFillColor(i2);
    }

    @ReactProp(defaultDouble = 0.0d, name = JDPureVideoManager.SourceKey.RADIUS)
    public void setRadius(JDReactMapCircle jDReactMapCircle, double d) {
        jDReactMapCircle.setRadius(d);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "strokeColor")
    public void setStrokeColor(JDReactMapCircle jDReactMapCircle, int i2) {
        jDReactMapCircle.setStrokeColor(i2);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(JDReactMapCircle jDReactMapCircle, float f2) {
        jDReactMapCircle.setStrokeWidth(JDReactHelper.newInstance().getDensity(this.context) * f2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactMapCircle createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactMapCircle(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager
    @ReactProp(defaultFloat = 1.0f, name = "zIndex")
    public void setZIndex(JDReactMapCircle jDReactMapCircle, float f2) {
        jDReactMapCircle.setZIndex((int) f2);
    }
}
