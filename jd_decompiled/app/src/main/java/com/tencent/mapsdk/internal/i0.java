package com.tencent.mapsdk.internal;

import android.location.Location;
import com.tencent.tencentmap.mapsdk.maps.LocationSource;
import com.tencent.tencentmap.mapsdk.maps.model.MyLocationStyle;

/* loaded from: classes9.dex */
public interface i0 {
    void a();

    void b();

    void c();

    boolean d();

    Location getMyLocation();

    void setLocationSource(LocationSource locationSource);

    void setMyLocationStyle(MyLocationStyle myLocationStyle);
}
