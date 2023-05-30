package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.tencent.map.lib.models.IndoorCellInfo;
import com.tencent.tencentmap.mapsdk.maps.CustomRender;
import com.tencent.tencentmap.mapsdk.maps.LocationSource;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapComponent;
import com.tencent.tencentmap.mapsdk.maps.model.Arc;
import com.tencent.tencentmap.mapsdk.maps.model.ArcOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.CustomLayer;
import com.tencent.tencentmap.mapsdk.maps.model.CustomLayerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Language;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.MapFontSize;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.MyLocationStyle;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider;
import com.tencent.tencentmap.mapsdk.maps.model.Polygon;
import com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import com.tencent.tencentmap.mapsdk.maps.model.RestrictBoundsFitMode;
import com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class m1 implements u1, TencentMap {
    @Override // com.tencent.mapsdk.internal.u1
    public void a() {
        qa.h(pa.P);
        j();
        qa.i(pa.P);
    }

    public void a(int i2) {
        qa.b(pa.W, "setIndoorConfigType", Integer.valueOf(i2));
    }

    @Override // com.tencent.mapsdk.internal.u1
    public void a(Bundle bundle) {
        qa.h(pa.M);
        b(bundle);
        qa.i(pa.M);
    }

    public abstract void a(boolean z);

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Arc addArc(ArcOptions arcOptions) {
        qa.b(pa.W, "addArc", (Object) (arcOptions != null ? arcOptions.toString() : ""));
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Circle addCircle(CircleOptions circleOptions) {
        qa.b(pa.W, "addCircle", (Object) (circleOptions != null ? circleOptions.toString() : ""));
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public CustomLayer addCustomLayer(CustomLayerOptions customLayerOptions) {
        qa.b(pa.W, "addCustomLayer", (Object) qa.a(getMapContext().getContext(), customLayerOptions != null ? customLayerOptions.getLayerId() : ""));
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Marker addMarker(MarkerOptions markerOptions) {
        qa.b(pa.W, "addMarker", (Object) (markerOptions != null ? markerOptions.toString() : ""));
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void addOnMapLoadedCallback(TencentMap.OnMapLoadedCallback onMapLoadedCallback) {
        qa.b(pa.W, "addOnMapLoadedCallback", Boolean.valueOf(onMapLoadedCallback != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Polygon addPolygon(PolygonOptions polygonOptions) {
        qa.b(pa.W, "addPolygon", (Object) (polygonOptions != null ? polygonOptions.toString() : ""));
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Polyline addPolyline(PolylineOptions polylineOptions) {
        qa.b(pa.W, "addPolyline", (Object) (polylineOptions != null ? polylineOptions.toString() : ""));
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void addTencentMapGestureListener(TencentMapGestureListener tencentMapGestureListener) {
        qa.b(pa.W, "addTencentMapGestureListener", Boolean.valueOf(tencentMapGestureListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        qa.b(pa.W, "addTileOverlay", (Object) (tileOverlayOptions != null ? tileOverlayOptions.toString() : ""));
        return null;
    }

    @Override // com.tencent.mapsdk.internal.u1
    public void b() {
        qa.h(pa.S);
        h();
        qa.i(pa.S);
    }

    public void b(Bundle bundle) {
    }

    @Override // com.tencent.mapsdk.internal.u1
    public void c() {
        qa.h(pa.Q);
        i();
        qa.i(pa.Q);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void clear() {
        qa.b(pa.W, "clear", Integer.valueOf(qa.i(pa.W, "clear")));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void clearAllOverlays() {
        qa.b(pa.W, "clearAllOverlays", Integer.valueOf(qa.i(pa.W, "clearAllOverlays")));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void clearCache() {
        qa.b(pa.W, "clearCache", Integer.valueOf(qa.i(pa.W, "clearCache")));
    }

    @Override // com.tencent.mapsdk.internal.u1
    public void d() {
        qa.h(pa.N);
        l();
        qa.i(pa.N);
    }

    @Override // com.tencent.mapsdk.internal.u1
    public void e() {
        qa.h(pa.O);
        k();
        qa.i(pa.O);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void enableAutoMaxSkew(boolean z) {
        qa.b(pa.W, "enableAutoMaxSkew", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void enableMultipleInfowindow(boolean z) {
        qa.b(pa.W, "enableMultipleInfowindow", Boolean.valueOf(z));
    }

    @Override // com.tencent.mapsdk.internal.u1
    public void f() {
        qa.h(pa.R);
        m();
        qa.i(pa.R);
    }

    public abstract boolean g();

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public <T extends TencentMapComponent.Component> T getMapComponent(Class<T> cls) {
        return (T) getMapContext().getMapComponent(cls);
    }

    public void h() {
    }

    public abstract void i();

    public abstract void j();

    public abstract void k();

    public abstract void l();

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void loadKMLFile(String str) {
        qa.b(pa.W, "loadKMLFile", (Object) str);
    }

    public abstract void m();

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void removeOnMapLoadedCallback(TencentMap.OnMapLoadedCallback onMapLoadedCallback) {
        qa.b(pa.W, "removeOnMapLoadedCallback", Boolean.valueOf(onMapLoadedCallback != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void removeTencentMapGestureListener(TencentMapGestureListener tencentMapGestureListener) {
        qa.b(pa.W, "removeTencentMapGestureListener", Boolean.valueOf(tencentMapGestureListener != null));
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public void resetIndoorCellInfo() {
        qa.b(pa.W, "resetIndoorParkSpaceColors", (Object) "");
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setBlockRouteEnabled(boolean z) {
        qa.b(pa.W, "setBlockRouteEnabled", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setBuilding3dEffectEnable(boolean z) {
        qa.b(pa.W, "setBuilding3dEffectEnable", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setBuildingBlackList(List<LatLngBounds> list) {
        qa.b(pa.W, "setBuildingBlackList", (Object) (list != null ? list.toString() : ""));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setBuildingEnable(boolean z) {
        qa.b(pa.W, "setBuildingEnable", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setCameraCenterProportion(float f2, float f3) {
        qa.b(pa.W, "setCameraCenterProportion", (Object) (f2 + "#" + f3));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setCameraCenterProportion(float f2, float f3, boolean z) {
        qa.b(pa.W, "setCameraCenterProportion", (Object) (f2 + "#" + f3 + "#" + z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setCustomRender(CustomRender customRender) {
        qa.b(pa.W, "setCustomRender", customRender);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setDrawPillarWith2DStyle(boolean z) {
        qa.b(pa.W, "setDrawPillarWith2DStyle", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setForeignLanguage(Language language) {
        qa.b(pa.W, "setForeignLanguage", language);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setHandDrawMapEnable(boolean z) {
        qa.b(pa.W, "setHandDrawMapEnable", Boolean.valueOf(z));
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorCellInfo(List<IndoorCellInfo> list) {
        qa.b(pa.W, "setIndoorParkSpaceColors", (Object) (list != null ? list.toString() : ""));
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorEnabled(boolean z) {
        qa.b(pa.W, "setIndoorEnabled", Boolean.valueOf(z));
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorFloor(int i2) {
        qa.b(pa.W, "setIndoorFloor", Integer.valueOf(i2));
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorFloor(String str, String str2) {
        qa.b(pa.W, "setIndoorFloor", (Object) (str + "#" + str2));
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorMaskColor(int i2) {
        qa.b(pa.W, "setIndoorMaskColor", Integer.valueOf(i2));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setInfoWindowAdapter(TencentMap.InfoWindowAdapter infoWindowAdapter) {
        qa.b(pa.W, "setInfoWindowAdapter", Boolean.valueOf(infoWindowAdapter != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setLocationCompassHidden(boolean z) {
        qa.b(pa.W, "setLocationCompassEnabled", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setLocationNavigationGravityLineHidden(boolean z) {
        qa.b(pa.W, "setLocationNavigationGravityLineEnabled", Boolean.valueOf(z));
    }

    @Override // com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setLocationSource(LocationSource locationSource) {
        if (locationSource != null) {
            qa.b(pa.W, "setLocationSource", (Object) locationSource.getClass().getSimpleName());
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapCenterAndScale(float f2, float f3, float f4) {
        qa.b(pa.W, "setMapCenterAndScale", (Object) (f2 + "#" + f3 + "#" + f4));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapFontSize(MapFontSize mapFontSize) {
        qa.b(pa.W, "setMapFontSize", mapFontSize);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapFrameRate(float f2) {
        qa.b(pa.W, "setMapFrameRate", Float.valueOf(f2));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapStyle(int i2) {
        qa.b(pa.W, "setMapStyle", Integer.valueOf(i2));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapType(int i2) {
        qa.b(pa.W, "setMapType", Integer.valueOf(i2));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMaxZoomLevel(int i2) {
        qa.b(pa.W, "setMaxZoomLevel", Integer.valueOf(i2));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMinZoomLevel(int i2) {
        qa.b(pa.W, "setMinZoomLevel", Integer.valueOf(i2));
    }

    @Override // com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setMyLocationClickListener(TencentMap.OnMyLocationClickListener onMyLocationClickListener) {
        qa.b(pa.W, "setMyLocationClickListener", Boolean.valueOf(onMyLocationClickListener != null));
    }

    @Override // com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setMyLocationEnabled(boolean z) {
        qa.b(pa.W, "setMyLocationEnabled", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap, com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        qa.b(pa.W, "setMyLocationStyle", (Object) (myLocationStyle != null ? myLocationStyle.toString() : ""));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnCameraChangeListener(TencentMap.OnCameraChangeListener onCameraChangeListener) {
        qa.b(pa.W, "setOnCameraChangeListener", Boolean.valueOf(onCameraChangeListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnCompassClickedListener(TencentMap.OnCompassClickedListener onCompassClickedListener) {
        qa.b(pa.W, "setOnCompassClickedListener", Boolean.valueOf(onCompassClickedListener != null));
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public void setOnIndoorStateChangeListener(TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        qa.b(pa.W, "setOnIndoorStateChangeListener", Boolean.valueOf(onIndoorStateChangeListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnInfoWindowClickListener(TencentMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        qa.b(pa.W, "OnInfoWindowClickListener", Boolean.valueOf(onInfoWindowClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMapClickListener(TencentMap.OnMapClickListener onMapClickListener) {
        qa.b(pa.W, "setOnMapClickListener", Boolean.valueOf(onMapClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMapLoadedCallback(TencentMap.OnMapLoadedCallback onMapLoadedCallback) {
        addOnMapLoadedCallback(onMapLoadedCallback);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMapLongClickListener(TencentMap.OnMapLongClickListener onMapLongClickListener) {
        qa.b(pa.W, "setOnMapLongClickListener", Boolean.valueOf(onMapLongClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMapPoiClickListener(TencentMap.OnMapPoiClickListener onMapPoiClickListener) {
        qa.b(pa.W, "setOnMapPoiClickListener", Boolean.valueOf(onMapPoiClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMarkerClickListener(TencentMap.OnMarkerClickListener onMarkerClickListener) {
        qa.b(pa.W, "setOnMarkerClickListener", Boolean.valueOf(onMarkerClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMarkerDragListener(TencentMap.OnMarkerDragListener onMarkerDragListener) {
        qa.b(pa.W, "OnMarkerDragListener", Boolean.valueOf(onMarkerDragListener != null));
    }

    @Override // com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setOnMyLocationChangeListener(TencentMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        qa.b(pa.W, "setOnMyLocationChangeListener", Boolean.valueOf(onMyLocationChangeListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnPolygonClickListener(TencentMap.OnPolygonClickListener onPolygonClickListener) {
        qa.b(pa.W, "setOnPolygonClickListener", Boolean.valueOf(onPolygonClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnPolylineClickListener(TencentMap.OnPolylineClickListener onPolylineClickListener) {
        qa.b(pa.W, "setOnPolylineClickListener", Boolean.valueOf(onPolylineClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnScaleViewChangedListener(TencentMap.OnScaleViewChangedListener onScaleViewChangedListener) {
        qa.b(pa.W, "setOnScaleViewChangedListener", Boolean.valueOf(onScaleViewChangedListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnTapMapViewInfoWindowHidden(boolean z) {
        qa.b(pa.W, "setOnTapMapViewInfoWindowHidden", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnTrafficEventClickListener(TencentMap.OnTrafficEventClickListener onTrafficEventClickListener) {
        qa.b(pa.W, "setOnTrafficEventClickListener", Boolean.valueOf(onTrafficEventClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnVectorOverlayClickListener(TencentMap.OnVectorOverlayClickListener onVectorOverlayClickListener) {
        qa.b(pa.W, "setOnVectorOverlayClickListener", Boolean.valueOf(onVectorOverlayClickListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOverSeaEnable(boolean z) {
        qa.b(pa.W, "enableOverSea", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOverSeaTileProvider(OverSeaTileProvider overSeaTileProvider) {
        qa.b(pa.W, "setOverSeaTileProvider", overSeaTileProvider);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setPadding(int i2, int i3, int i4, int i5) {
        qa.b(pa.W, "setPadding", (Object) (i2 + "#" + i3 + "#" + i4 + "#" + i5));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setPadding(int i2, int i3, int i4, int i5, boolean z) {
        qa.b(pa.W, "setPadding", (Object) (i2 + "#" + i3 + "#" + i4 + "#" + i5 + "#" + z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setPointToCenter(int i2, int i3) {
        qa.b(pa.W, "setPointToCenter", (Object) (i2 + "#" + i3));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setPoisEnabled(boolean z) {
        qa.b(pa.W, "setPoisEnabled", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setRestrictBounds(LatLngBounds latLngBounds, RestrictBoundsFitMode restrictBoundsFitMode) {
        String str;
        if (latLngBounds == null) {
            str = "null restrictBounds";
        } else {
            str = latLngBounds.toString() + "#" + restrictBoundsFitMode;
        }
        qa.b(pa.W, "setRestrictBounds", (Object) str);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setSatelliteEnabled(boolean z) {
        qa.b(pa.W, "setSatelliteEnabled", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setTencentMapGestureListener(TencentMapGestureListener tencentMapGestureListener) {
        qa.b(pa.W, "setTencentMapGestureListener", Boolean.valueOf(tencentMapGestureListener != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setTrafficEnabled(boolean z) {
        qa.b(pa.W, "setTrafficEnabled", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setTrafficMode(int i2, int i3) {
        qa.b(pa.W, "setTrafficMode", (Object) (i2 + "#" + i3));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void showBuilding(boolean z) {
        qa.b(pa.W, "showBuilding", Boolean.valueOf(z));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void snapshot(TencentMap.SnapshotReadyCallback snapshotReadyCallback) {
        qa.b(pa.W, "snapshot", Boolean.valueOf(snapshotReadyCallback != null));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void snapshot(TencentMap.SnapshotReadyCallback snapshotReadyCallback, Bitmap.Config config) {
        StringBuilder sb = new StringBuilder();
        sb.append(snapshotReadyCallback != null);
        sb.append("#");
        sb.append(config);
        qa.b(pa.W, "snapshot", (Object) sb.toString());
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void snapshot(TencentMap.SnapshotReadyCallback snapshotReadyCallback, Bitmap.Config config, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(snapshotReadyCallback != null);
        sb.append("#");
        sb.append(config);
        sb.append("#");
        sb.append(i2);
        qa.b(pa.W, "snapshot", (Object) sb.toString());
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void stopAnimation() {
        qa.b(pa.W, "stopAnimation", Integer.valueOf(qa.i(pa.W, "stopAnimation")));
    }
}
