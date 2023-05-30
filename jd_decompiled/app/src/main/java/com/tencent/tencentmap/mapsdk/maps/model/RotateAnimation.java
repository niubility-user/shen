package com.tencent.tencentmap.mapsdk.maps.model;

@Deprecated
/* loaded from: classes9.dex */
public class RotateAnimation extends BaseAnimation {
    public float mFromDegree;
    public float mPivoteX;
    public float mPivoteY;
    public float mPivoteZ;
    public float mToDegree;

    public RotateAnimation(float f2, float f3, float f4, float f5, float f6) {
        this.mFromDegree = f2;
        this.mToDegree = f3;
        this.mPivoteX = f4;
        this.mPivoteY = f5;
        this.mPivoteZ = f6;
    }
}
