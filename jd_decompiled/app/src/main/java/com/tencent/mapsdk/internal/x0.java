package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Polygon;
import com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions;
import java.util.List;

/* loaded from: classes9.dex */
public final class x0 extends v0<q0> implements Polygon {

    /* renamed from: g  reason: collision with root package name */
    public q0 f17450g;

    public x0(q0 q0Var) {
        this.f17450g = q0Var;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polygon
    public boolean contains(LatLng latLng) {
        return this.f17450g.contains(latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Fillable
    public int getFillColor() {
        return this.f17450g.getFillColor();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polygon
    public List<LatLng> getPoints() {
        return this.f17450g.getPoints();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public int getStrokeColor() {
        return this.f17450g.getStrokeColor();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public float getStrokeWidth() {
        return this.f17450g.getStrokeWidth();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public Object getTag() {
        return this.f17450g.getTag();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Clickable
    public boolean isClickable() {
        q0 q0Var = this.f17450g;
        if (q0Var != null) {
            return q0Var.isClickable();
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Clickable
    public void setClickable(boolean z) {
        this.f17450g.setClickable(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Fillable
    public void setFillColor(int i2) {
        this.f17450g.setFillColor(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polygon
    public void setOptions(PolygonOptions polygonOptions) {
        this.f17450g.setOptions(polygonOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polygon
    public void setPoints(List<LatLng> list) {
        q0 q0Var = this.f17450g;
        if (q0Var == null) {
            return;
        }
        q0Var.setPoints(list);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public void setStrokeColor(int i2) {
        this.f17450g.setStrokeColor(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Strokeable
    public void setStrokeWidth(float f2) {
        this.f17450g.setStrokeWidth(f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public void setTag(Object obj) {
        this.f17450g.setTag(obj);
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: y  reason: merged with bridge method [inline-methods] */
    public q0 x() {
        return this.f17450g;
    }
}
