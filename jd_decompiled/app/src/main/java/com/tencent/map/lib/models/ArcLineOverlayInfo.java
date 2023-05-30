package com.tencent.map.lib.models;

import android.graphics.Color;
import androidx.annotation.Keep;
import com.tencent.map.sdk.utilities.visualization.datamodels.FromToLatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Keep
/* loaded from: classes9.dex */
public class ArcLineOverlayInfo extends OverlayListenerInfo {
    private static final float DEFAULT_MAX_OPACITY = 1.0f;
    private static final int DEFAULT_MAX_ZOOM = 22;
    private static final float DEFAULT_MIN_OPACITY = 0.0f;
    private static final int DEFAULT_MIN_ZOOM = 3;
    public int mType = 0;
    public int mZIndex = 0;
    public int activeIndex = 0;
    public float mOpacity = 1.0f;
    public boolean mVisibility = true;
    public int mMinZoom = 3;
    public int mMaxZoom = 22;
    public int mDisplayLevel = 1;
    public float mWidth = 4.0f;
    public int[] mColors = {Color.argb(255, 0, 255, 170), Color.argb(255, 0, 255, 170), Color.argb(255, 0, 255, 170)};
    public float[] mColorPoints = {0.0f, 0.5f, 1.0f};
    public int mColorMapSize = 200;
    public boolean mDraw3D = false;
    public boolean mAnimate = false;
    public int mHighLightDuration = 5000;
    public int mAnimateDuration = 5000;
    public int mAnimateColor = -16776961;
    public FromToLatLng[] notes = new FromToLatLng[0];
    public int[] nodeIndexes = new int[0];

    public int getAnimateColor() {
        return this.mAnimateColor;
    }

    public boolean getIsAnimate() {
        return this.mAnimate;
    }

    public boolean isVisible() {
        return this.mVisibility;
    }

    public void setActiveDataIndex(int i2) {
        this.activeIndex = i2;
    }

    public void setAnimate(boolean z) {
        this.mAnimate = z;
    }

    public void setAnimateColor(int i2) {
        this.mAnimateColor = i2;
    }

    public void setAnimateDuration(int i2) {
        this.mAnimateDuration = i2;
    }

    public void setDataList(List<Collection<FromToLatLng>> list) {
        if (list == null || list.size() == 0) {
            this.notes = new FromToLatLng[0];
            this.nodeIndexes = new int[0];
            return;
        }
        this.nodeIndexes = new int[list.size()];
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Collection<FromToLatLng> collection = list.get(i2);
            this.nodeIndexes[i2] = collection.size();
            arrayList.addAll(collection);
        }
        this.notes = (FromToLatLng[]) arrayList.toArray(new FromToLatLng[0]);
    }

    public void setDisplayLevel(int i2) {
        if (i2 == 1 || i2 == 2) {
            this.mDisplayLevel = i2;
        }
    }

    public void setDraw3D(boolean z) {
        this.mDraw3D = z;
    }

    public void setHighLightDuration(int i2) {
        this.mHighLightDuration = i2;
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

    public void setNodes(FromToLatLng[] fromToLatLngArr) {
        this.notes = fromToLatLngArr;
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

    public void setType(int i2) {
        this.mType = i2;
    }

    public void setVisibility(boolean z) {
        this.mVisibility = z;
    }

    public void setWidth(float f2) {
        this.mWidth = f2;
    }

    public void setZoomLevelRange(int i2, int i3) {
        if (i2 <= i3) {
            setMinZoom(i2);
            setMaxZoom(i3);
        }
    }

    public void setzIndex(int i2) {
        this.mZIndex = i2;
    }
}
