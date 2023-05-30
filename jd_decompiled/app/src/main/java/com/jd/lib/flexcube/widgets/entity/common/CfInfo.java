package com.jd.lib.flexcube.widgets.entity.common;

/* loaded from: classes15.dex */
public class CfInfo {
    public boolean radiusChange;
    public float radiusLB;
    public float radiusLT;
    public float radiusRB;
    public float radiusRT;

    public CfInfo() {
    }

    public boolean isEmpty() {
        return 0.0f == this.radiusLT && 0.0f == this.radiusRT && 0.0f == this.radiusLB && 0.0f == this.radiusRB;
    }

    public boolean setHeightHalf(int i2) {
        boolean z;
        if (this.radiusLT == -1.0f) {
            this.radiusLT = i2;
            this.radiusChange = true;
            z = true;
        } else {
            z = false;
        }
        if (this.radiusRT == -1.0f) {
            this.radiusRT = i2;
            this.radiusChange = true;
            z = true;
        }
        if (this.radiusLB == -1.0f) {
            this.radiusLB = i2;
            this.radiusChange = true;
            z = true;
        }
        if (this.radiusRB == -1.0f) {
            this.radiusRB = i2;
            this.radiusChange = true;
            return true;
        }
        return z;
    }

    public CfInfo(float f2, float f3, float f4, float f5) {
        this.radiusLT = f2;
        this.radiusRT = f3;
        this.radiusLB = f4;
        this.radiusRB = f5;
    }
}
