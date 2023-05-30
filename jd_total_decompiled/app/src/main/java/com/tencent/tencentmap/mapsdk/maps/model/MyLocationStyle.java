package com.tencent.tencentmap.mapsdk.maps.model;

import android.graphics.Color;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes9.dex */
public class MyLocationStyle {
    public static final int LOCATION_TYPE_FOLLOW_NO_CENTER = 2;
    public static final int LOCATION_TYPE_LOCATION_ROTATE = 0;
    public static final int LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER = 1;
    public static final int LOCATION_TYPE_MAP_ROTATE_NO_CENTER = 3;
    private LocationCompass locationCompass;
    private LocationNavigationGravityline locationNavigationGravityline;
    private BitmapDescriptor mIcon;
    private float anchorU = 0.5f;
    private float anchorV = 0.5f;
    private int fillColor = Color.argb(102, 0, (int) R2.anim.pop_center_out, 255);
    private int strokeColor = Color.argb(127, 0, (int) R2.anim.pop_center_out, 255);
    private float strokeWidth = 1.0f;
    private int myLocationType = 0;

    public MyLocationStyle anchor(float f2, float f3) {
        this.anchorU = f2;
        this.anchorV = f3;
        return this;
    }

    public MyLocationStyle fillColor(int i2) {
        this.fillColor = i2;
        return this;
    }

    public float getAnchorU() {
        return this.anchorU;
    }

    public float getAnchorV() {
        return this.anchorV;
    }

    public int getFillColor() {
        return this.fillColor;
    }

    public LocationCompass getLocationCompass() {
        return this.locationCompass;
    }

    public LocationNavigationGravityline getLocationNavigationGravityline() {
        return this.locationNavigationGravityline;
    }

    public BitmapDescriptor getMyLocationIcon() {
        return this.mIcon;
    }

    public int getMyLocationType() {
        return this.myLocationType;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public MyLocationStyle icon(BitmapDescriptor bitmapDescriptor) {
        this.mIcon = bitmapDescriptor;
        return this;
    }

    public MyLocationStyle myLocationType(int i2) {
        this.myLocationType = i2;
        return this;
    }

    public MyLocationStyle setLocationCompass(LocationCompass locationCompass) {
        this.locationCompass = locationCompass;
        return this;
    }

    public MyLocationStyle setLocationNavigationGravityline(LocationNavigationGravityline locationNavigationGravityline) {
        this.locationNavigationGravityline = locationNavigationGravityline;
        return this;
    }

    public MyLocationStyle strokeColor(int i2) {
        this.strokeColor = i2;
        return this;
    }

    public MyLocationStyle strokeWidth(int i2) {
        this.strokeWidth = i2;
        return this;
    }

    public String toString() {
        return "{anchorU=" + this.anchorU + ", anchorV=" + this.anchorV + ", fillColor=" + this.fillColor + ", strokeColor=" + this.strokeColor + ", strokeWidth=" + this.strokeWidth + ", myLocationType=" + this.myLocationType + ", mIcon=" + this.mIcon + '}';
    }
}
