package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.MarkerInfo;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class qd extends MarkerInfo implements uc {
    public qd(double d, double d2, String str) {
        super(d, d2, str);
    }

    public qd(LatLng latLng, String str) {
        super(latLng.getLatitude(), latLng.getLongitude(), str);
    }
}
