package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.Projection;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.util.List;

/* loaded from: classes9.dex */
public interface s4 extends Projection {

    /* loaded from: classes9.dex */
    public interface a {
        void a(float f2, GeoPoint geoPoint, double d);
    }

    double a(Point point2, Point point3);

    float a(LatLng latLng, LatLng latLng2, int i2, int i3, int i4, int i5, LatLng latLng3);

    GeoPoint a(o5 o5Var);

    o5 a(Context context, LatLng latLng);

    o5 a(GeoPoint geoPoint);

    w5 a(LatLng latLng);

    LatLng a(PointF pointF);

    LatLng a(w5 w5Var);

    void a(List<? extends Boundable> list, List<GeoPoint> list2, Rect rect, a aVar);

    LatLng[] a();

    PointF b(LatLng latLng);

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    double metersPerPixel(double d);
}
