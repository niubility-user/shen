package com.tencent.map.sdk.utilities.visualization.aggregation;

import android.graphics.Color;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public abstract class AggregationOverlayProvider extends BaseOverlayProvider {
    private static final double DEFAULT_MAX_HEIGHT = 100.0d;
    private static final int DEFAULT_MAX_INTENSITY = 2000;
    private static final int DEFAULT_MIN_INTENSITY = 0;
    private static final int DEFAULT_MIN_SIZE = 10;
    private static final int DEFAULT_SIZE = 2000;
    private LatLng mGeoReferencePoint;
    private WeightedLatLng[] mNodes;
    private static final int[] DEFAULT_COLORS = {Color.argb(255, 31, 44, 71), Color.argb(255, 40, 72, (int) R2.anim.lib_cashier_sdk_pop_out_animation_bottom), Color.argb(255, 38, 97, 217), Color.argb(255, 90, 140, 242), Color.argb(255, 153, (int) R2.anim.slide_in_from_bottom, 255)};
    private static final double DEFAULT_MIN_HEIGHT = 0.0d;
    private static final double[] DEFAULT_START_POINTS = {DEFAULT_MIN_HEIGHT, 0.10000000149011612d, 0.15000000596046448d, 0.30000001192092896d, 0.5d};
    private int mType = 0;
    private float mSize = 2000.0f;
    private float mGap = 0.0f;
    private int[] mColors = DEFAULT_COLORS;
    private double[] mStartPoints = DEFAULT_START_POINTS;
    private double mMinIntensity = DEFAULT_MIN_HEIGHT;
    private double mMaxIntensity = 2000.0d;
    private boolean mRangeFlag = false;
    private double mMaxHeight = DEFAULT_MAX_HEIGHT;
    private double mMinHeight = DEFAULT_MIN_HEIGHT;
    private boolean mDraw3D = false;
    private boolean mAnimate = false;
    private int mAnimateDuration = 0;

    /* loaded from: classes9.dex */
    public enum AggregationOverlayType {
        HoneyComb,
        Square
    }

    public AggregationOverlayProvider colors(int[] iArr, double[] dArr) {
        boolean z;
        if (iArr != null && dArr != null && iArr.length > 0 && dArr.length > 0 && iArr.length == dArr.length) {
            int i2 = 1;
            while (true) {
                if (i2 >= dArr.length) {
                    z = true;
                    break;
                } else if (dArr[i2 - 1] > dArr[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z && dArr[0] >= DEFAULT_MIN_HEIGHT && dArr[dArr.length - 1] <= 1.0d) {
                this.mColors = iArr;
                this.mStartPoints = dArr;
            }
        }
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public AggregationOverlayProvider displayLevel(int i2) {
        super.displayLevel(i2);
        return this;
    }

    public AggregationOverlayProvider enable3D(boolean z) {
        this.mDraw3D = z;
        return this;
    }

    public AggregationOverlayProvider gap(float f2) {
        if (f2 >= 0.0f) {
            this.mGap = f2;
        }
        return this;
    }

    public int getAnimateDuration() {
        return this.mAnimateDuration;
    }

    public int[] getColors() {
        return this.mColors;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getDisplayLevel() {
        return super.getDisplayLevel();
    }

    public float getGap() {
        return this.mGap;
    }

    public LatLng getGeoReferencePoint() {
        return this.mGeoReferencePoint;
    }

    public double getMaxHeight() {
        return this.mMaxHeight;
    }

    public double getMaxIntensity() {
        return this.mMaxIntensity;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getMaxZoom() {
        return super.getMaxZoom();
    }

    public double getMinHeight() {
        return this.mMinHeight;
    }

    public double getMinIntensity() {
        return this.mMinIntensity;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getMinZoom() {
        return super.getMinZoom();
    }

    public WeightedLatLng[] getNodes() {
        return this.mNodes;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public float getOpacity() {
        return super.getOpacity();
    }

    public AggregationOverlayType getOverlayType() {
        return AggregationOverlayType.values()[this.mType];
    }

    public boolean getRangeFlag() {
        return this.mRangeFlag;
    }

    public float getSize() {
        return this.mSize;
    }

    public double[] getStartPoints() {
        return this.mStartPoints;
    }

    public int getType() {
        return getOverlayType().ordinal();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getZIndex() {
        return super.getZIndex();
    }

    public boolean isAnimate() {
        return this.mAnimate;
    }

    public boolean isEnable3D() {
        return this.mDraw3D;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public boolean isVisibility() {
        return super.isVisibility();
    }

    public AggregationOverlayProvider nodes(WeightedLatLng[] weightedLatLngArr) {
        if (weightedLatLngArr != null) {
            this.mNodes = weightedLatLngArr;
        }
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public AggregationOverlayProvider opacity(float f2) {
        super.opacity(f2);
        return this;
    }

    public AggregationOverlayProvider setAnimateDuration(int i2) {
        if (i2 == 0) {
            this.mAnimate = false;
            this.mAnimateDuration = 0;
        } else if (i2 > 0) {
            this.mAnimateDuration = i2;
            this.mAnimate = true;
        }
        return this;
    }

    public void setGeoReferencePoint(LatLng latLng) {
        this.mGeoReferencePoint = latLng;
    }

    public AggregationOverlayProvider setHeightRange(double d, double d2) {
        if (d <= d2 && d >= DEFAULT_MIN_HEIGHT) {
            this.mMaxHeight = d2;
            this.mMinHeight = d;
        }
        return this;
    }

    public AggregationOverlayProvider setIntensityRange(double d, double d2) {
        if (d < d2 && d >= DEFAULT_MIN_HEIGHT) {
            this.mMaxIntensity = d2;
            this.mMinIntensity = d;
            this.mRangeFlag = true;
        }
        return this;
    }

    public AggregationOverlayProvider size(float f2) {
        if (f2 < 10.0f) {
            return this;
        }
        this.mSize = f2;
        return this;
    }

    public AggregationOverlayProvider type(AggregationOverlayType aggregationOverlayType) {
        this.mType = aggregationOverlayType.ordinal();
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public AggregationOverlayProvider visibility(boolean z) {
        super.visibility(z);
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public AggregationOverlayProvider zIndex(int i2) {
        super.zIndex(i2);
        return this;
    }

    public AggregationOverlayProvider zoomRange(int i2, int i3) {
        if (i2 <= i3) {
            super.minZoom(i2);
            super.maxZoom(i3);
        }
        return this;
    }
}
