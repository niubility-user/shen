package com.tencent.tencentmap.mapsdk.maps;

import com.tencent.tencentmap.mapsdk.maps.model.CamerParameter;

/* loaded from: classes9.dex */
public final class CameraUpdate {
    private final CamerParameter camerPara;

    public CameraUpdate(CamerParameter camerParameter) {
        this.camerPara = camerParameter;
    }

    public CamerParameter getParams() {
        return this.camerPara;
    }
}
