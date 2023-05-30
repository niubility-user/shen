package com.tencent.tencentmap.mapsdk.maps.model;

/* loaded from: classes9.dex */
public final class ArcOptions {
    public LatLng mEndLatLng;
    public LatLng mPassLatLng;
    public boolean mShowArrow;
    public LatLng mStartLatLng;
    public int mStrokeColor;
    public float mAngle = 0.0f;
    public float mWidth = 5.0f;
    public float mStrokeWidth = 0.0f;
    public int mColor = -16777216;

    public ArcOptions angle(float f2) {
        this.mAngle = f2;
        return this;
    }

    public ArcOptions color(int i2) {
        this.mColor = i2;
        return this;
    }

    public float getAngle() {
        return this.mAngle;
    }

    public int getColor() {
        return this.mColor;
    }

    public LatLng getEndLatLng() {
        return this.mEndLatLng;
    }

    public LatLng getPassLatLng() {
        return this.mPassLatLng;
    }

    public LatLng getStartLatLng() {
        return this.mStartLatLng;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public float getStrokeWidth() {
        return this.mStrokeWidth;
    }

    public float getWidth() {
        return this.mWidth;
    }

    public boolean isShowArrow() {
        return this.mShowArrow;
    }

    public ArcOptions pass(LatLng latLng) {
        this.mPassLatLng = latLng;
        return this;
    }

    public ArcOptions points(LatLng latLng, LatLng latLng2) {
        this.mStartLatLng = latLng;
        this.mEndLatLng = latLng2;
        return this;
    }

    public ArcOptions showArrow(boolean z) {
        this.mShowArrow = z;
        return this;
    }

    public ArcOptions strokeColor(int i2) {
        this.mStrokeColor = i2;
        return this;
    }

    public ArcOptions strokeWidth(float f2) {
        this.mStrokeWidth = f2;
        return this;
    }

    public ArcOptions width(float f2) {
        this.mWidth = f2;
        return this;
    }
}
