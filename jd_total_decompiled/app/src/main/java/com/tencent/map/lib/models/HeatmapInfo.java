package com.tencent.map.lib.models;

import androidx.annotation.Keep;
import com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Keep
/* loaded from: classes9.dex */
public class HeatmapInfo extends OverlayListenerInfo {
    private static final float DEFAULT_MAX_OPACITY = 1.0f;
    private static final int DEFAULT_MAX_ZOOM = 22;
    private static final float DEFAULT_MIN_OPACITY = 0.0f;
    private static final int DEFAULT_MIN_ZOOM = 3;
    public int colorMapSize;
    public float[] colorPoints;
    public int[] colors;
    public int maxZoom;
    public int minZoom;
    public float opacity;
    public int radius;
    public boolean visible;
    public int activeIndex = 0;
    public int level = 1;
    public int zIndex = 0;
    public int sample = 4;
    public float maxHeight = 1000.0f;
    public boolean draw3D = false;
    public boolean intensityFlag = false;
    public float maxIntensity = 0.0f;
    public float minIntensity = 0.0f;
    public boolean mAnimate = false;
    public int mAnimateDuration = 5000;
    public WeightedLatLng[] notes = new WeightedLatLng[0];
    public int[] nodeIndexes = new int[0];

    public boolean isAnimate() {
        return this.mAnimate;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setActiveIndex(int i2) {
        this.activeIndex = i2;
    }

    public void setAnimate(boolean z) {
        this.mAnimate = z;
    }

    public void setAnimateTime(int i2) {
        this.mAnimateDuration = i2;
    }

    public void setColorPoints(float[] fArr) {
        this.colorPoints = fArr;
    }

    public void setColors(int[] iArr) {
        this.colors = iArr;
    }

    public void setDataList(List<Collection<WeightedLatLng>> list) {
        if (list == null) {
            this.notes = new WeightedLatLng[0];
            this.nodeIndexes = new int[0];
            return;
        }
        this.nodeIndexes = new int[list.size()];
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Collection<WeightedLatLng> collection = list.get(i2);
            this.nodeIndexes[i2] = collection.size();
            arrayList.addAll(collection);
        }
        this.notes = (WeightedLatLng[]) arrayList.toArray(new WeightedLatLng[0]);
    }

    public void setDraw3D(boolean z) {
        this.draw3D = z;
    }

    public void setLevel(int i2) {
        if (i2 == 1 || i2 == 2) {
            this.level = i2;
        }
    }

    public void setMaxHeight(float f2) {
        this.maxHeight = f2;
    }

    public void setMaxZoom(int i2) {
        if (i2 <= 22) {
            this.maxZoom = i2;
        } else {
            this.maxZoom = 22;
        }
    }

    public void setMinZoom(int i2) {
        if (i2 >= 3) {
            this.minZoom = i2;
        } else {
            this.minZoom = 3;
        }
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

    public void setRadius(int i2) {
        this.radius = i2;
    }

    public void setSample(int i2) {
        this.sample = i2;
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
