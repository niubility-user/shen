package com.tencent.tencentmap.mapsdk.maps;

import android.graphics.Point;
import android.graphics.PointF;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.VisibleRegion;

/* loaded from: classes9.dex */
public interface Projection {
    LatLng fromScreenLocation(Point point2);

    VisibleRegion getVisibleRegion();

    float[] glModelMatrix(PointF pointF, float f2);

    float glPixelRatio();

    float[] glProjectionMatrix();

    PointF glVertexForCoordinate(LatLng latLng);

    float[] glViewMatrix();

    double metersPerPixel(double d);

    Point toScreenLocation(LatLng latLng);
}
