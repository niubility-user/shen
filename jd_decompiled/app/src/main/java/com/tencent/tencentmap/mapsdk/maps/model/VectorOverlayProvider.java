package com.tencent.tencentmap.mapsdk.maps.model;

import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;

/* loaded from: classes9.dex */
public interface VectorOverlayProvider {
    VectorOverlayProvider displayLevel(int i2);

    void enableClick(boolean z);

    int getDisplayLevel();

    int getMaxZoom();

    int getMinZoom();

    float getOpacity();

    int getZIndex();

    boolean isClickEnabled();

    boolean isVisibility();

    VectorOverlayProvider maxZoom(int i2);

    VectorOverlayProvider minZoom(int i2);

    VectorOverlayProvider opacity(float f2);

    VectorOverlayProvider setVectorOverlayLoadedListener(VectorOverlay.OnVectorOverlayLoadListener onVectorOverlayLoadListener);

    VectorOverlayProvider visibility(boolean z);

    VectorOverlayProvider zIndex(int i2);
}
