package com.tencent.tencentmap.mapsdk.maps.model;

/* loaded from: classes9.dex */
public class LocationNavigationGravityline {
    private int color;
    private LatLng destination;
    private float width;

    public LocationNavigationGravityline(float f2, int i2, LatLng latLng) {
        this.width = f2;
        this.color = i2;
        this.destination = latLng;
    }

    public int getColor() {
        return this.color;
    }

    public LatLng getDestination() {
        return this.destination;
    }

    public float getWidth() {
        return this.width;
    }

    public void setColor(int i2) {
        this.color = i2;
    }

    public void setDestination(LatLng latLng) {
        this.destination = latLng;
    }

    public void setWidth(float f2) {
        this.width = f2;
    }
}
