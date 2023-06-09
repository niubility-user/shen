package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* loaded from: classes12.dex */
public final class FinderPattern extends ResultPoint {
    private final int count;
    private final float estimatedModuleSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FinderPattern(float f2, float f3, float f4) {
        this(f2, f3, f4, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean aboutEquals(float f2, float f3, float f4) {
        if (Math.abs(f3 - getY()) > f2 || Math.abs(f4 - getX()) > f2) {
            return false;
        }
        float abs = Math.abs(f2 - this.estimatedModuleSize);
        return abs <= 1.0f || abs <= this.estimatedModuleSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FinderPattern combineEstimate(float f2, float f3, float f4) {
        int i2 = this.count;
        int i3 = i2 + 1;
        float x = (i2 * getX()) + f3;
        float f5 = i3;
        return new FinderPattern(x / f5, ((this.count * getY()) + f2) / f5, ((this.count * this.estimatedModuleSize) + f4) / f5, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCount() {
        return this.count;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }

    private FinderPattern(float f2, float f3, float f4, int i2) {
        super(f2, f3);
        this.estimatedModuleSize = f4;
        this.count = i2;
    }
}
