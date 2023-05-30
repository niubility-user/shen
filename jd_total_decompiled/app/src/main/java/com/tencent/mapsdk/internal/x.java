package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.s4;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.VisibleRegion;
import java.util.List;

/* loaded from: classes9.dex */
public class x implements s4 {
    @Override // com.tencent.mapsdk.internal.s4
    public double a(Point point2, Point point3) {
        return 0.0d;
    }

    @Override // com.tencent.mapsdk.internal.s4
    public float a(LatLng latLng, LatLng latLng2, int i2, int i3, int i4, int i5, LatLng latLng3) {
        return 0.0f;
    }

    @Override // com.tencent.mapsdk.internal.s4
    public GeoPoint a(o5 o5Var) {
        return new GeoPoint();
    }

    @Override // com.tencent.mapsdk.internal.s4
    public o5 a(Context context, LatLng latLng) {
        return new o5();
    }

    @Override // com.tencent.mapsdk.internal.s4
    public o5 a(GeoPoint geoPoint) {
        return new o5();
    }

    @Override // com.tencent.mapsdk.internal.s4
    public w5 a(LatLng latLng) {
        return new w5(0.0d, 0.0d);
    }

    @Override // com.tencent.mapsdk.internal.s4
    public LatLng a(PointF pointF) {
        return new LatLng();
    }

    @Override // com.tencent.mapsdk.internal.s4
    public LatLng a(w5 w5Var) {
        return new LatLng();
    }

    @Override // com.tencent.mapsdk.internal.s4
    public void a(List<? extends Boundable> list, List<GeoPoint> list2, Rect rect, s4.a aVar) {
    }

    @Override // com.tencent.mapsdk.internal.s4
    public LatLng[] a() {
        return new LatLng[4];
    }

    @Override // com.tencent.mapsdk.internal.s4
    public PointF b(LatLng latLng) {
        return new PointF();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public LatLng fromScreenLocation(Point point2) {
        return new LatLng();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public VisibleRegion getVisibleRegion() {
        return new VisibleRegion(new LatLng(0.0d, 0.0d), new LatLng(0.0d, 0.0d), new LatLng(0.0d, 0.0d), new LatLng(0.0d, 0.0d), new LatLngBounds(new LatLng(0.0d, 0.0d), new LatLng(0.0d, 0.0d)));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public float[] glModelMatrix(PointF pointF, float f2) {
        return new float[16];
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public float glPixelRatio() {
        return 0.0f;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public float[] glProjectionMatrix() {
        return new float[16];
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public PointF glVertexForCoordinate(LatLng latLng) {
        return new PointF();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public float[] glViewMatrix() {
        return new float[16];
    }

    @Override // com.tencent.mapsdk.internal.s4, com.tencent.tencentmap.mapsdk.maps.Projection
    public double metersPerPixel(double d) {
        return 0.0d;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public Point toScreenLocation(LatLng latLng) {
        return new Point();
    }
}
