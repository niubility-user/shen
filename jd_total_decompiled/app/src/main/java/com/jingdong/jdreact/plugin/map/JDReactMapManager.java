package com.jingdong.jdreact.plugin.map;

import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.manto.jsapi.refact.lbs.JsApiLocation;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes13.dex */
public class JDReactMapManager extends ViewGroupManager<JDReactMapView> {
    private static final int ANIMATE_TO_COORDINATE = 2;
    private static final int ANIMATE_TO_LOCATION = 6;
    private static final int ANIMATE_TO_REGION = 1;
    private static final int FIT_TO_COORDINATES = 5;
    private static final int FIT_TO_ELEMENTS = 3;
    private static final int FIT_TO_SUPPLIED_MARKERS = 4;
    private static final int MAP_TYPE_NORMAL = 1;
    private static final int MAP_TYPE_SATELLITE = 2;
    private static final String TAG = "JDReactMapManager";
    private static final int ZOOM_IN = 7;
    private static final int ZOOM_OUT = 8;
    private final Map<String, Integer> MAP_TYPES = MapBuilder.of(ScaleModeConstants.TEXT_SCALE_MODE_STANDARD, 1, "satellite", 2);
    private final ReactApplicationContext appContext;
    private ThemedReactContext mReactContext;

    public JDReactMapManager(ReactApplicationContext reactApplicationContext) {
        this.appContext = reactApplicationContext;
    }

