package com.tencent.tencentmap.mapsdk.maps.model;

@Deprecated
/* loaded from: classes9.dex */
public class EmergeAnimation extends BaseAnimation {
    public LatLng mStartPoint;

    public EmergeAnimation(LatLng latLng) {
        this.mStartPoint = latLng;
    }

    public LatLng getStartPoint() {
        return this.mStartPoint;
    }
}
