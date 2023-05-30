package com.tencent.tencentmap.mapsdk.maps;

import android.location.Location;

/* loaded from: classes9.dex */
public interface LocationSource {

    /* loaded from: classes9.dex */
    public interface OnLocationChangedListener {
        void onLocationChanged(Location location);
    }

    void activate(OnLocationChangedListener onLocationChangedListener);

    void deactivate();
}
