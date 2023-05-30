package com.tencent.mapsdk.internal;

import android.view.View;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

/* loaded from: classes9.dex */
public interface q4 extends t4 {

    /* renamed from: c  reason: collision with root package name */
    public static final String f17016c = "tencent_map_infowindow_view";
    public static final String d = "tencent_map_infowindow_content_title";

    /* renamed from: e  reason: collision with root package name */
    public static final String f17017e = "tencent_map_infowindow_content_snippet";

    void a(MarkerOptions markerOptions);

    void e(boolean z);

    void k();

    View o();

    boolean r();

    void setAnchor(float f2, float f3);

    void setFixingPoint(int i2, int i3);

    void setFixingPointEnable(boolean z);

    void setPosition(LatLng latLng);

    void u();

    void w();
}
