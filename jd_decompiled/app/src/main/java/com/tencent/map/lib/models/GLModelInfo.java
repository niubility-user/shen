package com.tencent.map.lib.models;

import androidx.annotation.Keep;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;

@Keep
/* loaded from: classes9.dex */
public class GLModelInfo extends OverlayListenerInfo {
    private static final float DEFAULT_MAX_OPACITY = 1.0f;
    private static final int DEFAULT_MAX_ZOOM = 22;
    private static final float DEFAULT_MIN_OPACITY = 0.0f;
    private static final int DEFAULT_MIN_ZOOM = 3;
    public double mAltitude;
    public int mCoordType;
    public LatLngBounds mLatLngBounds;
    public String mModelFilePath;
    public LatLng mPosition;
    public float mRotationX;
    public float mRotationY;
    public float mRotationZ;
    public double mScale = 1.0d;
    public int mAnimate = 0;
    public int mMinZoom = 3;
    public int mMaxZoom = 22;
    public float opacity = 1.0f;
    public int level = 1;
    public int zIndex = 0;
    public boolean visible = true;
    public float exposure = 1.0f;
    public boolean buildingHidden = true;
    public boolean clickEnabled = false;
    public int mPixelWidth = 64;
    public int mPixelHeight = 64;

    public void enableClick(boolean z) {
        this.clickEnabled = z;
    }

    public float getExposure() {
        return this.exposure;
    }

    public LatLng getPosition() {
        return this.mPosition;
    }

    public float getRotationX() {
        return this.mRotationX;
    }

    public float getRotationY() {
        return this.mRotationY;
    }

    public float getRotationZ() {
        return this.mRotationZ;
    }

    public double getScale() {
        return this.mScale;
    }

    public boolean isClickEnabled() {
        return this.clickEnabled;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setCoordType(int i2) {
        this.mCoordType = i2;
    }

    public void setExposure(float f2) {
        this.exposure = f2;
    }

    public void setLevel(int i2) {
        if (i2 == 1 || i2 == 2) {
            this.level = i2;
        }
    }

    public void setMaxZoom(int i2) {
        if (i2 <= 22) {
            this.mMaxZoom = i2;
        } else {
            this.mMaxZoom = 22;
        }
    }

    public void setMinZoom(int i2) {
        if (i2 >= 3) {
            this.mMinZoom = i2;
        } else {
            this.mMinZoom = 3;
        }
    }

    public void setModelPosition(LatLng latLng) {
        this.mPosition = latLng;
        this.mAltitude = latLng.getAltitude();
    }

    public void setOpacity(float f2) {
        float f3 = 1.0f;
        if (f2 <= 1.0f) {
            f3 = 0.0f;
            if (f2 >= 0.0f) {
                this.opacity = f2;
                return;
            }
        }
        this.opacity = f3;
    }

    public void setPixelBound(int i2, int i3) {
        this.mPixelWidth = i2;
        this.mPixelHeight = i3;
    }

    public void setRotationX(float f2) {
        this.mRotationX = f2;
    }

    public void setRotationY(float f2) {
        this.mRotationY = f2;
    }

    public void setRotationZ(float f2) {
        this.mRotationZ = f2;
    }

    public void setScale(float f2) {
        this.mScale = f2;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }

    public void setZoomLevelRange(int i2, int i3) {
        if (i2 <= i3) {
            setMinZoom(i2);
            setMaxZoom(i3);
        }
    }

    public void setzIndex(int i2) {
        this.zIndex = i2;
    }
}
