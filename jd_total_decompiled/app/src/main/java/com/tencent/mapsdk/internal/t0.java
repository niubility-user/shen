package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public final class t0 extends v0<h0> implements Circle {

    /* renamed from: g  reason: collision with root package name */
    private h0 f17266g;

    public t0(h0 h0Var) {
        this.f17266g = h0Var;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public boolean contains(LatLng latLng) {
        return x().contains(latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public LatLng getCenter() {
        return x().getCenter();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Fillable
    public int getFillColor() {
        return x().getFillColor();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public double getRadius() {
        return x().getRadius();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public int getStrokeColor() {
        return x().getStrokeColor();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public float getStrokeWidth() {
        return x().getStrokeWidth();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public Object getTag() {
        return x().getTag();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Clickable
    public boolean isClickable() {
        return x().isClickable();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public void setCenter(LatLng latLng) {
        x().setCenter(latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Clickable
    public void setClickable(boolean z) {
        x().setClickable(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Fillable
    public void setFillColor(int i2) {
        x().setFillColor(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    @Deprecated
    public void setOptions(CircleOptions circleOptions) {
        x().setOptions(circleOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public void setRadius(double d) {
        x().setRadius(d);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public void setStrokeColor(int i2) {
        x().setStrokeColor(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public void setStrokeWidth(float f2) {
        x().setStrokeWidth(f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public void setTag(Object obj) {
        x().setTag(obj);
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: y  reason: merged with bridge method [inline-methods] */
    public h0 x() {
        return this.f17266g;
    }
}
