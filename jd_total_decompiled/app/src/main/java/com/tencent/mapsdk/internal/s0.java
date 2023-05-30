package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.Arc;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public final class s0 extends v0<g0> implements Arc {

    /* renamed from: g  reason: collision with root package name */
    private g0 f17215g;

    public s0(g0 g0Var) {
        this.f17215g = g0Var;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Arc
    public LatLng getCenter() {
        return x().getCenter();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Colorable
    public int getColor() {
        return x().getColor();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Arc
    public double getLength() {
        return x().getLength();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Arc
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

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Widthable
    public float getWidth() {
        return x().getWidth();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Colorable
    public void setColor(int i2) {
        x().setColor(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public void setStrokeColor(int i2) {
        x().setStrokeColor(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public void setStrokeWidth(float f2) {
        x().setStrokeWidth(f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Widthable
    public void setWidth(float f2) {
        x().setWidth(f2);
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: y  reason: merged with bridge method [inline-methods] */
    public g0 x() {
        return this.f17215g;
    }
}
