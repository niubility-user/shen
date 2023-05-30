package com.tencent.tencentmap.mapsdk.maps.model;

import android.os.Parcel;

/* loaded from: classes9.dex */
public class CircleOptions {
    private boolean boVisible;
    private double dRadius;
    private float fStrokeWidth;
    private int iFillColor;
    private int iLevel;
    private int iStrokeColor;
    private int iZindex;
    private LatLng latlngCenter;
    private boolean mClickable;

    public CircleOptions() {
        this.iLevel = 2;
        this.latlngCenter = new LatLng(39.984253d, 116.307439d);
        this.dRadius = 1.0d;
        this.fStrokeWidth = 1.0f;
        this.iStrokeColor = -16777216;
        this.iFillColor = 0;
        this.iZindex = 0;
        this.boVisible = true;
    }

    public CircleOptions(int i2, LatLng latLng, double d, float f2, int i3, int i4, int i5, boolean z) {
        this.iLevel = 2;
        this.latlngCenter = new LatLng(39.984253d, 116.307439d);
        this.dRadius = 1.0d;
        this.fStrokeWidth = 1.0f;
        this.iStrokeColor = -16777216;
        this.iFillColor = 0;
        this.boVisible = true;
        this.latlngCenter = latLng;
        this.dRadius = d;
        this.fStrokeWidth = f2;
        this.iStrokeColor = i3;
        this.iFillColor = i4;
        this.iZindex = i5;
        this.boVisible = z;
    }

    public CircleOptions center(LatLng latLng) {
        this.latlngCenter = latLng;
        return this;
    }

    public CircleOptions clickable(boolean z) {
        this.mClickable = z;
        return this;
    }

    public CircleOptions fillColor(int i2) {
        this.iFillColor = i2;
        return this;
    }

    public LatLng getCenter() {
        return this.latlngCenter;
    }

    public int getFillColor() {
        return this.iFillColor;
    }

    public int getLevel() {
        return this.iLevel;
    }

    public double getRadius() {
        return this.dRadius;
    }

    public int getStrokeColor() {
        return this.iStrokeColor;
    }

    public float getStrokeWidth() {
        return this.fStrokeWidth;
    }

    public int getZIndex() {
        return this.iZindex;
    }

    public boolean isClickable() {
        return this.mClickable;
    }

    public boolean isVisible() {
        return this.boVisible;
    }

    public CircleOptions level(int i2) {
        if (i2 >= 0 && i2 <= 2) {
            this.iLevel = i2;
        }
        return this;
    }

    public CircleOptions radius(double d) {
        this.dRadius = d;
        return this;
    }

    public CircleOptions strokeColor(int i2) {
        this.iStrokeColor = i2;
        return this;
    }

    public CircleOptions strokeWidth(float f2) {
        if (f2 < 0.0f) {
            f2 = 1.0f;
        }
        this.fStrokeWidth = f2;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.boVisible = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        if (parcel == null) {
            return;
        }
        parcel.writeDouble(this.dRadius);
    }

    public CircleOptions zIndex(int i2) {
        this.iZindex = i2;
        return this;
    }
}
