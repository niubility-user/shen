package com.tencent.tencentmap.mapsdk.maps.model;

/* loaded from: classes9.dex */
public interface AoiLayer extends IOverlay {

    /* loaded from: classes9.dex */
    public interface OnAoiLayerLoadListener {
        void onAoiLayerLoaded(boolean z, AoiLayer aoiLayer);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IOverlay
    String getId();

    LatLng location();

    String name();

    boolean remove();
}
