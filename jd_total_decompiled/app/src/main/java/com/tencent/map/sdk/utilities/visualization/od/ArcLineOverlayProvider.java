package com.tencent.map.sdk.utilities.visualization.od;

import android.graphics.Color;
import com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.datamodels.FromToLatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public final class ArcLineOverlayProvider extends BaseOverlayProvider {
    private static final int DEFAULT_COLOR_MAP_SIZE = 200;
    private static final int[] DEFAULT_GRADIENT_COLORS = {Color.argb(255, 0, 128, 255), Color.argb(51, 0, 170, 255), Color.argb(255, 0, 128, 255)};
    private static final float[] DEFAULT_GRADIENT_START_POINTS = {0.0f, 0.5f, 1.0f};
    private List<FromToLatLng> dataList = new ArrayList();
    private float mWidth = 6.0f;
    private float mRadian = 45.0f;
    private boolean mDraw3D = false;
    private boolean mAnimate = true;
    private int mHighLightDuration = 200;
    private int mAnimationDuration = 2000;
    private int mAnimateColor = -10046465;
    private int[] mColors = DEFAULT_GRADIENT_COLORS;
    private float[] mColorPoints = DEFAULT_GRADIENT_START_POINTS;
    public int mColorMapSize = 200;

    public ArcLineOverlayProvider animateColor(int i2) {
        this.mAnimateColor = i2;
        return this;
    }

    public ArcLineOverlayProvider data(List<FromToLatLng> list) {
        if (list != null) {
            this.dataList = list;
        }
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public ArcLineOverlayProvider displayLevel(int i2) {
        super.displayLevel(i2);
        return this;
    }

    public ArcLineOverlayProvider enable3D(boolean z) {
        this.mDraw3D = z;
        return this;
    }

    public int getAnimateColor() {
        return this.mAnimateColor;
    }

    public int getAnimateDuration() {
        return this.mAnimationDuration;
    }

    public int getColorMapSize() {
        return this.mColorMapSize;
    }

    public float[] getColorPoints() {
        return this.mColorPoints;
    }

    public int[] getColors() {
        return this.mColors;
    }

    public List<FromToLatLng> getData() {
        return this.dataList;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getDisplayLevel() {
        return super.getDisplayLevel();
    }

    public int getHighLightDuration() {
        return this.mHighLightDuration;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getMaxZoom() {
        return super.getMaxZoom();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getMinZoom() {
        return super.getMinZoom();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public float getOpacity() {
        return super.getOpacity();
    }

    public float getRadian() {
        return this.mRadian;
    }

    public float getWidth() {
        return this.mWidth;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getZIndex() {
        return super.getZIndex();
    }

    public ArcLineOverlayProvider gradient(int[] iArr) {
        if (iArr != null) {
            if (iArr.length == 1) {
                this.mColors = new int[]{iArr[0], iArr[0], iArr[0]};
                this.mColorPoints = new float[]{0.0f, 0.5f, 1.0f};
            } else if (iArr.length == 2) {
                this.mColors = iArr;
                this.mColorPoints = new float[]{0.0f, 1.0f};
            } else if (iArr.length == 3) {
                this.mColors = iArr;
                this.mColorPoints = new float[]{0.0f, 0.5f, 1.0f};
            }
        }
        return this;
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

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public ArcLineOverlayProvider opacity(float f2) {
        super.opacity(f2);
        return this;
    }

    public ArcLineOverlayProvider radian(float f2) {
        if (f2 > 0.0f && f2 <= 90.0f) {
            this.mRadian = f2;
            Iterator<FromToLatLng> it = this.dataList.iterator();
            while (it.hasNext()) {
                it.next().setArc(this.mRadian);
            }
        }
        return this;
    }

    public ArcLineOverlayProvider setAnimateDuration(int i2) {
        if (i2 == 0) {
            this.mAnimate = false;
            this.mAnimationDuration = 0;
        } else if (i2 > 0) {
            this.mAnimationDuration = i2;
            this.mAnimate = true;
        }
        return this;
    }

    public ArcLineOverlayProvider setHighlightDuration(int i2) {
        if (i2 >= 0) {
            this.mHighLightDuration = i2;
        }
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public ArcLineOverlayProvider visibility(boolean z) {
        super.visibility(z);
        return this;
    }

    public ArcLineOverlayProvider width(float f2) {
        if (f2 > 0.0f) {
            this.mWidth = f2;
        }
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public ArcLineOverlayProvider zIndex(int i2) {
        super.zIndex(i2);
        return this;
    }

    public ArcLineOverlayProvider zoomRange(int i2, int i3) {
        if (i2 <= i3) {
            super.minZoom(i2);
            super.maxZoom(i3);
        }
        return this;
    }
}
