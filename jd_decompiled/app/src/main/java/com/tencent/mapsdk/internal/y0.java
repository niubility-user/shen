package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.tencent.tencentmap.mapsdk.maps.model.Animation;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import java.util.List;

/* loaded from: classes9.dex */
public final class y0 extends v0<r0> implements Polyline {
    @Deprecated

    /* renamed from: h  reason: collision with root package name */
    public static final int f17478h = 4;
    @Deprecated

    /* renamed from: i  reason: collision with root package name */
    public static final int f17479i = 3;
    @Deprecated

    /* renamed from: j  reason: collision with root package name */
    public static final int f17480j = 2;
    @Deprecated

    /* renamed from: k  reason: collision with root package name */
    public static final int f17481k = 1;
    @Deprecated

    /* renamed from: l  reason: collision with root package name */
    public static final int f17482l = 6;
    @Deprecated

    /* renamed from: m  reason: collision with root package name */
    public static final int f17483m = 0;
    @Deprecated

    /* renamed from: n  reason: collision with root package name */
    public static final int f17484n = 33;
    @Deprecated
    public static final int o = 19;

    /* renamed from: g  reason: collision with root package name */
    private final r0 f17485g;

    public y0(r0 r0Var) {
        this.f17485g = r0Var;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void addTurnArrow(int i2, int i3) {
        this.f17485g.addTurnArrow(i2, i3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void appendPoint(LatLng... latLngArr) {
        this.f17485g.appendPoint(latLngArr);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void appendPoints(List<LatLng> list) {
        this.f17485g.appendPoints(list);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void arrowSpacing(int i2) {
        this.f17485g.arrowSpacing(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void cleanTurnArrow() {
        this.f17485g.cleanTurnArrow();
    }

    public void d(boolean z) {
        this.f17485g.d(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void eraseColor(int i2) {
        this.f17485g.eraseColor(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void eraseTo(int i2, LatLng latLng) {
        this.f17485g.a(i2, latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public int getColor() {
        return this.f17485g.getColor();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public int[][] getColors() {
        return this.f17485g.getColors();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public List<Integer> getPattern() {
        return this.f17485g.getPattern();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public List<LatLng> getPoints() {
        return this.f17485g.getPoints();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public PolylineOptions getPolylineOptions() {
        return this.f17485g.getPolylineOptions();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public Object getTag() {
        return this.f17485g.getTag();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public PolylineOptions.Text getText() {
        r0 r0Var = this.f17485g;
        if (r0Var == null) {
            return null;
        }
        return r0Var.getText();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public Rect getVisibleRect() {
        return this.f17485g.getVisibleRect();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public float getWidth() {
        return this.f17485g.getWidth();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public boolean isAboveMaskLayer() {
        return this.f17485g.isAboveMaskLayer();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Clickable
    public boolean isClickable() {
        r0 r0Var = this.f17485g;
        if (r0Var != null) {
            return r0Var.isClickable();
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public boolean isGradientEnable() {
        return this.f17485g.isGradientEnable();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void pattern(List<Integer> list) {
        this.f17485g.pattern(list);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setAboveMaskLayer(boolean z) {
        this.f17485g.setAboveMaskLayer(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public void setAnimation(Animation animation) {
        this.f17485g.setAnimation(animation);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setArrow(boolean z) {
        this.f17485g.setArrow(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setBorderColors(int[] iArr) {
        this.f17485g.setBorderColors(iArr);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Clickable
    public void setClickable(boolean z) {
        this.f17485g.setClickable(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setColor(int i2) {
        this.f17485g.setColor(i2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setColorTexture(BitmapDescriptor bitmapDescriptor) {
        this.f17485g.setColorTexture(bitmapDescriptor);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setColorTexture(String str) {
        this.f17485g.setColorTexture(BitmapDescriptorFactory.fromAsset(str));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setColors(int[] iArr, int[] iArr2) {
        this.f17485g.setColors(iArr, iArr2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setEraseable(boolean z) {
        this.f17485g.setEraseable(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setGradientEnable(boolean z) {
        r0 r0Var = this.f17485g;
        if (r0Var == null) {
            return;
        }
        r0Var.setGradientEnable(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setPoints(List<LatLng> list) {
        this.f17485g.setPoints(list);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setPolylineOptions(PolylineOptions polylineOptions) {
        if (polylineOptions == null) {
            return;
        }
        this.f17485g.setPolylineOptions(polylineOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public void setTag(Object obj) {
        this.f17485g.setTag(obj);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setText(PolylineOptions.Text text) {
        r0 r0Var = this.f17485g;
        if (r0Var == null) {
            return;
        }
        r0Var.setText(text);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline
    public void setWidth(float f2) {
        this.f17485g.setWidth(f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Polyline, com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public void startAnimation(Animation animation) {
        this.f17485g.startAnimation(animation);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public boolean startAnimation() {
        r0 r0Var = this.f17485g;
        if (r0Var != null) {
            return r0Var.startAnimation();
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: y  reason: merged with bridge method [inline-methods] */
    public r0 x() {
        return this.f17485g;
    }
}
