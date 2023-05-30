package com.tencent.map.lib.models;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import com.tencent.map.sdk.utilities.visualization.datamodels.ScatterLatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Keep
/* loaded from: classes9.dex */
public class ScatterPlotInfo extends OverlayListenerInfo {
    private static final int DEFAULT_MAX_ZOOM = 22;
    private static final int DEFAULT_MIN_ZOOM = 3;
    public double[] circleStartPoints;
    public int[] colors;
    public float[] colorsPoints;
    public int mBitmapHeight;
    public int mBitmapWidth;
    public float maxIntensity;
    public int maxZoom;
    public float minIntensity;
    public int minZoom;
    public float opacity;
    public int radius;
    public boolean visible;
    public int activeIndex = 0;
    public int mType = 0;
    public int level = 1;
    public int zIndex = 0;
    public boolean draw3D = false;
    public int colorMapSize = 200;
    public boolean mAnimate = false;
    public boolean intensityFlag = false;
    public int mMinRadius = 0;
    public int mMaxRadius = 30;
    public int mStrokeColor = -1;
    public int mStrokeWidth = 4;
    public ScatterLatLng[] notes = new ScatterLatLng[0];
    public int[] nodeIndexes = new int[0];
    public Bitmap[] mBitmaps = new Bitmap[0];

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

    public void setColors(int[] iArr) {
        this.colors = iArr;
    }

    public void setColorsPoints(double[] dArr) {
        this.circleStartPoints = dArr;
    }

    public void setColorsPoints(float[] fArr) {
        this.colorsPoints = fArr;
    }

    public void setDataList(List<Collection<ScatterLatLng>> list) {
        if (list == null) {
            this.notes = new ScatterLatLng[0];
            this.nodeIndexes = new int[0];
            return;
        }
        this.nodeIndexes = new int[list.size()];
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Collection<ScatterLatLng> collection = list.get(i2);
            this.nodeIndexes[i2] = collection.size();
            arrayList.addAll(collection);
        }
        this.notes = (ScatterLatLng[]) arrayList.toArray(new ScatterLatLng[0]);
    }

    public void setDraw3D(boolean z) {
        this.draw3D = z;
    }

    public void setLevel(int i2) {
        this.level = i2;
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
        this.opacity = f2;
    }

    public void setRadius(int i2) {
        this.radius = i2;
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
