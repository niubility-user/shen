package com.tencent.tencentmap.mapsdk.maps.model;

/* loaded from: classes9.dex */
public class AoiLayerOptions {
    private int mMinLevel = -1;
    private int mMaxLevel = -1;

    public int getMaxLevel() {
        return this.mMaxLevel;
    }

    public int getMinLevel() {
        return this.mMinLevel;
    }

    public AoiLayerOptions setDisplayLevel(int i2, int i3) {
        if (i2 >= 0 && i3 >= 0 && i2 <= i3) {
            if (i2 < 3) {
                i2 = 3;
            }
            if (i3 > 20) {
                i3 = 20;
            }
            this.mMinLevel = i2;
            this.mMaxLevel = i3;
        }
        return this;
    }
}
