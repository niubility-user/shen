package com.tencent.mapsdk.internal;

import android.graphics.Point;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.VisibleRegion;

/* loaded from: classes9.dex */
public interface k0 {
    LatLng fromScreenLocation(Point point2);

    VisibleRegion getVisibleRegion();

    double metersPerPixel(double d);

    Point toScreenLocation(LatLng latLng);
}
