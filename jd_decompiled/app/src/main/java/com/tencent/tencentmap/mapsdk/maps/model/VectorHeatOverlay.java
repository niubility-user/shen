package com.tencent.tencentmap.mapsdk.maps.model;

/* loaded from: classes9.dex */
public interface VectorHeatOverlay {
    VectorHeatAggregationUnit getUnit(LatLng latLng);

    void remove();

    void setOpacity(float f2);

    void setVisibility(boolean z);
}
