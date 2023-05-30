package com.tencent.tencentmap.mapsdk.maps.model;

/* loaded from: classes9.dex */
public interface IAnimatorModel {

    /* loaded from: classes9.dex */
    public interface IAnimatorEndListener {
        void onAnimatorEnd();
    }

    float getRotation();

    void setPosition(LatLng latLng);

    void setRotation(float f2);
}
