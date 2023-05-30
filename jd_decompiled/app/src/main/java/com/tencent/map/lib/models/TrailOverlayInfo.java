package com.tencent.map.lib.models;

import androidx.annotation.Keep;
import com.tencent.map.sdk.utilities.visualization.datamodels.TrailLatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Keep
/* loaded from: classes9.dex */
public class TrailOverlayInfo extends OverlayListenerInfo {
    private static final int DEFAULT_MAX_ZOOM = 22;
    private static final int DEFAULT_MIN_ZOOM = 3;
    public float[] colorPoints;
    public int[] colors;
    public int mType = 0;
    public int mZIndex = 0;
    public int activeIndex = 0;
    public float mOpacity = 1.0f;
    public boolean mVisibility = true;
    public int mMinZoom = 3;
    public int mMaxZoom = 22;
    public int mDisplayLevel = 1;
    public float mWidth = 4.0f;
    public boolean mAnimate = true;
    public int mHighLightDuration = 5000;
    public int mAnimateStartTime = 0;
    public int mAnimateEndTime = 2000;
    public int mAnimateColor = -16776961;
    public int colorMapSize = 200;
    public float mPlayRatio = 1.0f;
    public int mPulseInterval = 200;
    public TrailLatLng[] notes = new TrailLatLng[0];
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

    public void setAnimateEndTime(int i2) {
        this.mAnimateEndTime = i2;
    }

    public void setAnimateStartTime(int i2) {
        this.mAnimateStartTime = i2;
    }

    public void setDataList(List<Collection<TrailLatLng>> list) {
        if (list == null || list.size() == 0) {
            this.notes = new TrailLatLng[0];
            this.nodeIndexes = new int[0];
            return;
        }
        this.nodeIndexes = new int[list.size()];
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Collection<TrailLatLng> collection = list.get(i2);
            this.nodeIndexes[i2] = collection.size();
            arrayList.addAll(collection);
        }
        this.notes = (TrailLatLng[]) arrayList.toArray(new TrailLatLng[0]);
    }

    public void setDisplayLevel(int i2) {
        this.mDisplayLevel = i2;
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

    public void setNodes(TrailLatLng[] trailLatLngArr) {
        this.notes = trailLatLngArr;
    }

    public void setOpacity(float f2) {
        this.mOpacity = f2;
    }

    public void setPlayRatio(float f2) {
        this.mPlayRatio = f2;
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
