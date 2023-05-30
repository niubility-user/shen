package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable;
import com.tencent.tencentmap.mapsdk.maps.model.AnimationListener;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import java.util.List;

/* loaded from: classes9.dex */
public interface o0 extends p0, Marker {
    int a();

    void a(Bitmap bitmap);

    void a(TencentMap.OnMarkerClickListener onMarkerClickListener);

    void a(BitmapDescriptor bitmapDescriptor, BitmapDescriptor bitmapDescriptor2);

    void a(LatLng latLng);

    void a(boolean z);

    List<Boundable<s4>> b();

    @Deprecated
    void c(boolean z);

    Rect g();

    boolean h();

    q4 j();

    TencentMap.OnMarkerClickListener m();

    Point s();

    void setAnimationListener(AnimationListener animationListener);

    TencentMap.InfoWindowAdapter t();

    List<LatLng> v();
}