    private void emitMapError(ThemedReactContext themedReactContext, String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("message", str);
        createMap.putString("type", str2);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) themedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onError", createMap);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        Map<String, Integer> of = MapBuilder.of("animateToRegion", 1, "animateToCoordinate", 2, "fitToElements", 3, "fitToSuppliedMarkers", 4, "fitToCoordinates", 5, "animateToLocation", 6);
        of.putAll(MapBuilder.of("zoomIn", 7, "zoomOut", 8));
        return of;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        Map of = MapBuilder.of("onMapReady", MapBuilder.of("registrationName", "onMapReady"), "onPress", MapBuilder.of("registrationName", "onPress"), "onLongPress", MapBuilder.of("registrationName", "onLongPress"), "onMarkerPress", MapBuilder.of("registrationName", "onMarkerPress"), "onMarkerSelect", MapBuilder.of("registrationName", "onMarkerSelect"), "onMarkerDeselect", MapBuilder.of("registrationName", "onMarkerDeselect"), "onCalloutPress", MapBuilder.of("registrationName", "onCalloutPress"));
        of.putAll(MapBuilder.of("onMarkerDragStart", MapBuilder.of("registrationName", "onMarkerDragStart"), "onMarkerDrag", MapBuilder.of("registrationName", "onMarkerDrag"), "onMarkerDragEnd", MapBuilder.of("registrationName", "onMarkerDragEnd"), "onPanDrag", MapBuilder.of("registrationName", "onPanDrag"), JsApiLocation.LOC_CHANGE_EVNET_NAME, MapBuilder.of("registrationName", JsApiLocation.LOC_CHANGE_EVNET_NAME)));
        return of;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMap";
    }

    @ReactProp(name = "userLocationAnnotationTitle")
    public void seLocationTitle(JDReactMapView jDReactMapView, @Nullable String str) {
        jDReactMapView.updateLocationTitle(str);
    }

    @ReactProp(name = "bklbsParam")
    public void setBKLbsParam(JDReactMapView jDReactMapView, ReadableMap readableMap) {
        jDReactMapView.setLbsParam(readableMap);
    }

    @ReactProp(defaultBoolean = false, name = "handlePanDrag")
    public void setHandlePanDrag(JDReactMapView jDReactMapView, boolean z) {
        jDReactMapView.setHandlePanDrag(z);
    }

    @ReactProp(name = "initialRegion")
    public void setInitialRegion(JDReactMapView jDReactMapView, ReadableMap readableMap) {
        jDReactMapView.setInitialRegion(readableMap);
    }

    @ReactProp(name = "lbsParam")
    public void setLbsParam(JDReactMapView jDReactMapView, ReadableMap readableMap) {
        jDReactMapView.setLbsParam(readableMap);
    }

    @ReactProp(name = "legalLabelInsets")
    public void setLegalLabelInsets(JDReactMapView jDReactMapView, int i2) {
        jDReactMapView.getMap().getUiSettings().setLogoPosition(i2);
    }

    @ReactProp(name = "mapType")
    public void setMapType(JDReactMapView jDReactMapView, @Nullable String str) {
        if (this.MAP_TYPES.get(str).intValue() == 2) {
            jDReactMapView.map.setSatelliteEnabled(true);
        } else {
            jDReactMapView.map.setSatelliteEnabled(false);
        }
    }

    @ReactProp(defaultBoolean = true, name = "moveOnMarkerPress")
    public void setMoveOnMarkerPress(JDReactMapView jDReactMapView, boolean z) {
        jDReactMapView.setMoveOnMarkerPress(z);
    }

    @ReactProp(name = "region")
    public void setRegion(JDReactMapView jDReactMapView, ReadableMap readableMap) {
        jDReactMapView.setRegion(readableMap);
    }

    @ReactProp(defaultBoolean = false, name = "satelliteEnabled")
    public void setSatelliteEnabled(JDReactMapView jDReactMapView, boolean z) {
        jDReactMapView.getMap().setSatelliteEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "scrollEnabled")
    public void setScrollEnabled(JDReactMapView jDReactMapView, boolean z) {
        jDReactMapView.getMap().getUiSettings().setScrollGesturesEnabled(z);
        jDReactMapView.setScrollValue(z);
    }

    @ReactProp(defaultBoolean = false, name = "showsTraffic")
    public void setShowTraffic(JDReactMapView jDReactMapView, boolean z) {
        jDReactMapView.map.setTrafficEnabled(z);
    }

    @ReactProp(defaultBoolean = true, name = "showsScale")
    public void setShowsScacle(JDReactMapView jDReactMapView, boolean z) {
        jDReactMapView.getMap().getUiSettings().setZoomGesturesEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "showsUserLocation")
    public void setShowsUserLocation(JDReactMapView jDReactMapView, boolean z) {
        jDReactMapView.setShowLocationEnable(z);
    }

    @ReactProp(name = "userTrackingMode")
    public void setTrackingMode(JDReactMapView jDReactMapView, @Nullable String str) {
        jDReactMapView.setTrackEnable(str != null && str.startsWith("trackingFollow"));
    }

    @ReactProp(name = "zoomLevel")
    public void setZoom(JDReactMapView jDReactMapView, int i2) {
        jDReactMapView.map.moveCamera(CameraUpdateFactory.zoomTo(i2));
    }

    @ReactProp(defaultBoolean = false, name = "zoomEnabled")
    public void setZoomGesturesEnabled(JDReactMapView jDReactMapView, boolean z) {
        jDReactMapView.getMap().getUiSettings().setZoomGesturesEnabled(z);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(JDReactMapView jDReactMapView, View view, int i2) {
        jDReactMapView.addFeature(view, i2);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactMapView createViewInstance(ThemedReactContext themedReactContext) {
        JDReactMapView jDReactMapView = new JDReactMapView(themedReactContext);
        this.mReactContext = themedReactContext;
        jDReactMapView.getMap().setSatelliteEnabled(false);
        return jDReactMapView;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(JDReactMapView jDReactMapView, int i2) {
        return jDReactMapView.getFeatureAt(i2);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(JDReactMapView jDReactMapView) {
        return jDReactMapView.getFeatureCount();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(JDReactMapView jDReactMapView) {
        jDReactMapView.doDestroy();
        super.onDropViewInstance((JDReactMapManager) jDReactMapView);
        ((ThemedReactContext) jDReactMapView.getContext()).removeLifecycleEventListener(jDReactMapView);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(JDReactMapView jDReactMapView, int i2, @Nullable ReadableArray readableArray) {
        switch (i2) {
            case 1:
                ReadableMap map = readableArray.getMap(0);
                Integer valueOf = Integer.valueOf(readableArray.getInt(1));
                Double valueOf2 = Double.valueOf(map.getDouble(PdLVBody.LONGITUDE));
                Double valueOf3 = Double.valueOf(map.getDouble(PdLVBody.LATITUDE));
                Double valueOf4 = Double.valueOf(map.getDouble("longitudeDelta"));
                Double valueOf5 = Double.valueOf(map.getDouble("latitudeDelta"));
                jDReactMapView.animateToRegion(new LatLngBounds(new LatLng(valueOf3.doubleValue() - (valueOf5.doubleValue() / 2.0d), valueOf2.doubleValue() - (valueOf4.doubleValue() / 2.0d)), new LatLng(valueOf3.doubleValue() + (valueOf5.doubleValue() / 2.0d), valueOf2.doubleValue() + (valueOf4.doubleValue() / 2.0d))), valueOf.intValue());
                return;
            case 2:
                ReadableMap map2 = readableArray.getMap(0);
                Integer valueOf6 = Integer.valueOf(readableArray.getInt(1));
                jDReactMapView.animateToCoordinate(new LatLng(Double.valueOf(map2.getDouble(PdLVBody.LATITUDE)).doubleValue(), Double.valueOf(map2.getDouble(PdLVBody.LONGITUDE)).doubleValue()), valueOf6.intValue());
                return;
            case 3:
                jDReactMapView.fitToElements(readableArray.getBoolean(0));
                return;
            case 4:
                jDReactMapView.fitToSuppliedMarkers(readableArray.getArray(0), readableArray.getBoolean(1));
                return;
            case 5:
                jDReactMapView.fitToCoordinates(readableArray.getArray(0), readableArray.getMap(1), readableArray.getBoolean(2));
                return;
            case 6:
                jDReactMapView.moveToLocation();
                return;
            case 7:
                jDReactMapView.setZoomIn();
                return;
            case 8:
                jDReactMapView.setZoomOut();
                return;
            default:
                return;
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(JDReactMapView jDReactMapView, int i2) {
        jDReactMapView.removeFeatureAt(i2);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void updateExtraData(JDReactMapView jDReactMapView, Object obj) {
        jDReactMapView.updateExtraData(obj);
    }
}
