package com.tencent.map.lib.models;

import androidx.annotation.Keep;
import com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

@Keep
/* loaded from: classes9.dex */
public class AggregationOverlayInfo extends OverlayListenerInfo {
    private static final float DEFAULT_MAX_OPACITY = 1.0f;
    private static final int DEFAULT_MAX_ZOOM = 22;
    private static final float DEFAULT_MIN_OPACITY = 0.0f;
    private static final int DEFAULT_MIN_ZOOM = 3;
    public LatLng mGeoReferencePoint;
    public int mType = 0;
    public float mSize = 2000.0f;
    public float mGap = 0.0f;
    public float mOpacity = 1.0f;
    public boolean mVisibility = true;
    public int mMinZoom = 3;
    public int mMaxZoom = 22;
    public int mDisplayLevel = 1;
    public int mZIndex = 0;
    public double mMinHeight = 0.0d;
    public double mMaxHeight = 1000.0d;
    public double mMinIntensity = 0.0d;
    public double mMaxIntensity = 2000.0d;
    public boolean mRangeFlag = false;
    public int[] mColors = {1174031124, -1711650028, -637908204};
    public double[] mStartPoints = {0.0d, 0.6d, 0.8d};
    public boolean mDraw3D = false;
    public boolean mAnimate = false;
    public int mAnimateDuration = 5000;
    public WeightedLatLng[] mNodes = new WeightedLatLng[0];

    public boolean isAnimate() {
        return this.mAnimate;
    }

    public boolean isVisible() {
        return this.mVisibility;
    }

    public void setAnimate(boolean z) {
        this.mAnimate = z;
    }

    public void setAnimateTime(int i2) {
        this.mAnimateDuration = i2;
    }

    public void setColors(int[] iArr) {
        this.mColors = iArr;
    }

    public void setDisplayLevel(int i2) {
        if (i2 == 1 || i2 == 2) {
            this.mDisplayLevel = i2;
        }
    }

    public void setDraw3D(boolean z) {
        this.mDraw3D = z;
    }

    public void setGap(float f2) {
        this.mGap = f2;
    }

    public void setHeightRange(double d, double d2) {
        if (d > d2 || d < 0.0d) {
            this.mMinHeight = 0.0d;
            this.mMaxHeight = 1000.0d;
            return;
        }
        this.mMaxHeight = d2;
        this.mMinHeight = d;
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

    public void setNodes(WeightedLatLng[] weightedLatLngArr) {
        this.mNodes = weightedLatLngArr;
    }

    public void setOpacity(float f2) {
        float f3 = 1.0f;
        if (f2 <= 1.0f) {
            f3 = 0.0f;
            if (f2 >= 0.0f) {
                this.mOpacity = f2;
                return;
            }
        }
        this.mOpacity = f3;
    }

    public void setShowRange(double d, double d2) {
        boolean z;
        if (d > d2 || d < 0.0d) {
            this.mMinIntensity = 0.0d;
            this.mMaxIntensity = 2000.0d;
            z = false;
        } else {
            this.mMinIntensity = d;
            this.mMaxIntensity = d2;
            z = true;
        }
        this.mRangeFlag = z;
    }

    public void setSize(float f2) {
        this.mSize = f2;
    }

    public void setStartPoints(double[] dArr) {
        this.mStartPoints = dArr;
    }

    public void setType(int i2) {
        this.mType = i2;
    }

    public void setVisibility(boolean z) {
        this.mVisibility = z;
    }

    public void setZIndex(int i2) {
        this.mZIndex = i2;
    }

    public void setZoomLevelRange(int i2, int i3) {
        if (i2 <= i3) {
            setMinZoom(i2);
            setMaxZoom(i3);
        }
    }
}
