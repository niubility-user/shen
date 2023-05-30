package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapNavi;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.IOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.MapRouteSection;
import java.util.List;

/* loaded from: classes9.dex */
public class ei extends VectorMap implements TencentMapNavi {
    public ei(qc qcVar) {
        super(qcVar);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public IntersectionOverlay addIntersectionEnlargeOverlay(IntersectionOverlayOptions intersectionOverlayOptions) {
        return getMapPro().addIntersectionEnlargeOverlay(intersectionOverlayOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void addSegmentsWithRouteName(List<MapRouteSection> list, List<LatLng> list2) {
        getMapPro().addSegmentsWithRouteName(list, list2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void animateToNaviPosition(LatLng latLng, float f2, float f3) {
        getMapPro().animateToNaviPosition(latLng, f2, f3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void animateToNaviPosition(LatLng latLng, float f2, float f3, float f4) {
        getMapPro().animateToNaviPosition(latLng, f2, f3, f4);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void animateToNaviPosition(LatLng latLng, float f2, float f3, float f4, boolean z) {
        getMapPro().animateToNaviPosition(latLng, f2, f3, f4, z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void animateToNaviPosition2(LatLng latLng, float f2, float f3, float f4, boolean z) {
        getMapPro().animateToNaviPosition2(latLng, f2, f3, f4, z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public float calNaviLevel(LatLngBounds latLngBounds, float f2, int i2, boolean z) {
        return getMapPro().calNaviLevel(latLngBounds, f2, i2, z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public float calNaviLevel2(LatLng latLng, LatLng latLng2, float f2, float f3, int i2, boolean z) {
        return getMapPro().calNaviLevel2(latLng, latLng2, f2, f3, i2, z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public float calNaviLevel3(LatLng latLng, LatLng latLng2, float f2, int i2, int i3, int i4, int i5, boolean z) {
        return getMapPro().calNaviLevel3(latLng, latLng2, f2, i2, i3, i4, i5, z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public CameraPosition calculateZoomToSpanLevelAsync(List<IOverlay> list, List<LatLng> list2, int i2, int i3, int i4, int i5, TencentMap.AsyncOperateCallback<CameraPosition> asyncOperateCallback) {
        return getMapPro().calculateZoomToSpanLevelAsync(list, list2, i2, i3, i4, i5, asyncOperateCallback);
    }

    @Override // com.tencent.mapsdk.vector.VectorMap, com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void clearRouteNameSegments() {
        getMapPro().clearRouteNameSegments();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public boolean isNaviStateEnabled() {
        return getMapPro().isNaviStateEnabled();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void moveToNavPosition(CameraUpdate cameraUpdate, LatLng latLng) {
        getMapPro().moveToNavPosition(cameraUpdate, latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void setNavCenter(int i2, int i3) {
        getMapPro().setNavCenter(i2, i3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void setNaviFixingProportion(float f2, float f3) {
        getMapPro().setNaviFixingProportion(f2, f3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void setNaviFixingProportion2D(float f2, float f3) {
        getMapPro().setNaviFixingProportion2D(f2, f3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void setNaviStateEnabled(boolean z) {
        getMapPro().setNaviStateEnabled(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapNavi
    public void setOptionalResourcePath(String str) {
        getMapPro().setOptionalResourcePath(str);
    }
}
