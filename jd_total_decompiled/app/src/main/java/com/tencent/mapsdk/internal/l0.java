package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public interface l0 {
    void a(int i2, float f2);

    void a(int i2, int i3, int i4, int i5, int i6);

    void a(int i2, int[] iArr);

    void a(boolean z);

    void b(int i2);

    void b(int i2, int i3, int i4, int i5, int i6);

    void b(boolean z);

    boolean b();

    float c(int i2);

    void d(int i2);

    void e(int i2);

    void f(int i2);

    boolean isCompassEnabled();

    boolean isIndoorLevelPickerEnabled();

    boolean isMyLocationButtonEnabled();

    boolean isRotateGesturesEnabled();

    boolean isScrollGesturesEnabled();

    boolean isTiltGesturesEnabled();

    boolean isZoomControlsEnabled();

    boolean isZoomGesturesEnabled();

    void setAllGesturesEnabled(boolean z);

    void setCompassEnabled(boolean z);

    void setCompassExtraPadding(int i2);

    void setCompassExtraPadding(int i2, int i3);

    void setGestureRotateByMapCenter(boolean z);

    void setGestureScaleByMapCenter(boolean z);

    void setIndoorLevelPickerEnabled(boolean z);

    void setLogoScale(float f2);

    void setLogoSize(int i2);

    void setMyLocationButtonEnabled(boolean z);

    void setRotateGesturesEnabled(boolean z);

    void setScaleViewEnabled(boolean z);

    void setScaleViewFadeEnable(boolean z);

    void setScrollGesturesEnabled(boolean z);

    void setTiltGesturesEnabled(boolean z);

    void setZoomControlsEnabled(boolean z);

    void setZoomGesturesEnabled(boolean z);

    void setZoomPosition(int i2);
}
