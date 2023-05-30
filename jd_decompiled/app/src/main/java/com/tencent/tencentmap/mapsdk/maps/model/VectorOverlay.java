package com.tencent.tencentmap.mapsdk.maps.model;

import com.tencent.map.lib.models.CommandFunctionModelClass;
import com.tencent.map.lib.models.ReturnInfoModelClass;

/* loaded from: classes9.dex */
public interface VectorOverlay extends IOverlay {

    /* loaded from: classes9.dex */
    public interface OnVectorOverlayClickListener {
        void onClicked(LatLng latLng, String str, String str2);
    }

    /* loaded from: classes9.dex */
    public interface OnVectorOverlayLoadListener {
        void onVectorOverlayLoaded(boolean z);
    }

    void enableClick(boolean z);

    ReturnInfoModelClass.ReturnStatus executeCommandFunction(CommandFunctionModelClass.BaseCommandFunction baseCommandFunction);

    String getType();

    boolean isClickEnabled();

    void remove();

    void setLevel(int i2);

    void setOpacity(float f2);

    void setVisibility(boolean z);

    void setZIndex(int i2);
}
